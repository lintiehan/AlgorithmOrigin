package Problem;

//组合问题
public class Combination {
	public static void main(String[] args) {
		//String s = "123";
		// getResultByBit(s);
		int[] a= {1,2,3};
		int[] b = new int[a.length];
		getCombination(a, b, 0);
	}

	public static void getResultByBit(String str) {
		int len = str.length();
		for (int i = 0; i < (1 << len); i++) {
			for (int j = 0; j < len; j++) {
				if ((i & (1 << j)) != 0) {
					System.out.print(str.charAt(j) + " ");
				}
			}
			System.out.println();
		}
	}

	public static void getCombination(int[] a, int[] b, int index) {
		if (index >= a.length) {
			return;
		}
		for (int i = 0; i < a.length; i++) {
			if (index == 0 || a[i] >= b[index - 1]) {
				b[index] = a[i];
				show(b, index);
				getCombination(a, b, index + 1);
			}
		}
	}

	private static void show(int[] b, int index) {
		for (int i = 0; i < index + 1; i++) {
			System.out.print(b[i] + " ");
		}
		System.out.println();
	}
}
