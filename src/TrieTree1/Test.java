package TrieTree1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

public class Test {
	 public static void main(String[] args) throws IOException {
		 //	System.out.println(Character.getNumericValue(49));
	        Trie trie = new Trie();
	        FileReader fr=new FileReader(new File("1.txt"));
	        
	        BufferedReader br=new BufferedReader(fr);
	        String line="";
	        String[]arrs=null;
	        while((line=br.readLine())!=null)
	        {
	        	arrs=line.split(" ");
	        	trie.addTrieNode(arrs[1].toLowerCase(),Integer.valueOf(arrs[0])); 
	        }
	        br.close();
	        fr.close();
	        
	        HashSet<Integer> hashSet=trie.searchTrieNode("go");
	        Iterator<Integer>iterator=hashSet.iterator();
	       while(iterator.hasNext())
	       {
	    	   System.out.println("-->"+iterator.next());
	       }
	        System.out.println("出现次数为："+trie.wordCount( "go")); 
	 
	 
	 }
}
