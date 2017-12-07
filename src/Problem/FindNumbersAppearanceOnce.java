package Problem;
/*
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次，
 * 请写程序找出这两个只出现一次的数字。要求时间复杂度是 O(n)，空间复杂度是 O(1)。
 */

public class FindNumbersAppearanceOnce {
	public static void main(String[] args) {
		int [] data= {1,2,1,2,6,5};
		int[] result1=find(data);
		System.out.println(result1[0] + " " + result1[1]);
	}

	private static int[] find(int[] data) {
		int []result = {0,0};
		if(data==null||data.length<2)
		{
			return result;
		}
		int xor=0;
		for(int i:data)
		{
			xor^=i;
			System.out.println(xor);
		} 
		int indexOf1=findFirstBit(xor);
		System.out.println("---->"+indexOf1);
		for(int i:data)
		{
			if(isBit(i,indexOf1))
			{
				result[0]^=i;
			}else {
				result[1]^=i;
			}
		}
		return result;
	}

	private static int findFirstBit(int num) {
		int index=0;
		while((num&1)==0&&index<32)
		{
			num>>=1;
			index++;
		}
		return index;
	}

	private static boolean isBit(int num, int indexBit) {
		num>>>=indexBit;
		return (num&1)==1;
	}
}
