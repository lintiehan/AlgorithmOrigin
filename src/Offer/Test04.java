package Offer;

public class Test04 {
	public static void main(String[] args) {
		int[] num1 = { 1, 2, 5, 9, 10, 32 };
		int[] num2 = { 4, 6, 9, 16, 1000 };
		merge(num1, num2);
	}

	private static void merge(int[] num1, int[] num2) {
		// TODO Auto-generated method stub
		int len = num1.length + num2.length;

		int[] temp = new int[len];
		int i = 0;
		int j = 0;
		int count = 0;
		while (i < num1.length && j < num2.length) {
			if (num1[i] < num2[j]) {
				temp[count++] = num1[i++];
			 
			} else   {
				temp[count++] = num2[j++];
			 
			}
		}

		while (i < num1.length) {
			temp[count++] = num1[i++];
		}
		while (j < num2.length) {
			temp[count++] = num2[j++];
		}
		for (int ind = 0; ind < temp.length; ind++) {
			System.out.print(temp[ind] + " ");
		}
	}
}
