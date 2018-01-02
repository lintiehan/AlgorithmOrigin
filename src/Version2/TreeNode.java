package Version2 ;

public class TreeNode implements Cloneable {
	boolean isVisted = false;
	TreeNode parentChild = null;
	TreeNode leftChild = null;
	TreeNode centerChild = null;
	TreeNode rightChild = null;
	int position=0;
	int key = 0;

	public TreeNode() {
	}

	public TreeNode(int key) {
		this.key = key;

		this.leftChild = null;
		this.rightChild = null;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public TreeNode(int key, String label) {
		this.key = key;
		this.parentChild = null;
		this.leftChild = null;
		this.centerChild = null;
		this.rightChild = null;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		TreeNode node = (TreeNode) super.clone();
		return node;
	}
}
