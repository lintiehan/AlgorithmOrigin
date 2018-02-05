package Base_Thread;

public class wait_notify_Test {
	public static Object object = new Object();

	static class ThreadOne extends Thread {
		@Override
		public void run() {
			synchronized (object) {
				System.out.println("线程 "+Thread.currentThread().getName()
						+" 获得锁对象");
			try {
				System.out.println("线程 "+Thread.currentThread().getName()
						+" 阻塞并释放锁对象");
				object.wait();
				} catch (Exception e) {
					// TODO: handle exception
				}
			System.out.println("线程 "+Thread.currentThread().getName()
					+" 执行完成");
			
				
			}
		}
	}
	static class ThreadTwo extends Thread {
		@Override
		public void run() {
			synchronized (object) {
				System.out.println("线程 "+Thread.currentThread().getName()
						+" 获得锁对象");
			try {
				System.out.println("线程 "+Thread.currentThread().getName()
						+" 唤醒了wait状态的线程");
				object.notify();
				} catch (Exception e) {
					// TODO: handle exception
				}
			System.out.println("线程 "+Thread.currentThread().getName()
					+" 执行完成");
			
				
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		ThreadOne threadOne=new ThreadOne();
		ThreadTwo threadTwo=new ThreadTwo();
		threadOne.start();
		
		Thread.sleep(2000);
		threadTwo.start();
	}
}
