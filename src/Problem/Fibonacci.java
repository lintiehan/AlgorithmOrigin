package Problem;

public class Fibonacci {
	// 数组 时间复杂度O(n)
	static long fib1(int n) {
		long[] fibarr = new long[n + 1];
		fibarr[0] = 0;
		fibarr[1] = 1;
		for (int i = 2; i <= n; i++) {
			fibarr[i] = fibarr[i - 1] + fibarr[i - 2];
		}
		long result = fibarr[n];
		return result;
	}

	// 数组 时间复杂度O(n) 空间复杂度O(1)
	static long fib2(int n) {
		long f1 = 0;
		long f2 = 1;
		long f3 = 0;
		for (int i = 2; i <= n; i++) {
			f3 = f1 + f2;
			f1 = f2;
			f2 = f3;
		}
		return f3;
	}

	public static void main(String[] args) {
		System.out.println(fib1(4));
		System.out.println(fib2(4));
	}
}
