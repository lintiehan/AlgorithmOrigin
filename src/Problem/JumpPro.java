package Problem;

public class JumpPro {
	//һ��̨���ܹ���n�������һ�ο�����1����Ҳ������2�������ܹ��ж���������  ���ֽⷨ
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
	
	  //�Զ����µĶ�̬�滮
	static long Jump2(int stageNum)
	{
		long[] counter=new long[101];
		if(0!=counter[stageNum])
			return counter[stageNum];
		//����ݹ����
		if(stageNum<=0)
			return 0;
		else if(1==stageNum)
			return counter[1]=1;
		else if(2==stageNum)
			return counter[2]=2;
		counter[stageNum]=Jump2(stageNum-1)+Jump2(stageNum-2);
		return counter[stageNum];
	}
	
	
	
	//�Ե����ϵĶ�̬�滮 stageNum���Ϊ100
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
		//��ֹ�±�Խ��
		if(stageNum>100)
			stageNum=100;
		
		for(int i=calculatedIndex+1;i<=stageNum;i++)
		{
			counter[i]=counter[i-1]+counter[i-2];
		}
		calculatedIndex=stageNum;
		return counter[stageNum];
	}
	
	
	
	//��̬��̨������ :һ��̨���ܹ���n�������һ�ο�����1����Ҳ������2������Ҳ������n����
	//���ܹ��ж������������������㷨��ʱ�临�Ӷ�
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
