package Problem;

public class FindNotDouble {
	public static void main(String[] args) {
		int array[]= {1,1,2,4,3,4,3};
		int num=findNumOne(array);
		System.out.println(num);
		findNumRepeat();
	}
	public static int findNumOne(int []a)
	{
		int n=a.length;
		int result=a[0];
		int i;
		for( i=1;i<n;i++)
		{
			result^=a[i];
		}
		return result;
	}
	
	public static void findNumRepeat()
	{
		int []a= {1,2,3,4,10,1};
		 int n=a.length;
		 int temp1=0;
		 int temp2=0;
		 for(int i=0;i<n-1;i++)
		 {
			 temp1+=(i+1);
			 temp2+=a[i];
		 }
		 temp2+=a[n-1];
		 int re=temp2-temp1;
		 System.out.println("重复数字为："+re);
	}
}
