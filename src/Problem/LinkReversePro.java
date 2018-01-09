package Problem;
 
public class LinkReversePro {
	private static class Node{
		int val;
		Node next;
		Node(int val){
			this.val=val;
			this.next=null;
		}
		public int getVal() {
			return val;
		}
		public void setVal(int val) {
			this.val = val;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		
	}
	/*
	 * 遍历反转法:从前往后反转各个结点的指针域的指向。 
	基本思路是：将当前节点cur的下一个节点 cur.getNext()缓存到temp后，然后更改当前节点指针指向上一结点pre。也就是说在反转当前结点指针指向前，先把当前结点的指针域用tmp临时保存，以便下一次使用，其过程可表示如下： 
	pre：上一结点 
	cur: 当前结点 
	tmp: 临时结点，用于保存当前结点的指针域（即下一结点）
	*/
	public static Node ReverseList(Node node)
	{
		if(node==null)
			return node;
		Node pre=node;//上一节点
		Node cur=node.getNext();//当前节点
		Node temp;//临时节点，用于保存当前节点的指针域
		while(cur!=null)
		{
			temp=cur.getNext();
			cur.setNext(pre);//反转指针域的指向
			//指针往下移动
			pre=cur;
			cur=temp;
		}
		// 最后将原链表的头节点的指针域置为null，还回新链表的头结点，即原链表的尾结点
		node.setNext(null);
		return pre;
	}
	
	
	/*递归实现
	递归反转法：在反转当前节点之前先反转后续节点。这样从头结点开始，层层深入直到尾结点才开始反转指针域的指向。简单的说就是从尾结点开始，逆向反转各个结点的指针域指向，其过程图如下所示： 
	head：是前一结点的指针域（PS：前一结点的指针域指向当前结点） 
	head.getNext()：是当前结点的指针域（PS：当前结点的指针域指向下一结点） 
	reHead：是反转后新链表的头结点（即原来单链表的尾结点） 
	*/
	public static Node ReverseListByRecursion(Node node)
	{
	     // node看作是前一结点，head.getNext()是当前结点，cur是反转后新链表的头结点
		if(node==null||node.getNext()==null)
		{
			return node;
		}
		//先反转后续节点head.getNext() 
		Node cur=ReverseListByRecursion(node.getNext());
		//将当前结点的指针域指向前一结点
		node.getNext().setNext(node);
		//前一结点的指针域令为null
		node.setNext(null);
		return cur;//反转后新链表的头结点
	}
	
	
	public static void main(String[] args) {
		Node node1=new Node(1);
		Node node2=new Node(2);
		Node node3=new Node(3);
		node1.setNext(node2);
		node2.setNext(node3);
	
		Node cur=node1;
		System.out.println("翻转前：");
		while(cur!=null)
		{
			System.out.print(cur.getVal()+" ");
			cur=cur.getNext();
		}
		
		 System.out.println();
		System.out.println("翻转后：");
		node1=ReverseList(node1);
		while(node1!=null)
		{
			System.out.print(node1.getVal()+" ");
			node1=node1.getNext();
		}
		
	}
}
