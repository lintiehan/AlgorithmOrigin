package Test_new1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Test_Greedy {

	public static void main(String[] args) throws Exception {
		int[] min = new int[100];
		int[] max = new int[100];
		for (int k = 1; k < 10; k++) {
			for (int q = 0; q < 100; q++) {
				TreeNode node = BuildBinary.buildRamdonBT();
				int bh = BinaryTree.bheight(node);
				TreeNode tNode = BinaryTree.MergeAll(node);
				BinaryTree.setParents(tNode);
				int pc = 16;
				List<TreeNode> nodelist = new TreeDiv().divAll(tNode, pc, k);
				int[] sum = TreeDiv.showResult(nodelist, pc, k);
				Arrays.sort(sum);
				min[q] = sum[0];
				max[q] = sum[sum.length - 1];

			}
			System.out.println("k="+k+" "+getAve(min)+" "+getAve(max));
		 
		}

	}

	public static double getAve(int[] nums) {
		double sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		return sum / nums.length;
	}
}
