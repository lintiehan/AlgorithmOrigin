package BalanceTree.copy;

public class BinaryTree {
	private TreeNode root = null;

	public BinaryTree() {
		root = new TreeNode(1, "rootNode(A)");
	}

	/*
	 * ����һ�ö����� A B C D E F
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

	// ���ĸ߶�
	public int height() {
		return height(root);
	}

	public int height(TreeNode subTree) {
		if (subTree == null) {
			return 0;// �ݹ���� �����߶�Ϊ0
		} else {
			int i = height(subTree.leftChild);
			int j = height(subTree.rightChild);
			return (i < j) ? (j + 1) : (i + 1);
		}
	}

	// �ڵ����
	public int size() {
		return size(root);
	}

	// ���ع��c��
	private int size(TreeNode subTree) {
		if (subTree == null) {
			return 0;
		} else {
			return 1 + size(subTree.leftChild) + size(subTree.rightChild);
		}
	}

	// ����˫�׽��
	public TreeNode parent(TreeNode element) {
		return (root == null || root == element) ? null : parent(root, element);
	}

	public TreeNode parent(TreeNode subTree, TreeNode element) {
		if (subTree == null) {
			return null;
		}
		if (subTree.leftChild == element || subTree.rightChild == element) {
			// ���ظ��ڵ��ַ
			return subTree;
		}
		TreeNode p;
		// �������������ң������������û���ҵ����µ�������ȥ��
		if ((p = parent(subTree.leftChild, element)) != null) {
			// �ݹ���������������
			return p;
		} else {
			// �ݹ���������������
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

	// ���ͷ�ĳ�����ʱ���ý��������������Ѿ��ͷţ�
	// �ʲ�ȡ����������������ĳ�����ʱ���ý��Ĵ洢�ռ��ͷ�
	public void destory(TreeNode subTree) {
		// ɾ����ΪsubTree������
		if (subTree != null) {
			// ɾ��������
			destory(subTree.leftChild);
			// ɾ��������
			destory(subTree.rightChild);
			// ɾ�����ڵ�
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

	// ǰ�����
	public void preOrder(TreeNode subTree) {
		if (subTree != null) {
			visted(subTree);
			preOrder(subTree.leftChild);
			preOrder(subTree.rightChild);
		}
	}

	// �������
	public void inOrder(TreeNode subTree) {
		if (subTree != null) {
			inOrder(subTree.leftChild);
			visted(subTree);
			inOrder(subTree.rightChild);
		}
	}

	// �������
	public void postOrder(TreeNode subTree) {
		if (subTree != null) {
			postOrder(subTree.leftChild);
			postOrder(subTree.rightChild);
			visted(subTree);
		}
	}


	/*
	 * ��ӡ"���������"
	 *
	 * key -- �ڵ�ļ�ֵ direction -- 0����ʾ�ýڵ��Ǹ��ڵ�; -1����ʾ�ýڵ������ĸ���������; 1����ʾ�ýڵ������ĸ������Һ��ӡ�
	 */
	private void print(TNode tree, int key, int direction) {
		if (tree != null) {
			if (direction == 0) // tree�Ǹ��ڵ�
				System.out.printf("%2d is root\n", tree.key, key);
			else // tree�Ƿ�֧�ڵ�
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
