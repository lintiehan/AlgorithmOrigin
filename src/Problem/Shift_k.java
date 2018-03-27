package Problem;

import java.util.concurrent.ConcurrentHashMap;

public class Shift_k {
	public static void main(String[] args) {
		int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
		shift(arr, 3);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	public static void shift(int[] a, int k) {
		int n = a.length;
		k = k % n;// 为了防止k比n大，右移k位跟右移动k%n位的结果是一样的
		// reverse(a, n-k, n-1);
		// reverse(a, 0, n-k-1);
		reverse(a, 0, n - 1);
	}

	public static void reverse(int[] a, int b, int c) {
		for (; b < c; b++, c--) {
			int temp = a[c];
			a[c] = a[b];
			a[b] = temp;
		 ConcurrentHashMap<K, V>
		}
	}
}
