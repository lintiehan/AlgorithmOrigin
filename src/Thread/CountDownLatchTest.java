package Thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CountDownLatchTest {
	private static CountDownLatch sCountDownLatch=null;
	private static final int count=3;

	public static void main(String[] args) throws InterruptedException {
		sCountDownLatch =new CountDownLatch(count);
		//线程池
		ExecutorService fixpools=Executors.newFixedThreadPool(count);
		//执行线程
		fixpools.execute(new ConsumeRunnable("a"));
		fixpools.execute(new ConsumeRunnable("b"));
		fixpools.execute(new ConsumeRunnable("c"));
		try {
			sCountDownLatch.await();
			System.out.println("3个主线成已经执行完毕");
			System.out.println("继续执行主线程");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private static class ConsumeRunnable implements Runnable{
		private String name;
		public ConsumeRunnable(String name)
		{
			this.name=name;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("子线程 "+name+"正在执行");
			try {
				Thread.sleep(3000);//模拟耗时操作
				System.out.println("子线程  "+name+"执行完毕");
				sCountDownLatch.countDown();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
