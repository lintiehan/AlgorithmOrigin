package Test_new1;

import java.io.File;
import java.util.List;

public class Test_Algorithm1 {
	final static int num = 1000;

	public static void main(String[] args) throws Exception {

		long start = System.currentTimeMillis();

		BinaryTree bt = new BinaryTree();
		bt.createBTree(bt.root);
		//TreeNode node = bt.root;

		TreeNode node = BuildBinary.buildRamdonBT();
	    //BinaryTree.printBinaryTree(node, node.key, 0);

		int bh = BinaryTree.bheight(node);
		TreeNode tNode = BinaryTree.MergeAll(node);

		int[][] result = BinaryTree.printTernaryTree(tNode);
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
		long time = System.currentTimeMillis() - start;
		System.out.println("time cost " + time + "ms");
	}

}
 