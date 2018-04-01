package Problem;

import java.util.Scanner;

public class MaxSum {
	public static void main(String[] args) {
		int[] arr = {3,9,7,5,1,-1,1,7,2 };
		int i = getMax(arr);
		System.out.println(i);
	}

	private static int getMaxValue(int[] arr) {
		// TODO Auto-generated method stub
		int[] temp = new int[arr.length];//辅助数组
		temp[0] = arr[0];
		temp[1] = arr[1];
		int curMaxValue = arr[0]; // 当前位置最大值
		if (temp[1] > temp[0]) {
			curMaxValue = temp[1];
		}
		
		for (int i = 2; i < arr.length; i++) {
			int val = arr[i];
			for (int j = 0; j < i - 1; j++) {
				if (val + temp[j] > curMaxValue) {
					curMaxValue = val + temp[j];
				}			
			}			
			temp[i] = curMaxValue;
		}
		return curMaxValue;
	}
 
    public static int getMax(int []arr)
    {
    	int []temp=new int[arr.length];
    	temp[0]=arr[0];
    	temp[1]=arr[1];
    	int curMax=temp[0];
    	if(curMax<temp[1])
    		curMax=temp[1];
    	
    	for(int i=2;i<arr.length;i++)
    	{
    		int val=arr[i];
    		for(int j=0;j<i-1;j++)
    		{
    			if(temp[j]+val>curMax)
    			{
    				curMax=temp[j]+val;
    			}
    		}
    		temp[i]=curMax;
    	}
    	return temp[arr.length-1];
    }
}
