package Test;

public class Test_Distribute {
	public static void main1(String[] args) throws CloneNotSupportedException {
		Tree tree = new Tree();
		TreeNode[] temp = Tree.DistributeServer(tree.root, 5);
		Tree.printSelectedNodes(temp);
	}

	public static void main(String[] args) throws Exception {

		long start = System.currentTimeMillis();

		/*BinaryTree bt = new BinaryTree();
		bt.createBTree(bt.root);
		TreeNode node = bt.root;*/
		 TreeNode node = BuildBinary.buildRamdonBT();
		// BinaryTree.printBinaryTree(node, node.key, 0);

		int bh = BinaryTree.bheight(node);
		TreeNode tNode = BinaryTree.MergeAll(node);

		int[][] result = BinaryTree.printTernaryTree(tNode);
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}

		System.out.print("BH is:" + bh);
		int th = BinaryTree.theight(tNode);
		System.out.print("   TH by Algo1 is:" + th);
		System.out.print("   height difference is:" + (bh - th));

		long time = System.currentTimeMillis() - start;
		System.out.println("所花时间： " + time + "ms");

		BinaryTree.setParents(tNode);

		TreeNode[] temp = Tree.DistributeServer(tNode,7);
		System.err.println("######################");
		Tree.printSelectedNodes(temp);
	}
}
