package DP;
/*
 * 给定一个背包，容量为C，有n个物品，各物品对应重量为Weight[n]，物品价值为 Value[n]，
 * 向量y=[0,1,0…1,0]代表物品的选法，
 * 要么是0、要么是1，为1代表选取第i个物品，为0表示不选取第i个物品。
 */
public class TestPac {
	/**
	 * @param args
	 */
	int C=12;
	int Weight[]={2,3,4,5,6};
	int Value[]={1,4,3,6,8};
	int y[]={-1,-1,-1,-1,-1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestPac pp=new TestPac();
		System.out.println(pp.f(4,12));
		pp.printY();
	}
	
	public void printY(){
		for(int i=0;i<y.length;i++)
		{
			System.out.println(y[i]);
		}
	}
	
	public int f(int n ,int C)
	{
		if(n==-1||C==0)
			return 0;
		int tmp1=f(n-1,C);
		
		if(Weight[n]>C)
		{
			y[n]=0;
			return tmp1;
		}
		int tmp2=Value[n]+f(n-1,C-Weight[n]);
		if(tmp1>tmp2)
		{
			y[n]=0;
			return tmp1;
		}
		y[n]=1;
		return tmp2;	
	}
}
