package TrieTree;

import java.awt.List;
import java.util.HashSet;
import java.util.LinkedList;

public class Node {
	char content;
	boolean isEnd;
	int count;
	LinkedList<Node>childList;
 
	public Node(char c)
	{
		childList=new LinkedList<>();
		isEnd=false;
		content=c;
		count=0;
		
	}
	
	public Node subNode(char c)
	{
		if(childList!=null)
		{
			for(Node eachChild:childList)
			{
				if(eachChild.content==c)
				{
					return eachChild;
				}
			}
		}
		return null;
	}
}
