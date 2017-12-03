package DP;

import java.util.Scanner;

public class LongestPerf {
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		String [] strings=new String[3];
		
		for(int i=0;i<3;i++)
		{
			String temp=scanner.nextLine();
			strings[i]=temp;
		}
		
		String common=getLongestResult(strings);
		System.out.println(common);
	}

	private static String getLongestResult(String[] strings) {
		// TODO Auto-generated method stub
		if(strings.length==0)
		{
			return "";
		}
	 
		String cur=strings[0];
		
		for(int i=1;i<strings.length;i++)
		{
			String temp=strings[i];
			String current=cur;
			cur="";
			for(int j=0;j<Math.min(temp.length(), current.length());j++ )
			{
				if(temp.charAt(j)==current.charAt(j))
				{
					cur+=current.charAt(j);
				}else
				{
					break;
				}
			}
		}
		return cur;
	}
}
