package Test;

import java.awt.Window;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import javax.security.auth.kerberos.KerberosKey;

public class Tree {
	private static final int PROCESSOR = 4;
	public static TreeNode[] result = new TreeNode[PROCESSOR];
	static double standarDif = 100000;
	static int NUM = 0;
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

	public static void main12(String[] args) throws CloneNotSupportedException {
		Tree tree = new Tree();
		printTree(tree.root);
		TreeNode dNode = (TreeNode) tree.root.clone();
		dNode.leftChild = null;
		printTree(dNode);
		printTree(tree.root);
	}

	public static void main (String[] args) throws CloneNotSupportedException {
		Tree tree = new Tree();
		printTree(tree.root);
		NUM = getSumNode(tree.root);

		int maxheight = getSumNode(tree.root) / PROCESSOR;
		System.out.println("--->" + maxheight);
		if (maxheight > treeHeight(tree.root)) {
			dfs(tree.root, treeHeight(tree.root));
		} else {
			dfs(tree.root, maxheight);
		}

		Iterator<TreeNode> iterator = SQ.iterator();
		while (iterator.hasNext()) {
			printTree(SQ.pop());
			System.out.println("-----");
		}
/*
		System.out.println("========");
		printTree(result[0]);
		System.out.println("----");
		printTree(result[1]);
		System.out.println("----");
		printTree(result[2]);
		System.out.println("----");
		printTree(result[3]);*/

		// printTree(result[3]);
	}

	public static void dfs(TreeNode node, int h) throws CloneNotSupportedException {
		System.out.println("进入dfs");
		if (SQ.size() > PROCESSOR || treeHeight(node) == 1) {
			System.out.println("条件不予许");
			return;
		}

		if (PROCESSOR == SQ.size() && checkNums(SQ) == true) {
			System.out.println("完成匹配");
			TreeNode node1 = SQ.pop();
			TreeNode node2 = SQ.pop();
			TreeNode node3 = SQ.pop();
			TreeNode node4 = SQ.pop();
			/*
			 * TreeNode node5 = SQ.pop(); TreeNode node6 = SQ.pop(); TreeNode node7 =
			 * SQ.pop(); TreeNode node8 = SQ.pop();
			 */
			int tempheight = treeHeight(node1) + treeHeight(node2) + treeHeight(node3) + treeHeight(node4);
			int nums[]=new int[] {treeHeight(node1),treeHeight(node2),treeHeight(node3),treeHeight(node4)};
			StandardDeviation s = new StandardDeviation();

			int[] arr = { 2, 4, 4, 5, 5, 6, 2, 3, 3, 6 };

			double temp = new StandardDeviation().calc(s, nums);
			//System.out.println(temp);
			if (temp <standarDif
					&& (getSumNode(node1) + getSumNode(node2) + getSumNode(node3) + getSumNode(node4)) == NUM) {
				standarDif = temp;
				result[0] = (TreeNode) node1.clone();
				result[1] = (TreeNode) node2.clone();
				result[2] = (TreeNode) node3.clone();
				result[3] = (TreeNode) node4.clone();
				/*
				 * result[0] = node1; result[1] = node2; result[2] = node3; result[3] = node4;
				 */
				System.err.println("后配选择");
				printTree(node1);
				System.out.println("----");
				printTree(node2);
				System.out.println("----");
				printTree(node3);
				System.out.println("----");
				printTree(node4);
				System.out.println("----");
				/*
				 * printTree(node5); System.out.println("----"); printTree(node6);
				 * System.out.println("----"); printTree(node7); System.out.println("----");
				 * printTree(node8);
				 */
				System.err.println("========");
			}
			/*
			 * SQ.push(node8); SQ.push(node7); SQ.push(node6); SQ.push(node5);
			 */
			SQ.push(node4);
			SQ.push(node3);
			SQ.push(node2);
			SQ.push(node1);

			return;
		}

		for (int i = h; i >= 2; i--) {
			if (treeHeight(node) < i) {
				continue;
			}
			if (SQ.size() < PROCESSOR) {

				TreeNode temp = findTreeNode(node, i);
				SQ.push(temp);
				System.out.println("----size:" + SQ.size() + " i的值为" + i);

				node = removeNode(node, temp);
				System.err.println("剩余部分，进行下一次筛选:::begin");
				printTree(node);
				System.err.println("剩余部分，进行下一次筛选:::end");

				dfs(node, h);

				node = reverseNode(node, SQ.pop());
				System.err.println("恢复上一次状态 :::begin");
				System.err.println("----size:" + SQ.size() + " i的值为" + i);
				printTree(node);
				System.err.println("恢复上一次状态  :::end");
			}
		}
		return;
	}

	private static TreeNode findTreeNode(TreeNode p, int h) {
		/*
		 * if (treeHeight(p) == 1) { System.out.println("高度不足"); return null; }
		 */
		boolean flag = true;
		TreeNode temp = null;
		TreeNode q = p;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while (p != null && flag) {

			for (; p.leftChild != null; p = p.leftChild)
				stack.push(p);

			while (p != null && (p.rightChild == null || p.rightChild == q)) {
				if (treeHeight(p) == h) {
					System.err.println("该节点符合条件");
					printTree(p);
					System.err.println("该节点符合条件");

					temp = p;
					flag = false;
					break;
				}

				q = p;
				if (stack.empty())
					break;
				p = stack.pop();
			}

			stack.push(p);
			p = p.rightChild;
		}

		return temp;
	}

