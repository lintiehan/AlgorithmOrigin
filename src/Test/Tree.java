package Test;

import java.awt.Window;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import javax.security.auth.kerberos.KerberosKey;

public class Tree {
	private static final int PROCESSOR = 2;
	static LinkedList<TreeNode> SQ1 = new LinkedList<TreeNode>();
	static Stack<TreeNode> SQ = new Stack<TreeNode>();
	static TreeNode root;

	public Tree() {
		root = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		TreeNode node8 = new TreeNode(8);
		TreeNode node9 = new TreeNode(9);
		TreeNode node10 = new TreeNode(10);
		TreeNode node11 = new TreeNode(11);
		TreeNode node12 = new TreeNode(12);
		TreeNode node13 = new TreeNode(13);
		TreeNode node14 = new TreeNode(14);
		TreeNode node15 = new TreeNode(15);
		TreeNode node16 = new TreeNode(16);
		TreeNode node17 = new TreeNode(17);

		root.leftChild = node2;
		root.rightChild = node3;
		node2.parentChild = root;
		node3.parentChild = root;

		node2.leftChild = node4;
		node2.rightChild = node5;
		node4.parentChild = node2;
		node5.parentChild = node2;

		node3.leftChild = node6;
		node3.rightChild = node7;
		node6.parentChild = node3;
		node7.parentChild = node3;

		node4.leftChild = node8;
		node4.rightChild = node9;
		node8.parentChild = node4;
		node9.parentChild = node4;

		node9.leftChild = node14;
		node9.rightChild = node15;
		node14.parentChild = node9;
		node15.parentChild = node9;

		node6.leftChild = node10;
		node6.rightChild = node11;
		node10.parentChild = node6;
		node11.parentChild = node6;

		node7.leftChild = node12;
		node7.rightChild = node13;
		node12.parentChild = node7;
		node13.parentChild = node7;

		node12.leftChild = node16;
		node12.rightChild = node17;
		node16.parentChild = node12;
		node17.parentChild = node12;
	}

	public static void main (String[] args) {
		Tree tree = new Tree();
		printTree(tree.root);
		int maxheight = getSumNode(tree.root) / PROCESSOR;
		// int minheight = countHeight(avenodes);
		System.out.println("--->" + maxheight);
		if (maxheight > treeHeight(tree.root)) {
			dfs(tree.root, treeHeight(tree.root));
		} else {
			dfs(tree.root, maxheight);
		}

		/*
		 * Iterator<TreeNode> iterator = SQ.iterator(); while (iterator.hasNext()) {
		 * printTree(SQ.pop()); System.out.println("-----"); }
		 */

	}

	public static void main1(String[] args) {
		Tree tree = new Tree();
		printTree(tree.root);
		TreeNode temp = findTreeNode(tree.root, 4);
		System.out.println(temp.parentChild.key);
		printTree(temp);
		removeNode(tree.root, temp);
		printTree(tree.root);
		reverseNode(tree.root, temp);
		printTree(tree.root);
	}

	private static void removeNode(TreeNode node, TreeNode temp) {
		if (node != null) {
			if (node.leftChild == temp) {
				node.leftChild = null;
			} else if (node.rightChild == temp) {
				node.rightChild = null;
			}
			removeNode(node.leftChild, temp);
			removeNode(node.rightChild, temp);

		}

	}

	private static TreeNode findTreeNode(TreeNode p, int h) {

		TreeNode temp = null;
		TreeNode q = p;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while (p != null) {
			// 左子树入栈
			for (; p.leftChild != null; p = p.leftChild)
				stack.push(p);
			// 当前节点无右子或右子已经输出
			while (p != null && (p.rightChild == null || p.rightChild == q)) {
				if (treeHeight(p) == h)
					return p;
				q = p;// 记录上一个已输出节点
				if (stack.empty())
					break;
				p = stack.pop();
			}
			// 处理右子
			stack.push(p);
			p = p.rightChild;
		}
		return null;
	}

