package Problem;

import java.util.HashSet;
import java.util.Set;

/*
 * 有两个有序整数集合a和b，写一个函数找出它们的交集
 */
public class CommonPart {
	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 5, 6 };
		int[] b = { 1, 2, 5 };
		/*
		 * Set<Integer> ss=getSame(a, b); for(Integer s:ss) { System.out.println(s); }
		 */
		int[] c = getSame1(a, b);
		for (int s : c) {
			System.out.println(s);
		}
	}

	// Method1
	public static Set<Integer> getSame(int[] a, int[] b) {
		Set<Integer> set = new HashSet<Integer>();
		int i = 0, j = 0;
		while (i < a.length && j < b.length) {
			if (a[i] < b[j]) {
				i++;
			} else if (a[i] > b[j]) {
				j++;
			} else {
				set.add(a[i]);
				i++;
				j++;
			}
		}
		return set;
	}

	// Method2
	public static int[] getSame1(int[] a, int[] b) {
		if (a[0] > b[b.length - 1] || b[0] > a[a.length - 1]) {
			return new int[0];
		}
		int[] commonpart = new int[Math.max(a.length, b.length)];
		int offset = 0;
		for (int i = 0, s = i; i < a.length && s < b.length; i++) {
			while (a[i] > b[s]) {
				s++;
			}
			if (a[i] == b[s]) {
				commonpart[offset++] = b[s++];
			}
			while (i < (a.length - 1) && a[i] == a[i + 1]) {
				i++;
			}
		}
		if (commonpart.length == offset) {
			return commonpart;
		}
		int[] duplicate = new int[offset];
		System.arraycopy(commonpart, 0, duplicate, 0, offset);
		return duplicate;
	}
}
