package TrieTree1;
 
import java.util.HashSet; 

public class Node {
	char content;
	boolean isEnd;
	int count;
	Node[]childNodes;
	//插入记录时的编号id
	public HashSet<Integer> hashSet=new HashSet<Integer>();
	
	public Node()
	{
		childNodes=new Node[26];
		
		count=0;
		
	}
	 
}
