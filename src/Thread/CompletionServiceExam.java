package Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CompletionServiceExam {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service=Executors.newCachedThreadPool();
		CompletionService<Integer>completionService=new ExecutorCompletionService<>(service);
		for (int i = 0; i < 5; i++) {
			completionService.submit(new Task(i));
		}
		service.shutdown();
		for(int i=0;i<5;i++)
		{
			Future<Integer>future=completionService.take();
			System.out.println(future.get());
		}
	} 
}
class Task implements Callable<Integer>
{
	private final int sum;
	public Task(int sum) {
		// TODO Auto-generated constructor stub
		this.sum=sum;
	}
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		TimeUnit.SECONDS.sleep(sum);
		return sum*sum;
		 
	}
	
}