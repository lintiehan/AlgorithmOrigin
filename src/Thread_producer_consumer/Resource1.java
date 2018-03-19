package Thread_producer_consumer;
/*
 * 
 wait() / nofity() 方法是基类Object的两个方法，也就意味着所有Java类都会拥有这两个方法，
 这样，我们就可以为任何对象实现同步机制。

wait()方法：当缓冲区已满/空时，生产者/消费者线程停止自己的执行，放弃锁，使自己处于等等状态，让其他线程执行。

notify()方法：当生产者/消费者向缓冲区放入/取出一个产品时，向其他等待的线程发出可执行的通知，
同时放弃锁，使自己处于等待状态。
 */

public class Resource1 {
	/* 资源序号 */
	private int number = 0;
	/* 资源标记 */
	private boolean flag = false;

	public synchronized void produce() {
		// 先判断标记是否已经生产了，如果已经生产，等待消费
		while (flag) {
			try {
				wait();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		number++;
		System.out.println(Thread.currentThread().getName() + "生产者------------" + number);
		flag = true;
		notifyAll();
	}

	public synchronized void consume() {
		// 先判断标记是否已经生产了，如果已经生产，等待消费
		while (!flag) {
			try {
				wait();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + "消费者****" + number);
		flag = false;
		notifyAll();
	}

	public static void main(String[] args) {
		Resource1 resource = new Resource1();
		new Producer1(resource).start();// 生产者线程
		new Consumer1(resource).start();// 消费者线程
	}
}

class Producer1 extends Thread {

	// 所在放置的仓库
	private Resource1 resource;

	// 构造函数，设置仓库
	public Producer1(Resource1 resource) {
		this.resource = resource;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resource.produce();
		}
	}
}

class Consumer1 extends Thread {

	// 所在放置的仓库
	private Resource1 resource;

	// 构造函数，设置仓库
	public Consumer1(Resource1 resource) {
		this.resource = resource;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resource.consume();
		}

	}
}