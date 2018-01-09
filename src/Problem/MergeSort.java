package Problem;

//两个有序数组的合并排序
public class MergeSort {
	  public static int[] sort(int []a,int []b)
	  {
		  int merge[]=new int [a.length+b.length];
		  int lena=0,lenb=0,lenmerge=0;
		  
		  
		  while(lena<a.length&&lenb<b.length)
		  {
			  if(a[lena]<b[lenb])
			  {
				  merge[lenmerge++]=a[lena++];
			  }else
			  {
				  merge[lenmerge++]=b[lenb++];
			  }
		  }
		  while(lena<a.length)
		  {
			  merge[lenmerge++]=a[lena++];
		  }
		  while(lenb<b.length)
		  {
			  merge[lenmerge++]=b[lenb++];
		  }
		  return merge;
	  }
	  public static void main(String[] args) {
		int []a= {1,3,5,7,9,19};
		int []b= {2,6};
		int []c=sort(a, b);
		for(int i:c)
		{
			System.out.print(i+" ");
		}
	}
} 
