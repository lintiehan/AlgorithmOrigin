package Test_new;

import java.util.ArrayList;
import java.util.Collections;

public class BuildBinary {

	/**
	 * @param node
	 * @param data
	 * @param childrens
	 */
	public static void buildTree(TreeNode node, int i, ArrayList<TreeNode> childrens, int[] a) {
		if (node == null) {
			node = new TreeNode(a[i]);
			childrens.add(node);
		} else {
			Collections.shuffle(childrens);
			TreeNode temp = childrens.remove(0);
			temp.leftChild = new TreeNode(a[i]);
			temp.rightChild = new TreeNode(a[i + 1]);
			childrens.add(temp.leftChild);
			childrens.add(temp.rightChild);
		}

	}

	static TreeNode root;

	public BuildBinary() {
		root = new TreeNode(1);
		// TODO Auto-generated constructor stub
	}

	public static TreeNode buildRamdonBT() {

		BinaryTree bt = new BinaryTree();

		ArrayList<TreeNode> childrens = new ArrayList<>();
		childrens.add(bt.root);
		int[] a = new int[Test_Algorithm1.num];
		for (int i = 0; i < Test_Algorithm1.num; i++) {
			a[i] = i + 2;
		}
		for (int i = 0; i < a.length - 1; i = i + 2) {
			buildTree(bt.root, i, childrens, a);
		}
		bt.SetLable(bt.root);
		return bt.root;
	}

	// set label
	public void setString(TreeNode subTree) {
		if (subTree.leftChild == null && subTree.rightChild == null) {
			subTree.label = "TLeafL";
		} else
			subTree.label = "TLeafN";
	}

	// set label
	public void SetLable(TreeNode subTree) {
		if (subTree != null) {
			setString(subTree);
			SetLable(subTree.leftChild);
			SetLable(subTree.rightChild);
		}
	}

	public static void main(String[] args) {
		// int[] a =
		// {2,4,5,6,7,89,10,20,11,23,15,14,15,13,16,15,18,48,20,46,99,75,15,5,7,6,101};
		int[] a = new int[1024];
		for (int i = 0; i < 1023; i++) {
			a[i] = i + 1;
		}
		BinaryTree bt = new BinaryTree();
		ArrayList<TreeNode> childrens = new ArrayList<>();

		for (int ii = 1; ii <= 100; ii++) {
			for (int i = 0; i < a.length - 1; i = i + 2) {
				buildTree(bt.root, i, childrens, a);
			}

			childrens.clear();
			bt.root = null;
		}

		bt.printBinaryTree(bt.root, 100, 0);
	}

}