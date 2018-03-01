package Test_new1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

import org.w3c.dom.stylesheets.LinkStyle;

public class TreeDiv {
	private static int PROCESSOR;
	private static TreeNode[] RESULT;
	static double standarDif;
	static double standarDifNum;
	static int NUM = 0;
	private static int count = 1;
	static Stack<TreeNode> SQ = new Stack<TreeNode>();
	static TreeNode root;

	public TreeDiv() {
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

		node7.leftChild = node14;
		node7.centerChild = node15;
		node7.rightChild = node16;
		node14.parentChild = node7;
		node15.parentChild = node7;
		node16.parentChild = node7;
		node14.position = -1;
		node15.position = 0;
		node16.position = 1;

		node10.leftChild = node17;
		node10.centerChild = node18;
		node10.rightChild = node19;
		node17.parentChild = node10;
		node18.parentChild = node10;
		node19.parentChild = node10;
		node17.position = -1;
		node18.position = 0;
		node19.position = 1;

		node11.leftChild = node20;
		node11.centerChild = node21;
		node11.rightChild = node22;
		node20.parentChild = node11;
		node21.parentChild = node11;
		node22.parentChild = node11;
		node20.position = -1;
		node21.position = 0;
		node22.position = 1;
		standarDif = 100000;
		standarDifNum = 100000;
	}

