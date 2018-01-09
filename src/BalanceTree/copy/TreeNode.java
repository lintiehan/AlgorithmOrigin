package BalanceTree.copy;

public class TreeNode extends TNode {
	int key=0;
	String data=null;
	boolean isVisted=false;
	TreeNode leftChild=null;
	TreeNode rightChild=null;
	TreeNode centerChild=null;
	public TreeNode() {}
	
	public TreeNode(int key,String date)
	{
		this.key=key;
		this.data=date;
		this.leftChild=null;
		this.rightChild=null;
		this.centerChild=null;
	}
}
