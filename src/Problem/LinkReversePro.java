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
	 * ������ת��:��ǰ����ת��������ָ�����ָ�� 
	����˼·�ǣ�����ǰ�ڵ�cur����һ���ڵ� cur.getNext()���浽temp��Ȼ����ĵ�ǰ�ڵ�ָ��ָ����һ���pre��Ҳ����˵�ڷ�ת��ǰ���ָ��ָ��ǰ���Ȱѵ�ǰ����ָ������tmp��ʱ���棬�Ա���һ��ʹ�ã�����̿ɱ�ʾ���£� 
	pre����һ��� 
	cur: ��ǰ��� 
	tmp: ��ʱ��㣬���ڱ��浱ǰ����ָ���򣨼���һ��㣩
	*/
	public static Node ReverseList(Node node)
	{
		if(node==null)
			return node;
		Node pre=node;//��һ�ڵ�
		Node cur=node.getNext();//��ǰ�ڵ�
		Node temp;//��ʱ�ڵ㣬���ڱ��浱ǰ�ڵ��ָ����
		while(cur!=null)
		{
			temp=cur.getNext();
			cur.setNext(pre);//��תָ�����ָ��
			//ָ�������ƶ�
			pre=cur;
			cur=temp;
		}
		// ���ԭ�����ͷ�ڵ��ָ������Ϊnull�������������ͷ��㣬��ԭ�����β���
		node.setNext(null);
		return pre;
	}
	
	
	/*�ݹ�ʵ��
	�ݹ鷴ת�����ڷ�ת��ǰ�ڵ�֮ǰ�ȷ�ת�����ڵ㡣������ͷ��㿪ʼ���������ֱ��β���ſ�ʼ��תָ�����ָ�򡣼򵥵�˵���Ǵ�β��㿪ʼ������ת��������ָ����ָ�������ͼ������ʾ�� 
	head����ǰһ����ָ����PS��ǰһ����ָ����ָ��ǰ��㣩 
	head.getNext()���ǵ�ǰ����ָ����PS����ǰ����ָ����ָ����һ��㣩 
	reHead���Ƿ�ת���������ͷ��㣨��ԭ���������β��㣩 
	*/
	public static Node ReverseListByRecursion(Node node)
	{
	     // node������ǰһ��㣬head.getNext()�ǵ�ǰ��㣬cur�Ƿ�ת���������ͷ���
		if(node==null||node.getNext()==null)
		{
			return node;
		}
		//�ȷ�ת�����ڵ�head.getNext() 
		Node cur=ReverseListByRecursion(node.getNext());
		//����ǰ����ָ����ָ��ǰһ���
		node.getNext().setNext(node);
		//ǰһ����ָ������Ϊnull
		node.setNext(null);
		return cur;//��ת���������ͷ���
	}
	
	
	public static void main(String[] args) {
		Node node1=new Node(1);
		Node node2=new Node(2);
		Node node3=new Node(3);
		node1.setNext(node2);
		node2.setNext(node3);
	
		Node cur=node1;
		System.out.println("��תǰ��");
		while(cur!=null)
		{
			System.out.print(cur.getVal()+" ");
			cur=cur.getNext();
		}
		
		 System.out.println();
		System.out.println("��ת��");
		node1=ReverseList(node1);
		while(node1!=null)
		{
			System.out.print(node1.getVal()+" ");
			node1=node1.getNext();
		}
		
	}
}
