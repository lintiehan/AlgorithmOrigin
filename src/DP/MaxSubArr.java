package DP;

public class MaxSubArr {
	public static int max(int m, int n) {
		return m > n ? m : n;
	}

	public static int maxSub(int[] arr) {
		int n = arr.length;
		int[] end = new int[n];
		int[] all = new int[n];
		end[0] = all[0] = arr[0];
		for (int i = 1; i < n; i++) {
			end[i] = max(end[i - 1] + arr[i], arr[i]);
			all[i] = max(end[i], all[i - 1]);
		}
		show(end);
		System.out.println();
		show(all);
		return all[n - 1];
	}

	public static int maxSub1(int[] arr) {
		int n = arr.length;
		int nAll = arr[0];// 有n个数组的最大子数组和
		int nEnd = arr[0];// 有n个数组包括最后一个元素的子数组的最大和

		for (int i = 1; i < n; i++) {
			nEnd = max(nEnd + arr[i], arr[i]);
			nAll = max(nEnd, nAll);
		}
		return nAll;
	}

	public static void maxSub2(int[] arr) {
		int begin = 0;// 记录最大子数组的起始位置
		int end = 0;// 记录最大子数组的结束位置

		int maxSum = Integer.MIN_VALUE;
		int nSum = 0;// 包含子数组最后一位的最大值
		int nStar = 0;

		for (int i = 0; i < arr.length; i++) {
			System.out.println("nSum--->" + nSum+"  i---->"+i);
			 
			if (nSum < 0) {
				nSum = arr[i];
				nStar = i;
				System.out.println("###i---> " + i);
			} else {
				nSum += arr[i];
			}
			System.out.println("3333nSum--->" + nSum+"  i---->"+i);
			
			if (nSum > maxSum) {
				maxSum = nSum;
				begin = nStar;
				end = i;
			}
		}
		System.out.println("max=" + maxSum);
		System.out.println("begin=" + begin);
		System.out.println("end=" + end);
	}

	public static void main(String[] args) {
		int[] array = { 1, -2, 4, 8, -4, 7, -1, -5 };
		maxSub2(array);
		// System.out.println(maxSub (array));
	}

	public static void show(int[] temp) {
		for (int i = 0; i < temp.length; i++) {
			System.out.print(temp[i] + " ");
		}
	}

}
