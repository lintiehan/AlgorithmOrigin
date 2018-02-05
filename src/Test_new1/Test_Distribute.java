package Test_new1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
 

public class Test_Distribute {

	public static void main(String[] args) throws Exception {

		//long start = System.currentTimeMillis();
		
		/*
		 * BinaryTree bt = new BinaryTree(); bt.createBTree(bt.root); TreeNode node =
		 * bt.root;
		 */
		TreeNode node = BuildBinary.buildRamdonBT();
		// BinaryTree.printBinaryTree(node, node.key, 0);

		int bh = BinaryTree.bheight(node);
		TreeNode tNode = BinaryTree.MergeAll(node);

		/*int[][] result = BinaryTree.printTernaryTree(tNode);
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
		System.out.print("the num of node is:" + TreeDiv.getSumNode(tNode));
		System.out.print("   BH is:" + bh);
		 
		int th = BinaryTree.theight(tNode);
		System.out.print("   TH by Algo1 is:" + th);
		System.out.print("   height difference is:" + (bh - th));*/
		System.out.println();
		BinaryTree.setParents(tNode);
		int pc=3;
		int k=6;
		 
		List<TreeNode> nodelist=new TreeDiv().divAll(tNode,pc,k);
		
	//	System.out.println("pc's size "+pc+"   list.size "+nodelist.size());
	//	TreeDiv.showList(nodelist,pc,k);
		int []sum=TreeDiv.showResult(nodelist,pc,k);
		Arrays.sort(sum);
		for (int i = 0; i < sum.length; i++) {
			System.out.print(" " + sum[i]);
		}
		System.out.print(" min:" + sum[0]);
		System.out.print("  max:" + sum[sum.length - 1]);
		System.out.println("  ave:" + getAve(sum));
		//long time = System.currentTimeMillis() - start;
		//System.out.println("cost time " + time + "ms");
		 
	}
	public static double getAve(int[] nums) {
		double sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		return sum / nums.length;
	}
}
