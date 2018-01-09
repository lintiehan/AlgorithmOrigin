package BalanceTree;

public class BinaryTree {
	private TreeNode root = null;

	public BinaryTree() {
		root = new TreeNode(1, "A");
	}

	/*
	 * ����һ�ö����� A B C D E F
	 */
	public void createBTree(TreeNode root) {
		TreeNode newNodeB = new TreeNode(2, "B");
		TreeNode newNodeC = new TreeNode(3, "C");
		TreeNode newNodeD = new TreeNode(4, "D");
		TreeNode newNodeE = new TreeNode(5, "E");
		TreeNode newNodeCl = new TreeNode(6, "E");
		TreeNode newNodeCr = new TreeNode(7, "E");
		root.leftChild = newNodeB;
		root.rightChild = newNodeC;
		root.leftChild.leftChild = newNodeD;
		root.leftChild.rightChild = newNodeE;
		root.rightChild.leftChild=newNodeCl; 
		
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
		System.out.println("key:" + subTree.key + "--name:" + subTree.label);
		traverse(subTree.leftChild);
		traverse(subTree.rightChild);
	}

	public void visted(TreeNode subTree) {
		subTree.isVisted = true;
		System.out.println("key:" + subTree.key + "--name:" + subTree.label);
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

	public void DFS(TreeNode node) {
		if (node != null) {
			DFS(node.leftChild);
			DFS(node.rightChild);
			if (node!=null) {	 
				TreeNode tnodeparent = parent(node);								 
				if(node.leftChild!=null&node.rightChild!=null&node!=root) {
					System.out.println("-->"+node.key);
					TreeNode temp=new TreeNode();
					temp.leftChild = node.leftChild;
					temp.centerChild = node;
					temp.rightChild =node.rightChild;
					temp.key = 999;
					temp.label="TNodeN";
					if(tnodeparent.leftChild.equals(node))
					{
						tnodeparent.leftChild=temp;
					}
					else
					{
						tnodeparent.rightChild=temp;
					}
					 			
				}else if (node.leftChild!=null&node.rightChild!=null&node==root) {
					System.out.println("-->"+node.key);
					TreeNode temp=new TreeNode();
					temp.key = 555;
					temp.leftChild = node.leftChild;
					temp.centerChild = node;
					temp.rightChild =node.rightChild;
					temp.label="ROOT";
					root=temp;
					System.out.println("@��@ "+temp.leftChild.key+temp.centerChild.key+temp.rightChild.key);
				}
			}			 
	}
		 
	}
 
	public void printAllChild(TreeNode n)
	{
		if(n!=null)
		{
			System.out.println("Label:-"+n.label +"-----���ڵ�ֵ:"+n.key);
			if(n.leftChild!=null)
			{
				System.out.print("  leftChild:"+n.leftChild.key);
			}
			if(n.centerChild!=null)
			{
				System.out.print("  centerchild:"+n.centerChild.key);
			}  
			if(n.rightChild!=null)
			{
				System.out.print("  rightChild:"+n.rightChild.key);
			}   
		}
		System.out.println();		 
	}
	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree();
		bt.createBTree(bt.root);
		// System.out.println("the size of the tree is "+bt.size());
		// System.out.println("the height of the tree is " + bt.height());

		 bt.DFS(bt.root);
	//	bt.print(t, t.key, 0);
		bt.printAllChild(bt.root);
		bt.printAllChild(bt.root.leftChild);
		bt.printAllChild(bt.root.rightChild);
		  
		
		 
	}
}
