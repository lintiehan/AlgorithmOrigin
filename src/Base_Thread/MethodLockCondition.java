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
/*
 * public interface Lock {
    void lock();

    //如果当前线程未被中断，则获取锁
    void lockInterruptibly() throws InterruptedException;

    //仅在调用时锁为空闲状态才获取该锁
    boolean tryLock();

    //如果锁在给定的等待时间内空闲，并且当前线程未被中断，则获取锁
    boolean tryLock(long time, TimeUnit unit) throws InterruptedException;

    //释放锁
    void unlock();

    //返回绑定到此Lock实例的新Condition实例
    Condition newCondition();
}```

与内置加锁机制不同的是，Lock提供了一种无条件的、可轮询的、定时的以及可中断的锁获取操作，所有加锁和解锁的方法都是显式的。在Lock的实现中必须提供与内置锁相同的内存可见性语义。

ReentrantLock实现了Lock接口，并提供了与synchronized相同的互斥性和内存可见性。在获取ReentrantLock时，有着与进入同步代码块相同的内存语义；
在释放ReentrantLock时，同样有着与退出同步代码块相同的内存语义。

此外，与synchronized一样，ReentrantLock还提供了可重入的加锁语义。ReentrantLock支持在Lock接口中定义的所有获取锁模式，
并且与synchronized相比，它还为处理锁的不可用性问题提供了更高的灵活性。

为什么要创建一种与内置锁如此相似的新加锁机制？在大多数情况下，内置锁都能很好地工作，但在功能上存在一些局限性，
例如：无法中断一个正在等待获取锁的线程，或者无法在请求获取一个锁时无限地等待下去。
内置锁必须在获取该锁的代码块中释放，这就简化了编码工作，并且与异常处理操作实现了很好的交互，但却```
无法实现非阻塞结构的加锁规则```。
这些都是使用synchronized的原因，但在某些情况下，一种更灵活的加锁机制通常能提供更好的活跃性或性能。

* 轮询锁与定时锁
可定时的与可轮询的锁获取模式是由```tryLock```方法实现的，与无条件的锁获取模式相比，它具有更完善的错误恢复机制。内置锁中死锁是一个严重的问题。```可定时与可轮询的锁可以避免死锁的发生```。
如果不能获得所有需要的锁，那么可以```使用可定时的或可轮询的锁获取方式，从而使你重新获得控制权，它会释放已经获得的锁，然后重新尝试获取所有锁(或者至少会将这个失败记录到日志，并采取其他措施)```。
在实现具有时间限制的操作时，```定时锁```同样非常有用。当在带有时间限制的操作中调用了一个阻塞方法时，它能根据剩余时间来提供一个时限。如果操作不能在指定的时间内给出结果，那么就会使程序提前结束。当使用内置锁时，在开始请求锁后，这个操作将无法取消，因此内置锁很难实现带有时间限制的操作。
* 可中断的锁获取操作
正如```定时的锁获取```操作能在带有时间限制的操作中使用独占锁，```可中断的锁获取```操作同样能在可取消的操作中使用加锁。```lockInterruptibly```方法能够在获得锁的同时保持对中断的响应。
```可中断的锁获取操作的标准结构```比普通的锁获取操作略微复杂一些，因为需要两个try块(如果在可中断的锁获取操作中抛出了InterruptedException，那么可以使用标准的try-finally加锁模式)。
* 非块结构的加锁
在```内置锁```中，锁的获取和释放等操作都是基于代码块的---释放锁的操作总是与获取锁的操作处于同一个代码块，而不考虑控制权如何退出该代码块。自动的锁释放操作简化了对程序的分析，避免了可能的编码错误，但有时候需要更灵活的加锁规则。
我们通过```降低锁的粒度```可以提高代码的可伸缩性。```锁分段技术```在基于散列的容器中实现了不同的散列链，以便使用不同的锁。我们可以通过采用类似的原则来降低链表中锁的粒度，即为每个链表节点使用一个独立的锁，使不同的线程能独立地对链表的不同部分进行操作。每个节点的锁将保护链表指针以及在该节点中存储的数据，因此当遍历或修改链表时，我们必须持有该节点上的这个锁，直到获得了下一个节点的锁，只有这样，才能释放前一个节点上的锁。这也称为```连锁式加锁或锁耦合。```

###### 2.性能考虑因素 
锁的实现方式越好，将需要越少的系统调用和上下文切换，并且在共享内存总线上的内存同步通信量也越少。
在```Java5```中，当从单线程变化到多线程时，内置锁的性能将急剧下降，而ReentrantLock的性能下降则更为平缓，因而它具有更好的可伸缩性。
在```Java6```中，内置锁的性能不会由于竞争而急剧下降，并且两者的可伸缩性也基本相当。

###### 3.公平性
在ReentrantLock的构造函数中提供了两种公平性选择：创建一个非公平的锁(默认)或者一个公平的锁。在```公平的锁```上，线程将按照它们发出请求的顺序来获得锁。但在```非公平的锁```上，则允许插队：当一个线程请求非公平的锁时，如果在发出请求的同时该锁的状态变为可用，那么这个线程将跳过队列中所有的等待线程并获得这个锁。
当持有锁的时间相对较长，或者请求锁的平均时间间隔较长，那么应该使用```公平锁```。在这些情况下，插队带来的吞吐量提升则可能不会出现。

###### 4.在synchronized和ReentrantLock之间进行选择
ReentrantLock在加锁和内存上提供的语义与内置锁相同，此外它还提供了一些其他功能，包括定时的锁等待、可中断的锁等待、公平性，以及实现非块结构的加锁。
ReentrantLock在性能上似乎优于内置锁，在java6中略有胜出，java5中则是远远胜出。

那么为什么不放弃synchronized，并在所有新的并发代码中都使用ReentrantLock？
与显式锁相比，内置锁仍然具有很大的优势:
ReentrantLock的危险性比同步机制要高，如果忘记在finally块中调用unlock，那么虽然代码表面上能正常运行，但实际上已经埋下了一颗定时炸弹。
```仅当内置锁不能满足需求时，才可以考虑使用ReentrantLock```
在一些内置锁无法满足需求的情况下，ReentrantLock可以作为一种高级工具。
当需要一些高级功能时才应该使用ReentrantLock，这些功能包括：可定时的、可轮询的与可中断的锁获取操作，公平队列，以及非块结构的锁。
否则还是应该优先使用synchronized。未来的Java版本中更可能会提升synchronized而不是ReentrantLock的性能。

###### 5.读-写锁
ReentrantLock实现了一种```标准的互斥锁```：每次最多只有一个线程能持有ReentrantLock。但对于维护数据的完整性来说，互斥通常是一种过于强硬的加锁规则，因此也就不必要地限制了并发性。
互斥是一种保守的加锁策略，虽然可以避免“写/写”冲突和“写/读”冲突，但同样也避免了“读/读”冲突。在许多情况下，数据结构上的操作都是“读操作”---虽然它们也是可变的并且在某些情况下被修改，但其中大多数访问操作都是读操作。此时，如果能够放宽加锁需求，允许多个执行读操作的线程同时访问数据结构，那么将提升程序的性能。只要每个线程都能确保读取到最新的数据，并且在读取数据时不会有其他的线程修改数据，那么就不会发生问题。在这种情况下就可以使用```读/写锁```：一个资源可以被多个读操作访问，或者被一个写操作访问，但两者不能同时进行。
```java
public interface ReadWriteLock {
    Lock readLock();
    Lock writeLock();
}```
要读取由ReadWriteLock保护的数据，必须首先获得```读取锁```;当需要修改ReadWriteLock保护的数据时，必须首先获得```写入锁```。
在实际情况中，对于在多处理器系统上被频繁读取的数据结构，读-写锁能够提高性能。而在其他情况下，读-写锁的性能比独占锁的性能要略差一些，这是因为它的复杂性更高。
 
*/