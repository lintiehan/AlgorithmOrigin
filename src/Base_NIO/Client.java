package Base_NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Client {
	private static String DEFAULT_HOST = "127.0.0.1";
	private static int DEFAULT_PORT = 12345;
	private static ClientHandle clientHandle;

	public static void start() {
		start(DEFAULT_HOST, DEFAULT_PORT);
	}

	private static synchronized void start(String ip, int port) {
		// TODO Auto-generated method stub
		if (clientHandle != null) {
			clientHandle.stop();
		}
		clientHandle = new ClientHandle(ip, port);
		new Thread(clientHandle, "Server").start();
	}

	// 向服務器发送消息
	public static boolean sendMsg(String msg) throws Exception {
		if (msg.equals("q"))
			return false;
		clientHandle.sendMsg(msg);
		return true;
	}

	public static void main(String[] args) {
		start();
	}
}

class ClientHandle implements Runnable {
	private String host;
	private int port;
	private Selector selector;
	private SocketChannel socketChannel;
	private volatile boolean started;

	public ClientHandle(String ip, int port) {
		// TODO Auto-generated constructor stub
		this.host = ip;
		this.port = port;
		try {
			// 创建选择器
			selector = Selector.open();
			// 打开监听通道
			socketChannel = SocketChannel.open();
			// 如果为true，则此通道将被置于阻塞模式，如果为false，则此通道将被置于非阻塞模式
			socketChannel.configureBlocking(false);// 开启非阻塞模式
			started = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			doConnect();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		// 轮询selector
		while (started) {
			try {
				// 无论是否有读写事件发生，selector每个一秒被唤醒一次
				selector.select(1000);
				// 阻塞，只有当至少一个注册事件发生的时候才会继续
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
				e.printStackTrace();
				System.exit(1);
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
		if (key.isValid()) {
			SocketChannel sc = (SocketChannel) key.channel();
			if (key.isConnectable()) {
				if (sc.finishConnect())
					;
				else
					System.exit(1);
			}

			// 读消息
			if (key.isReadable()) {
				// 创建Bytebuffer，并开辟一个1M的缓冲区
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				//读取请求码流，对字节进行编解码
				int readBytes = sc.read(buffer);
				if(readBytes>0)
				{
					//将缓冲区当前的limit设置为position=0，用于后续对缓冲区的读取操作
					buffer.flip();
					//根据缓冲区可读字节数创建字节数组
					byte[] bytes=new byte[buffer.remaining()]; 
					//将缓冲区可读字节数组复制到新建的数组中
					buffer.get(bytes);
					String result=new String(bytes, "UTF-8");
					System.out.println("客户端收到消息: "+result);
				}
				else if(readBytes<0)
				{
					key.cancel();
					sc.close();
				}
			}
		}
	}

	private void doWrite(SocketChannel channel, String request) throws IOException {
		// 将消息编码为字节数组
		byte[] bytes = request.getBytes();
		// 根据数组容量创建ByteBuffer
		ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
		// 将字节数组复制到缓冲区中
		writeBuffer.put(bytes);
		// flip操作
		writeBuffer.flip();
		channel.write(writeBuffer);
	}

	private void doConnect() throws Exception {
		// TODO Auto-generated method stub
		if (socketChannel.connect(new InetSocketAddress(host, port)))
			;
		else
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
	}

	public void sendMsg(String msg) throws IOException {
		// TODO Auto-generated method stub
		socketChannel.register(selector, SelectionKey.OP_READ);
		doWrite(socketChannel, msg);
	}

	public void stop() {
		// TODO Auto-generated method stub
		started = false;
	}
}