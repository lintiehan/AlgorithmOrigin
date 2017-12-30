package Test;

import java.awt.Window;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import javax.security.auth.kerberos.KerberosKey;

public class Tree {
	private static int PROCESSOR;
	public static TreeNode[] result;
	static double standarDif = 100000;
	static int NUM = 0;
	private static int count = 1;
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
		TreeNode node18 = new TreeNode(18);
		TreeNode node19 = new TreeNode(19);

		TreeNode node20 = new TreeNode(20);
		TreeNode node21 = new TreeNode(21);
		TreeNode node22 = new TreeNode(22);

		root.leftChild = node2;
		root.centerChild = node3;
		root.rightChild = node4;
		node2.parentChild = root;
		node3.parentChild = root;
		node4.parentChild = root;

		node2.leftChild = node5;
		node2.centerChild = node6;
		node2.rightChild = node7;
		node5.parentChild = node2;
		node6.parentChild = node2;
		node7.parentChild = node2;

		node4.leftChild = node8;
		node4.centerChild = node9;
		node4.rightChild = node10;
		node8.parentChild = node4;
		node9.parentChild = node4;
		node10.parentChild = node4;

		node5.leftChild = node11;
		node5.centerChild = node12;
		node5.rightChild = node13;
		node11.parentChild = node5;
		node12.parentChild = node5;
		node13.parentChild = node5;

		node9.leftChild = node14;
		node9.centerChild = node15;
		node9.rightChild = node16;
		node14.parentChild = node9;
		node15.parentChild = node9;
		node16.parentChild = node9;

		node16.leftChild = node17;
		node16.centerChild = node18;
		node16.rightChild = node19;
		node17.parentChild = node16;
		node18.parentChild = node16;
		node19.parentChild = node16;

