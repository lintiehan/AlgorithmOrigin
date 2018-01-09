package BalanceTree.copy;

public class TNode{
 
	int key;
	String label;
	TNode leftChild=null;
	TNode centerChild=null;
	TNode rightChild=null;
	 
	public TNode() {}
	
	public TNode(int key )
	{
		this.leftChild=null;
		this.centerChild=null;
		this.rightChild=null;
	}
}
