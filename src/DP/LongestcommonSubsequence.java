package DP;
 

public class LongestcommonSubsequence {
	public static void main(String[] args) {
		String a="rabcew";
		String b="wabcdabcew";
		// System.out.println(longestCommonSubsequence(a, b));
		longestCommonSubsequence2(a, b);
	}
	 
	public static void longestCommonSubsequence2(String str1,String str2)
	{	
		 
		 
		 char[]cs1=str1.toCharArray();
		 char[]cs2=str2.toCharArray();
		 
		 int matri[][]=new int[cs1.length][cs2.length];
				 
		 for(int i=0;i<matri.length;i++)
		 {
			 for(int j=0;j<matri[i].length;j++)
			 {
				 if((i==0||j==0)&&(cs1[i]==cs2[j]))
				 {
					 matri[i][j]= 1;
				 }
				 else if( cs1[i]==cs2[j])
				 {
					 matri[i][j]= matri[i-1][j-1]+1; 
				 }
			 }
		 }
		 
		 for (int i = 0; i < matri.length; i++) {
				for (int j = 0; j < matri[i].length; j++) {
					System.out.print(matri[i][j] + " ");
				}
				System.out.println();
			}
	}
	
	
	
	public static int longestCommonSubsequence(String str1,String str2)
	{
		return compute(str1.toCharArray(), str2.toCharArray());
	}
	public static int compute(char []str1,char []str2)
	{
		int length1=str1.length;
		int length2=str2.length;
		
		int [][] opt=new int [length1+1][length2+1];
		for(int i=length1-1;i>=0;i--)
		{
			for(int j=length2-1;j>=0;j--)
			{
				if(str1[i]==str2[j])
					opt[i][j]=opt[i+1][j+1]+1;
				else
					opt[i][j]=Math.max(opt[i+1][j], opt[i][j+1]);
			}
		}
		System.out.println("sub1:"+new String(str1));
		System.out.println("sub1:"+new String(str2));
		System.out.print("LCS:");
	
		int i=0,j=0;
		while(i<length1&&j<length2)
		{
			if(str1[i]==str2[j])
			{
				System.out.print(str1[i]);
				i++;
				j++;
			}else if(opt[i+1][j]>=opt[i][j+1])
			{
				i++;
			}else
			{
				j++;
			}
		}
		System.out.println();
		
		for(int i1=0;i1<opt.length;i1++)
		{
			for(int j1=0;j1<opt[i1].length;j1++)
			{
				System.out.print(opt[i1][j1]+" ");
			}
			System.out.println();
		}
		
		return opt[0][0];
	}
}
