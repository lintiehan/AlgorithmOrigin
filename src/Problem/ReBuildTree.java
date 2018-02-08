package Problem;

//根据前序遍历与中序遍历还原二叉树
public class ReBuildTree {

	static class BinaryTreeNode {
		int mVal;
		BinaryTreeNode mLeft;
		BinaryTreeNode mRight;

		public BinaryTreeNode() {
			this(0);
		}

		public BinaryTreeNode(int val) {
			this.mVal = val;
			this.mLeft = null;
			this.mRight = null;
		}

	}

	/**
	 * 
	 * @param preOrder
	 *            前序遍历
	 * @param inOrder
	 *            中序遍历
	 * @param length
	 *            节点的数目
	 * @return
	 */
	public static BinaryTreeNode Construct(int[] preOrder, int[] inOrder, int length) {
		if (preOrder == null || inOrder == null || length <= 0) {
			return null;
		}
		try {
			return ConstructCore(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1);
		} catch (InvalidPutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param PreOrder
	 *            前序遍历
	 * @param startPreIndex
	 *            前序遍历的开始下标
	 * @param endPreIndex
	 *            前序遍历的结束下标
	 * @param InOrder
	 *            中序遍历
	 * @param startInIndex
	 *            中序遍历的开始下标
	 * @param endInIndex
	 *            中序遍历的结束下标
	 * @return 还原后的根节点
	 * @throws InvalidPutException
	 */
	public static BinaryTreeNode ConstructCore(int[] preOrder, int startPreIndex, int endPreIndex, int[] inOrder,
			int startInIndex, int endInIndex) throws InvalidPutException {

		int rootValue = preOrder[startPreIndex];
		BinaryTreeNode root = new BinaryTreeNode(rootValue);

		//只有一个节点即为根节点
		if (startPreIndex == endPreIndex) {
			if (startInIndex == endInIndex && preOrder[startPreIndex] == inOrder[startInIndex]) {
				System.out.println("only one element");
				return root;
			} else {
				throw new InvalidPutException();
			}
		}

		 
		int rootInIndex = startInIndex;
		//找到中序遍历的中心节点
		while (rootInIndex <= endInIndex && inOrder[rootInIndex] != rootValue) {
			++rootInIndex;
		}

		if (rootInIndex == endInIndex && inOrder[rootInIndex] != rootValue) {
			throw new InvalidPutException();

		}

		int leftLength = rootInIndex - startInIndex;

		int leftPreOrderEndIndex = startPreIndex + leftLength;

		if (leftLength > 0) {
	 
			root.mLeft = ConstructCore(preOrder, startPreIndex + 1, leftPreOrderEndIndex, inOrder, startInIndex,
					rootInIndex - 1);
		}

		if (leftLength < endPreIndex - startPreIndex) {
		 
			root.mRight = ConstructCore(preOrder, leftPreOrderEndIndex + 1, endPreIndex, inOrder, rootInIndex + 1,
					endInIndex);
		}
		return root;

	}

	static class InvalidPutException extends Exception {

		private static final long serialVersionUID = 1L;

	}

	public static void printPreOrder(BinaryTreeNode root) {
		if (root == null) {
			return;
		} else {
			System.out.print(root.mVal + " ");
		}

		if (root.mLeft != null) {
			printPreOrder(root.mLeft);
		}

		if (root.mRight != null) {
			printPreOrder(root.mRight);
		}
	}

	public static void main(String[] args) {

		int[] preOrder = new int[] { 1, 2, 4, 7, 3, 5, 6, 8 };
		int[] inOrder = new int[] { 4, 7, 2, 1, 5, 3, 8, 6 };

		printPreOrder(Construct(preOrder, inOrder, preOrder.length));

	}

}