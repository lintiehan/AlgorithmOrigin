package Offer;

import java.util.Comparator;

import Sort.QuickSort;

/*
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
      例如输入数组{3， 32, 321}，则扫描输出这3 个数字能排成的最小数字321323。
 */
public class Test07 {
	private static class MyComparator implements Comparator<String>
	{
		@Override
		public int compare(String o1, String o2) {
			// TODO Auto-generated method stub
			if(o1==null||o2==null) {
				try {
					throw new IllegalAccessException();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			String s1=o1+o2;
			String s2=o2+o1;
			return s1.compareTo(s2);
		}
	}
	
	private static void quickSort(String []array,int start,int end,Comparator<String> comparator)
	{
		if(start<end)
		{
			String pivot=array[start];
			int left=start;
			int right=end;
			while(start<end)
			{
				while(start<end&&comparator.compare(array[end], pivot)>=0)
				{
					end--;
				}
				array[start]=array[end];
				while(start<end&&comparator.compare(array[start], pivot)<=0)
				{
					start++;
				}
				array[end]=array[start];
			}
			array[start]=pivot;
			quickSort(array,left,start-1,comparator);
			quickSort(array,start+1,end,comparator);
		}
	}
	public static void main(String[] args) {

        String[] data = {"3", "5", "1", "4", "2"};
        System.out.println(printMinNumber(data));
 
    }
	private static String printMinNumber(String[] data) {
		MyComparator comparator=new MyComparator();
		quickSort(data,0,data.length-1,comparator);
		StringBuilder builder=new StringBuilder(256);
		for(String s: data)
		{
			builder.append(s);
		}
		return builder.toString();
	 
	}
}
 