package Test_new1;

public class TreeNode implements Cloneable {
	boolean isVisted = false;
	String label = "";
	TreeNode parentChild = null;
	TreeNode leftChild = null;
	TreeNode centerChild = null;
	TreeNode rightChild = null;
	int key = 0;
	int position=0;
	public TreeNode() {
	}

	public TreeNode(int key) {
		this.key = key;
		this.label = "";
		this.parentChild = null;
		this.leftChild = null;
		this.centerChild = null;
		this.rightChild = null;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public TreeNode(int key, String label) {
		this.key = key;
		this.label = label;
		this.parentChild = null;
		this.leftChild = null;
		this.centerChild = null;
		this.rightChild = null;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		try {
			TreeNode node = (TreeNode) super.clone();

			return node;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	public TreeNode getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(TreeNode leftChild) {
		this.leftChild = leftChild;
	}

	public TreeNode getCenterChild() {
		return centerChild;
	}

	public void setCenterChild(TreeNode centerChild) {
		this.centerChild = centerChild;
	}

	public TreeNode getRightChild() {
		return rightChild;
	}

	public void setRightChild(TreeNode rightChild) {
		this.rightChild = rightChild;
	}
}
