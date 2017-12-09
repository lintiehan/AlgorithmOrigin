package TrieTree1;
 
import java.util.HashSet;

public class Trie {
	public Node root=new Node(); 
 
	public void addTrieNode(String word, int id)
    {
		addTrieNode(root, word, id);
    }
	public void addTrieNode(Node root,String word,int id)
	{
		if(word.length()==0)
		{
			return;
		}
		//求字符地址，方便将该字符放入到26叉中
		int k=word.charAt(0)-'a';		
		
		if( root.childNodes[k]==null)
		{
			root.childNodes[k]=new Node();
			//记录字符
			root.childNodes[k].content=word.charAt(0);
		}
		//该id途径的节点
		root.childNodes[k].hashSet.add(id);
		String nextWord=word.substring(1);
		//说明是最后一个字符，统计该词出现的次数
		if(nextWord.length()==0)
		{
			root.childNodes[k].count++;
		}
		
		addTrieNode(root.childNodes[k],nextWord,id);
	}
	 
	
	
	
	public HashSet<Integer> searchTrieNode(String word) {
		HashSet<Integer>hs=new HashSet<Integer>();
		return searchTrieNode(root,word,hs);
	}
	HashSet<Integer> searchTrieNode(Node root,String word,HashSet<Integer>hashSet) {
 
		if(word.length()==0)
		{
			return hashSet;
		}
		int k=word.charAt(0)-'a';
		String nextWord=word.substring(1);
		if(nextWord.length()==0)
		{
			//采用动态规划的思想，word最后节点记录下这途径的id
			hashSet=root.childNodes[k].hashSet;
		}
		searchTrieNode(root.childNodes[k],nextWord, hashSet);
		return hashSet;
		
	}
	public int wordCount(String word)
	{
		int count=0;
		wordCount(root,word,count);
		return count;
	}
	public void wordCount(Node root,String word,int count)
	{
		if(word.length()==0)
			return  ;
		int k=word.charAt(0)-'a'; 
		String nextWord=word.substring(1);
		if(nextWord.length()==0)
		{
			count=root.childNodes[k].count;
		}
		wordCount(root.childNodes[k],nextWord,count);
	 
	}
	public void deleteTrieNode(String word,int id)
	{
		deleteTrieNode(root, word, id);
	}
	public void deleteTrieNode(Node root,String word,int id)
	{
		 if(word.length()==0)
			 return;
		 
		 int k=word.charAt(0)-'a';
		 
		 if(root.childNodes[k]==null)
		 {
			 return;
		 }
		 String nextWord=word.substring(1);
		 
		 if(word.length()==0&&root.childNodes[k].count>0)
		 {
			 root.childNodes[k].count--;
		 }
		 
		 root.childNodes[k].hashSet.remove(id);
		 deleteTrieNode(root.childNodes[k], nextWord,id);
	}
	 
 
}

