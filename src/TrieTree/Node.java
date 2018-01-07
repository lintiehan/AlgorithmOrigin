package TrieTree;
 
import java.util.HashSet; 

public class Node {
	public int count;//出现的次数
	 public int prefix_num;//以该字符串为前缀的字串数
	 public Node childs[];
	 public boolean isLeaf;
	 public Node()
	 {
		 count=0;
		 prefix_num=0;
		 isLeaf=false;
		 childs=new Node[26];
	 }
}