	public static void show(int[] nums) {

		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + " ");
		}

	}

	public static void main(String[] args) throws CloneNotSupportedException {
		TreeDiv tDiv = new TreeDiv();

		int pc = 3;
		int k = 2;
		List<TreeNode> nodelist = new TreeDiv().divAll(tDiv.root, pc, k);

		System.out.println("pc's size " + pc + "   list.size " + nodelist.size());

		TreeDiv.showList(nodelist, pc, k);

		int[] sum = TreeDiv.showResult(nodelist, pc, k);
		Arrays.sort(sum);
		show(sum);
		int min = sum[0];
		int max = sum[sum.length - 1];

		System.out.println("k=" + k + " " + min + " " + max);
	}

	public static TreeNode[] DistributeServer(TreeNode node, int serverNum) throws CloneNotSupportedException {

		PROCESSOR = 4;
		NUM = getSumNode(node);
		RESULT = new TreeNode[PROCESSOR];
		int maxheight = getSumNode(node) / PROCESSOR;
		if (maxheight > treeHeight(node)) {
			dfs(node, treeHeight(node));
		} else {
			dfs(node, maxheight + 1);
		}
		return RESULT;
	}

	public static List<TreeNode> divTwo(TreeNode node, int nums, int k) throws CloneNotSupportedException {

		ArrayList<TreeNode> temp = new ArrayList<>();
		temp.add(node);
		while (true) {

			SQ.clear();
			TreeNode operate = temp.remove(0);

			setParents(operate);
			TreeNode[] t = new TreeDiv().DistributeServer(operate, 2);
			temp.add(t[0]);
			temp.add(t[1]);
			temp.add(t[2]);
			temp.add(t[3]);
			/*
			 * System.out.println("选中节点如下："); printTree(t[0]); printTree(t[1]);
			 * printTree(t[2]); printTree(t[3]); System.out.println("=======");
			 */
			Collections.sort(temp, new NodeComparatorByHeight());
			if (temp.size() >= nums) {
				break;
			}
		}
		return temp;
	}

	public static void dfs(TreeNode node, int h) throws CloneNotSupportedException {

		if (PROCESSOR == SQ.size() && checkNums(SQ) == true) {
			// System.err.println("存在选择的上一步");
			int heights[] = getNodesHeight(SQ);
			double heighttemp = StandardDeviation.calc(heights);
			int nums[] = getNodesNum(SQ);
			double numtemp = StandardDeviation.calc(nums);

			if (heighttemp <= standarDif) {
				if (numtemp <= standarDifNum) {
					// System.out.println("#######"+temp);
					standarDif = heighttemp;
					standarDifNum = numtemp;
					// System.err.println("存在选择");
					TreeNode[] tempArray = getNodes(SQ);
					// printSelectedNodes(tempArray);
					RESULT = copyObject(tempArray);
				}
			}

			return;
		}

		for (int i = h; i >= 1; i--) {

			if (SQ.size() < PROCESSOR) {
				TreeNode temp = postFindNode(node, i);
				// System.out.println("找到了该节点：");
				// printTree(temp);
				SQ.push(temp);

				node = preRemoveNode(node, temp);
				// System.out.println("进入下一次查找的节点 此时 i " + i + " SQ 的大小为:" + SQ.size());
				// printTree(node);

				dfs(node, h);

				if (SQ.size() == 3) {
					node = preReverseNode(node, SQ.pop());
					node = preReverseNode(node, SQ.pop());
					node = preReverseNode(node, SQ.pop());
				} else {
					node = preReverseNode(node, SQ.pop());
				}

				// System.out.println("恢复上一次查找的节点 此时 i " + i + " SQ 的大小为:" + SQ.size());
				// printTree(node);
			}
		}

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
		// System.out.println("preRemoveNode");
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
		// System.out.println("------>>" + output.size());
		while (output.size() > 0) {
			TreeNode no = (TreeNode) output.pop();
			// System.out.println("no 的值为" + no.key + " position " + no.position);
			// System.out.println(" tempnode.parentChild的值为 " + tempnode.parentChild);

			if (no != null && tempnode != null && no == tempnode.parentChild) {
				// System.out.println("2222");
				if (no.leftChild == tempnode) {
					// System.out.println("左孩子remove");
					SQ.push(no.centerChild);
					SQ.push(no.rightChild);

					no.leftChild = null;
					no.centerChild = null;
					no.rightChild = null;
					break;
				} else if (no.centerChild == tempnode) {
					// System.out.println("中心孩子remove");
					SQ.push(no.leftChild);
					SQ.push(no.rightChild);

					no.leftChild = null;
					no.centerChild = null;
					no.rightChild = null;
					break;
				} else if (no.rightChild == tempnode) {
					// System.out.println("右孩子remove");
					SQ.push(no.centerChild);
					SQ.push(no.leftChild);

					no.leftChild = null;
					no.centerChild = null;
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
			if (no != null && tempnode != null && tempnode.parentChild == no) {
				// System.out.println("存在节点");
				if (tempnode.position == -1) {
					no.leftChild = tempnode;
					tempnode.parentChild = no;
					// System.err.println("左孩子恢复");
					break;
				} else if (tempnode.position == 0) {
					no.centerChild = tempnode;
					tempnode.parentChild = no;
					// System.err.println("中心孩子恢复");
					break;
				} else if (tempnode.position == 1) {
					no.rightChild = tempnode;
					tempnode.parentChild = no;
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
			if (node != null) {
				nodes[index++] = (TreeNode) node.clone();
				SQtemp.push(node);
			}
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

	// 后序查找高度为h的节点
	private static TreeNode postFindNode(TreeNode node, int h) throws CloneNotSupportedException {
		TreeNode temp = null;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		Stack<TreeNode> output = new Stack<TreeNode>();
		stack.push(node);
		TreeNode curr = null;
		while (stack.size() > 0) {
			curr = stack.pop();
			output.push(curr);
			if (curr != null && curr.leftChild != null)
				stack.push(curr.leftChild);
			if (curr != null && curr.centerChild != null)
				stack.push(curr.centerChild);
			if (curr != null && curr.rightChild != null)
				stack.push(curr.rightChild);
		}
		while (output.size() > 0) {
			TreeNode no = (TreeNode) output.pop();
			if (treeHeight(no) == h) {
				temp = no;
				break;
			}
		}
		return temp;
	}

	private static TreeNode[] copyObject(TreeNode[] tempArray) throws CloneNotSupportedException {
		TreeNode[] target = new TreeNode[tempArray.length];
		for (int i = 0; i < tempArray.length; i++) {
			// target[i]=(TreeNode) tempArray[i].clone();
			target[i] = copy(tempArray[i]);
		}
		return target;
	}

	// 打印所有符合要求的节点
	public static void printSelectedNodes(TreeNode[] temp) {
		for (int i = 0; i < temp.length; i++) {
			System.out.println("the height of node is : " + treeHeight(temp[i]));
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

	public static void setParents(TreeNode node) {
		if (node != null) {

			setParents(node.leftChild);
			setParents(node.centerChild);
			setParents(node.rightChild);
			if (node.leftChild != null) {
				node.leftChild.parentChild = node;
				node.leftChild.position = -1;

			}
			if (node.centerChild != null) {
				node.centerChild.parentChild = node;
				node.centerChild.position = 0;

			}
			if (node.rightChild != null) {
				node.rightChild.parentChild = node;
				node.rightChild.position = 1;

			}
		}
	}

	public List<TreeNode> divAll(TreeNode node, int pc, int k) {
		if (node == null) {
			return null;
		}
		LinkedList<TreeNode> queue = new LinkedList<>();
		int index = 0;
		TreeNode temp;
		queue.add(node);
		while (!queue.isEmpty() && queue.size() <= k * pc) {
			temp = queue.remove(index);

			if (temp.leftChild != null) {
				queue.add(index, temp.leftChild);
			}
			if (temp.centerChild != null) {
				queue.add(index + 1, temp.centerChild);
			}
			if (temp.rightChild != null) {
				queue.add(index + 2, temp.rightChild);
			}

			index = getBiggestIndex(queue);
			if (queue.size() + 2 > k * pc) {
				break;
			}
		} 
		return queue;
	}

	private int getBiggestIndex(LinkedList<TreeNode> queue) {
		int num = 0;
		int index = 0;
		for (int i = 0; i < queue.size(); i++) {
			TreeNode temp = queue.get(i);
			if (getSumNode(temp) >= num) {
				num = getSumNode(temp);
				index = i;
			}
		}
		return index;
	}

	public static List<List<TreeNode>> divNodeByNums(List<TreeNode> list, int pc, int k) {
	 
		List<List<TreeNode>> nodelist = new ArrayList<>();
		Iterator iterator = list.iterator();
		LinkedList<TreeNode> temp = new LinkedList<>();
		int count = 0;
	 	if (list.size() % k != 0) {
		 
			while (iterator.hasNext()) {
				// printTree((TreeNode) iterator.next());
				temp.add((TreeNode) iterator.next());
				if (temp.size() == k) {
					LinkedList<TreeNode> t = (LinkedList<TreeNode>) temp.clone();
					nodelist.add(t);
					temp.clear();
					count++;
				}
				if (count == list.size() / k) {
					break;
				}
			}
			while (iterator.hasNext()) {
				temp.add((TreeNode) iterator.next());
			}
			nodelist.add(temp);
		} else {
			 
			while (iterator.hasNext()) {
				// printTree((TreeNode) iterator.next());
				temp.add((TreeNode) iterator.next());
				if (temp.size() == k) {
					LinkedList<TreeNode> t = (LinkedList<TreeNode>) temp.clone();
					nodelist.add(t);
					temp.clear();					
				}
			}

		}
	 
		return nodelist;
	}

	public static void showList(List<TreeNode> queuetemp, int pc, int k) {
		List<List<TreeNode>> queue = divNodeByNums(queuetemp, pc, k);

		for (int i = 0; i < queue.size(); i++) {

			System.out.println("case " + (i + 1));
			for (int j = 0; j < queue.get(i).size(); j++) {
				System.out.println("     case " + j + " SIZE: " + getSumNode(queue.get(i).get(j)));
				TreeDiv.printTree(queue.get(i).get(j));
				System.out.println("-------");
			}
			System.out.println("========");
		}
	}

	public static int[] showResult(List<TreeNode> nodelist, int pc, int k) {
		// TODO Auto-generated method stub
		List<List<TreeNode>> queue = divNodeByNums(nodelist, pc, k);
		int[] sum = new int[queue.size()];
		for (int i = 0; i < queue.size(); i++) {
			int temp = 0;
			for (int j = 0; j < queue.get(i).size(); j++) {
				temp += getSumNode(queue.get(i).get(j));
			}
			sum[i] = temp;
		}

		return sum;
	}

	public static double getAve(int[] nums) {
		double sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		return sum / nums.length;
	}
}

class NodeComparatorByNumAsc implements Comparator<TreeNode> {
	@Override
	public int compare(TreeNode p1, TreeNode p2) {
		return TreeDiv.getSumNode(p2) - TreeDiv.getSumNode(p1);
	}
}

class NodeComparatorByNumDes implements Comparator<TreeNode> {
	@Override
	public int compare(TreeNode p1, TreeNode p2) {
		return TreeDiv.getSumNode(p1) - TreeDiv.getSumNode(p2);
	}
}

class NodeComparatorByHeight implements Comparator<TreeNode> {
	@Override
	public int compare(TreeNode p1, TreeNode p2) {
		return TreeDiv.treeHeight(p2) - TreeDiv.treeHeight(p1);
	}
}