	public static TreeNode fromBottom(TreeNode node, int height) {

		if (node != null) {
			fromBottom(node.leftChild, height);
			fromBottom(node.rightChild, height);
			if (treeHeight(node) == height) {
				System.out.println("找到符合条件的节点--->" + node.key);
				if (node == root) {

					SQ.push(node);
					node = null;
				} else if (node.parentChild != null && node.parentChild.leftChild == node) {
					SQ.push(node);
					node.parentChild.leftChild = null;
				} else if (node.parentChild != null && node.parentChild.rightChild == node) {
					SQ.push(node);
					node.parentChild.rightChild = null;
				}

				return node;
			}

		}
		return node;
	}

	public static void dfs(TreeNode node, int h) {
		if (node == null) {
			System.out.println("空");
			return;
		}
		if (PROCESSOR == SQ.size() - 1) {
			System.out.println("分配如下：");
			Iterator<TreeNode> iterator = SQ.iterator();
			while (iterator.hasNext()) {
				System.out.println("----case：");
				printTree(SQ.pop());
			}
			return;
		}
		for (int i = h; i >= 2; i--) {

			if (SQ.size() > PROCESSOR - 1) {
				continue;
			}

			 if (SQ.size() <= PROCESSOR - 1) {
				System.out.println("----size:" + SQ.size() + "   i的值为：" + i);
				node = fromBottom(node, i);
				dfs(node, h);
				reverseNode(node, SQ.pop());
				fromBottom(node, i);

			} 
		
 
		}
	}

	private static void reverseNode(TreeNode node, TreeNode temp) {
		if (node != null) {
			if (temp.parentChild==node) { 
				if (node.leftChild == null) {
					node.leftChild = temp;
				} else if (node.rightChild == null){
					node.rightChild = temp;
				}

			}
			reverseNode(node.leftChild, temp);
			reverseNode(node.rightChild, temp);
		}else {
			node=temp;
		}

	}

	 
	public static void split(int height) {

	}

	public static int treeHeight(TreeNode subTree) {
		if (subTree == null) {
			return 0;// 递归结束 空树高度丿0
		} else {
			int i = treeHeight(subTree.leftChild);
			int j = treeHeight(subTree.rightChild);
			return (i < j) ? (j + 1) : (i + 1);
		}
	}

	public static int getSumNode(TreeNode node) {
		if (node == null) {
			return 0;
		} else {
			int a = getSumNode(node.leftChild);
			int b = getSumNode(node.rightChild);
			return 1 + a + b;
		}
	}

	public static void printTree(TreeNode root) {

		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		TreeNode node = null;
		TreeNode last = root;
		TreeNode nlast = root;
		if (root == null) {
			return;
		}
		while (!queue.isEmpty()) {
			node = queue.poll();
			temp.add(node.key);
			if (node.leftChild != null) {
				queue.offer(node.leftChild);
				nlast = node.leftChild;
			}

			if (node.rightChild != null) {
				queue.offer(node.rightChild);
				nlast = node.rightChild;
			}
			if (node == last) {
				res.add(temp);
				last = nlast;
				temp = new ArrayList<Integer>();
			}
		}
		int[][] result = new int[res.size()][];
		for (int i = 0; i < res.size(); i++) {
			result[i] = new int[res.get(i).size()];
			for (int j = 0; j < result[i].length; j++) {
				// result[i][j] = res.get(i).get(j);
				System.out.print(res.get(i).get(j) + " ");
			}
			System.out.println();
		}
	}

	public static void BFS(TreeNode node) {
		if (node == null) {
			return;
		}
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		TreeNode current = null;
		queue.offer(node);
		while (!queue.isEmpty()) {
			current = queue.poll();// 出队队头元素并访问
			System.out.print(current.key + " ");
			if (current.leftChild != null) {
				queue.offer(current.leftChild);
			}
			if (current.rightChild != null) {
				queue.offer(current.rightChild);
			}

		}
	}
}
