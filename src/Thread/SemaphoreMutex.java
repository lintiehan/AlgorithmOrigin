package Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreMutex {
	// 初始化为1，互斥信号量
	private final static Semaphore mutex = new Semaphore(1);

	public static void main(String[] args) {
		ExecutorService pools = Executors.newCachedThreadPool();

		for (int i = 0; i < 10; i++) {
			final int index = i;
			Runnable runnable = new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						mutex.acquire();
						System.out.println(
								String.format("[Thread-%s]任务id --- %s", Thread.currentThread().getId(), index));
						TimeUnit.SECONDS.sleep(1);
					} catch (Exception e) {
						// TODO: handle exception
					} finally {
						//使用完成释放锁
						mutex.release();
						System.out.println("-----------release");
					}
				}
			};
			pools.execute(runnable);
		}
		pools.shutdown();
	}
}
