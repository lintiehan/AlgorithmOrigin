package DP;

public class Test {
	public static void main(String[] args) {
		int[] array = { 1, -2, 4, 8, -4, 7, -1, -5 };
		maxSub2(array);
	}

	public static void maxSub2(int[] arr) {
		int begin = 0;
		int end = 0;
		int size = arr.length;
		int max = Integer.MIN_VALUE;
		int tempmax = 0;
		int start=0;
		for(int i=0;i<size;i++)
		{
			if(tempmax<0)
			{
				tempmax=arr[i];
				start=i;
			}else
			{
				tempmax+=arr[i];
			}
			
			if(tempmax>max)
			{
				max=tempmax;
				begin=start;
				end=i;
			}
		}
		
		System.out.println(max);
		System.out.println(begin);
		System.out.println(end);
	}

	public static int getmax(int n, int m) {
		return n > m ? n : m;
	}
}
