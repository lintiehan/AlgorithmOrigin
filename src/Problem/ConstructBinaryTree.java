package Problem;

public class ConstructBinaryTree {
	static class BinaryTreeNode {
		int val;
		BinaryTreeNode leftnode;
		BinaryTreeNode rightnode;

		public BinaryTreeNode() {
		}

		public BinaryTreeNode(int val) {
			this.val = val;
			this.leftnode = null;
			this.rightnode = null;
		}
	}

	public static BinaryTreeNode Construct(int[] preOrder,int[] inOrder,int len)
	{
		if(preOrder==null||inOrder==null||len<=0)
		{
			return null;
		}
		
		try {
			return ConstructCore(preOrder, 0, preOrder.length-1, 
					inOrder, 0, inOrder.length-1);
		} catch (InvalidPutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	static class InvalidPutException extends Exception {

		private static final long serialVersionUID = 1L;

	}

 

	/**
	 * 
	 * @param PreOrder
	 *            前序遍历序列
	 * @param startPreIndex
	 *            前序序列开始位置
	 * @param endPreIndex
	 *            前序序列结束位置
	 * @param InOrder
	 *            中序遍历序列
	 * @param startInIndex
	 *            中序序列开始位置
	 * @param endInIndex
	 *            中序序列结束位置
	 * @return 根结点
	 * @throws InvalidPutException
	 */
	public static BinaryTreeNode ConstructCore(int[] preOrder, int startPreIndex, int endPreIndex, int[] inOrder,
			int startInIndex, int endInIndex) throws InvalidPutException {
		int rootval = preOrder[startPreIndex];
		System.out.println("根节点为："+rootval);
		BinaryTreeNode root = new BinaryTreeNode(rootval);

		// 只有一个元素
		if (startPreIndex == endPreIndex) {
			if (startInIndex == endInIndex 
					&& preOrder[startPreIndex] == inOrder[startInIndex]) {
				System.out.println("只有一个元素");
				return root;
			} else {
				throw new InvalidPutException();
			}
		}
		// 在中序遍历中找到根节点的索引
		int rootInIndex = startInIndex;

		while (rootInIndex <= endInIndex && inOrder[rootInIndex] != rootval) {
			++rootInIndex;
		}

		if (rootInIndex == endInIndex && inOrder[rootInIndex] != rootval) {
			throw new InvalidPutException();

		}

		int leftlen = rootInIndex - startInIndex;

		int leftPreOrderEndIndex = startPreIndex + leftlen;

		if (leftlen > 0) {
			// 构建左子数
			root.leftnode = ConstructCore(preOrder, startPreIndex + 1,
				leftPreOrderEndIndex, inOrder, startInIndex,
					rootInIndex - 1);
		}

		if (leftlen < endPreIndex - startPreIndex) {
			root.rightnode = ConstructCore(preOrder, leftPreOrderEndIndex + 1, endPreIndex, inOrder, rootInIndex + 1,
					endInIndex);
		}

		return root;
	}

	public static void printPreOrder(BinaryTreeNode root) {
		if (root == null) {
			return;
		} else {
			System.out.print(root.val + " ");
		}

		if (root.leftnode != null) {
			printPreOrder(root.leftnode);
		}

		if (root.rightnode != null) {
			printPreOrder(root.rightnode);
		}
	}

	public static void main(String[] args) {
		int[] preOrder = new int[] { 1, 2, 4, 7, 3, 5, 6, 8 };
		int[] inOrder = new int[] { 4, 7, 2, 1, 5, 3, 8, 6 };
		printPreOrder(Construct(preOrder, inOrder, preOrder.length));
	}
}
