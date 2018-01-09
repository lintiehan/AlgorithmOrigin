package Sort;

import java.lang.reflect.Array;

public class Test {
	public static void main(String[] args) {
		/*int []a={15,15,6,100,515,65,97,76,13,27};
		sort1(a,0,a.length-1);
		show(a);*/
		int i=5;
		for(int j=0;j<10;j++){
			System.out.println("--->"+i);
			i=i++;
			System.out.println(i);
		 }
		System.out.println(i);
	}
	
	public static int getmiddle(int []a,int low,int high)
	{
		int temp=a[low];
		while(low<high)
		{
			while(low<high&&temp<a[high])
			{
				high--;
			}
			a[low]=a[high];
			while(low<high&&temp>a[low])
			{
				low++;
			}
			a[high]=a[low];
		}
		a[low]=temp;
		return low;
	}
	public static void sort1(int []a,int low,int high)
	{
		if(low<high)
		{
			int middle=getmiddle(a, low, high);
			sort1(a,low,middle-1);
			sort1(a,middle+1,high);
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
	public static void sort(int []a )
	{
		int len=a.length;
		int temp=0;
		int j=0;
		for(int i=0;i<len;i++)
		{
			temp=a[i];
			for(j=i;j>0&&temp<a[j-1];j--){
				a[j]=a[j-1];
			}
			a[j]=temp;
		}
	}
}
