package BalanceTree;

public class TreeNode {
	int key=0;
	String label=null;
	boolean isVisted=false;
	TreeNode leftChild=null;
	TreeNode rightChild=null;
	TreeNode centerChild=null;
	public TreeNode() {}
	
	public TreeNode(int key,String date)
	{
		this.key=key;
		this.label=date;
		this.leftChild=null;
		this.rightChild=null;
		this.centerChild=null;
	}
}
