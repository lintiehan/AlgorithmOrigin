package DFS;
import java.util.Iterator;
import java.util.TreeSet;

import org.omg.PortableInterceptor.Interceptor;

public class AllArrange { 
	static int count=0;
	public static void main(String[] args) {
		String testString="aabc";
		Permutation1(testString.toCharArray(),0,testString.length());
		 System.out.println("---------------");
		 
		// Permutation(testString.toCharArray(),0,testString.length());
		/*Iterator<Integer> i=values.iterator();
		while(i.hasNext())
		{
			System.out.println(i.next());
		}*/
		 System.out.println(count);
	}
	public static void swap(char[] str,int a, int b )
	{
		 char temp=str[a];
		 str[a]=str[b];
		 str[b]=temp;
	}
	 
	public static void Permutation(char[] str,int index,int size){
		if(index==size-1)
		{
			 String string="";
			 for(int i=0;i<str.length;i++)
			 {
				 string+=str[i];
				 System.out.print(str[i]);
			 }
			// values.add(Integer.valueOf(string));
			 System.out.println();
		}else {
			for(int i=index;i<size;i++)
			{
				swap(str,index,i);
				Permutation(str,index+1,size);
				swap(str,index,i);
			}
		}
	}
	
	/** 
     * 判断下标为end的位置元素是否与start...end-1元素相等，相等返回false，不等返回true 
     * @param array 
     * @param start 
     * @param end 
     * @return 
     */  
    private static boolean isSwap(char[] array, int start, int end) {  
   //	System.out.println("start="+array[start]+" end ="+array[end]);
        for (int i = start; i < end; i++) {  
            if(array[end] == array[i]){  
                return false;  
            }  
        }  
        return true;  
    }  
    
    //去重全排列
    public static void Permutation1(char[] str,int start,int end){
		if(start==end-1)
		{
			 String string="";
			 for(int i=0;i<str.length;i++)
			 {
				 string+=str[i];
			 
			 }
			 count++;
			// values.add(Integer.valueOf(string));
			 System.out.println(string);
		}else {
			for(int i=start;i<end;i++)
			{
				if(isSwap(str,start,i)) {
					swap(str,start,i);
					Permutation1(str,start+1,end);
					swap(str,start,i);
				}				 
			}
		}
	}
}
