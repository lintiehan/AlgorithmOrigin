package Problem;

import java.util.Scanner;

public class LongestCommonPrefix {
	 public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String[] strs=new String [3];
		for(int i=0;i<3;i++)
		{
			String temp=sc.nextLine();
			strs[i]=temp;
		}
		
		String result=getLongestCommonPart(strs);
		System.out.println(result);
	}

	private static String getLongestCommonPart(String[] strs) {
		// TODO Auto-generated method stub
		if(strs.length==0)
			return "";
		String cur=strs[0];
		for(int i=1;i<strs.length;i++)
		{
			String temp=strs[i];
			String res=cur;
			cur="";
			for(int j=0;j<Math.min(temp.length(), res.length());j++)
			{
				if(temp.charAt(j)==res.charAt(j))
				{
					cur+=temp.charAt(j);
				}else
				{
					break;
				}
			}
		}
		return cur;
	}
}
