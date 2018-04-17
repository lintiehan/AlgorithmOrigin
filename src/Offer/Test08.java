package Offer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import Sort.QuickSort;

/*
 * 例如输入数组｛1 、2 、4、7 、11 、15 ｝和数字15. 由于4+ 11 = 15 ，因此输出4 和11 。
 */
public class Test08 {
	public static List<Integer> findNumbersWithSum(int[] data, int sum) {
		List<Integer> result = new ArrayList<>(2);
		if (data == null || data.length < 2) {
			return result;
		}

		int end = data.length - 1;
		int start = 0;
		long cursum; // 统计和，取long是防止结果溢出
		while (start < end) {
			cursum = data[start] + data[end];
			if (cursum == sum) {
				result.add(data[start]);
				result.add(data[end]);
				System.out.println(data[start]+"-"+data[end]);
				start++;
				end--;
			} else if (cursum < sum) {
				start++;
			} else {
				end--;
			}

		}
		return result;
	}

	public static void main(String[] args) {
		int[] data1 = { 0, 2, 4, 7, 11, 15 };
		System.out.println(findNumbersWithSum(data1, 15));
	}
}
