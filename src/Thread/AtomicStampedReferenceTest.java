package Thread;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceTest {
	private static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(100, 1);
	private static AtomicInteger atomicInteger = new AtomicInteger(100);

	public static void main(String[] args) throws InterruptedException {
		// AtomicInteger
		Thread at1 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				atomicInteger.compareAndSet(100, 110);
				atomicInteger.compareAndSet(110, 100);
			}
		});

		Thread at2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (Exception e) {
					// TODO: handle exception
				}
				System.out.println("AtomicInteger:" + atomicInteger.compareAndSet(100, 120));
			}
		});
		at1.start();
		at2.start();
		at1.join();
		at2.join();

		// AtomicStampedReference
		Thread tsr1 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					// 让tsr2先获取stamp，到期预期时间戳不一致
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 预期引用100 更新后引用110 预期标识getStamp() 更新后标识getStamp()+1
				atomicStampedReference.compareAndSet(100, 110, atomicStampedReference.getStamp(),
						atomicStampedReference.getStamp() + 1);
				atomicStampedReference.compareAndSet(110, 100, atomicStampedReference.getStamp(),
						atomicStampedReference.getStamp() + 1);
			}
		});

		Thread tsr2 = new Thread(new Runnable() {

			@Override
			public void run() {
				int stamp=atomicStampedReference.getStamp();
				try {
					TimeUnit.SECONDS.sleep(2); //线程tsr1执行完
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				//compareAndSet(预期引用、更新后的引用、预期标志、更新后的标志)
				System.out.println("AtomicStampedReference："+atomicStampedReference.compareAndSet(100,120,stamp,stamp + 1));
			}
		});

		tsr1.start();
		tsr2.start();
	}
}
