package Offer;

public class Test03 {
	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7};
		reverArr(arr,2);
		for(int i=0;i<arr.length;i++)
		{
			System.out.print(arr[i]+" ");
		}
	}

	private static void reverArr(int[] arr, int k) {
		int n=arr.length;
		shift(arr, n-k,n-1);
		shift(arr, 0,n-k-1);
		shift(arr,0,n-1);
	}
	public static void shift(int[]arr,int left,int right)
	{
		
		for(;left<right;left++,right--)
		{
			int temp=arr[left];
			arr[left]=arr[right];
			arr[right]=temp;
		}
	}
}
