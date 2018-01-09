package TrieTree;

import java.awt.MenuComponent;
import java.util.HashMap;
import java.util.HashSet;

public class Trie {
	private Node root;

	public Trie() {
		root = new Node();
	}
	  /** 
     * 插入字串，用循环代替迭代实现 
     * @param root 
     * @param words 
     */  
   
	public void inserNode(String word) {
		inserNode(this.root, word);
	}

	private void inserNode(Node root, String word) {
		word = word.toLowerCase();
		char wd[] = word.toCharArray();
		
		for (int i = 0; i < wd.length; i++) {
			int index = wd[i] - 'a';// 下标
			
			if (root.childs[index] != null) {
				// 已经存在，该子节点次数+1
				root.childs[index].prefix_num++;
			} else {
				root.childs[index] = new Node();
				root.childs[index].prefix_num++;
			}

			// 如果到了字符串最后一个字符，标记结束
			if (i == wd.length - 1) {
				root.childs[index].isLeaf = true;
				root.childs[index].count++;
			}
			root = root.childs[index];
		}
		
	}
	 /** 
     * 遍历Trie树，查找所有的words以及出现次数 
     * @return HashMap<String, Integer> map 
     */  
	public HashMap<String, Integer>getAllWords()
	{
		return preTraversal(this.root,"");
	}

	private HashMap<String, Integer> preTraversal(Node root, String prefixs) {
		HashMap<String, Integer>map=new HashMap<String,Integer>();
		if(root!=null)
		{
			if(root.isLeaf==true)
			{
				//当前为一个单词
				map.put(prefixs, root.count);
			}
			for(int i=0;i<root.childs.length;i++)
			{
				if(root.childs[i]!=null)
				{
					char temp=(char) (i+'a');
					//递归调用前序遍历
					String tempStr=prefixs+temp;
					map.putAll(preTraversal(root.childs[i], tempStr)); 
				}
			}
		}
		return map;
	}
	/** 
     * 查询某字串是否在字典树中 
     * @param word 
     * @return true if exists ,otherwise  false  
     */  
	boolean isExist(String word)
	{
		return searchWord(this.root,word);
	}

	private boolean searchWord(Node root, String word) {
		char [] chs=word.toLowerCase().toCharArray();
		for(int i=0;i<chs.length;i++)
		{
			int index=chs[i]-'a';
			if(root.childs[index]==null)
			{
				return false;
			}
			root=root.childs[index];
		}
		// TODO Auto-generated method stub
		return true;
	}
	
	 /** 
     * 得到以某字串为前缀的字串集，包括字串本身！ 类似单词输入法的联想功能 
     * @param prefix 字串前缀 
     * @return 字串集以及出现次数，如果不存在则返回null 
     */  
	public HashMap<String, Integer>getWordsForPrefix(String prefix)
	{
		return getWordsForPrefix(this.root,prefix);
	}
	private HashMap<String, Integer> getWordsForPrefix(Node root, String prefix) {
		HashMap<String, Integer> map=new HashMap<String ,Integer>();
		char[] chs=prefix.toLowerCase().toCharArray();
		
		for(int i=0;i<chs.length;i++)
		{
			int index=chs[i]-'a';
			if(root.childs[index]==null)
			{
				return null;
			}
			root=root.childs[index];
		}
		return preTraversal(root, prefix);
	}
}
