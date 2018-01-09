package BalanceTree.copy;

public class BinaryTree {
	private TreeNode root = null;

	public BinaryTree() {
		root = new TreeNode(1, "rootNode(A)");
	}

	/*
	 * 创建一棵二叉树 A B C D E F
	 */
	public void createBTree(TreeNode root) {
		TreeNode newNodeB = new TreeNode(2, "B");
		TreeNode newNodeC = new TreeNode(3, "C");
		TreeNode newNodeD = new TreeNode(4, "D");
		TreeNode newNodeE = new TreeNode(5, "E");
		root.leftChild = newNodeB;
		root.rightChild = newNodeC;
		root.leftChild.leftChild = newNodeD;
		root.leftChild.rightChild = newNodeE;
	}

	public boolean isEmpty() {
		return root == null;

	}

	// 树的高度
	public int height() {
		return height(root);
	}

	public int height(TreeNode subTree) {
		if (subTree == null) {
			return 0;// 递归结束 空树高度为0
		} else {
			int i = height(subTree.leftChild);
			int j = height(subTree.rightChild);
			return (i < j) ? (j + 1) : (i + 1);
		}
	}

	// 节点个数
	public int size() {
		return size(root);
	}

	// 返回節點樹
	private int size(TreeNode subTree) {
		if (subTree == null) {
			return 0;
		} else {
			return 1 + size(subTree.leftChild) + size(subTree.rightChild);
		}
	}

	// 返回双亲结点
	public TreeNode parent(TreeNode element) {
		return (root == null || root == element) ? null : parent(root, element);
	}

	public TreeNode parent(TreeNode subTree, TreeNode element) {
		if (subTree == null) {
			return null;
		}
		if (subTree.leftChild == element || subTree.rightChild == element) {
			// 返回父节点地址
			return subTree;
		}
		TreeNode p;
		// 现在左子树中找，如果左子树中没有找到，猜到右子树去找
		if ((p = parent(subTree.leftChild, element)) != null) {
			// 递归在左子树中搜索
			return p;
		} else {
			// 递归在右子树中搜索
			return parent(subTree.rightChild, element);
		}
	}

	public TreeNode getLeftChildNode(TreeNode element) {
		return (element != null) ? element.leftChild : null;
	}

	public TreeNode getRightChildNode(TreeNode element) {
		return (element != null) ? element.rightChild : null;
	}

	public TreeNode getRoot() {
		return root;
	}

	// 在释放某个结点时，该结点的左右子树都已经释放，
	// 故采取后续遍历，当访问某个结点时将该结点的存储空间释放
	public void destory(TreeNode subTree) {
		// 删除跟为subTree的子树
		if (subTree != null) {
			// 删除左子树
			destory(subTree.leftChild);
			// 删除右子树
			destory(subTree.rightChild);
			// 删除根节点
			subTree = null;
		}
	}

	public void traverse(TreeNode subTree) {
		System.out.println("key:" + subTree.key + "--name:" + subTree.data);
		traverse(subTree.leftChild);
		traverse(subTree.rightChild);
	}

	public void visted(TreeNode subTree) {
		subTree.isVisted = true;
		System.out.println("key:" + subTree.key + "--name:" + subTree.data);
		;
	}

	// 前序遍历
	public void preOrder(TreeNode subTree) {
		if (subTree != null) {
			visted(subTree);
			preOrder(subTree.leftChild);
			preOrder(subTree.rightChild);
		}
	}

	// 中序遍历
	public void inOrder(TreeNode subTree) {
		if (subTree != null) {
			inOrder(subTree.leftChild);
			visted(subTree);
			inOrder(subTree.rightChild);
		}
	}

	// 后序遍历
	public void postOrder(TreeNode subTree) {
		if (subTree != null) {
			postOrder(subTree.leftChild);
			postOrder(subTree.rightChild);
			visted(subTree);
		}
	}


	/*
	 * 打印"二叉查找树"
	 *
	 * key -- 节点的键值 direction -- 0，表示该节点是根节点; -1，表示该节点是它的父结点的左孩子; 1，表示该节点是它的父结点的右孩子。
	 */
	private void print(TNode tree, int key, int direction) {
		if (tree != null) {
			if (direction == 0) // tree是根节点
				System.out.printf("%2d is root\n", tree.key, key);
			else // tree是分支节点
				System.out.printf("%2d is %2d's %6s child\n", tree.key, key, direction == 1 ? "right" : "left");
			 

			print(tree.leftChild, tree.key, -1);
			print(tree.rightChild, tree.key, 1);
		}
	}

	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree();
		bt.createBTree(bt.root);
		bt.print(bt.root, bt.root.key, 0);
		System.out.println("the size of the tree is "+bt.size());
		System.out.println("the height of the tree is " + bt.height());
 
	}
}
