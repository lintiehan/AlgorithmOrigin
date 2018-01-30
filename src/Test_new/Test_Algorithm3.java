package Test_new;

import java.io.File;

public class Test_Algorithm3 {
	public static void main(String[] args) throws Exception {

		BinaryTree bt = new BinaryTree();
		bt.createBTree(bt.root);
		// TreeNode node = bt.root;

		TreeNode node = BuildBinary.buildRamdonBT();
		// BinaryTree.printBinaryTree(node, node.key, 0);
		int bh = BinaryTree.bheight(node);
		TreeNode tNode = BinaryTree.MergeAll(node);

		int[][] result1 = BinaryTree.printTernaryTree(tNode);
		for (int i = 0; i < result1.length; i++) {
			for (int j = 0; j < result1[i].length; j++) {
				System.out.print(result1[i][j] + " ");
			}
			System.out.println();
		}
		System.out.print("BH is:" + bh);
		int th = BinaryTree.theight(tNode);
		System.out.print("   TH by Algo1 is:" + th);
		int result = bh - th;
		System.out.print("   height difference is:" + result);
		System.out.println();
		bt = null;
		System.out.println("================================");
		System.out.println("================================");

		Alg3 alg3 = new Alg3();
		alg3.preOrder(tNode, (TreeNode)tNode.clone());
		System.out.println("Algorithm3 :the height of Ternary Tree:" + bt.theight(tNode));
		int[][] result2 = BinaryTree.printTernaryTree(tNode);
		for (int i = 0; i < result2.length; i++) {
			for (int j = 0; j < result2[i].length; j++) {
				System.out.print(result2[i][j] + " ");
			}
			System.out.println();
		}

	}
}
