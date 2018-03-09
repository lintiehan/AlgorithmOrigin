package ExecutorTest;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
//并行计算数组的和
public class ConcurrentCalculator {
	private ExecutorService exec;
	private int cpuCoreNumber;
	private List<Future<Long>> tasks = new ArrayList<Future<Long>>();

	//内部类
	class SumCalculator implements Callable<Long> {
		private int[] numbers;
		private int start;
		private int end;

		public SumCalculator(int[] numbers, int start, int end) {
			// TODO Auto-generated constructor stub
			this.numbers = numbers;
			this.start = start;
			this.end = end;
		}

		@Override
		public Long call() throws Exception {
			// TODO Auto-generated method stub
			Long sum = 0l;
			for (int i = start; i < end; i++) {
				sum += numbers[i];
			}
			return sum;
		}
	}

	public ConcurrentCalculator() {
		cpuCoreNumber = Runtime.getRuntime().availableProcessors();
		exec = Executors.newFixedThreadPool(cpuCoreNumber);
	}

	public Long sum(final int[] numbers) {
		// 根据CPU核心个数拆分任务，创建FutureTask并提交到Executor
		for (int i = 0; i < cpuCoreNumber; i++) {
			int increment = numbers.length / cpuCoreNumber;
			int start = increment * i;
			int end = increment * i + increment;
			if (end > numbers.length) {
				end = numbers.length;
			}

			SumCalculator sumCalculator = new SumCalculator(numbers, start, end);
			FutureTask<Long> task = new FutureTask<>(sumCalculator);
			tasks.add(task);
			if (!exec.isShutdown()) {
				exec.submit(task);
			}
		}
		return getResult();
	}

	/**
	 * 迭代每个只任务，获得部分和，相加返回
	 * 
	 * @return
	 */
	public Long getResult() {
		Long result = 0l;
		for (Future<Long> task : tasks) {
			try {
				// 如果计算未完成则阻塞
				Long sunSum = task.get();
				result += sunSum;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return result;
	}

	public void close() {
		exec.shutdown();
	}

	public static void main(String[] args) {
		int[] numbers = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		ConcurrentCalculator calculator = new ConcurrentCalculator();
		Long sum = calculator.sum(numbers);
		System.out.println(sum);
		calculator.close();
	}
}
