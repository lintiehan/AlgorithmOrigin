package Test;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class StandardDeviation {
	/** 样本的个数 */
	private int count;

	/** 平均值 */
	private double averageVar;

	/** sn样本方差 */
	private double standardDeviationSum;

	/** 样本标准差 */
	private double standard_Deviation;

	public StandardDeviation() {
		this(0, 0.0, 0.0);
	}

	public StandardDeviation(int count, double standardDeviationSum, double averageVar) {
		this.count = count;
		this.standardDeviationSum = standardDeviationSum;
		this.averageVar = averageVar;
		recomputerstandard_Deviation();
	}

	public synchronized int getCount() {
		return count;
	}

	private void recomputerstandard_Deviation() {
		int count = getCount();
		standard_Deviation = count > 1 ? Math.sqrt(standardDeviationSum / (count - 1)) : Double.NaN;
	}

	/**
	 * 获取运行时样本的方差
	 * 
	 * @return double
	 */
	public synchronized double getRunningVariance() {
		return standard_Deviation;
	}

	/**
	 * 增加一个样本时重新计算
	 * 
	 * @param sample
	 *            void
	 */
	public synchronized void addSample(double sample) {
		if (++count == 1) {
			averageVar = sample;
			standardDeviationSum = 0.0;
		} else {
			double oldaverageVar = averageVar;
			double diff = sample - oldaverageVar;
			averageVar += diff / count;
			standardDeviationSum += diff * (sample - averageVar);
		}

		recomputerstandard_Deviation();
	}

	/**
	 * 移除一个样本时重新计算
	 * 
	 * @param sample
	 *            void
	 */
	public synchronized void removeSample(double sample) {
		int oldCount = getCount();
		double oldaverageVar = averageVar;

		if (oldCount == 0) {
			throw new IllegalStateException();
		}

		if (--count == 0) {
			averageVar = Double.NaN;
			standardDeviationSum = Double.NaN;
		} else {
			averageVar = (oldCount * oldaverageVar - sample) / (oldCount - 1);
			standardDeviationSum = (sample - averageVar) * (sample - oldaverageVar);
		}

		recomputerstandard_Deviation();
	}

	public static double calc(StandardDeviation s, int[] arr) {
		int sum = 0, average = 0;
		double sn = 0.0;
		double standardDeviation = 0.0;

		for (int i = 0, len = arr.length; i < len; i++) {
			sum = sum + arr[i];
			s.addSample(arr[i]);
		}

		average = sum / 10;
		System.out.println("average value is = " + average);

		for (int j = 0, len = arr.length; j < len; j++) {
			sn = sn + Math.pow((arr[j] - average), 2);
		}

		// 测试数据计算
		standardDeviation = s.getRunningVariance(); 
		// System.out.println(standardDeviation);
		return standardDeviation;
	}

	public static void main(String[] args) {
		StandardDeviation s = new StandardDeviation();

		int[] arr = { 3,3,3,3};

		double temp = calc(s, arr);
		System.out.println(temp);
		// 通过函数计算
		double result = s.getRunningVariance();
		System.out.println(result);

	}
}
