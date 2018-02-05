package Test_new1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

import org.omg.Messaging.SyncScopeHelper;

public class BinaryTree {
	static TreeNode root = null;
	static int count = 0;
	static ArrayList<TreeNode> SaveNode = new ArrayList<>();

	public BinaryTree() {
		root = new TreeNode(1, "");
	}

	public void createBTree(TreeNode root) {
		TreeNode newNode2 = new TreeNode(2, "");
		TreeNode newNode3 = new TreeNode(3, "");
		TreeNode newNode4 = new TreeNode(4, "");
		TreeNode newNode5 = new TreeNode(5, "");
		TreeNode newNode6 = new TreeNode(6, "");
		TreeNode newNode7 = new TreeNode(7, "");
		TreeNode newNode8 = new TreeNode(8, "");
		TreeNode newNode9 = new TreeNode(9, "");
		TreeNode newNode10 = new TreeNode(10, "");
		TreeNode newNode11 = new TreeNode(11, "");
		TreeNode newNode12 = new TreeNode(12, "");
		TreeNode newNode13 = new TreeNode(13, "");
		TreeNode newNode14 = new TreeNode(14, "");
		TreeNode newNode15 = new TreeNode(15, "");
		TreeNode newNode16 = new TreeNode(16, "");
		TreeNode newNode17 = new TreeNode(17, "");
		TreeNode newNode18 = new TreeNode(18, "");
		TreeNode newNode19 = new TreeNode(19, "");
		TreeNode newNode20 = new TreeNode(20, "");
		TreeNode newNode21 = new TreeNode(21, "");
		TreeNode newNode22 = new TreeNode(22, "");
		TreeNode newNode23 = new TreeNode(23, "");
		TreeNode newNode24 = new TreeNode(24, "");
		TreeNode newNode25 = new TreeNode(25, "");
		TreeNode newNode26 = new TreeNode(26, "");
		TreeNode newNode27 = new TreeNode(27, "");
		TreeNode newNode28 = new TreeNode(28, "");
		TreeNode newNode29 = new TreeNode(29, "");
		TreeNode newNode30 = new TreeNode(30, "");
		TreeNode newNode31 = new TreeNode(31, "");
		TreeNode newNode32 = new TreeNode(32, "");
		TreeNode newNode33 = new TreeNode(33, "");
		TreeNode newNode34 = new TreeNode(34, "");
		TreeNode newNode35 = new TreeNode(35, "");
		TreeNode newNode36 = new TreeNode(36, "");
		TreeNode newNode37 = new TreeNode(37, "");
		TreeNode newNode38 = new TreeNode(38, "");
		TreeNode newNode39 = new TreeNode(39, "");
		root.leftChild = newNode2;
		root.rightChild = newNode3;

		newNode2.leftChild = newNode4;
		newNode2.rightChild = newNode5;
		newNode4.leftChild = newNode8;
		newNode4.rightChild = newNode9;
		newNode5.leftChild = newNode10;
		newNode5.rightChild = newNode11;
		newNode11.leftChild = newNode16;
		newNode11.rightChild = newNode17;
		newNode17.leftChild = newNode24;
		newNode17.rightChild = newNode25;
		newNode16.leftChild = newNode22;
		newNode16.rightChild = newNode23;
		newNode23.leftChild = newNode28;
		newNode23.rightChild = newNode29;
		newNode28.leftChild = newNode32;
		newNode28.rightChild = newNode33;
		newNode8.leftChild = newNode14;
		newNode8.rightChild = newNode15;
		newNode15.leftChild = newNode20;
		newNode15.rightChild = newNode21;
		newNode14.leftChild = newNode18;
		newNode14.rightChild = newNode19;
		newNode19.leftChild = newNode26;
		newNode19.rightChild = newNode27;
		newNode27.leftChild = newNode30;
		newNode27.rightChild = newNode31;
		newNode30.leftChild = newNode34;
		newNode30.rightChild = newNode35;
		newNode35.leftChild = newNode38;
		newNode35.rightChild = newNode39;
		newNode31.leftChild = newNode36;
		newNode31.rightChild = newNode37;
		newNode3.leftChild = newNode6;
		newNode3.rightChild = newNode7;
		newNode7.leftChild = newNode12;
		newNode7.rightChild = newNode13;

		SetLable(root);
	}

	public static void setString(TreeNode subTree) {
		if (subTree.leftChild == null && subTree.rightChild == null) {
			subTree.label = "TLeafL";
		} else
			subTree.label = "TLeafN";
		subTree.isVisted = true;
	}

	public static void SetLable(TreeNode subTree) {
		if (subTree != null) {
			setString(subTree);
			SetLable(subTree.leftChild);
			SetLable(subTree.rightChild);
		}
	}

	public int bheight() {
		return bheight(root);
	}

	public static int bheight(TreeNode subTree) {
		if (subTree == null) {
			return 0;// 騾貞ｽ堤ｻ捺據 遨ｺ譬鷹ｫ伜ｺｦ荳ｿ0
		} else {
			int i = bheight(subTree.leftChild);
			int j = bheight(subTree.rightChild);
			return (i < j) ? (j + 1) : (i + 1);
		}
	}

	public static int theight(TreeNode subTree) {
		if (subTree == null) {
			return 0;// 騾貞ｽ堤ｻ捺據 遨ｺ譬鷹ｫ伜ｺｦ荳ｿ0
		} else {
			int i = theight(subTree.leftChild);
			int j = theight(subTree.centerChild);
			int k = theight(subTree.rightChild);
			int result = Math.max(Math.max(i, j), k);
			return result + 1;
		}
	}

	public TreeNode parent(TreeNode element) {
		return (root == null || root == element) ? null : parent(root, element);
	}

