package Test;

public class mk {
	public static void main(String[] args) throws CloneNotSupportedException {
		Tree tree=new Tree();
		TreeNode [] temp=Tree.DistributeServer(tree.root, 5);
		Tree.printSelectedNodes(temp);
	}
}
