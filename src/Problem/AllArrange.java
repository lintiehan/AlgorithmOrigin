package Problem;
import java.util.Iterator;
import java.util.TreeSet;

import org.omg.PortableInterceptor.Interceptor;

public class AllArrange {
	static TreeSet<Integer> values=new TreeSet();
	public static void main(String[] args) {
		String testString="123";
		Permutation(testString.toCharArray(),0,testString.length());
		 System.out.println("---------------");
		Iterator<Integer> i=values.iterator();
		while(i.hasNext())
		{
			System.out.println(i.next());
		}
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
			 
			 values.add(Integer.valueOf(string));
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
}