	public static TreeNode parent(TreeNode subTree, TreeNode element) {
		if (subTree == null) {
			return null;
		}
		if (subTree.leftChild == element || subTree.rightChild == element) {
			// 霑泌屓辷ｶ闃らせ蝨ｰ蝮ｿ
			return subTree;
		}
		TreeNode p;
		// 邇ｰ蝨ｨ蟾ｦ蟄撰ｿｽ?�ｿｽ荳ｭ謇ｾ?�ｿｽ�ｿｽ螯よ棡蟾ｦ蟄撰ｿｽ?�ｿｽ荳ｭ豐｡譛画伽蛻ｰ?�ｿｽ�ｿｽ迪懷芦蜿ｳ蟄撰ｿｽ?�ｿｽ蜴ｻ謇ｾ
		if ((p = parent(subTree.leftChild, element)) != null) {
			// 騾貞ｽ貞惠蟾ｦ蟄撰ｿｽ?�ｿｽ荳ｭ謳懃ｴｿ
			return p;
		} else {
			// 騾貞ｽ貞惠蜿ｳ蟄撰ｿｽ?�ｿｽ荳ｭ謳懃ｴｿ
			return parent(subTree.rightChild, element);
		}
	}

	public static boolean isLeaf(TreeNode node) {
		if (node != null) {
			if (node.label == "TLeafL" || node.isVisted == true)
				return true;
			isLeaf(node.leftChild);
			isLeaf(node.rightChild);

		}
		return false;
	}

	public static TreeNode MergeAll(TreeNode subTree) throws Exception {
		TreeNode subTree0 = Merge(subTree);
		reset();
		while (isLeaf(subTree0)) {

			// System.out.println("驕榊紙谺｡謨ｰ" + count);
			TreeNode temp = Merge2(subTree0, subTree0);
			subTree0 = temp;
			reset();
			count++;
		}
		return subTree0;
	}

	public static TreeNode Merge(TreeNode subTree) throws Exception {
		if (subTree != null && subTree.leftChild != null && subTree.rightChild != null) {
			Merge(subTree.leftChild);
			Merge(subTree.rightChild);
			TreeNode parent = parent(root, subTree);
			/*
			 * System.out.println( "Merge------>謇ｧ陦檎噪闃らせ?�ｿｽ?" + subTree.key + " �ｿｽ?遲ｾ荳ｺ:"
			 * + subTree.label + " 譏ｯ蜷ｦ蜿ｯ謫搾ｿｽ?-----" + subTree.isVisted);
			 * System.out.println("Merge------>ﾖｴ蟾ｦ蟄ｩ�ｿｽ?" + subTree.leftChild.key +
			 * " �ｿｽ?遲ｾ荳ｺ:" + subTree.leftChild.label);
			 * System.out.println("Merge------>ﾖｴﾖｴ蜿ｳ蟄ｩ�ｿｽ?" + subTree.rightChild.key +
			 * " �ｿｽ?遲ｾ荳ｺ:" + subTree.rightChild.label);
			 */
			if (parent != null && subTree.isVisted == true && subTree.leftChild.label == "TLeafL"
					&& subTree.rightChild.label == "TLeafL") {

				parent.isVisted = false;
				SaveNode.add(parent);
				subTree = twinmeger(parent, subTree);

			} else if (parent != null && subTree.isVisted == true && subTree.leftChild.label == "TLeafL"
					&& subTree.rightChild.label == "TLeafN") {

				parent.isVisted = false;
				SaveNode.add(parent);
				subTree = rightmeger(parent, subTree);

			} else if (parent != null && subTree.isVisted == true && subTree.leftChild.label == "TLeafN"
					&& subTree.rightChild.label == "TLeafL") {

				parent.isVisted = false;
				SaveNode.add(parent);
				subTree = leftmeger(parent, subTree);

			} else if (parent == null && subTree.isVisted == true && subTree.leftChild.label == "TLeafL"
					&& subTree.rightChild.label == "TLeafN") {

				TreeNode newnode = new TreeNode();
				TreeNode temp = new TreeNode();

				newnode.leftChild = subTree.rightChild.leftChild;
				newnode.rightChild = subTree.rightChild.rightChild;
				newnode.centerChild = temp;

				temp.leftChild = new TreeNode(subTree.leftChild.key);
				temp.rightChild = new TreeNode(subTree.rightChild.key);
				temp.centerChild = new TreeNode(subTree.key);
				temp.label = "TNodeR";
				temp.key = 999;

				newnode.label = "TNodeR";
				newnode.key = 999;
				newnode.isVisted = true;

				/*
				 * System.out.println("Merge-root-TNodeR-->" + newnode.leftChild.key + " " +
				 * newnode.centerChild.key + " " + newnode.rightChild.key);
				 */

				subTree = newnode;

			} else if (parent == null && subTree.isVisted == true && subTree.leftChild.label == "TLeafN"
					&& subTree.rightChild.label == "TLeafL") {

				TreeNode newnode = new TreeNode();
				TreeNode temp = new TreeNode();

				newnode.leftChild = subTree.leftChild.leftChild;
				newnode.rightChild = subTree.leftChild.rightChild;
				newnode.centerChild = temp;

				temp.leftChild = new TreeNode(subTree.leftChild.key);
				temp.rightChild = new TreeNode(subTree.rightChild.key);
				temp.centerChild = new TreeNode(subTree.key);
				temp.label = "TNodeL";
				temp.key = 777;

				newnode.label = "TNodeL";
				newnode.key = 777;
				newnode.isVisted = true;

				/*
				 * System.out.println("Merge-root-TNodeL-->" + newnode.leftChild.key + " " +
				 * newnode.centerChild.key + " " + newnode.rightChild.key);
				 * System.out.println();
				 */
				subTree = newnode;
			} else if (parent == null && subTree.isVisted == true && subTree.leftChild.label == "TLeafL"
					&& subTree.rightChild.label == "TLeafL") {

				TreeNode newnode = new TreeNode();
				newnode.leftChild = new TreeNode(subTree.leftChild.key);
				newnode.rightChild = new TreeNode(subTree.rightChild.key);
				newnode.centerChild = new TreeNode(subTree.key);
				newnode.label = "TNodeN";
				newnode.key = 888;

				/*
				 * System.out.println("Merge-root-TNodeN-->" + newnode.leftChild.key + " " +
				 * newnode.centerChild.key + " " + newnode.rightChild.key);
				 * System.out.println();
				 */
				subTree = newnode;
			}
		}
		return subTree;
	}

