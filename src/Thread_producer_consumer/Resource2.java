package Thread_producer_consumer;


 
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
 * Lock && Condition
 */

public class Resource2 {
	// 仓库最大存储量
	private final int MAX_SIZE = 100;
	// 仓库储存的载体
	private LinkedList<Object> list = new LinkedList<Object>();
	// 锁
	private final Lock lock = new ReentrantLock();

	// 仓库满的条件变量
	private final Condition full = lock.newCondition();

	// 仓库空的条件变量
	private final Condition empty = lock.newCondition();

	/*
	 * 生产num个产品
	 */
	public void produce(int num) {
		// 获得锁
		lock.lock();

		while (list.size() + num > MAX_SIZE) {
			System.out.println("【要生产的产品数量】:" + num + " \t 【库存量】:" + list.size() + "\t 暂时不能执行生产任务!");
			try {
				// 条件不足，生产阻塞
				full.await();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		for (int i = 1; i <= num; i++) {
			list.add(new Object());
		}
		System.out.println("【已经生产产品数】:" + num + "\t 【现仓储量为】:" + list.size());
		// 唤醒其他所有线程
		full.signalAll();
		empty.signalAll();

		// 释放锁
		lock.unlock();
	}

	/*
	 * 消费num个产品
	 */
	public void consume(int num) {

		lock.lock();

		while (list.size() < num) {
			System.out.println("【要消费的产品数量】:" + num + " \t【库存量】:" + list.size() + " \t 暂时不能执行生产任务!");
			try {
				// 条件不足，消费阻塞
				empty.await();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		// 消费条件满足情况下，消费num个产品
		for (int i = 1; i <= num; ++i) {
			list.remove();
		}

		System.out.println("【已经消费产品数】:" + num + " \t 【现仓储量为】:" + list.size());

		// 唤醒其他所有线程
		full.signalAll();
		empty.signalAll();

		// 释放锁
		lock.unlock();
	}

	// get/set方法
	public LinkedList<Object> getList() {
		return list;
	}

	public void setList(LinkedList<Object> list) {
		this.list = list;
	}

	public int getMAX_SIZE() {
		return MAX_SIZE;
	}

	public static void main(String[] args) {
		Resource2 Resource2 = new Resource2();
		// 生产者对象
		Producer2 p1 = new Producer2(Resource2);
		Producer2 p2 = new Producer2(Resource2);
		Producer2 p3 = new Producer2(Resource2);
		Producer2 p4 = new Producer2(Resource2);
		Producer2 p5 = new Producer2(Resource2);
		// 消费者对象
		Consumer2 c1 = new Consumer2(Resource2);
		Consumer2 c2 = new Consumer2(Resource2);
		Consumer2 c3 = new Consumer2(Resource2);
		// 设置生产者产品生产数量
		p1.setNum(10);
		p2.setNum(10);
		p3.setNum(10);
		p4.setNum(10);
		p5.setNum(10);
		// 设置消费者产品消费数量
		c1.setNum(19);
		c2.setNum(20);
		c3.setNum(30);
		// 线程开始执行
		c1.start();
		c2.start();
		c3.start();
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		p5.start();

	}
}

class Consumer2 extends Thread {
	private int num;

	private Resource2 Resource2;

	public Consumer2(Resource2 Resource2) {
		this.Resource2 = Resource2;
	}

	@Override
	public void run() {
		Resource2.consume(num);
	}

	// get/set方法
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Resource2 getResource2() {
		return Resource2;
	}

	public void setResource2(Resource2 Resource2) {
		this.Resource2 = Resource2;
	}

}

class Producer2 extends Thread {
	private int num;

	private Resource2 Resource2;

	public Producer2(Resource2 Resource2) {
		this.Resource2 = Resource2;
	}

	// get/set方法
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Resource2 getResource2() {
		return Resource2;
	}

	public void setResource2(Resource2 Resource2) {
		this.Resource2 = Resource2;
	}

	@Override
	public void run() {
		Resource2.produce(num);
	}
}
