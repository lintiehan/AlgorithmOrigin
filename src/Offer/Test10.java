package Offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import Sort.QuickSort;

/*
 * 例如输入数组｛1 、2 、4、7 、11 、15 ｝和数字15. 由于4+ 11 = 15 ，因此输出4 和11 。
 */
public class Test10 {

	public static long count(int n) {
		int coins[] = { 1, 5, 10, 20, 50, 100 };
		int h = coins.length;

		long[][] dp = new long[h][n + 1];
		Arrays.fill(dp[0], 1);
		for (int i = 1; i < h; i++) {
			for (int j = 1; j <= n; j++) {
				int m = j / coins[i];
				for (int k = 0; k <= m; k++) {
					dp[i][j] = dp[i][j] + dp[i - 1][j - k * coins[i]];
				}
			}
		}
		return dp[h - 1][n];
	}

	public static void main(String[] args) {

		System.out.println(count(8845));
	}
}