	public static TreeNode Merge2(TreeNode newroot, TreeNode subTree) throws Exception {
		if (subTree != null && subTree.leftChild != null && subTree.rightChild != null) {
			Merge2(newroot, subTree.leftChild);
			Merge2(newroot, subTree.rightChild);
			TreeNode parent = parent(newroot, subTree);

			/*
			 * System.out.println("Merge2------>ﾖｴ謇ｧ陦檎噪闃らせ:" + subTree.key +
			 * " �ｿｽ?遲ｾ荳ｺ?�ｿｽ?" + subTree.label + " 譏ｯ蜷ｦ蜿ｯ謫堺ｽ懶ｿｽ?-----" + subTree.isVisted);
			 * System.out.println("Merge2------>ﾖｴ蟾ｦ蟄ｩ�ｿｽ?:" + subTree.leftChild.key +
			 * " �ｿｽ?遲ｾ荳ｺ?�ｿｽ?" + subTree.leftChild.label+ " 譏ｯ蜷ｦ蜿ｯ謫堺ｽ懶ｿｽ?-----" +
			 * subTree.leftChild.isVisted); System.out.println("Merge2------>ﾖｴ蜿ｳ蟄ｩ�ｿｽ?:" +
			 * subTree.rightChild.key + " �ｿｽ?遲ｾ荳ｺ?�ｿｽ?" + subTree.rightChild.label+
			 * " 譏ｯ蜷ｦ蜿ｯ謫堺ｽ懶ｿｽ?-----" + subTree.rightChild.isVisted); System.out.println();
			 */ // kind 1
			if (isAllow(subTree)
					&& (subTree.isVisted == true && subTree.leftChild.label == "TLeafL"
							&& subTree.rightChild.label != "TLeafN")
					|| (subTree.isVisted == true && subTree.leftChild.label == ""
							&& subTree.rightChild.label != "TLeafN")) {
				// System.out.println("Merge2----2");
				subTree = rightmeger1(parent, subTree);
			}
			// kind 2
			else if ((isAllow(subTree) && subTree.isVisted == true && subTree.leftChild.label != "TLeafN"
					&& subTree.rightChild.label == "TLeafL")
					|| (isAllow(subTree) && subTree.isVisted == true && subTree.leftChild.label != "TLeafN"
							&& subTree.rightChild.label == "")) {
				// System.out.println("Merge2----4");
				subTree = leftmeger1(parent, subTree);

			}
			// kind 3
			else if (isAllow(subTree) && subTree.isVisted == true && subTree.leftChild.label == "TLeafN"
					&& subTree.rightChild.label == "TNodeN") {
				TreeNode newnode = new TreeNode();
				TreeNode temp = new TreeNode();
				if (subTree.label == "TLeafN") {
					// System.out.println("Merge2----5");
					newnode.leftChild = subTree.leftChild.leftChild;
					newnode.rightChild = subTree.leftChild.rightChild;
					newnode.centerChild = temp;
					temp.leftChild = new TreeNode(subTree.leftChild.key);
					temp.rightChild = subTree.rightChild;
					temp.centerChild = new TreeNode(subTree.key);
					temp.label = "TNodeL";
					temp.key = 777;
					newnode.label = "TNodeL";
					newnode.key = 777;
					newnode.isVisted = true;
					if (newnode.rightChild.label == "TLeafL") {
						newnode.rightChild.label = "";
					}
					if (newnode.leftChild.label == "TLeafL") {
						newnode.leftChild.label = "";
					}
				} else {
					// System.out.println("Merge2----6");
					newnode.leftChild = subTree.leftChild.leftChild;
					newnode.rightChild = subTree.leftChild.rightChild;
					newnode.centerChild = temp;
					temp.leftChild = new TreeNode(subTree.leftChild.key);
					temp.rightChild = subTree.rightChild;
					temp.centerChild = subTree.centerChild;
					temp.label = "TNodeL";
					temp.key = 777;
					newnode.label = "TNodeL";
					newnode.key = 777;
					newnode.isVisted = true;
					if (newnode.rightChild.label == "TLeafL") {
						newnode.rightChild.label = "";
					}
					if (newnode.leftChild.label == "TLeafL") {
						newnode.leftChild.label = "";
					}
				}

				if (parent == null) {
					subTree = newnode;
				} else {
					parent.isVisted = false;
					SaveNode.add(parent);
					if (parent.leftChild == subTree) {
						parent.leftChild = newnode;
					} else {
						parent.rightChild = newnode;
					}
				}

			}
			// kind 4
			else if (isAllow(subTree) && subTree.isVisted == true && subTree.leftChild.label == "TNodeN"
					&& subTree.rightChild.label == "TLeafN") {

				TreeNode newnode = new TreeNode();
				TreeNode temp = new TreeNode();
				if (subTree.label == "TLeafN") {
					// System.out.println("Merge2----7");
					newnode.leftChild = subTree.rightChild.leftChild;
					newnode.rightChild = subTree.rightChild.rightChild;
					newnode.centerChild = temp;
					temp.leftChild = subTree.leftChild;
					temp.rightChild = new TreeNode(subTree.rightChild.key);
					temp.centerChild = new TreeNode(subTree.key);
					temp.label = "TNodeR";
					temp.key = 999;
					newnode.label = "TNodeR";
					newnode.key = 999;
					newnode.isVisted = true;
					if (newnode.rightChild.label == "TLeafL") {
						newnode.rightChild.label = "";
					}
					if (newnode.leftChild.label == "TLeafL") {
						newnode.leftChild.label = "";
					}

				} else {
					// System.out.println("Merge2----8");
					newnode.leftChild = subTree.rightChild.leftChild;
					newnode.rightChild = subTree.rightChild.rightChild;
					newnode.centerChild = temp;
					temp.leftChild = subTree.leftChild;
					temp.rightChild = new TreeNode(subTree.rightChild.key);
					temp.centerChild = subTree.centerChild;

					temp.label = "TNodeR";
					temp.key = 999;
					newnode.label = "TNodeR";
					newnode.key = 999;
					newnode.isVisted = true;
					if (newnode.rightChild.label == "TLeafL") {
						newnode.rightChild.label = "";
					}
					if (newnode.leftChild.label == "TLeafL") {
						newnode.leftChild.label = "";
					}
				}

				if (parent == null) {
					subTree = newnode;
				} else {
					parent.isVisted = false;
					SaveNode.add(parent);
					if (parent.leftChild == subTree) {
						parent.leftChild = newnode;
					} else {
						parent.rightChild = newnode;
					}
				}
			}
			// kind 5
			else if (isAllow(subTree) && subTree.isVisted == true && subTree.label == "TLeafN"
					&& subTree.leftChild.label == "TNodeN" && subTree.rightChild.label == "TNodeN") {
				// System.out.println("Merge2----10");
				TreeNode newnode = new TreeNode();
				newnode.leftChild = subTree.leftChild;
				newnode.centerChild = new TreeNode(subTree.key);
				newnode.rightChild = subTree.rightChild;
				/*
				 * System.out.println("kind 5-->"+newnode.leftChild.label
				 * +" "+newnode.centerChild.label +" "+newnode.rightChild.label);
				 */
				newnode.label = getLabel(newnode);
				newnode.key = getKey(newnode.label);
				if (parent == null) {
					subTree = newnode;
				} else {
					parent.isVisted = false;
					SaveNode.add(parent);

					if (parent.leftChild == subTree) {
						parent.leftChild = newnode;
					} else {
						parent.rightChild = newnode;
					}
				}

			}
			// kind 6
			else if ((isAllow(subTree) && subTree.isVisted == true && subTree.label == "TLeafN"
					&& subTree.leftChild.label == "TNodeL" && subTree.rightChild.label == "TNodeN")
					|| (isAllow(subTree) && subTree.isVisted == true && subTree.label == "TLeafN"
							&& subTree.leftChild.label == "TNodeR" && subTree.rightChild.label == "TNodeN")) {

				// System.out.println("Merge2----91");
				TreeNode newnode = new TreeNode();
				TreeNode temp = new TreeNode();

				newnode.leftChild = subTree.leftChild.leftChild;
				newnode.rightChild = subTree.leftChild.rightChild;
				newnode.centerChild = temp;

				temp.leftChild = subTree.leftChild.centerChild;
				temp.centerChild = new TreeNode(subTree.key);
				temp.rightChild = subTree.rightChild;
				temp.label = getLabel(temp);
				/*
				 * System.out.println("kind 6-->"+newnode.leftChild.label
				 * +" "+newnode.centerChild.label +" "+newnode.rightChild.label);
				 */
				temp.key = getKey(temp.label);
				if (temp.rightChild.isVisted == true) {
					temp.rightChild.isVisted = false;
				}
				newnode.label = "TNodeL";
				newnode.key = 777;
				newnode.isVisted = true;
				if (parent == null) {
					subTree = newnode;
				} else {
					parent.isVisted = false;
					SaveNode.add(parent);
					if (parent.leftChild == subTree) {
						parent.leftChild = newnode;
					} else {
						parent.rightChild = newnode;
					}
				}
			}
			// kind 7
			else if ((isAllow(subTree) && subTree.isVisted == true && subTree.label == "TLeafN"
					&& subTree.leftChild.label == "TNodeN" && subTree.rightChild.label == "TNodeL")
					|| (isAllow(subTree) && subTree.isVisted == true && subTree.label == "TLeafN"
							&& subTree.leftChild.label == "TNodeN" && subTree.rightChild.label == "TNodeR")) {

				// System.out.println("Merge2----92");
				TreeNode newnode = new TreeNode();
				TreeNode temp = new TreeNode();

				newnode.leftChild = subTree.rightChild.leftChild;
				newnode.rightChild = subTree.rightChild.rightChild;
				newnode.centerChild = temp;

				temp.leftChild = subTree.leftChild;
				temp.centerChild = new TreeNode(subTree.key);
				temp.rightChild = subTree.rightChild.centerChild;
				temp.label = getLabel(temp);
				/*
				 * System.out.println("kind 7-->"+newnode.leftChild.label
				 * +" "+newnode.centerChild.label +" "+newnode.rightChild.label);
				 */
				temp.key = getKey(temp.label);
				if (temp.rightChild.isVisted == true) {
					temp.rightChild.isVisted = false;
				}
				newnode.label = "TNodeR";
				newnode.key = 999;
				newnode.isVisted = true;
				if (parent == null) {
					subTree = newnode;
				} else {
					parent.isVisted = false;
					SaveNode.add(parent);
					if (parent.leftChild == subTree) {
						parent.leftChild = newnode;
					} else {
						parent.rightChild = newnode;
					}
				}
			}
			// kind 8
			else if (isAllow(subTree) && subTree.isVisted == true && subTree.label != "TLeafN"
					&& subTree.leftChild.label == "TNodeN" && subTree.rightChild.label == "TNodeN") {
				// System.out.println("Merge2----1144444");

				TreeNode newnode = new TreeNode();

				newnode.leftChild = subTree.leftChild;
				newnode.centerChild = subTree.centerChild;

				newnode.rightChild = subTree.rightChild;
				newnode.label = getLabel(newnode);
				/*
				 * System.out.println("kind 8-->"+newnode.leftChild.label
				 * +" "+newnode.centerChild.label +" "+newnode.rightChild.label);
				 */
				newnode.key = getKey(newnode.label);
				// System.out.println("譁ｰ闃らせ�ｿｽ?遲ｾ?�ｿｽ?" + newnode.label + newnode.key);
				if (parent != null && parent.leftChild == subTree) {
					parent.isVisted = false;
					SaveNode.add(parent);
					// System.out.println("2");
					parent.leftChild = newnode;
				} else if (parent != null && parent.rightChild == subTree) {
					parent.isVisted = false;
					SaveNode.add(parent);
					// System.out.println("3");
					parent.rightChild = newnode;
				} else if (parent == null) {
					subTree = newnode;
				}
			}
			// kind 9
			else if ((isAllow(subTree) && subTree.isVisted == true && subTree.label != "TLeafN"
					&& subTree.leftChild.label == "TNodeL" && subTree.rightChild.label == "TNodeN")
					|| (isAllow(subTree) && subTree.isVisted == true && subTree.label != "TLeafN"
							&& subTree.leftChild.label == "TNodeR" && subTree.rightChild.label == "TNodeN")) {
				// System.out.println("Merge2----12");
				TreeNode newnode = new TreeNode();
				TreeNode temp = new TreeNode();
				newnode.leftChild = subTree.leftChild.leftChild;
				newnode.rightChild = subTree.leftChild.rightChild;
				newnode.centerChild = temp;

				temp.leftChild = subTree.leftChild.centerChild;
				temp.centerChild = subTree.centerChild;
				temp.rightChild = subTree.rightChild;
				temp.label = getLabel(temp);
				// System.out.println("kind 9-->"+temp.leftChild.label
				// +" "+temp.centerChild.label
				// +" "+temp.rightChild.label);
				temp.key = getKey(temp.label);

				newnode.label = "TNodeL";
				newnode.key = 777;
				newnode.isVisted = true;
				// System.out.println("譁ｰ闃らせ�ｿｽ?遲ｾ?�ｿｽ?" + newnode.label + newnode.key);

				if (parent == null) {
					// System.out.println("11");
					subTree = newnode;
				} else if (parent != null && parent.leftChild == subTree) {
					// System.out.println("12");
					parent.isVisted = false;
					SaveNode.add(parent);
					parent.leftChild = newnode;
				} else if (parent != null && parent.rightChild == subTree) {
					// System.out.println("13");
					parent.isVisted = false;
					SaveNode.add(parent);
					parent.rightChild = newnode;
				}
			}
			// kind 10
			else if ((isAllow(subTree) && subTree.isVisted == true && subTree.label != "TLeafN"
					&& subTree.leftChild.label == "TNodeN" && subTree.rightChild.label == "TNodeL")
					|| (isAllow(subTree) && subTree.isVisted == true && subTree.label != "TLeafN"
							&& subTree.leftChild.label == "TNodeN" && subTree.rightChild.label == "TNodeR")) {
				// System.out.println("Merge2----13");
				TreeNode newnode = new TreeNode();
				TreeNode temp = new TreeNode();
				newnode.leftChild = subTree.rightChild.leftChild;
				newnode.rightChild = subTree.rightChild.rightChild;
				newnode.centerChild = temp;

				temp.rightChild = subTree.rightChild.centerChild;
				temp.centerChild = subTree.centerChild;
				temp.leftChild = subTree.leftChild;
				temp.label = getLabel(temp);
				temp.key = getKey(temp.label);
				// System.out.println("kind 10-->"+temp.leftChild.label
				// +" "+temp.centerChild.label
				// +" "+temp.rightChild.label);
				newnode.label = "TNodeR";
				newnode.key = 999;
				newnode.isVisted = true;
				// System.out.println("譁ｰ闃らせ�ｿｽ?遲ｾ?�ｿｽ?" + newnode.label + newnode.key);
				if (newnode.leftChild.label == "TLeafL") {
					newnode.leftChild.label = "";
				} else if (newnode.rightChild.label == "TLeafL") {
					newnode.rightChild.label = "";
				}
				if (parent == null) {
					// System.out.println("11");
					subTree = newnode;
				} else if (parent != null && parent.leftChild == subTree) {
					// System.out.println("12");

					parent.isVisted = false;
					SaveNode.add(parent);

					parent.leftChild = newnode;
				} else if (parent != null && parent.rightChild == subTree) {
					// System.out.println("13");

					parent.isVisted = false;
					SaveNode.add(parent);

					parent.rightChild = newnode;
				}
			}

			// kind 11
			else if ((isAllow(subTree) && subTree.isVisted == true && subTree.label == "TLeafN"
					&& subTree.leftChild.label == "TNodeL" && subTree.rightChild.label == "TNodeR")
					|| (isAllow(subTree) && subTree.isVisted == true && subTree.label == "TLeafN"
							&& subTree.leftChild.label == "TNodeR" && subTree.rightChild.label == "TNodeL")) {

				System.out.println("R-TL-L L-TL-R");
				TreeNode newnode = new TreeNode();
				TreeNode temp = new TreeNode();

				newnode.leftChild = subTree.leftChild.leftChild;
				newnode.rightChild = subTree.leftChild.rightChild;
				newnode.centerChild = temp;

				temp.leftChild = subTree.leftChild.centerChild;
				temp.centerChild = new TreeNode(subTree.key);
				temp.rightChild = subTree.rightChild;
				temp.label = getLabel(temp);

				temp.key = getKey(temp.label);

				// System.out.println("kind 11-->"+temp.leftChild.label
				// +" "+temp.centerChild.label
				// +" "+temp.rightChild.label);
				//

				if (temp.rightChild.isVisted == true) {
					temp.rightChild.isVisted = false;
				}
				newnode.label = "TNodeL";
				newnode.key = 777;
				newnode.isVisted = true;
				if (parent == null) {
					SaveNode.add(newnode);
					subTree = newnode;
				} else {
					parent.isVisted = false;
					SaveNode.add(parent);
					if (parent.leftChild == subTree) {
						parent.leftChild = newnode;
					} else {
						parent.rightChild = newnode;
					}
				}
			}

		}
		return subTree;
	}

