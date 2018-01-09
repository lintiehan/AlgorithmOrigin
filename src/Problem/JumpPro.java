package Problem;

public class JumpPro {
	//一个台阶总共有n级，如果一次可以跳1级，也可以跳2级。求总共有多少总跳法  三种解法
	static long Jump1(int stageNum)
	{
		if(stageNum<=0)
			return 0;
		else if(1==stageNum)
			return 1;
		else if(2==stageNum)
			return 2;
		return Jump1(stageNum-1)+Jump1(stageNum-2);
	}
	
	  //自顶向下的动态规划
	static long Jump2(int stageNum)
	{
		long[] counter=new long[101];
		if(0!=counter[stageNum])
			return counter[stageNum];
		//定义递归出口
		if(stageNum<=0)
			return 0;
		else if(1==stageNum)
			return counter[1]=1;
		else if(2==stageNum)
			return counter[2]=2;
		counter[stageNum]=Jump2(stageNum-1)+Jump2(stageNum-2);
		return counter[stageNum];
	}
	
	
	
	//自底向上的动态规划 stageNum最大为100
	static long Jump3(int stageNum)
	{
		long counter[]= new long[101];
		counter[1]=1;
		counter[2]=2;		
		int calculatedIndex=2;		
		if(stageNum<=calculatedIndex)
		{
			return counter[stageNum];
		}	
		//防止下边越界
		if(stageNum>100)
			stageNum=100;
		
		for(int i=calculatedIndex+1;i<=stageNum;i++)
		{
			counter[i]=counter[i-1]+counter[i-2];
		}
		calculatedIndex=stageNum;
		return counter[stageNum];
	}
	
	
	
	//变态跳台阶问题 :一个台阶总共有n级，如果一次可以跳1级，也可以跳2级……也可以跳n级。
	//求总共有多少总跳法，并分析算法的时间复杂度
	static long Jump(int num)
	{
		long counter[]= new long[101];
		counter[0]=1;
		counter[1]=1;
		counter[2]=2;	
		int calculatedIndex=2;
		if(num<=calculatedIndex)
		{
			return counter[num];
		}
		if(num>100)
			num=100;
		
		for(int i=calculatedIndex+1;i<=num;i++)
		{
			counter[i]=2*counter[i-1];
		}
		calculatedIndex=num;
		return counter[num];
	}
	
	
	public static void main(String[] args) {
	
		System.out.println(Jump(3));
	}
}
