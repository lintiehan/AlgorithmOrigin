package Sort;

public class BinarySearch {
	public static void main(String[] args) {
		int [] arr= {1,2,3,4,5,6,7,8,9};
		int index=search(arr, 1);
		System.out.println(index);
	}
	
	//二分查找非递归实现
	public static int search(int[]arr,int key)
	{
		int mid;
		int start=0;
		int end=arr.length-1;
		while(start<=end)
		{
			mid=(end-start)/2+start;
			if(key<arr[mid])
			{
				end=mid-1;
			}else if(key>arr[mid])
			{
				start=mid+1;
			}else
			{
				return mid;
			}
		}
		return -1;
	}
}