	private static TreeNode leftmeger1(TreeNode parent, TreeNode subTree) {
		TreeNode newnode = new TreeNode();
		TreeNode temp = new TreeNode();
		if (subTree.leftChild.label == "TNodeN") {
			if (subTree.label == "TLeafN") {
				// System.out.println("leftmeger1--->TNodeN 111 ");
				newnode.leftChild = subTree.leftChild;
				newnode.rightChild = new TreeNode(subTree.rightChild.key);
				newnode.centerChild = new TreeNode(subTree.key);
				newnode.label = "TNodeN";
				newnode.key = 888;
				newnode.isVisted = false;
			} else {
				// System.out.println("leftmeger1--->TNodeN 222 ");
				newnode.leftChild = subTree.leftChild;
				newnode.centerChild = subTree.centerChild;

				newnode.rightChild = subTree.rightChild;
				newnode.label = getLabel(newnode);
				newnode.key = getKey(newnode.label);
				newnode.isVisted = false;
			}

			if (newnode.leftChild.label == "TLeafL") {
				newnode.leftChild.label = "";
			}
			if (newnode.rightChild.label == "TLeafL") {
				newnode.rightChild.label = "";
			}
		}

		else if (subTree.leftChild.label == "TNodeL") {
			// System.out.println("leftmeger1--->TNodeL");
			if (subTree.label == "TLeafN") {
				newnode.leftChild = subTree.leftChild.leftChild;
				newnode.rightChild = subTree.leftChild.rightChild;
				newnode.centerChild = temp;

				temp.leftChild = subTree.leftChild.centerChild;
				temp.rightChild = new TreeNode(subTree.rightChild.key);
				temp.centerChild = new TreeNode(subTree.key);

				temp.label = "TNodeL";
				temp.key = 777;
				newnode.label = "TNodeL";
				newnode.key = 777;
			} else {
				newnode.leftChild = subTree.leftChild.leftChild;
				newnode.rightChild = subTree.leftChild.rightChild;
				newnode.centerChild = temp;

				temp.leftChild = subTree.leftChild.centerChild;
				temp.rightChild = new TreeNode(subTree.rightChild.key);
				temp.centerChild = subTree.centerChild;

				temp.label = "TNodeL";
				temp.key = 777;
				newnode.label = "TNodeL";
				newnode.key = 777;
			}

			newnode.isVisted = true;
			if (newnode.leftChild.label == "TLeafL") {
				newnode.leftChild.label = "";
			} else if (newnode.rightChild.label == "TLeafL") {
				newnode.rightChild.label = "";
			}
		} else if (subTree.leftChild.label == "TNodeR") {
			// System.out.println("leftmeger1--->TNodeR");

			if (subTree.label == "TLeafN") {
				newnode.leftChild = subTree.leftChild.leftChild;
				newnode.rightChild = subTree.leftChild.rightChild;
				newnode.centerChild = temp;

				temp.leftChild = subTree.leftChild.centerChild;
				temp.rightChild = new TreeNode(subTree.rightChild.key);
				temp.centerChild = new TreeNode(subTree.key);

				temp.label = "TNodeR";
				temp.key = 999;
				newnode.label = "TNodeL";
				newnode.key = 777;
				newnode.isVisted = true;
			} else {
				newnode.leftChild = subTree.leftChild.leftChild;
				newnode.rightChild = subTree.leftChild.rightChild;
				newnode.centerChild = temp;

				temp.leftChild = subTree.leftChild.centerChild;
				temp.rightChild = new TreeNode(subTree.rightChild.key);
				temp.centerChild = subTree.centerChild;

				temp.label = "TNodeL";
				temp.key = 777;
				newnode.label = "TNodeL";
				newnode.key = 777;
				newnode.isVisted = true;
			}

			if (newnode.leftChild.label == "TLeafL") {
				newnode.leftChild.label = "";
			}
			if (newnode.rightChild.label == "TLeafL") {
				newnode.rightChild.label = "";
			}
		}
		if (parent == null) {
			subTree = newnode;
		} else {
			parent.isVisted = false;
			SaveNode.add(parent);
			if (parent.leftChild == subTree) {
				parent.leftChild = newnode;

			} else if (parent.rightChild == subTree) {
				parent.rightChild = newnode;

			}
		}

		return newnode;
	}

