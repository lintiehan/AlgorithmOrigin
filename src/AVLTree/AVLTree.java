package AVLTree;

public class AVLTree<T extends Comparable<T>> {
	private AVLTreeNode<T> mRoot;// 根节点

	// AVL树的节点
	class AVLTreeNode<T extends Comparable<T>> {
		T key; // 关键字
		int height;// 高度
		AVLTreeNode<T> left; // 左孩子
		AVLTreeNode<T> right;// 右孩子

		public AVLTreeNode(T key, AVLTreeNode<T> left, AVLTreeNode<T> right) {
			this.key = key;
			this.left = left;
			this.right = right;
			this.height = 0;
		}
	}

	public AVLTree() {
		mRoot = null; 
	}  

	// 获取树的高度
	private int height(AVLTreeNode<T> tree) {
		if (tree != null)
			return tree.height;
		return 0;
	}

	public int height() {
		return height(mRoot);
	}

	// 比较节点大小
	private int max(int a, int b) {
		return a > b ? a : b;
	}

	void preOrder() {
		preOrder(mRoot);
	}

	// 前序遍历
	public void preOrder(AVLTreeNode<T> tree) {
		if (tree != null) {
			System.out.println(tree.key + " ");
			preOrder(tree.left);
			preOrder(tree.right);
		}
	}

	// 中序遍历
	public void inOrder() {
		inOrder(mRoot);
	}

	// 前序遍历
	private void inOrder(AVLTreeNode<T> tree) {
		if (tree != null) {
			inOrder(tree.left); 
			System.out.println(tree.key + " ");
			inOrder(tree.right);
		}
	}

	// 后序遍历
	public void postOrder() {
		postOrder(mRoot);
	}

	// 前序遍历
	private void postOrder(AVLTreeNode<T> tree) {
		if (tree != null) {
			preOrder(tree.left);
			preOrder(tree.right);
			System.out.println(tree.key + " ");
		}
	}

	public AVLTreeNode<T> search(T key) {
		return search(mRoot, key);
	}

	// 递归查找AVL树x中键值为key的节�?
	private AVLTreeNode<T> search(AVLTreeNode<T> x, T key) {
		if (x == null)
			return x;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return search(x.left, key);
		else if (cmp > 0)
			return search(x.right, key);
		else
			return x;
	}

	public AVLTreeNode<T> iterativeSearch(T key) {
		return iterativeSearch(mRoot, key);
	}