	private static boolean checkNums(Stack<TreeNode> SQ) {
		int numSum = 0;
		Stack<TreeNode> SQtemp = new Stack<TreeNode>();
		Iterator<TreeNode> iterator = SQ.iterator();
		while (iterator.hasNext()) {
			TreeNode temp = SQ.pop();
			System.out.println("itemnums " + getSumNode(temp) + " height " + treeHeight(temp));
			numSum += getSumNode(temp);

			SQtemp.push(temp);
			System.out.println("-----");

		}
		Iterator<TreeNode> iterator1 = SQtemp.iterator();
		while (iterator1.hasNext()) {
			SQ.push(SQtemp.pop());
		}

		if (numSum == NUM) {
			System.out.println("all num :" + numSum);
			return true;
		}

		return false;
	}

	public static void main1(String[] args) {
		Tree tree = new Tree();

		teseNode(tree.root);

	}

	private static void teseNode(TreeNode node) {

		TreeNode temp = findTreeNode(node, 3);
		SQ.push(temp);
		printTree(temp);

		node = removeNode(node, temp);

		System.out.println("当前所有节点-----start");
		printTree(node);
		System.out.println("当前所有节点-----end");

		TreeNode temp1 = findTreeNode(node, 3);
		SQ.push(temp1);
		printTree(temp1);

		node = removeNode(node, temp1);
		System.out.println("当前所有节点-----start");
		printTree(node);
		System.out.println("当前所有节点-----end");

		TreeNode temp2 = findTreeNode(node, 3);
		printTree(temp2);
		SQ.push(temp2);

		node = removeNode(node, temp2);
		System.out.println("当前所有节点-----start");
		printTree(node);
		System.out.println("当前所有节点-----end");

		TreeNode temp3 = findTreeNode(node, 3);
		printTree(temp3);
		SQ.push(temp3);
		node = removeNode(node, temp3);
		printTree(node);

		System.out.println("栈大小" + SQ.size());
		System.out.println("开始恢复");
		node = preorderTraversal(node, SQ.pop());
		printTree(node);

		node = preorderTraversal(node, SQ.pop());
		printTree(node);
		node = preorderTraversal(node, SQ.pop());
		printTree(node);
		node = preorderTraversal(node, SQ.pop());
		printTree(node);
		// System.out.println("&&&");
		// printTree(root);
	}

	public static TreeNode preorderTraversal(TreeNode node, TreeNode temp) {

		Stack<TreeNode> treeStack = new Stack<>();
		if (node == null) // 如果为空树则返回
		{
			System.out.println("根节点恢复");
			node = temp;
		} else {
			treeStack.push(node);
			while (!treeStack.isEmpty()) {
				TreeNode tempNode = treeStack.pop();
				if (tempNode != null) {
					if (temp.parentChild == tempNode) {
						if (tempNode.rightChild == null) {
							System.out.println("右孩子恢复");
							tempNode.rightChild = temp;
							break;
						} else if (tempNode.leftChild == null) {
							System.out.println("左孩子恢复");
							tempNode.leftChild = temp;
							break;
						}else {
							System.out.println("什么都没有");
						}
					}
					treeStack.push(tempNode.leftChild); // 入栈右孩子
					treeStack.push(tempNode.rightChild);// 入栈左孩子
				}
			}
		}

		return node;
	}

	private static TreeNode reverseNode(TreeNode node, TreeNode temp) {
		if (node != null) {
			if (temp.parentChild == node) {
				if (node.rightChild == null) {
					System.out.println("右孩子恢复");
					node.rightChild = temp;

				} else if (node.leftChild == null) {
					System.out.println("左孩子恢复");
					node.leftChild = temp;

				}
			}
			reverseNode(node.leftChild, temp);
			reverseNode(node.rightChild, temp);

		} else if (node == null) {
			System.out.println("根节点恢复");
			node = temp;

		}
		return node;
	}

	private static TreeNode removeNode(TreeNode node, TreeNode temp) {

		if (node != null) {
			if (node.leftChild == temp) {
				node.leftChild = null;
				System.out.println("移除左孩子");

			} else if (node.rightChild == temp) {
				System.out.println("移除右孩子");
				node.rightChild = null;

			} else if (node == temp) {
				System.out.println("移除根");
				node = null;
				return node;
			}
			removeNode(node.leftChild, temp);
			removeNode(node.rightChild, temp);
		}
		return node;
	}

	public static TreeNode fromBottom(TreeNode node, int height) {

		if (node != null) {

			if (treeHeight(node) == height) {
				System.out.println("该节点符合" + node.key);
				SQ.push(node);
				return node;

			}
			fromBottom(node.leftChild, height);
			fromBottom(node.rightChild, height);
		}
		return node;
	}

	public static int treeHeight(TreeNode subTree) {
		if (subTree == null) {
			return 0;
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
			current = queue.poll();
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