	private static TreeNode rightmeger1(TreeNode parent, TreeNode subTree) {

		TreeNode newnode = new TreeNode();
		TreeNode temp = new TreeNode();
		if (subTree.rightChild.label == "TNodeN") {
			if (subTree.label == "TLeafN") {
				// System.out.println("rightmeger1.1--->TNodeN");
				newnode.leftChild = new TreeNode(subTree.leftChild.key);
				newnode.rightChild = subTree.rightChild;
				newnode.centerChild = new TreeNode(subTree.key);
				newnode.label = "TNodeN";
				newnode.key = 888;
				// System.out.println("rightmeger1.1--->TNodeN " + newnode.leftChild.key + " " +
				// newnode.centerChild.key+" "+newnode.rightChild.key);
			} else {
				// System.out.println("rightmeger1.2--->TNodeN");
				newnode.leftChild = subTree.leftChild;
				newnode.rightChild = subTree.rightChild;
				newnode.centerChild = subTree.centerChild;
				newnode.label = getLabel(newnode);
				newnode.key = getKey(newnode.label);
				// System.out.println("rightmeger1.2--->TNodeN " + newnode.leftChild.key + " " +
				// newnode.centerChild.key+" "+newnode.rightChild.key);
			}
		} else if (subTree.rightChild.label == "TNodeR") {

			if (subTree.label == "TLeafN") {
				newnode.leftChild = subTree.rightChild.leftChild;
				newnode.rightChild = subTree.rightChild.rightChild;
				newnode.centerChild = temp;
				temp.leftChild = new TreeNode(subTree.leftChild.key);
				temp.rightChild = subTree.rightChild.centerChild;
				temp.centerChild = new TreeNode(subTree.key);
				temp.label = "TNodeR";
				temp.key = 999;
			} else {
				// System.out.println("rightmeger1.2--->TNodeN 22");
				newnode.leftChild = subTree.rightChild.leftChild;
				newnode.rightChild = subTree.rightChild.rightChild;
				newnode.centerChild = temp;

				temp.leftChild = new TreeNode(subTree.leftChild.key);
				temp.rightChild = subTree.rightChild.centerChild;
				temp.centerChild = subTree.centerChild;
				temp.label = "TNodeR";
				temp.key = 999;
			}
			newnode.label = "TNodeR";
			newnode.key = 999;
			newnode.isVisted = true;
			if (newnode.leftChild.label == "TLeafL") {

				newnode.leftChild.label = "";
			} else if (newnode.rightChild.label == "TLeafL") {
				newnode.rightChild.label = "";
			}

			/*
			 * System.out.println("rightmeger1--TNodeR->" + newnode.leftChild.key + " " +
			 * newnode.centerChild.key + " " + newnode.rightChild.key);
			 */
		} else if (subTree.rightChild.label == "TNodeL") {

			if (subTree.label == "TLeafN") {
				newnode.leftChild = subTree.rightChild.leftChild;
				newnode.rightChild = subTree.rightChild.rightChild;
				newnode.centerChild = temp;
				temp.leftChild = new TreeNode(subTree.leftChild.key);
				temp.rightChild = subTree.rightChild.centerChild;
				temp.centerChild = new TreeNode(subTree.key);
				temp.label = "TNodeR";
				temp.key = 999;
			} else {
				// System.out.println("rightmeger1.2--->TNodeN 22");
				newnode.leftChild = subTree.rightChild.leftChild;
				newnode.rightChild = subTree.rightChild.rightChild;
				newnode.centerChild = temp;
				newnode.isVisted = true;
				temp.leftChild = new TreeNode(subTree.leftChild.key);
				temp.rightChild = subTree.rightChild.centerChild;
				temp.centerChild = subTree.centerChild;
				temp.label = "TNodeL";
				temp.key = 777;
			}
			newnode.label = "TNodeR";
			newnode.key = 999;
			newnode.isVisted = true;
			/*
			 * System.out.println("rightmeger1--TNodeL->" + newnode.leftChild.key + " " +
			 * newnode.centerChild.key + " " + newnode.rightChild.key);
			 */
			if (parent != null && newnode.leftChild.label == "TLeafL") {

				newnode.leftChild.label = "";
			} else if (parent != null && newnode.rightChild.label == "TLeafL") {

				newnode.rightChild.label = "";
			}

		}

		if (parent == null) {
			subTree = newnode;
		} else {
			parent.isVisted = false;
			SaveNode.add(parent);
			if (parent.leftChild == subTree) {
				// System.out.println("2");
				parent.leftChild = newnode;
			} else {
				// System.out.println("3");
				parent.rightChild = newnode;
			}
		}

		return newnode;
	}

