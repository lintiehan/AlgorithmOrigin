package Offer;

public class TEST {
	static int count = 0;
	static int target = 8;
	static int[] nums = { 2, 2, 2, 4, 6};

	public static void main(String[] args) {

		//dfs2(nums[0], 1, "");
		//System.out.println(count);

	//	dfs1(nums[0], 1, "");
		//System.out.println(count);
		
		dfs3(1, nums[1], "");
		System.out.println(count);
	}

	public static void dfs1(int[] nums, int start, int tempsum, String operator) {
		System.out.println(operator);
		if (start == nums.length) {
			if (tempsum == target) {
				count++;
				for (int i = 0; i < nums.length; i++) {
					if (i != nums.length - 1) {
						System.out.print(nums[i] + "" + operator.indexOf(i));
					} else {
						System.out.println(nums[i] + "=" + target);
					}
				}
			}
		} else {
			dfs1(nums, start + 1, tempsum + nums[start], operator + "+");
			dfs1(nums, start + 1, tempsum - nums[start], operator + "-");
		}
	}

	public static void dfs2(int index, int num, String oper) {
		if (index == nums.length) {
			if (num == target) {
				count++;
				for (int i = 0; i < nums.length; i++) {
					if (i != nums.length - 1) {
						System.out.print(nums[i] + " " + oper.charAt(i));
					} else {
						System.out.println(nums[i] + "=" + target);
					}
				}
			}
		} else {
			dfs2(index + 1, num + nums[index], oper + "+");
			dfs2(index + 1, num - nums[index], oper + "-");
			dfs2(index + 1, num * nums[index], oper + "*");
			dfs2(index + 1, num / nums[index], oper + "/");
		}
	}
	
	public static void dfs3(int index,int temp,String oper)
	{
		if(index==nums.length)
		{
			if(temp==target)
			{
				count++;
				for(int i=0;i<nums.length;i++)
				{
					if(i!=nums.length-1)
					{
						System.out.print(nums[i]+""+oper.charAt(i));
					}else {
						System.out.println(nums[i]+"="+target);
					}
				}
			}
		}else {
			dfs3(index+1,temp+nums[index],oper+"+");
			dfs3(index+1,temp-nums[index],oper+"-");
		}
	}
}
