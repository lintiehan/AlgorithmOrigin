package Sort;
/*
 * 基本类型：采用调优的快速排序；
 * 源码中的快速排序，主要做了以下几个方面的优化：
　　1）当待排序的数组中的元素个数较少时，源码中的阀值为7，采用的是插入排序。
尽管插入排序的时间复杂度为0(n^2)，但是当数组元素较少时，插入排序优于快速排序，因为这时快速排序的递归操作影响性能。
　　2）较好的选择了划分元（基准元素）。能够将数组分成大致两个相等的部分，避免出现最坏的情况。
例如当数组有序的的情况下，选择第一个元素作为划分元，将使得算法的时间复杂度达到O(n^2).
　　源码中选择划分元的方法:
　　　　当数组大小为 size=7 时 ，取数组中间元素作为划分元。int n=m>>1;(此方法值得借鉴)
　　　　当数组大小 7<size<=40时，取首、中、末三个元素中间大小的元素作为划分元。
　　　　当数组大小 size>40 时 ，从待排数组中较均匀的选择9个元素，选出一个伪中数做为划分元。
　　3）根据划分元 v ，形成不变式 v* (<v)* (>v)* v*
　　普通的快速排序算法，经过一次划分后，将划分元排到素组较中间的位置，左边的元素小于划分元，右边的元素大于划分元，
而没有将与划分元相等的元素放在其附近，这一点，在Arrays.sort()中得到了较大的优化。
　　举例：15、93、15、41、6、15、22、7、15、20
　　因  7<size<=40,所以在15、6、和20 中选择v = 15 作为划分元。
　　经过一次换分后： 15、15、7、6、41、20、22、93、15、15. 与划分元相等的元素都移到了素组的两边。
　　接下来将与划分元相等的元素移到数组中间来，形成：7、6、15、15、15、15、41、20、22、93.
　　最后递归对两个区间进行排序[7、6]和[41、20、22、93].
 */
public class JavaSort {
	private JavaSort() {}
	
	/*
	 * 对指定的int类型数组按数字升序进行排序
	 */
	public static void sort(int []a)
	{
		sort(a,0,a.length);
	}
	/*
	 * 对指定 int 型数组的指定范围按数字升序进行排序
	 */
	public static void sort(int []a,int fromIndex,int toIndex)
	{
		 rangeCheck(a.length, fromIndex, toIndex);
		 sort1(a, fromIndex, toIndex - fromIndex);
	}
	/*
	 * 当待排序的数组中的元素个数小于 7 时，采用插入排序 。
	 尽管插入排序的时间复杂度为O(n^2),但是当数组元素较少时， 插入排序优于快速排序，
	 因为这时快速排序的递归操作影响性能。
	 */
	private static void sort1(int x[], int off, int len) {
		if(len<7)
		{
			for(int i=off;i<len+off;i++)
			{
				for(int j=i;j>off&&x[j-1]>x[j];j--)
				{
					swap(x,j,j-1);
				}
			}
			return ;
		}
		
		/*
		 * 当待排序的数组中的元素个数大于或等于7时，采用快速排序
		 * 选取一个划分元
		 * 较好的选择了划分元（基准元素），能够将数组分成大致两个相等的部分，避免出现最坏的情况
		 * 如在数组有序的情况下
		 * 选择第一个元素作为划分元，将使得算法的时间复杂度达到O(n^2)
		 */
		//当数组大小为7<size<40时，取首中末三个元素中间大小的元素作为划分元
		int m = off + (len >> 1);    //取数组的中间位置
		if(len>7)
		{
			int l=off;      //头
			int n=off+len -1;		//尾
			/*
			 * 当数组大小大于40时，从待排序数组中较均匀的选择9个元素，
			 * 选出一个伪中位数作为划分元
			 */			
			if(len>40)
			{
				int s=len/8;
				l=med3(x,1,1+s,1+2*s);
				m=med3(x,m-s,m,m+s);
				n=med3(x,n-2*s,n-s,n);
			}
			//取出中间大小的元素的位置
			 m = med3(x, l, m, n); // Mid-size, med of 3
		}
		
		//得到划分元V
		int v=x[m];
		// Establish Invariant: v* (<v)* (>v)* v*
		int a=off,b=a,c=off+len-1,d=c;
		while(true)
		{
			while(b<=c&&x[b]<=v)
			{
				if(x[b]==v)
				{
					swap(x,a++,b);
				}
				b++;
			}
			while(c>=b&&x[c]>=v)
			{
				if(x[c]==v)
				{
					swap(x,c,d--);
				}
				c--;
			}
			if(b>c)
			{
				break;
			}
			swap(x,b++,c--);
		}
		
		// Swap partition elements back to middle
		int s,n=off+len;
		s=Math.min(a-off, b-1);
		vecswap(x, off, b - s, s);
		s = Math.min(d - c, n - d - 1);
		vecswap(x, b, n - s, s);
		// Recursively sort non-partition-elements
		if ((s = b - a) > 1)
			sort1(x, off, s);
		if ((s = d - c) > 1)
			sort1(x, n - s, s);
	}
	
	private static void vecswap(int x[],int a,int b,int n)
	{
		for(int i=0;i<n;i++,a++,b++)
		{
			swap(x,a,b);
		}
	}
	
	private static int med3(int x[],int a,int b,int c)
	{
		return (x[a]<x[b]?(x[b] < x[c] ? b : x[a] < x[c] ? c : a)
				 : (x[b] > x[c] ? b : x[a] > x[c] ? c : a));
	}
	
	private static void rangeCheck(int arrayLen,int fromIndex,int toIndex)
	{
		if(fromIndex>toIndex)
		{
			throw new IllegalArgumentException("fromIndex(" + fromIndex
            + ") > toIndex(" + toIndex + ")");
		}
		if (fromIndex < 0)
			throw new ArrayIndexOutOfBoundsException(fromIndex);
		if (toIndex > arrayLen)
			 throw new ArrayIndexOutOfBoundsException(toIndex);
	}
	private static void swap(int []x,int a,int b)
	{
		int t=x[a];
		x[a]=x[b];
		x[b]=t;
	}
}
