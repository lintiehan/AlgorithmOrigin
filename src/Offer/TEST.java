package Offer;

public class TEST {
	static int count = 0;
	static int target = 3;
	static int[] nums = { 1, 1, 1, 1, 1 };

	public static void main(String[] args) {

		dfs2(nums[0], 1, "");
		System.out.println(count);

	//	dfs1(nums[0], 1, "");
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
}
