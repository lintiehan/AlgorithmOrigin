package Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

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
		standarDif = 100000;
		standarDifNum = 100000;
	}

	public static TreeNode[] DistributeServer(TreeNode node, int serverNum) throws CloneNotSupportedException {

		PROCESSOR = serverNum;
		NUM = getSumNode(node);
		RESULT = new TreeNode[PROCESSOR];
		int maxheight = getSumNode(node) / PROCESSOR;
		if (maxheight > treeHeight(node)) {
			dfs(node, treeHeight(node));
		} else {
			dfs(node, maxheight);
		}
		return RESULT;
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		TreeDiv tree = new TreeDiv();

		NUM = getSumNode(tree.root);
		PROCESSOR = 2;
		System.out.println("all nums：" + NUM);
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

		/*
		 * System.out.println("第二季分發"); TreeNode node1 = (TreeNode) RESULT[0].clone();
		 * TreeNode node2 = (TreeNode) RESULT[1].clone(); setParents(node1); TreeNode[]
		 * p = new TreeDiv().DistributeServer(node1, 2); System.out.println("======");
		 * printTree(p[0]); printTree(p[1]);
		 * 
		 * System.out.println("第3季分發"); setParents(node2);
		 * 
		 * TreeNode[] p1 = new TreeDiv().DistributeServer(node2, 2);
		 * System.out.println("======"); printTree(p1[0]); printTree(p1[1]);
		 */
		ArrayList<TreeNode> temp = new ArrayList<>();
		temp.add((TreeNode) RESULT[0].clone());
		temp.add((TreeNode) RESULT[1].clone());

		for (int i = 0; i < temp.size(); i++) {
			printTree(temp.get(i));
		}

		System.out.println("========");
		while (true) {
			if (temp.size() == 7) {
				break;
			}
			int last = temp.size() - 1;
			TreeNode operate = temp.remove(0);
			if (getSumNode(operate) <= 4) {
				continue;
			}
			setParents(operate);
			TreeNode[] t = new TreeDiv().DistributeServer(operate, 2);

			temp.add(t[0]);
			temp.add(t[1]);
		}
		System.out.println("========");

		for (int i = 0; i < temp.size(); i++) {
			printTree(temp.get(i));
			System.out.println("--------");
		}

	}

	public static void divTwo(TreeNode node, int nums) throws CloneNotSupportedException {

		ArrayList<TreeNode> temp = new ArrayList<>();
		temp.add(node);
		while (true) {
			if (temp.size() == nums) {
				break;
			}
			TreeNode operate = temp.remove(0);
			setParents(operate);
			if(treeHeight(operate)==2)
			{
				continue;
			}
			TreeNode[] t = new TreeDiv().DistributeServer(operate, 2);
			temp.add(t[0]);
			temp.add(t[1]);
			Collections.sort(temp, new NodeComparatorByHeight());

		}

		for (int i = 0; i < temp.size(); i++) {
			System.out.println();
			System.out.println("the height of node: " + treeHeight(temp.get(i)) + "  the num of node : "
					+ getSumNode(temp.get(i)));
			printTree(temp.get(i));
			System.out.println("--------");
		}
	}

	public static void dfs(TreeNode node, int h) throws CloneNotSupportedException {

		if (PROCESSOR == SQ.size() && checkNums(SQ) == true) {
			// System.err.println("存在选择的上一步");
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
					// System.err.println("存在选择");
					TreeNode[] tempArray = getNodes(SQ);

					// printSelectedNodes(tempArray);
					RESULT = copyObject(tempArray);
				}
			}
			return;
		}

		for (int i = h; i >= 2; i--) {
			if (treeHeight(node) < i) {
				continue;
			}
			if (SQ.size() < PROCESSOR) {
				TreeNode temp = postFindNode(node, i);
				// System.out.println("找到了该节点：");
				// printTree(temp);
				SQ.push(temp);
				node = preRemoveNode(node, temp);
				// System.out.println("进入下一次查找 此时 i " + i + " SQ 的大小为:" + SQ.size());
				// printTree(node);
				count++;
				dfs(node, h);
				count--;
				node = preReverseNode(node, SQ.pop());
				// System.out.println("恢复上一次查找 此时 i " + i + " SQ 的大小为:" + SQ.size());
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

			if (no != null && no == tempnode.parentChild) {
				// System.out.println("2222");
				if (no.leftChild == tempnode) {
					// System.out.println("左孩子remove");
					no.leftChild = null;

					break;
				} else if (no.centerChild == tempnode) {
					// System.out.println("中心孩子remove");
					no.centerChild = null;
					break;
				} else if (no.rightChild == tempnode) {
					// System.out.println("右孩子remove");
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
	public static List<TreeNode[]> divNodeByNums(TreeNode node, int pc) {
		if (node == null) {
			return null;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(node);
		while (!queue.isEmpty() && queue.size() <= pc) {
			int levelnum = queue.size();
			for (int i = 0; i < levelnum; i++) {
				TreeNode temp = queue.poll();
				if (temp.leftChild != null) {
					queue.offer(temp.leftChild);
				}
				if (temp.centerChild != null) {
					queue.offer(temp.centerChild);
				}
				if (temp.rightChild != null) {
					queue.offer(temp.rightChild);
				}
				if (queue.size() > pc) {
					break;
				}
			}
		}
		return divNodeByNums(queue);
	}

	public static List<TreeNode[]> divNodeByNums(Queue<TreeNode> queue) {
		List<TreeNode[]> nodelist = new ArrayList<>();

		List<TreeNode> list = new ArrayList<>();
	 
		Iterator<TreeNode> it = queue.iterator();
		while (it.hasNext()) {
			list.add(it.next());
		}
		Collections.sort(list, new NodeComparatorByNum());
		TreeNode[] temp = new TreeNode[] { list.remove(0), list.remove(1) };
		nodelist.add(temp);
		TreeNode[] temp1 = new TreeNode[] { list.remove(0), list.remove(1) };
		nodelist.add(temp1);
		for (int i = 0; i < list.size(); i++) {
			TreeNode[] te = new TreeNode[] { list.get(i) };
			nodelist.add(te);
		}
		return nodelist;
	}

	public static void showQueue(List<TreeNode[]> queue) {
		for(int i=0;i<queue.size();i++)
		{
			if(queue.get(i).length==1)
			{
				System.out.println("case "+(i+1));
				TreeDiv.printTree(queue.get(i)[0]);
			}else
			{
				System.out.println("case "+(i+1));
				for(int j=0;j<queue.get(i).length;j++)
				{
					System.out.println("     case "+j);
					TreeDiv.printTree(queue.get(i)[j]);
				 
				}
			}
			System.out.println("========");
			System.out.println();
		}
	}
}

class NodeComparatorByNum implements Comparator<TreeNode> {
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