	private static TreeNode leftmeger(TreeNode parent, TreeNode subTree) {
		TreeNode newnode = new TreeNode();
		TreeNode temp = new TreeNode();

		newnode.leftChild = subTree.leftChild.leftChild;
		newnode.rightChild = subTree.leftChild.rightChild;
		newnode.centerChild = temp;

		temp.leftChild = new TreeNode(subTree.leftChild.key);
		temp.centerChild = new TreeNode(subTree.key);
		temp.rightChild = new TreeNode(subTree.rightChild.key);

		temp.label = "TNodeL";
		temp.key = 777;

		newnode.label = "TNodeL";
		newnode.key = 777;
		newnode.isVisted = true;

		if (parent.leftChild == subTree) {
			parent.leftChild = newnode;
		} else {
			parent.rightChild = newnode;
		}

		/*
		 * System.out.println("Merge-TNodeL-->" + newnode.key + ":" +
		 * newnode.leftChild.key + " " + newnode.centerChild.key + " " +
		 * newnode.rightChild.key); System.out.println();
		 */
		return newnode;
	}

	private static TreeNode rightmeger(TreeNode parent, TreeNode subTree) {
		TreeNode newnode = new TreeNode();
		TreeNode temp = new TreeNode();

		newnode.leftChild = subTree.rightChild.leftChild;
		newnode.rightChild = subTree.rightChild.rightChild;
		newnode.centerChild = temp;

		temp.leftChild = new TreeNode(subTree.leftChild.key);
		temp.rightChild = new TreeNode(subTree.rightChild.key);
		temp.centerChild = new TreeNode(subTree.key);
		temp.label = "TNodeR";
		temp.key = 999;

		newnode.label = "TNodeR";
		newnode.key = 999;
		newnode.isVisted = true;

		if (parent.leftChild == subTree) {
			parent.leftChild = newnode;
		} else {
			parent.rightChild = newnode;
		}

		/*
		 * System.out.println("Merge-TNodeR-->" + newnode.key + ":" +
		 * newnode.leftChild.key + " " + newnode.centerChild.key + " " +
		 * newnode.rightChild.key); System.out.println();
		 */
		return newnode;
	}

