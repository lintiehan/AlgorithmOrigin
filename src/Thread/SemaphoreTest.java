package Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	public static void main(String[] args) {
		ExecutorService exec=Executors.newCachedThreadPool();
		//设置信号量同时执行的线程数为5
		final Semaphore semaphore=new Semaphore(5);
		//模拟20个客户端访问
		for(int index=0;index<20;index++)
		{
			final int NO=index;
			Runnable runnable=new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						//使用acquire获取锁
						semaphore.acquire();
						System.out.println("Accessing :" +NO);
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}finally {
						semaphore.release();
					}
				}
			};
			exec.execute(runnable);
		}
		exec.shutdown();
	}
}