	// 非�?�归查找AVL树x中键值为key的节�?
	private AVLTreeNode<T> iterativeSearch(AVLTreeNode<T> x, T key) {
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0)
				x = x.left;
			else if (cmp > 0)
				x = x.right;
			else
				return x;
		}
		return x;
	}

	// 查找�?小节点：返回tree为根节点的AVL树的�?小节�?
	private AVLTreeNode<T> minimum(AVLTreeNode<T> tree) {
		if (tree == null)
			return null;
		while (tree.left != null)
			tree = tree.left;
		return tree;
	}

	public T minimum() {
		AVLTreeNode<T> p = minimum(mRoot);
		if (p != null)
			return p.key;
		return null;
	}

	// 查找�?大节点：返回tree为根节点的AVL树的�?大节�?
	private AVLTreeNode<T> maximum(AVLTreeNode<T> tree) {
		if (tree == null)
			return null;
		while (tree.right != null)
			tree = tree.right;
		return tree;
	}

	public T maximum() {
		AVLTreeNode<T> p = maximum(mRoot);
		if (p != null)
			return p.key;
		return null;
	}

	// LL旋转
	private AVLTreeNode<T> LLRotation(AVLTreeNode<T> k2) {
		AVLTreeNode<T> k1;
		k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;

		k2.height = max(height(k2.left), height(k2.right)) + 1;
		k1.height = max(height(k1.left), k2.height) + 1;
		return k1;
	}

	// RR旋转
	private AVLTreeNode<T> RRRotation(AVLTreeNode<T> k1) {
		AVLTreeNode<T> k2;
		k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;

		k1.height = max(height(k1.left), height(k1.right)) + 1;
		k2.height = max(height(k2.left), k1.height) + 1;
		return k2;
	}

	/*
	 * LR：左右对应的情况(左双旋转)�?
	 *
	 * 返回值：旋转后的根节�?
	 */
	private AVLTreeNode<T> LRRotation(AVLTreeNode<T> k3) {
		k3.left = RRRotation(k3.left);

		return LLRotation(k3);
	}

	private AVLTreeNode<T> RLRotation(AVLTreeNode<T> k1) {
		k1.right = LLRotation(k1.right);

		return RRRotation(k1);
	}

	/*
	 * 将结点插入到AVL树中，并返回根节�?
	 *
	 * 参数说明�? tree AVL树的根结�? key 插入的结点的键�?? 返回值： 根节�?
	 */
	private AVLTreeNode<T> insertNode(AVLTreeNode<T> tree, T key) {
		if (tree == null) {
			tree = new AVLTreeNode<T>(key, null, null);
			if (tree == null) {
				System.out.println("ERROR: create avltreenode failed!");
				return null;
			}
		} else {
			int cmp = key.compareTo(tree.key);
			// 应该将key插入到tree的左子树的情�?
			if (cmp < 0) {
				tree.left = insertNode(tree.left, key);
				// 插入节点后，若AVL树失去平衡，则进行相应的调节
				if (height(tree.left) - height(tree.right) == 2) {
					if (key.compareTo(tree.left.key) < 0) {
						tree = LLRotation(tree);
					} else {
						tree = LRRotation(tree);
					}
				}
			} else if (cmp > 0) // 应该将key插入到tree的右子树的情�?
			{
				tree.right = insertNode(tree.right, key);
				if (height(tree.right) - height(tree.left) == 2) {
					if (key.compareTo(tree.right.key) > 0) {
						tree = RRRotation(tree);
					} else {
						tree = RLRotation(tree);
					}
				}
			}else {
				System.out.println("添加失败，不允许添加相同的节�?");
			}
		}
		tree.height = max(height(tree.left), height(tree.right)) + 1;
		return tree;
	}

	public void insert(T key) {
		mRoot = insertNode(mRoot, key);
	}

	/*
	 * 删除结点(z)，返回根节点
	 *
	 * 参数说明�? tree AVL树的根结�? z 待删除的结点 返回值： 根节�?
	 */
	private AVLTreeNode<T> removeNode(AVLTreeNode<T> tree, AVLTreeNode<T> z) {
		// 根为�? 或�?? 没有要删除的节点，直接返回null�?
		if (tree == null || z == null)
			return null;

		int cmp = z.key.compareTo(tree.key);
		if (cmp < 0) { // 待删除的节点�?"tree的左子树"�?
			tree.left = removeNode(tree.left, z);
			// 删除节点后，若AVL树失去平衡，则进行相应的调节�?
			if (height(tree.right) - height(tree.left) == 2) {
				AVLTreeNode<T> r = tree.right;
				if (height(r.left) > height(r.right))
					tree = RLRotation(tree);
				else
					tree = RRRotation(tree);
			}
		} else if (cmp > 0) { // 待删除的节点�?"tree的右子树"�?
			tree.right = removeNode(tree.right, z);
			// 删除节点后，若AVL树失去平衡，则进行相应的调节�?
			if (height(tree.left) - height(tree.right) == 2) {
				AVLTreeNode<T> l = tree.left;
				if (height(l.right) > height(l.left))
					tree = LRRotation(tree);
				else
					tree = LLRotation(tree);
			}
		} else { // tree是对应要删除的节点�??
			// tree的左右孩子都非空
			if ((tree.left != null) && (tree.right != null)) {
				if (height(tree.left) > height(tree.right)) {
					// 如果tree的左子树比右子树高；
					// �?(01)找出tree的左子树中的�?大节�?
					// (02)将该�?大节点的值赋值给tree�?
					// (03)删除该最大节点�??
					// 这类似于�?"tree的左子树中最大节�?"�?"tree"的替身；
					// 采用这种方式的好处是：删�?"tree的左子树中最大节�?"之后，AVL树仍然是平衡的�??
					AVLTreeNode<T> max = maximum(tree.left);
					tree.key = max.key;
					tree.left = removeNode(tree.left, max);
				} else {
					// 如果tree的左子树不比右子树高(即它们相等，或右子树比左子树�?1)
					// �?(01)找出tree的右子树中的�?小节�?
					// (02)将该�?小节点的值赋值给tree�?
					// (03)删除该最小节点�??
					// 这类似于�?"tree的右子树中最小节�?"�?"tree"的替身；
					// 采用这种方式的好处是：删�?"tree的右子树中最小节�?"之后，AVL树仍然是平衡的�??
					AVLTreeNode<T> min = maximum(tree.right);
					tree.key = min.key;
					tree.right = removeNode(tree.right, min);
				}
			} else {
				AVLTreeNode<T> tmp = tree;
				tree = (tree.left != null) ? tree.left : tree.right;
				tmp = null;
			}
		}

		return tree;
	}

	public void removeNode(T key) {
		AVLTreeNode<T> z;

		if ((z = search(mRoot, key)) != null)
			mRoot = removeNode(mRoot, z);
	}

	// �?毁AVL�?
	private void destroy(AVLTreeNode<T> tree) {
		if (tree == null)
			return;
		if (tree.left != null)
			destroy(tree.left);
		if (tree.right != null)
			destroy(tree.right);

		tree = null;
	}

	public void destroy() {
		destroy(mRoot);
	}

	/*
	 * 打印"二叉查找�?"
	 *
	 * key -- 节点的键�? direction -- 0，表示该节点是根节点; -1，表示该节点是它的父结点的左孩子; 1，表示该节点是它的父结点的右孩子�?
	 */
	private void print(AVLTreeNode<T> tree, T key, int direction) {
		if (tree != null) {
			if (direction == 0) // tree是根节点
				System.out.printf("%2d is root\n", tree.key, key);
			else // tree是分支节�?
				System.out.printf("%2d is %2d's %6s child\n", tree.key, key, direction == 1 ? "right" : "left");

			print(tree.left, tree.key, -1);
			print(tree.right, tree.key, 1);
		}
	}

	public void print() {
		if (mRoot != null)
			print(mRoot, mRoot.key, 0);
	}
}
