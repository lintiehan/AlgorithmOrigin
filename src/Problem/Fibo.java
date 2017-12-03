package Problem;

public class Fibo {
	public static void main(String[] args) {
		for(int h=1;h<=25;h++)
		{
			 	int r=2*getResult(h+1)-1;
			 
			System.out.println(r);
			
		} 
	}
	public static int getResult(int target)
	{
		int f1=1;
		int f2=1;
		int f3=0;
		if(target<=2)
			return 1;
		else
			for(int i=3;i<=target;i++)
			{
				f3=f1+f2;
				f1=f2;
				f2=f3;
			}
		return f3;
	}
}
