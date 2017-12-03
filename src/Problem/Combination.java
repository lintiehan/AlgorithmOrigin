package Problem;

public class Combination {
	public static void main(String[] args) {
		String s="abc";
		getResult(s);
	}
	public static void getResult(String str)
	{
		int len=str.length();
		for(int i=0;i<(1<<len);i++)
		{
			for(int j=0;j<len;j++)
			{	
				if ((i& (1<<j))!=0)
				{
					System.out.print(str.charAt(j)+" ");
				} 				 
			}
			 System.out.println();
		}
	}
}
