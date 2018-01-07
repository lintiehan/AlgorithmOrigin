package TrieTree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Test {
	public static void main(String args[]) // Just used for test
	{
		Trie  trie = new  Trie();
		trie.inserNode("I");
		trie.inserNode("Love");
		trie.inserNode("China");
		trie.inserNode("China");
		trie.inserNode("China");
		trie.inserNode("China");
		trie.inserNode("China");
		trie.inserNode("xiaoliang");
		trie.inserNode("xiaoliang");
		trie.inserNode("man");
		trie.inserNode("handsome");
		trie.inserNode("love");
		trie.inserNode("chinaha");
		trie.inserNode("her");
		trie.inserNode("know");

		HashMap<String, Integer> map = trie.getAllWords();

		for (String key : map.keySet()) {
			System.out.println(key + " 出现: " + map.get(key) + "次");
		}

		map = trie.getWordsForPrefix("chin");

		System.out.println("\n\n包含chin（包括本身）前缀的单词及出现次数：");
		for (String key : map.keySet()) {
			System.out.println(key + " 出现: " + map.get(key) + "次");
		}

		if (trie.isExist("xiaoming") == false) {
			System.out.println("\n\n字典树中不存在：xiaoming ");
		}

	}
}
