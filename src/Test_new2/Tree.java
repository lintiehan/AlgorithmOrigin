package Test_new2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import javax.xml.soap.Node;

public class Tree {
	private static int PROCESSOR = 4;
	private static TreeNode[] RESULT;
	static double standarDif ;
	static double standarDifNum;
	static int NUM = 0;
	private static int count = 1;
	static Stack<TreeNode> SQ;
	static Stack<TreeNode> finalresult = new Stack<TreeNode>();
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
		node2.position = -1;
		node3.position = 0;
		node4.position = 1;

		node2.leftChild = node5;
		node2.centerChild = node6;
		node2.rightChild = node7;
		node5.parentChild = node2;
		node6.parentChild = node2;
		node7.parentChild = node2;
		node5.position = -1;
		node6.position = 0;
		node7.position = 1;

		node4.leftChild = node8;
		node4.centerChild = node9;
		node4.rightChild = node10;
		node8.parentChild = node4;
		node9.parentChild = node4;
		node10.parentChild = node4;
		node8.position = -1;
		node9.position = 0;
		node10.position = 1;

		node5.leftChild = node11;
		node5.centerChild = node12;
		node5.rightChild = node13;
		node11.parentChild = node5;
		node12.parentChild = node5;
		node13.parentChild = node5;
		node11.position = -1;
		node12.position = 0;
		node13.position = 1;

		node9.leftChild = node14;
		node9.centerChild = node15;
		node9.rightChild = node16;
		node14.parentChild = node9;
		node15.parentChild = node9;
		node16.parentChild = node9;
		node14.position = -1;
		node15.position = 0;
		node16.position = 1;

		node16.leftChild = node17;
		node16.centerChild = node18;
		node16.rightChild = node19;
		node17.parentChild = node16;
		node18.parentChild = node16;
		node19.parentChild = node16;
		node17.position = -1;
		node18.position = 0;
		node19.position = 1;