		node19.leftChild = node20;
		node19.centerChild = node21;
		node19.rightChild = node22;
		node20.parentChild = node19;
		node21.parentChild = node19;
		node22.parentChild = node19;
	}

	public static TreeNode[] DistributeServer(TreeNode node, int serverNum) throws CloneNotSupportedException {
		PROCESSOR = serverNum;
		NUM = getSumNode(node);
		System.out.println("所有节点数目为：" + NUM);
		result = new TreeNode[PROCESSOR];
		int maxheight = getSumNode(node) / PROCESSOR;
		System.out.println("最大高度为：" + maxheight);
		if (maxheight > treeHeight(node)) {
			dfs(node, treeHeight(node));
		} else {
			dfs(node, maxheight);
		}

		return result;
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		Tree tree = new Tree();

		NUM = getSumNode(tree.root);
		PROCESSOR = 3;
		System.out.println("所有节点数目为：" + NUM);

		result = new TreeNode[PROCESSOR];
		int maxheight = getSumNode(tree.root) / PROCESSOR;
		System.out.println("最大高度为：" + maxheight);
		if (maxheight > treeHeight(tree.root)) {
			dfs(tree.root, treeHeight(tree.root));
		} else {
			dfs(tree.root, maxheight);
		}
		System.out.println("最后的选择----------");
		printSelectedNodes(result);
	}

	public static void main1(String[] args) {
		Tree tree = new Tree();
		TreeNode node = findTreeNode(tree.root, 4);
		printTree(node);
		tree.root.leftChild = null;
		printTree(tree.root);
		System.out.println();
		printTree(node.parentChild);
		System.out.println(node.parentChild);
		System.out.println(tree.root);
	}

	public static void dfs(TreeNode node, int h) throws CloneNotSupportedException {

		if (SQ.size() > PROCESSOR || treeHeight(node) == 1) {
			// System.out.println("条件不予许");
			return;
		}

		if (PROCESSOR == SQ.size() && checkNums(SQ) == true) {

			int nums[] = getNodesHeight(SQ);
			double temp = StandardDeviation.calc(nums);
			System.out.println("我可以--->" + temp);
			if (temp <= standarDif) {
				standarDif = temp;
				System.out.println("我变小了--->" + temp);
				System.err.println("存在选择");
				TreeNode[] tempArray = getNodes(SQ);
				printSelectedNodes(tempArray);

				copyObject(result, tempArray);
			}
			return;
		}

		for (int i = h; i >= 2; i--) {
			if (treeHeight(node) < i) {
				continue;
			}
			if (SQ.size() < PROCESSOR) {
				TreeNode temp = findTreeNodeTraversal(node, i);
				SQ.push(temp);
				node = removeNode(node, temp);
				dfs(node, h);
				node = reverseNode(node, SQ.pop());
			}
		}
		return;
	}

	private static void copyObject(TreeNode[] result2, TreeNode[] tempArray) throws CloneNotSupportedException {

		for (int i = 0; i < tempArray.length; i++) {
			result2[i] = (TreeNode) tempArray[i].clone();
		}
	}

	public static void printSelectedNodes(TreeNode[] temp) {
		for (int i = 0; i < temp.length; i++) {
			System.out.println("the height of node is : " + treeHeight(temp[i]));
			printTree(temp[i]);
			System.out.println("----------");
		}
	}

	private static TreeNode[] getNodes(Stack<TreeNode> temp) throws CloneNotSupportedException {
		TreeNode[] nodes = new TreeNode[temp.size()];
		Stack<TreeNode> SQtemp = new Stack<TreeNode>();
		int index = 0;
		Iterator<TreeNode> iterator = temp.iterator();
		while (iterator.hasNext()) {
			TreeNode node = (TreeNode) temp.pop().clone();

			nodes[index++] = node;
			SQtemp.push(node);
		}
		Iterator<TreeNode> iterator1 = SQtemp.iterator();
		while (iterator1.hasNext()) {
			temp.push(SQtemp.pop());
		}
		return nodes;
	}

	private static int[] getNodesHeight(Stack<TreeNode> temp) {
		int[] heights = new int[temp.size()];
		Stack<TreeNode> SQtemp = new Stack<TreeNode>();
		int index = 0;
		Iterator<TreeNode> iterator = temp.iterator();
		while (iterator.hasNext()) {
			TreeNode node = temp.pop();

			heights[index++] = treeHeight(node);
			SQtemp.push(node);
		}
		Iterator<TreeNode> iterator1 = SQtemp.iterator();
		while (iterator1.hasNext()) {
			temp.push(SQtemp.pop());
		}
		return heights;
	}

	public static TreeNode findTreeNodeTraversal(TreeNode node, int h) {
		TreeNode temp = null;
		boolean flag=true;
		
		Stack<TreeNode> treeStack = new Stack<>();
		if (node == null) // 如果为空树则返回
			return null;
		treeStack.push(node);
		while (!treeStack.isEmpty()&&flag) {
			TreeNode tempNode = treeStack.pop();
			if (tempNode != null) {
				if (treeHeight(tempNode) == h) {
					temp = tempNode;
					flag=false;
					break;
				}
				treeStack.push(tempNode.rightChild); // 入栈右孩子
				treeStack.push(tempNode.centerChild); // 入栈右孩子
				treeStack.push(tempNode.leftChild);// 入栈左孩子
			}
		}
		return temp;
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
					/*
					 * System.err.println("该节点符合条件"); printTree(p); System.err.println("该节点符合条件");
					 */

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
			// System.out.println("itemnums " + getSumNode(temp) + " height " +
			// treeHeight(temp));
			numSum += getSumNode(temp);

			SQtemp.push(temp);
			// System.out.println("-----");

		}
		Iterator<TreeNode> iterator1 = SQtemp.iterator();
		while (iterator1.hasNext()) {
			SQ.push(SQtemp.pop());
		}

		if (numSum == NUM) {
			// System.out.println("all num :" + numSum);
			return true;
		}

		return false;
	}

	public static TreeNode preorderTraversal(TreeNode node, TreeNode temp) {

		Stack<TreeNode> treeStack = new Stack<>();
		if (node == null) // 如果为空树则返回
		{
			// System.out.println("根节点恢复");
			node = temp;
		} else {
			treeStack.push(node);
			while (!treeStack.isEmpty()) {
				TreeNode tempNode = treeStack.pop();
				if (tempNode != null) {
					if (temp.parentChild == tempNode) {
						if (tempNode.rightChild == null) {
							// System.out.println("右孩子恢复");
							tempNode.rightChild = temp;
							break;
						}
						if (tempNode.centerChild == null) {
							// System.out.println("右孩子恢复");
							tempNode.centerChild = temp;
							break;
						} else if (tempNode.leftChild == null) {
							// System.out.println("左孩子恢复");
							tempNode.leftChild = temp;
							break;
						} else {
							// System.out.println("什么都没有");
						}
					}
					treeStack.push(tempNode.leftChild); // 入栈右孩子
					treeStack.push(tempNode.centerChild); // 入栈右孩子
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
					// System.out.println("右孩子恢复");
					node.rightChild = temp;

				} else if (node.centerChild == null) {
					// System.out.println("左孩子恢复");
					node.centerChild = temp;

				} else if (node.leftChild == null) {
					// System.out.println("左孩子恢复");
					node.leftChild = temp;

				}
			}
			reverseNode(node.leftChild, temp);
			reverseNode(node.centerChild, temp);
			reverseNode(node.rightChild, temp);

		} else if (node == null) {
			// System.out.println("根节点恢复");
			node = temp;

		}
		return node;
	}

	private static TreeNode removeNode(TreeNode node, TreeNode temp) {

		if (node != null) {
			if (node.leftChild == temp) {
				node.leftChild = null;
				// System.out.println("移除左孩子");

			} else if (node.centerChild == temp) {
				// System.out.println("移除右孩子");
				node.centerChild = null;

			} else if (node.rightChild == temp) {
				// System.out.println("移除右孩子");
				node.rightChild = null;

			} else if (node == temp) {
				// System.out.println("移除根");
				node = null;
				return node;
			}
			removeNode(node.leftChild, temp);
			removeNode(node.centerChild, temp);
			removeNode(node.rightChild, temp);
		}
		return node;
	}

	public static TreeNode fromBottom(TreeNode node, int height) {

		if (node != null) {

			if (treeHeight(node) == height) {
				// System.out.println("该节点符合" + node.key);
				SQ.push(node);
				return node;

			}
			fromBottom(node.leftChild, height);
			fromBottom(node.centerChild, height);
			fromBottom(node.rightChild, height);
		}
		return node;
	}

	public static int treeHeight(TreeNode subTree) {
		if (subTree == null) {
			return 0;// 递归结束 空树高度丿0
		} else {
			int i = treeHeight(subTree.leftChild);
			int j = treeHeight(subTree.centerChild);
			int k = treeHeight(subTree.rightChild);
			int result = Math.max(Math.max(i, j), k);
			return result + 1;
		}
	}

	public static int getSumNode(TreeNode node) {
		if (node == null) {
			return 0;
		} else {
			int i = getSumNode(node.leftChild);
			int j = getSumNode(node.centerChild);
			int k = getSumNode(node.rightChild);

			return i + k + j + 1;
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
			if (node.centerChild != null) {
				queue.offer(node.centerChild);
				nlast = node.centerChild;
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

}