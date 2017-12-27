package TrieTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.naming.directory.SearchControls;
 

public class Trie {
	private Node root;
	public Trie()
	{
		root=new Node(' ');
	}
	
	
	public void insert(String word)
	{
		if(search(word)==true)
			return;
		Node current=root;
		for(int i=0;i<word.length();i++)
		{
			Node child=current.subNode(word.charAt(i));
			if(child!=null)
			{
				current=child;
			}else {
				current.childList.add(new Node(word.charAt(i)));
				current=current.subNode(word.charAt(i));
			}
			current.count++;
		}
		// Set isEnd to indicate end of the word
        current.isEnd = true;
	}

	//在树中查找 如果已存在则返回true
	private boolean search(String word) {
		Node current=root;
		
		for(int i=0;i<word.length();i++)
		{
			if(current.subNode(word.charAt(i))==null)
				return false;
			else
				current=current.subNode(word.charAt(i));
		}
		   /*
	        * This means that a string exists, but make sure its
	        * a word by checking its 'isEnd' flag
	        */
		if(current.isEnd==true)
			return true;
		else
			return false;
	}

	public void deleteWord(String word)
	{
		if(search(word)==false)
			return;
		
		Node current=root;
		for(char c:word.toCharArray())
		{
			Node child=current.subNode(c);
			if(child.count==1)
			{
				current.childList.remove(child);
				return;
			}else {
				child.count--;
				current=child;
			}
		}
		current.isEnd=false;
	}
	public static void print(Node root) {
		ArrayList<ArrayList<Character>> res = new ArrayList<ArrayList<Character>>();
		ArrayList<Character> temp = new ArrayList<Character>();
		LinkedList<Node> queue = new LinkedList<Node>();

		queue.offer(root);
		Node node = null;	 
		Node tem=null;
		while (!queue.isEmpty()) {
			node = queue.poll(); 
			for (Node node2 : node.childList) {
				System.out.print(node2.content+" ");				
				if(node2.childList!=null)
				{						
					queue.offer(node2); 
				}
			}
			 
			 
			
		}
	}
	 public static void main(String[] args) {
	        Trie trie = new Trie();
	        trie.insert("ball");
	        trie.insert("balls");
	        trie.insert("sense");
	        trie.print(trie.root);
	        System.out.println();
	        // testing deletion
	        System.out.println(trie.search("balls"));
	        System.out.println(trie.search("ba"));
	        trie.deleteWord("balls");
	        System.out.println(trie.search("balls"));
	        System.out.println(trie.search("ball"));
	    }
 
}