		node19.leftChild = node20;
		node19.centerChild = node21;
		node19.rightChild = node22;
		node20.parentChild = node19;
		node21.parentChild = node19;
		node22.parentChild = node19;
		node20.position = -1;
		node21.position = 0;
		node22.position = 1;

	}

	public static TreeNode[] DistributeServer(TreeNode node1, int serverNum) throws CloneNotSupportedException {
		standarDif =100000;
		standarDifNum = 100000;
		TreeNode node = node1;
		SQ = new Stack<TreeNode>();
		PROCESSOR = serverNum;
		NUM = getSumNode(node);
		RESULT = new TreeNode[PROCESSOR];
		System.out.println();
		System.out.println("高度为：" + treeHeight(node) + "子节点数为： " + getSumNode(node));
		int maxheight = getSumNode(node) / PROCESSOR;
		if (maxheight > treeHeight(node)) {
			dfs(node, treeHeight(node));
			System.out.println("------1");
		} else {
			dfs(node, maxheight);
			System.out.println("-----2");
		}

		return RESULT;

	}

	public static void main(String[] args) throws CloneNotSupportedException {
		Tree tree = new Tree();
		standarDif =100000;
		standarDifNum = 100000;
		SQ = new Stack<TreeNode>();
		NUM = getSumNode(tree.root);
		PROCESSOR = 3;
		System.out.println("all nums：" + NUM);
		RESULT = null;
		RESULT = new TreeNode[PROCESSOR];

		printTree(tree.root);
		int maxheight = getSumNode(tree.root) / PROCESSOR;
		if (maxheight > treeHeight(tree.root)) {
			dfs(tree.root, treeHeight(tree.root));
		} else {
			dfs(tree.root, maxheight);
		}
		System.out.println("最后的选择----------" + count);
		printSelectedNodes(RESULT);

	}

	public static void dfs(TreeNode node, int h) throws CloneNotSupportedException {
		if (treeHeight(node) == 1) {
			System.out.println("条件不予许");
			return;
		}
		if (PROCESSOR == SQ.size() && checkNums(SQ) == true) {
			System.out.println("找到了");
			count++;
			int heights[] = getNodesHeight(SQ);
			double heighttemp = StandardDeviation.calc(heights);
			int nums[] = getNodesNum(SQ);
			double numtemp = StandardDeviation.calc(nums);
			if (heighttemp <= standarDif) {
				if (numtemp <= standarDifNum) {
					// System.out.println("#######"+temp);
					standarDif = heighttemp;
					standarDifNum = numtemp;
					System.err.println("存在选择");
					TreeNode[] tempArray = getNodes(SQ);
					printSelectedNodes(tempArray);
					RESULT = copyObject(tempArray);
				}
			}
			return;
		}

		for (int i = h; i >= 2; i--) {
			if (treeHeight(node) < i) {
				System.out.println("小于规定的高度");
				continue;
			}
			if (SQ.size() < PROCESSOR) {
				TreeNode temp = postFindNode(node, i);
				if(temp!=null)
				{
					printTree(temp);
				}
				SQ.push(temp);
				node = preRemoveNode(node, temp);
				System.out.println("下一次进入的节点");
				printTree(node);
				System.out.println("in i的值:" + i);
				// count++;
				dfs(node, h);
				System.out.println("back i的值:" + i);
				// count--;
				node = preReverseNode(node, SQ.pop());
			}
		}

	}

	private static TreeNode copy(TreeNode node) {
		TreeNode newnode;
		if (node == null) {
			return null;
		} else {
			newnode = new TreeNode();
			newnode.key = node.key;
			newnode.position = node.position;

			newnode.leftChild = copy(node.leftChild);
			newnode.centerChild = copy(node.centerChild);
			newnode.rightChild = copy(node.rightChild);
			return newnode;
		}
	}

	// 前序移除特定的节点
	private static TreeNode preRemoveNode(TreeNode node, TreeNode tempnode) throws CloneNotSupportedException {

		if (node == tempnode) {
			// System.out.println("移除根");
			node = null;
			return node;
		}

		Stack<TreeNode> stack = new Stack<TreeNode>();
		Stack<TreeNode> output = new Stack<TreeNode>();
		stack.push(node);
		TreeNode curr = null;
		while (stack.size() > 0) {
			curr = stack.pop();
			output.push(curr);
			if (curr.leftChild != null)
				stack.push(curr.leftChild);
			if (curr.centerChild != null)
				stack.push(curr.centerChild);
			if (curr.rightChild != null)
				stack.push(curr.rightChild);
		}
		while (output.size() > 0) {
			TreeNode no = (TreeNode) output.pop();
			if (no != null && tempnode.parentChild == no) {

				if (no.leftChild == tempnode) {
					no.leftChild = null;
					break;
				} else if (no.centerChild == tempnode) {
					no.centerChild = null;
					break;
				} else if (no.rightChild == tempnode) {
					no.rightChild = null;
					break;
				}
			}
		}
		return node;
	}

	// 前序恢复特定的节点
	private static TreeNode preReverseNode(TreeNode node, TreeNode tempnode) throws CloneNotSupportedException {

		if (node == null) {
			// System.err.println("根节点恢复");
			node = tempnode;
			return node;
		}
		TreeNode temp = null;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		Stack<TreeNode> output = new Stack<TreeNode>();
		stack.push(node);
		TreeNode curr = null;
		while (stack.size() > 0) {
			curr = stack.pop();
			output.push(curr);
			if (curr.leftChild != null)
				stack.push(curr.leftChild);
			if (curr.centerChild != null)
				stack.push(curr.centerChild);
			if (curr.rightChild != null)
				stack.push(curr.rightChild);
		}
		while (output.size() > 0) {
			// System.out.print (output.peek().key+" ");
			TreeNode no = (TreeNode) output.pop();
			if (no != null && tempnode.parentChild == no) {
				// System.out.println("存在节点");
				if (tempnode.position == -1) {
					no.leftChild = tempnode;
					// System.err.println("左孩子恢复");
					break;
				} else if (tempnode.position == 0) {
					no.centerChild = tempnode;
					// System.err.println("中心孩子恢复");
					break;
				} else if (tempnode.position == 1) {
					no.rightChild = tempnode;
					// System.err.println("右孩子恢复");
					break;
				}
			}
		}
		return node;
	}

	// 获取栈中的节点 （符合要求）
	private static TreeNode[] getNodes(Stack<TreeNode> temp) throws CloneNotSupportedException {
		TreeNode[] nodes = new TreeNode[temp.size()];
		Stack<TreeNode> SQtemp = new Stack<TreeNode>();
		int index = 0;
		Iterator<TreeNode> iterator = temp.iterator();
		while (iterator.hasNext()) {
			TreeNode node = temp.pop();
			nodes[index++] = (TreeNode) node.clone();
			SQtemp.push(node);
		}
		Iterator<TreeNode> iterator1 = SQtemp.iterator();
		while (iterator1.hasNext()) {
			temp.push(SQtemp.pop());
		}
		return nodes;
	}

	// 获取栈中的节点高度（求标准差）
	private static int[] getNodesHeight(Stack<TreeNode> temp) throws CloneNotSupportedException {
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

	// 获取栈中的节点数目（求标准差）
	private static int[] getNodesNum(Stack<TreeNode> temp) throws CloneNotSupportedException {
		int[] nums = new int[temp.size()];
		Stack<TreeNode> SQtemp = new Stack<TreeNode>();
		int index = 0;
		Iterator<TreeNode> iterator = temp.iterator();
		while (iterator.hasNext()) {
			TreeNode node = temp.pop();

			nums[index++] = getSumNode(node);
			SQtemp.push(node);
		}
		Iterator<TreeNode> iterator1 = SQtemp.iterator();
		while (iterator1.hasNext()) {
			temp.push(SQtemp.pop());
		}
		return nums;
	}

	// 后序查找高度为h的节点
	public static TreeNode postFindNode(TreeNode node, int h) throws CloneNotSupportedException {
		TreeNode temp = null;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		Stack<TreeNode> output = new Stack<TreeNode>();
		if (node != null) {
			stack.push(node);
			TreeNode curr = null;
			while (stack.size() > 0) {
				curr = stack.pop();
				output.push(curr);
				if (curr.leftChild != null)
					stack.push(curr.leftChild);
				if (curr.centerChild != null)
					stack.push(curr.centerChild);
				if (curr.rightChild != null)
					stack.push(curr.rightChild);
			}
			while (output.size() > 0) {
				TreeNode no = (TreeNode) output.pop();
				if (treeHeight(no) == h) {
					temp = no;
					break;
				}
			}
		}
		return temp;
	}

	private static TreeNode[] copyObject(TreeNode[] tempArray) throws CloneNotSupportedException {
		TreeNode[] target = new TreeNode[tempArray.length];
		for (int i = 0; i < tempArray.length; i++) {
			target[i] = copy(tempArray[i]);
		}
		return target;
	}

	// 打印所有符合要求的节点
	public static void printSelectedNodes(TreeNode[] temp) {
		for (int i = 0; i < temp.length; i++) {
			System.out.println("the height of node is : " + treeHeight(temp[i]) + " num " + getSumNode(temp[i]));
			printTree(temp[i]);
			System.out.println("----------");
		}
	}

	private static boolean checkNums(Stack<TreeNode> SQ) {

		int numSum = 0;
		Stack<TreeNode> SQtemp = new Stack<TreeNode>();
		Iterator<TreeNode> iterator = SQ.iterator();
		while (iterator.hasNext()) {
			TreeNode temp = SQ.pop();
			numSum += getSumNode(temp);
			SQtemp.push(temp);
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