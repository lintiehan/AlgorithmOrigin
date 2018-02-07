package Test_new2;

public class Alg3 {

	// C->L
	public static TreeNode singlerotationCenterToLeft(TreeNode root, TreeNode tnode) throws CloneNotSupportedException {
		
		TreeNode newnode = new TreeNode();
		TreeNode parent = BinaryTree.parent(root, tnode);
		if(parent!=null&&parent.leftChild!=null&&parent.centerChild!=null&&parent.rightChild!=null)
		{
		TreeNode temp = new TreeNode();
		newnode.centerChild =  root.centerChild.centerChild;
		newnode.leftChild = temp;
		newnode.rightChild =  root.centerChild.rightChild;
		temp.leftChild = root.leftChild;
		temp.centerChild = root.centerChild.leftChild;
		temp.rightChild = root.rightChild;
		temp.label = BinaryTree.getLabel(temp);
		temp.key = BinaryTree.getKey(temp.label);

		newnode.label = BinaryTree.getLabel(root);
		newnode.key = BinaryTree.getKey(root.label);
 
		if (parent.leftChild == tnode) {
			parent.leftChild = newnode;
		} else if (parent.centerChild == tnode) {
			parent.centerChild = newnode;
		} else if (parent.rightChild == tnode) {
			parent.rightChild = newnode;
		}
		}
		return tnode;
	}

	// C->R
	public static TreeNode singlerotationCenterToRight(TreeNode root, TreeNode tnode)
			throws CloneNotSupportedException {
	 
		
		TreeNode newnode = new TreeNode();
		TreeNode parent = BinaryTree.parent(root, tnode);
		if(parent!=null&&parent.leftChild!=null&&parent.centerChild!=null&&parent.rightChild!=null)
		{
			if (parent.leftChild == tnode) {
				parent.leftChild = newnode;
			} else if (parent.centerChild == tnode) {
				parent.centerChild = newnode;
			} else if (parent.rightChild == tnode) {
				parent.rightChild = newnode;
			}

			TreeNode temp = new TreeNode();
			TreeNode copy = (TreeNode) root.centerChild.clone();

			newnode.centerChild = copy.centerChild;
			newnode.rightChild = temp;
			newnode.leftChild = copy.leftChild;

			temp.leftChild = root.leftChild;
			temp.centerChild = root.centerChild.rightChild;
			temp.rightChild = root.rightChild;
			temp.label = BinaryTree.getLabel(temp);
			temp.key = BinaryTree.getKey(temp.label);

			newnode.label = BinaryTree.getLabel(root);
			newnode.key = BinaryTree.getKey(root.label);
	 
		}
		return newnode;
		
		 
	}

	// L->C
	public static TreeNode singlerotationLeftToCenter(TreeNode root, TreeNode tnode) throws CloneNotSupportedException {
 
		TreeNode newnode = new TreeNode();
		TreeNode parent = BinaryTree.parent(root, tnode);
		if(parent!=null&&parent.leftChild!=null&&parent.centerChild!=null&&parent.rightChild!=null)
		{
			if (parent.leftChild == tnode) {
				parent.leftChild = newnode;
			} else if (parent.centerChild == tnode) {
				parent.centerChild = newnode;
			} else if (parent.rightChild == tnode) {
				parent.rightChild = newnode;
			}
			
		 
			TreeNode temp = new TreeNode();	 
			newnode.centerChild = temp;
			newnode.leftChild = tnode.leftChild.leftChild;
			newnode.rightChild = tnode.leftChild.rightChild;
			
			temp.leftChild = tnode.leftChild.centerChild;
			temp.centerChild = tnode.centerChild;
			temp.rightChild = tnode.rightChild;

			temp.label = BinaryTree.getLabel(temp);
			temp.key = BinaryTree.getKey(temp.label);

			newnode.label = BinaryTree.getLabel(newnode);
			newnode.key = BinaryTree.getKey(newnode.label);
	 
		}
		return newnode;
	}

	// L->C C->L
	public static TreeNode doublerotationFromLeft(TreeNode root, TreeNode subtree) throws CloneNotSupportedException {
		subtree.leftChild = singlerotationLeftToCenter(root, subtree);
		return singlerotationCenterToLeft(root, subtree);

	}

	// R->C
	public static TreeNode singlerotationRightToCenter(TreeNode root, TreeNode tnode)
			throws CloneNotSupportedException {
		/*
		 * System.out.println("========"); BinaryTree.printAllChild(tnode);
		 * BinaryTree.printAllChild(tnode.leftChild);
		 * 
		 * BinaryTree.printAllChild(tnode.leftChild.leftChild );
		 * 
		 * System.out.println("========");
		 */
		TreeNode parent = BinaryTree.parent(root, tnode);
		TreeNode newnode = new TreeNode();
		if(parent!=null&&parent.leftChild!=null&&parent.centerChild!=null&&parent.rightChild!=null)
		{
			if (parent.leftChild == tnode) {
				parent.leftChild = newnode;
			} else if (parent.centerChild == tnode) {
				parent.centerChild = newnode;
			} else if (parent.rightChild == tnode) {
				parent.rightChild = newnode;
			}
			TreeNode temp = new TreeNode();
			TreeNode copy = (TreeNode) tnode.clone();
			newnode.leftChild = copy.rightChild.leftChild;
			newnode.rightChild = copy.rightChild.rightChild;
			newnode.centerChild = temp;
			temp.leftChild = tnode.leftChild;
			temp.centerChild = tnode.centerChild;
			temp.rightChild = tnode.rightChild.centerChild;

			temp.label = BinaryTree.getLabel(temp);
			temp.key = BinaryTree.getKey(temp.label);

			newnode.label = BinaryTree.getLabel(tnode);
			newnode.key = BinaryTree.getKey(tnode.label); 			 
		}	
		return tnode;
	}