	public static boolean isAllow(TreeNode node) {
		if (node.leftChild.label == "TLeafN" && node.rightChild.label == "TLeafN")
			return false;
		if (node.leftChild.label == "TNodeR" && node.rightChild.label == "TNodeL")
			return false;
		if (node.leftChild.label == "TNodeL" && node.rightChild.label == "TNodeR")
			return false;
		if (node.leftChild.label == "TNodeL" && node.rightChild.label == "TNodeL")
			return false;
		if (node.leftChild.label == "TNodeR" && node.rightChild.label == "TNodeR")
			return false;
		return true;
	}

	private static TreeNode twinmeger(TreeNode parent, TreeNode subTree) {
		TreeNode temp = new TreeNode();
		temp.leftChild = new TreeNode(subTree.leftChild.key);
		temp.rightChild = new TreeNode(subTree.rightChild.key);
		temp.centerChild = new TreeNode(subTree.key);
		temp.label = "TNodeN";
		temp.key = 888;

		if (parent.leftChild == subTree) {
			parent.leftChild = temp;
		} else {
			parent.rightChild = temp;
		}
		/*
		 * System.out.println("Merge-TNodeN-->" + temp.key + ":" + temp.leftChild.key +
		 * " " + temp.centerChild.key + " " + temp.rightChild.key);
		 * System.out.println();
		 */
		return temp;
	}

