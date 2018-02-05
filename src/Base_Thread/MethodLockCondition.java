package Base_Thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
 * Condition是在java 1.5中出现的，它用来替代传统的Object的wait()/notify()实现线程间的协作，
 * 它的使用依赖于 Lock，Condition、Lock 和 Thread 三者之间的关系如下图所示。
 * 相比使用Object的wait()/notify()，使用Condition的await()/signal()这种方式能够更加安全和高效地实现线程间协作。
 * Condition是个接口，基本的方法就是await()和signal()方法。
 * Condition依赖于Lock接口，生成一个Condition的基本代码是lock.newCondition() 。 
 * 必须要注意的是，Condition 的 await()/signal() 使用都必须在lock保护之内
 * ，也就是说，必须在lock.lock()和lock.unlock之间才可以使用。
 * 事实上，Conditon的await()/signal() 与 Object的wait()/notify() 有着天然的对应关系：

Conditon中的await()对应Object的wait()；
Condition中的signal()对应Object的notify()；
Condition中的signalAll()对应Object的notifyAll()。

实际上，Condition 实现了一种分组机制，将所有对临界资源进行访问的线程进行分组，以便实现线程间更精细化的协作，
例如通知部分线程。 
 */
public class MethodLockCondition {
	private Lock lock = new ReentrantLock(true);
	private Condition condition = lock.newCondition();
	private final ThreadToGo threadToGo = new ThreadToGo();

	class ThreadToGo {
		int value = 1;
	}

	public Runnable ThreadOne() {
		final String[] inputArr = Helper.buildNoArr(52);
		return new Runnable() {
			private String[] arr = inputArr;

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < arr.length; i = i + 2) {
					try {
						lock.lock();
						while (threadToGo.value == 2) {
							condition.await();
						}
						Helper.print(arr[i], arr[i + 1]);
						threadToGo.value = 2;
						condition.signal();
					} catch (Exception e) {
						// TODO: handle exception
					} finally {
						lock.unlock();
					}
				}
			}
		};
	}

	public Runnable ThreadTwo() {
		final String[] inputArr = Helper.buildCharArr(26);
		return new Runnable() {
			private String[] arr = inputArr;

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < arr.length; i++) {
					try {
						lock.lock();
						while (threadToGo.value == 1) {
							condition.await();
						}
						Helper.print(arr[i]);
						threadToGo.value = 1;
						condition.signal();
					} catch (Exception e) {
						// TODO: handle exception
					} finally {
						lock.unlock();
					}
				}
			}
		};
	}

	public static void main(String[] args) {
		MethodLockCondition two = new MethodLockCondition();
		Helper.instance.run(two.ThreadOne());
		Helper.instance.run(two.ThreadTwo());
		Helper.instance.shutdown();
	}
}
