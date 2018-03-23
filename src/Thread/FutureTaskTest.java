package Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
 
/* 实现原理：
 * FutureTask内部用一个volatile修饰的变量state来展示任务的状态
 * 把FutureTask提交到线程池或者线程执行start时调用run方法
 * 首先会判断当前状态是否为NEW状态，或者当前CAS设置执行当前线程，失败则返回
 * ，只有一个线程可以成功成功；如果当前状态为NEW，则调用任务的call方法执行任务
 * 调用set方法设置call方法返回结果以及task状态
 * 
 * 任务提交后，调用get获得结果，这个get方法是阻塞的，
 * 如果当前的状态是NEW（0）或是COMPLETING（1）则等待，
 * 因为位normal（2）或者exceptional（3）时候才说明数据计算完成了。
 */
public class FutureTaskTest implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		System.out.println("子线程在进行计算");
		Thread.sleep(1000);
		int sum=0;
		for(int i=0;i<100;i++)
		{
			sum+=i;
		}
		return sum;
	}
	public static void main(String[] args) {
		ExecutorService executorService=Executors.newCachedThreadPool();
		
		FutureTaskTest task=new FutureTaskTest();
		FutureTask<Integer> futureTask =new FutureTask(task);
		executorService.submit(futureTask );
		System.out.println("主线程在执行任务");
		
		try {
			System.out.println("task运行结果"+futureTask .get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executorService.shutdown();
	}
}
