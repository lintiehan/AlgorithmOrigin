package Problem;

public class ResolvePrime {
	public static void main(String[] args) {
		System.out.println(resolvePrime(90));  
	}
	public static String resolvePrime(int num)
	{
		StringBuffer sbBuffer=new StringBuffer(num+"=");
		//定义最小素数
		int i=2;
		//进行辗转相除法
		while(i<=num)
		{
			if(num%i==0)
			{
				sbBuffer.append(i+"*");
				num=num/i;
				i=2;
			}else {
				//若无法整除，则i自增
				i++;
			}
		}
		return sbBuffer.toString().substring(0,sbBuffer.length()-1);
	}
}
