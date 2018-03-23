package Sort;

public class Main {
	public static void main(String[] args) {
		int []nums= {49,38,65,97,76,13,27};
		show(nums);
		 QuickSort(nums,0,nums.length-1);
		 //SelectSort(nums);
		show(nums);
		int []test= {11,34,13,14,100,1,12,3,14,45};
		MergeSort(test);
		show(test);
	}
	
	//合并排序
	static int[]auk;
	private static void MergeSort(int []array)
	{
		auk=new int[array.length];
		MergeSort(array,0,array.length-1);
	}
	/**
	 * 归并排序
	 * 简介:将两个（或两个以上）有序表合并成一个新的有序表 即把待排序序列分为若干个子序列，每个子序列是有序的。然后再把有序子序列合并为整体有序序列
	 * 时间复杂度为O(nlogn)
	 * 稳定排序方式
	 * @param nums 待排序数组
	 * @return 输出有序数组
	 */ 
	private static int[] MergeSort(int []array,int low,int high)
	{	
		int mid=(low+high)/2;
		if(low<high)
		{
			//左边
			MergeSort(array,low,mid);
			//右边
			MergeSort(array,mid+1,high);
			//左右归并
			Merge(array,low,mid,high);
		}
		return array;
	}
	/**
	 * 将数组中low到high位置的数进行排序
	 * @param array 待排序数组
	 * @param low 待排的开始位置
	 * @param mid 待排中间位置
	 * @param high 待排结束位置
	 */
	private static void Merge(int []array,int low,int mid,int high)
	{
		int[]temp=new int[high-low+1];
		int i=low;
		int j=mid+1;
		int k=0;
		//把较小的树先移到新数组中
		while(i<=mid&&j<=high)
		{
			if(array[i]<array[j]) {
				temp[k++]=array[i++];
			}else
			{
				temp[k++]=array[j++];
			}
		}
		
		//把左边剩余的数移入数组
		while(i<=mid)
		{
			temp[k++]=array[i++];
		}
		//把右边剩余的数移入数组
		while(j<=high)
		{
			temp[k++]=array[j++];
		}
		
		//把新数组中的树覆盖arrays数组
		for(int k2=0;k2<temp.length;k2++)
		{
			array[k2+low]=temp[k2];
		}
	}

	/**  
	 * 插入排序
	 * 
	 * 从第一个元素开始，该元素可以认为已经被排序
	 * 取出下一个元素，在已经排序的元素序列中从后向前扫描 
	 * 如果该元素（已排序）大于新元素，将该元素移到下一位置  
	 * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置  
	 * 将新元素插入到该位置中  
	 * 重复步骤2  
	 * @param numbers  待排序数组
	 */  
	public static void insertSort(int[] numbers)
	{
	int size = numbers.length;
	int temp = 0 ;
	int j =  0;

	for(int i = 0 ; i < size ; i++)
	{
	    temp = numbers[i];
	    //假如temp比前面的值小，则将前面的值后移
	    for(j = i ; j > 0 && temp < numbers[j-1] ; j --)
	    {
	    	numbers[j] = numbers[j-1];
	    }
	    numbers[j] = temp;
	}
	}

	 /**
	 * 选择排序算法
	 * 在未排序序列中找到最小元素，存放到排序序列的起始位置  
	 * 再从剩余未排序元素中继续寻找最小元素，然后放到排序序列末尾。 
	 * 以此类推，直到所有元素均排序完毕。 
	 * @param numbers
	 */
	public static void SelectSort(int []nums)
	{
		int size=nums.length;
		int temp=0;
		for (int i=0;i<size;i++)
		{
			int k=i;
			for(int j=size-1;j>i;j--)
			{
				if(nums[j]<nums[k])
				{
					k=j;
				}
			}
			temp=nums[i];
			nums[i]=nums[k];
			nums[k]=temp;
		}
	}
	/**
	 * 快速排序
	 * 快速排序是通常被认为在同数量级（O(nlog2n)）的排序方法中平均性能最好的。
	 * 但若初始序列按关键码有序或基本有序时，快排序反而蜕化为冒泡排序。
	 * 为改进之，通常以“三者取中法”来选取基准记录，
	 * 即将排序区间的两个端点与中点三个记录关键码居中的调整为支点记录。
	 * 快速排序是一个不稳定的排序方法。
	 */
	//1.查找中轴（最低位作为中轴）所在位置：
	public static int GetMiddle(int []nums,int low ,int high)
	{
		int temp=nums[low];
		while(low<high)
		{
			while(low<high&&nums[high]>=temp) //比中轴小的记录移到低端
			{
				high--;
			}
			nums[low]=nums[high];
			while(low<high&&nums[low]<=temp)//比中轴大的记录移到高端
			{
				low++;
			}
			nums[high]=nums[low];
			//show(nums);
		}
		nums[low]=temp; //中轴记录到尾
	//	System.out.println(low);
		return low;//返回中轴位置、
	}
	//2、 递归形式的分治排序算法：
	public static void QuickSort(int []nums,int low,int high)
	{
		if(low<high)
		{
			int middle=GetMiddle(nums, low, high);
			QuickSort(nums,low,middle-1);//对低字段表进行递归排序
			QuickSort(nums,middle+1,high);//对高字段表进行递归排序
		}
	}
		
	/**
	 * 冒泡排序
	 * @param numbers
	 */
	public static void BubbleSort(int [] numbers)
	{
		int temp=0;
		int size =numbers.length;
		for(int i=0;i<size-1;i++)
		{
			for(int j=0;j<size-1-i;j++)
			{
				if(numbers[j]>numbers[j+1])
				{
					temp=numbers[j];
					numbers[j]=numbers[j+1];
					numbers[j+1]=temp;
				}
			}
		}
	}
	/**希尔排序的原理:根据需求，如果你想要结果从大到小排列，它会首先将数组进行分组，
	 * 然后将较大值移到前面，较小值移到后面，最后将整个数组进行插入排序，
	 * 这样比起一开始就用插入排序减少了数据交换和移动的次数，可以说希尔排序是加强版的插入排序
	 * 拿数组5, 2, 8, 9, 1, 3，4来说，数组长度为7，当increment为3时，数组分为两个序列
	 * 5，2，8和9，1，3，4，第一次排序，9和5比较，1和2比较，3和8比较，4和比其下标值小increment的数组值相比较
	 * 此例子是按照从大到小排列，所以大的会排在前面，第一次排序后数组为9, 2, 8, 5, 1, 3，4
	 * 第一次后increment的值变为3/2=1,此时对数组进行插入排序，
	 *实现数组从大到小排
	 *
	 *时间复杂度：O（n^2）.
	 */ 
 	public static void sheelSort(int []data)
 	{
 		int j=0;
 		int temp=0;
 		//每次将步长缩短为原来的一半
 		for(int increment=data.length/2;increment>0;increment/=2)
 		{
 			for(int i=increment;i<data.length;i++)
 			{
 				temp =data[i];
 				for(j=i;j>=increment;j-=increment)
 				{
 					if(temp>data[j-increment])
 					{
 						data[j]=data[j-increment];
 					}else {
 						break;
 					}
 				}
 				data[i]=temp;
 			}
 		}
 	}
	
	public static void show(int [] numbers)
	{
		for(int i=0;i<=numbers.length-1;i++)
		{
			System.out.print(numbers[i]+" ");
		}
		System.out.println();
	}
}