	// R->C C->R
	public static TreeNode doublerotationFromRight(TreeNode root, TreeNode subtree) throws CloneNotSupportedException {
		subtree.rightChild = singlerotationRightToCenter(root, subtree.rightChild);
		return singlerotationCenterToLeft(root, subtree);

	}
 
	public static void AVL(TreeNode root, TreeNode subtree) throws CloneNotSupportedException {
		TreeNode temp=new TreeNode();
		// C to L
		if (BinaryTree.theight(subtree.centerChild) > 1
				+ Math.max(BinaryTree.theight(subtree.leftChild), BinaryTree.theight(subtree.rightChild))
				&&(BinaryTree.theight(subtree.leftChild)>BinaryTree.theight(subtree.rightChild))) {
			System.out.println("C to L");
			singlerotationCenterToLeft(root, subtree);
		}
		// C to R
		else if (BinaryTree.theight(subtree.centerChild) > 1
				+ Math.max(BinaryTree.theight(subtree.leftChild), BinaryTree.theight(subtree.rightChild))
				&&(BinaryTree.theight(subtree.leftChild)<BinaryTree.theight(subtree.rightChild))) {
			System.out.println("C to R");
			singlerotationCenterToRight(root, subtree);
		}// R to C
		else if ((BinaryTree.theight(subtree.rightChild) > 1
				+ Math.max(BinaryTree.theight(subtree.centerChild), BinaryTree.theight(subtree.rightChild)))
				&& (BinaryTree.theight(subtree.rightChild.centerChild) < Math.max(
						BinaryTree.theight(subtree.rightChild.leftChild),
						BinaryTree.theight(subtree.rightChild.rightChild))
						|| BinaryTree.theight(subtree.rightChild.centerChild) == Math.max(
								BinaryTree.theight(subtree.rightChild.leftChild),
								BinaryTree.theight(subtree.rightChild.rightChild)))
				&& subtree.label != "TNodeL") {
			System.out.println("R to C");
		 
	singlerotationRightToCenter(root, subtree);
		}
		// L to C
		else if ((BinaryTree.theight(subtree.leftChild) > 1
				+ Math.max(BinaryTree.theight(subtree.centerChild), BinaryTree.theight(subtree.rightChild)))
				&& (BinaryTree.theight(subtree.leftChild.centerChild) < Math.max(
						BinaryTree.theight(subtree.leftChild.leftChild),
						BinaryTree.theight(subtree.leftChild.rightChild))
						|| BinaryTree.theight(subtree.leftChild.centerChild) == Math.max(
								BinaryTree.theight(subtree.leftChild.leftChild),
								BinaryTree.theight(subtree.leftChild.rightChild)))

				&& subtree.label != "TNodeR") {
			System.out.println("L to C");
			singlerotationLeftToCenter(root, subtree);
		}
		
		// L->C C->L
		else if ((BinaryTree.theight(subtree.leftChild) > 1
				+ Math.max(BinaryTree.theight(subtree.centerChild), BinaryTree.theight(subtree.rightChild)))
				&& (BinaryTree.theight(subtree.leftChild.centerChild) > Math.max(
						BinaryTree.theight(subtree.leftChild.leftChild),
						BinaryTree.theight(subtree.leftChild.rightChild)))

				&& subtree.label != "TNodeR") {
			System.out.println("L->C C->L");

			doublerotationFromLeft(root, subtree);
		}
		// L->R R->L
		else if ((BinaryTree.theight(subtree.rightChild) > 1
				+ Math.max(BinaryTree.theight(subtree.centerChild), BinaryTree.theight(subtree.rightChild)))
				&& (BinaryTree.theight(subtree.rightChild.centerChild) > Math.max(
						BinaryTree.theight(subtree.rightChild.leftChild),
						BinaryTree.theight(subtree.rightChild.rightChild)))

				&& subtree.label != "TNodeL") {

			System.out.println("L->R R->L");
			doublerotationFromRight(root, subtree);
		}   
	}

	 
	public void preOrder(TreeNode root, TreeNode subTree) throws CloneNotSupportedException {
	 
		if (subTree != null) {
		 
			AVL(root, subTree);
			preOrder(root, subTree.leftChild);
			preOrder(root, subTree.centerChild);
			preOrder(root, subTree.rightChild);
		}
	}

}
