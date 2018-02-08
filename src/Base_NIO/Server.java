package Base_NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/*
 * 可以看到，创建NIO服务端的主要步骤如下：

1.打开ServerSocketChannel，监听客户端连接
2.绑定监听端口，设置连接为非阻塞模式
3.创建Reactor线程，创建多路复用器并启动线程
4.将ServerSocketChannel注册到Reactor线程中的Selector上，监听ACCEPT事件
5.Selector轮询准备就绪的key
6.Selector监听到新的客户端接入，处理新的接入请求，完成TCP三次握手，简历物理链路
7.设置客户端链路为非阻塞模式
8.将新接入的客户端连接注册到Reactor线程的Selector上，监听读操作，读取客户端发送的网络消息
9.异步读取客户端消息到缓冲区
10.对Buffer编解码，处理半包消息，将解码成功的消息封装成Task
11.将应答消息编码为Buffer，调用SocketChannel的write将消息异步发送给客户端

因为应答消息的发送，SocketChannel也是异步非阻塞的，所以不能保证一次能吧需要发送的数据发送完，
此时就会出现写半包的问题。我们需要注册写操作，不断轮询Selector将没有发送完的消息发送完毕，
然后通过Buffer的hasRemain()方法判断消息是否发送完成。
 */
public class Server {
	private static int DEFAULT_PORT = 12345;
	private static ServerHandle serverHandle;

	public static void start() {
		start(DEFAULT_PORT);
	}

	public static void main(String[] args) {
		Server.start();
	}

	private static void start(int port) {
		// TODO Auto-generated method stub
		if (serverHandle != null) {
			serverHandle.stop();
		}
		serverHandle = new ServerHandle(port);
		new Thread(serverHandle, "Server").start();
	}
}

class ServerHandle implements Runnable {
	private Selector selector;
	private ServerSocketChannel serverSocketChannel;
	private volatile boolean started;

	/*
	 * 指定监听的接口
	 */
	public ServerHandle(int port) {
		// TODO Auto-generated constructor stub
		try {
			// 创建选择器
			selector = Selector.open();
			// 打开监听通道
			serverSocketChannel = ServerSocketChannel.open();

			// 如果为true，则此通道将被置于阻塞模式，若为false，则此通道将被置于非阻塞模式
			serverSocketChannel.configureBlocking(false);

			// 绑定端口 backlog设为1024
			serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);

			// 监听客户端连接请求
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			// 标记客户端连接请求
			started = true;
			System.out.println("服务器已启动，端口号:" + port);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (started) {
			try {
				// 无论是否有读写事件发生，selector每隔一秒被唤醒一次
				selector.select(1000);
				// 阻塞，只有当至少一个注册的事件发生的时候才会继续
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> it = keys.iterator();
				SelectionKey key = null;
				while (it.hasNext()) {
					key = it.next();
					it.remove();
					try {
						handleInput(key);
					} catch (Exception e) {
						// TODO: handle exception
						if (key != null) {
							key.cancel();
							if (key.channel() != null) {
								key.channel().close();
							}
						}
					}
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if (selector != null) {
			try {
				selector.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	private void handleInput(SelectionKey key) throws IOException {
		// TODO Auto-generated method stub

		if (key.isValid()) {
			// 处理新接入的请求消息
			if (key.isAcceptable()) {
				ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
				// 通过ServerSocketChannel的accept创建SocketChannel实例
				// 完成该操作意味着完成TCP三次握手操作，TCP物理链路正式建立
				SocketChannel sc = ssc.accept();
				// 设置为非阻塞
				sc.configureBlocking(false);
				// 注册为读
				sc.register(selector, SelectionKey.OP_READ);
			}
			// 读消息
			if (key.isReadable()) {
				SocketChannel sc = (SocketChannel) key.channel();
				// 创建ByteBuffer，并开辟一个1M的缓冲区
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				// 读取请求码流，返回读取的字节数
				int readBytes = sc.read(buffer);
				// 读取到字节，对字节进行编解码
				if (readBytes > 0) {
					// 将缓冲区当前的limit设置为position=0，用于后序对缓冲区的读取操作
					buffer.flip();
					// 根据缓冲区可读字节数创建字节数组
					byte[] bytes = new byte[buffer.remaining()];
					// 将缓冲区可读字节数组复制到新建的数组中
					buffer.get(bytes);
					String expression = new String(bytes, "UTF-8");
					System.out.println("服务器收到的消息是： " + expression);
					// 处理数据
					String result = null;
					try {
						result = Calculator.cal(expression).toString();
					} catch (Exception e) {
						// TODO: handle exception
						result = "计算错误" + e.getMessage();
					}
					doWrite(sc, result);
				}
				// 链路已经关闭，释放资源
				else if (readBytes < 0) {
					key.cancel();
					sc.close();
				}
			}

		}

	}

	// 异步发送应答消息
	private void doWrite(SocketChannel channel, String response) throws IOException {
		// TODO Auto-generated method stub
		// 将消息编码为字节数组
		byte[] bytes = response.getBytes();
		// 根据数组容量创建ByteBuffer
		ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
		// 将字节数组复制到缓冲区中
		writeBuffer.put(bytes);
		// flip操作
		writeBuffer.flip();
		// 发送缓冲区的字节数组
		channel.write(writeBuffer);
	}

	public void stop() {
		// TODO Auto-generated method stub
		started = false;
	}

}