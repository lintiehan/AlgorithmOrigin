package Base_AIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Handler;

import org.omg.PortableInterceptor.ServerIdHelper;

public class Server {
	private static int DEFAULT_PORT=12345;
	private static AsyncServerHandle serverHandle;
	public volatile static long clientCount=0;
	public static void start()
	{
		start(DEFAULT_PORT);
	}
	private static void start(int port) {
		// TODO Auto-generated method stub
		if(serverHandle!=null)
		{
			return;
		}
		serverHandle=new AsyncServerHandle(port);
		new Thread(serverHandle,"Server").start();
	}
}
class AsyncServerHandle implements Runnable{
	public CountDownLatch latch;
	public AsynchronousServerSocketChannel channel;
	
	public AsyncServerHandle(int port) {
		// TODO Auto-generated constructor stub
		try {
			//创建服务端通道
			channel=AsynchronousServerSocketChannel.open();
			//绑定端口
			channel.bind(new InetSocketAddress(port));
			System.out.println("服务器已启动： 端口号为 "+port);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void run() {
		/*
		 *  CountDownLatch初始化
		 *  在完成一组正在执行的操作之前，允许当前的线程一直阻塞
		 *  此处，让线程再次阻塞，防止服务端执行完成后退出
		 *  也可以使用while(true)+sleep
		 *  开发环境不需要担心这个问题，因为服务端是不回退出的	
		 */

		latch=new CountDownLatch(1);
		//用于接受客户端的连接
		channel.accept(this,new AcceptHandler());
		try {
			latch.await();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
//作为handler接收客户端连接
class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncServerHandle>{
	
	@Override
	public void completed(AsynchronousSocketChannel channel, AsyncServerHandle serverHandle) {
		// TODO Auto-generated method stub
		Server.clientCount++;
		System.out.println("连接的客户端数 "+Server.clientCount);
		serverHandle.channel.accept(serverHandle,this);
		//创建新的Buffer
		ByteBuffer buffer=ByteBuffer.allocate(1024);
		//异步读 第三个参数为接受消息回调的业务Handler
		channel.read(buffer,buffer,new ReadHandler(channel));
	}

	@Override
	public void failed(Throwable exc, AsyncServerHandle serverHandle) {
		// TODO Auto-generated method stub
		exc.printStackTrace();
		serverHandle.latch.countDown();
	}
	
}
class ReadHandler implements CompletionHandler<Integer, ByteBuffer>{
	//用于读取半包消息和发送消息
	private AsynchronousSocketChannel channel;
	
	public ReadHandler(AsynchronousSocketChannel channel) {
		// TODO Auto-generated constructor stub
		this.channel=channel;
	}
	//读取到消息后的处理
	@Override
	public void completed(Integer result, ByteBuffer attachment) {
		//flip操作
		attachment.flip();
		byte[]message=new byte[attachment.remaining()];
		attachment.get(message);
		try {
			String expression=new String(message,"UTF-8");
			System.out.println("服务器收到的消息 ："+expression);
			String calcResult=null;
			try {
				calcResult=Calculator.cal(expression).toString();
			} catch (Exception e) {
				// TODO: handle exception
				calcResult="计算错误"+e.getMessage();
			}
			doWrite(calcResult);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void doWrite(String result) {
		// TODO Auto-generated method stub
		byte[]bytes=result.getBytes();
		ByteBuffer writeBuffer=ByteBuffer.allocate(bytes.length);
		writeBuffer.put(bytes);
		writeBuffer.flip();
		//异步写数据 参数与前面的read一样
		channel.write(writeBuffer,writeBuffer,new CompletionHandler<Integer, ByteBuffer>(){

			@Override
			public void completed(Integer result, ByteBuffer buffer) {
				// TODO Auto-generated method stub
				if(buffer.hasRemaining())
				{
					channel.write(buffer,buffer,this);
				}else {
					//创建新的Buffer
					ByteBuffer readBuffer=ByteBuffer.allocate(1024);
					//异步读 第三个参数为接受消息回调的业务Handler
					channel.read(readBuffer,readBuffer,new ReadHandler(channel));
				}
			}

			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				// TODO Auto-generated method stub
				try {
					channel.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}});
		
	}
	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		// TODO Auto-generated method stub
		try {
			this.channel.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}