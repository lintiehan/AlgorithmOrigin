package Test_new1;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Test_Greedy {

	public static void main(String[] args) throws Exception {
		File file = new File("test.txt");
		String string="";
		int[] min = new int[2];
		int[] max = new int[2];
		for (int k = 1; k <= 3; k++) {
			for (int q = 0; q < 3; q++) {
				TreeNode node = BuildBinary.buildRamdonBT();
				int bh = BinaryTree.bheight(node);
				TreeNode tNode = BinaryTree.MergeAll(node);
				BinaryTree.setParents(tNode);
				int pc = 16;
				List<TreeNode> nodelist = new TreeDiv().divAll(tNode, pc, k);
				int[] sum = TreeDiv.showResult(nodelist, pc, k);
				Arrays.sort(sum);
				min[q] = sum[0];
				max[q] = sum[sum.length-1];
			}

			string = "k=" + k + " " + (float) getAve(min) + " " + (float) getAve(max);
			// System.out.println("k=" + k + " " + (float)getAve(min) + " " +
			// (float)getAve(max));
			System.out.println(string);
			show(min);
			System.out.println();
			show(max);
		}
		byte[] sourceByte = string.getBytes();
		FileOutputStream outputStream=new FileOutputStream(file);
		outputStream.write(sourceByte);
		outputStream.close();
	}

	public static double getAve(int[] nums) {
		double sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		return sum / (nums.length);
	}

	public static void show(int[] nums) {

		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}

	}
}
