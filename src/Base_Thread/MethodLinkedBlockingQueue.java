package Base_Thread;

import java.util.concurrent.LinkedBlockingQueue;
/*
 * BlockingQueue有四个具体的实现类：

ArrayBlockingQueue：规定大小的BlockingQueue，其构造函数必须带一个int参数来指明其大小。
其所含的对象是以FIFO（先入先出）顺序排序的。

LinkedBlockingQueue：大小不定的BlockingQueue，
若其构造函数带一个规定大小的参数，生成的BlockingQueue有大小限制，
若不带大小参数，所生成的BlockingQueue的大小由Integer.MAX_VALUE来决定。
其所含的对象是以FIFO顺序排序的。

PriorityBlockingQueue：类似于LinkedBlockingQueue,
但其所含对象的排序不是FIFO，而是依据对象的自然排序顺序或者是构造函数所带的Comparator决定的顺序。


SynchronousQueue：特殊的BlockingQueue，对其的操作必须是放和取交替完成的。
 BlockingQueue定义的常用方法如下:

add(Object)：把Object加到BlockingQueue里，如果BlockingQueue可以容纳，则返回true，否则抛出异常。
offer(Object)：表示如果可能的话，将Object加到BlockingQueue里，即如果BlockingQueue可以容纳，则返回true，否则返回false。
put(Object)：把Object加到BlockingQueue里，如果BlockingQueue没有空间，则调用此方法的线程被阻断直到BlockingQueue里有空间再继续。
poll(time)：获取并删除BlockingQueue里排在首位的对象，若不能立即取出，则可以等time参数规定的时间，取不到时返回null。当不传入time值时，立刻返回。
peek()：立刻获取BlockingQueue里排在首位的对象，但不从队列里删除，如果队列为空，则返回null。
take()：获取并删除BlockingQueue里排在首位的对象，若BlockingQueue为空，阻断进入等待状态直到BlockingQueue有新的对象被加入为止。
 */
public class MethodLinkedBlockingQueue {
	private final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

	public Runnable ThreadOne() {
		final String[] inputArr = Helper.buildNoArr(52);
		return new Runnable() {
			private String[] arr = inputArr;

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < arr.length; i += 2) {
					Helper.print(arr[i], arr[i + 1]);
					queue.offer("TwoToGo");
					while (!"OneToGo".equals(queue.peek())) {
					}
					queue.poll();
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
				for (int i = 0; i < arr.length; i ++) {
					while (!"TwoToGo".equals(queue.peek())) {
					}
					queue.poll();
					Helper.print(arr[i] );
					queue.offer("OneToGo");
					  
				}
			}
		};
	}
	public static void main(String[] args) {
		MethodLinkedBlockingQueue methodFour=new MethodLinkedBlockingQueue();
		Helper.instance.run(methodFour.ThreadOne());
		Helper.instance.run(methodFour.ThreadTwo());
		Helper.instance.shutdown();
	}
}