	public static int getKey(String label) {
		int key = 0;
		if (label == "TNodeN") {
			key = 888;
		} else if (label == "TNodeL") {
			key = 777;
		} else if (label == "TNodeR") {
			key = 999;
		} else {
			key = 200;
		}
		return key;
	}

	public static String getLabel(TreeNode node) {

		String label = "";
		if (node != null && node.leftChild != null && node.centerChild != null && node.rightChild != null) {
			if (node.leftChild.label == "") {
				node.leftChild.label = "TLeafL";
			}
			if (node.centerChild.label == "") {
				node.centerChild.label = "TLeafL";
			}
			if (node.rightChild.label == "") {
				node.rightChild.label = "TLeafL";
			}
			if ((node.leftChild.label == "TNodeN" && node.rightChild.label == "TNodeN")
					|| (node.leftChild.label == "TNodeN" && node.rightChild.label == "TLeafL")
					|| (node.leftChild.label == "TLeafL" && node.rightChild.label == "TLeafL")
					|| (node.leftChild.label == "TLeafL" && node.rightChild.label == "TNodeN")) {
				label = "TNodeN";
			} else if ((node.leftChild.label == "TNodeN" && node.rightChild.label == "TNodeR")
					|| (node.leftChild.label == "TLeafL" && node.rightChild.label == "TNodeR")
					|| (node.leftChild.label == "TNodeN" && node.rightChild.label == "TNodeL")
					|| (node.leftChild.label == "TLeafL" && node.rightChild.label == "TNodeL")
					|| (node.leftChild.label == "TNodeN" && node.rightChild.label == "TLeafN")
					|| (node.leftChild.label == "TLeafL" && node.rightChild.label == "TLeafN")

					|| (node.leftChild.label == "TNodeL" && node.rightChild.label == "TLeafN")) {
				label = "TNodeR";
			} else if ((node.leftChild.label == "TNodeL" && node.rightChild.label == "TNodeN")
					|| (node.leftChild.label == "TNodeL" && node.rightChild.label == "TLeafL")
					|| (node.leftChild.label == "TNodeR" && node.rightChild.label == "TNodeN")
					|| (node.leftChild.label == "TNodeR" && node.rightChild.label == "TLeafL")
					|| (node.leftChild.label == "TLeafN" && node.rightChild.label == "TNodeN")
					|| (node.leftChild.label == "TLeafN" && node.rightChild.label == "TLeafL")

					|| (node.leftChild.label == "TLeafN" && node.rightChild.label == "TNodeR")) {
				label = "TNodeL";
			} else {
				System.out.print("隸･闃らせ襍具ｿｽ?�ｿｽ螟ｱ雍･:蟾ｦ" + node.leftChild.label);
				System.out.print(" :荳ｭ" + node.centerChild.label);
				System.out.print(" :蜿ｳ" + node.rightChild.label);
				System.out.println();
				label = "failed";
			}
			if (node.leftChild.label == "TLeafL") {
				node.leftChild.label = "";
			}
			if (node.centerChild.label == "TLeafL") {
				node.centerChild.label = "";
			}
			if (node.rightChild.label == "TLeafL") {
				node.rightChild.label = "";
			}
		}

		return label;
	}

	static void printBinaryTree(TreeNode tree, int key, int direction) {
		if (tree != null) {
			if (direction == 0) // tree譏ｯ譬ｹ闃らせ
				System.out.printf("%2d is root\n", tree.key, key);
			else {
				System.out.printf("%2d(%6s) is %2d's %6s child\n", tree.key, tree.label, key,
						direction == 1 ? "right" : "left");
			}
			printBinaryTree(tree.leftChild, tree.key, -1);
			printBinaryTree(tree.rightChild, tree.key, 1);
		}
	}

	public static int[][] printTernaryTree(TreeNode root) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		TreeNode node = null;
		TreeNode last = root;
		TreeNode nlast = root;
		if (root == null) {
			return null;
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
				result[i][j] = res.get(i).get(j);
			}
		}
		return result;
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

	public static boolean checkNode(TreeNode node) {
		if ((node.label == "TLeafN" && node.leftChild.label == "TLeafL")
				|| (node.label == "TLeafN" && node.rightChild.label == "TLeafL")) {
			return true;
		}
		return false;
	}

	// 隶ｾ鄂ｮ豈擾ｿｽ?荳ｪ闃らせ逧Лabel
	public static void reset() {
		/*
		 * Iterator<TreeNode> iterator=SaveNode.iterator(); while(iterator.hasNext()) {
		 * TreeNode temp=iterator.next(); temp.isVisted=true; }
		 */
		for (int i = 0; i < SaveNode.size(); i++) {
			SaveNode.get(i).isVisted = true;
		}
		SaveNode.clear();
	}
}
