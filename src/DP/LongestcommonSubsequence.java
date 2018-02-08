package DP;
 

public class LongestcommonSubsequence {
	public static void main(String[] args) {
		String a="wabc";
		String b="abc";
		//System.out.println(longestCommonSubsequence(a, b));
		longestCommonSubsequence2(a, b);
	}
	 
	public static void longestCommonSubsequence2(String str1,String str2)
	{
		 int len1=str1.length()-1;
		 int len2=str2.length()-1;
		 
		 char[]cs1=str1.toCharArray();
		 char[]cs2=str2.toCharArray();
		 int []cnt=new int [Math.max(str1.length(), str2.length())];
		 int maxlen=0;
		 int pos=0;
		 
		 for(int i=0;i<=len1;i++)
		 {
			 for(int j=len2;j>=0;j--)
			 {
				 if(cs1[i]==cs2[j])
				 {
					 cnt[j+1]=cnt[j]+1;
					 if(cnt[j+1]>maxlen)
					 {
						 maxlen=cnt[j+1];
						 pos=j+1;
					 }
				 }else
				 {
					 cnt[j+1]=0;
				 }
			 }
		 }
		System.out.println(maxlen);
		for(int i=0;i<maxlen;i++)
		{
			System.out.print(cs2[pos-maxlen+i]);
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
		return opt[0][0];
	}
}
