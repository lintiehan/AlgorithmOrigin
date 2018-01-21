package Base;

import java.awt.List;
import java.io.PipedReader;
import java.util.HashMap;
import java.util.PrimitiveIterator.OfDouble;
import java.util.prefs.NodeChangeEvent;

public class MyLinkedList {
	Node head = null;// 链表头的引用

	// 添加节点
	public void addNode(int d) {
		Node newNode = new Node(d);
		if (head == null) {
			head = newNode;
			return;
		}
		Node temp = head;
		while (temp.next != null) {
			temp = temp.next;
		}
		// add node to end
		temp.next = newNode;
	}

	/*
	 * @param index:删除第index个节点
	 * 
	 * @return 成功返回true，失败返回false
	 */
	public boolean deleteNode(int index) {
		if (index < 1 || index > length()) {
			return false;
		}
		// 删除链表第一个元素
		if (index == 1) {
			head = head.next;
			return true;
		}
		int i = 1;
		Node preNode = head;
		Node curNode = preNode.next;
		while (curNode != null) {
			if (i == index) {
				preNode.next = curNode.next;
				return true;
			}
			preNode = curNode;
			curNode = curNode.next;
			i++;
		}
		return true;
	}

	// 返回节点的长度
	private int length() {
		int length = 0;
		Node tmp = head;
		while (tmp != null) {
			length++;
			tmp = tmp.next;
		}
		return length;
	}

	/*
	 * 对链表进行排序，返回排序后的头节点
	 */
	public Node orderList() {
		Node nextNode = null;
		int temp = 0;
		Node curNode = head;
		while (curNode.next != null) {
			nextNode = curNode.next;
			while (nextNode != null) {
				if (curNode.data > nextNode.data) {
					temp = curNode.data;
					curNode.data = nextNode.data;
					nextNode.data = temp;
				}
				nextNode = nextNode.next;
			}
			curNode = curNode.next;
		}
		return head;
	}

	/**
	 * 链表反转
	 * 
	 */
	public void ReverseIteratively(Node head) {
		Node reversedHead = head;
		Node cur = head;
		Node pPre = null;
		while (cur != null) {
			Node curNext = cur.next; // 要处理的下一个节点
			if (curNext == null)
				reversedHead = cur;

			cur.next = pPre;
			pPre = cur;
			cur = curNext;
		}
		this.head = reversedHead;
	}

	 

	public void printList() {
		Node temp = head;
		while (temp != null) {
			System.out.println(temp.data);
			temp = temp.next;
		}
	}

	public void printList(Node head) {
		Node temp = head;
		while (temp != null) {
			System.out.println(temp.data);
			temp = temp.next;
		}
	}

	/*
	 * 删除重复节点 时间复杂度较低，需要额外的储存空间来保存已遍历过的值
	 */
	public void deleteDuplecate1(Node head) {
		HashMap<Integer, Integer> table = new HashMap<Integer, Integer>();
		Node temp = head;
		Node pre = null;
		while (temp != null) {
			if (table.containsKey(temp.data))
				pre.next = temp.next;
			else {
				table.put(temp.data, 1);
				pre = temp;
			}
			temp = temp.next;
		}
	}

	/*
	 * 删除重复节点 进行双重循环遍历，外循环正常遍历链表，假如外循环当前遍历的节点为cur， 内循环开始循环，若碰到与cur所指节点值相同，则删除这个重复节点。
	 */
	public void deleteDuplecate2(Node head) {
		Node p = head;
		while (p != null) {
			Node cur = p;
			while (cur.next != null) {
				if (p.data == cur.next.data) {
					cur.next = cur.next.next;
				} else {
					cur = cur.next;
				}
				p = p.next;
			}
		}
	}
	/*
	 * 寻找单链表的中间节点
	 * （奇数）快指针到达链表尾部，慢指针则恰好到达链表中部
	 * （偶数）快指针到达链表尾部，慢指针所指节点与其下一个节点均为链表的中间节点
	 */
	public Node SearchMid(Node head) {
		Node cur1 = this.head;
		Node cur2 = this.head;
		while (cur1 != null && cur1.next != null && cur1.next.next != null) {
			cur1 = cur1.next.next;
			cur2 = cur2.next;
		}
		return cur2;
	}
	/*
	 * 找出链表倒数第K个数 设置两个指针，让其中一个指针比另一个指针先移动k-1步，然后两个
	 * 指针同时往前移动，循环直到先行的指针值为NULL时，另一个指针就是所要找的位置
	 */
	public Node findElement(Node head, int k) {
		if (k < 1 || k > this.length()) {
			return null;
		}
		Node cur1 = head;
		Node cur2 = head;
		System.out.println(cur1 == cur2);
		for (int i = 0; i < k - 1; i++) {
			cur1 = cur1.next;
		}
		while (cur1 != null) {
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		return cur2;
	}
	/*
	 * 检测一个链表是否有环
	 */
	public boolean isLoop(Node head)
	{
		Node fast=head;
		Node slow=head;
		if(fast==null)
		{
			return false;
		}
		while(fast!=null&&fast.next!=null)
		{
			fast=fast.next.next;
			slow=slow.next;
			if(fast==slow)
			{
				return true;
			}
		}
		return !(fast==null||fast.next==null);
	}
	/*
	 * 如何检测一个链表环的入口
	 */
	public Node findLoopPort(Node head)
	{
		Node slow=head;
		Node fast=head;
		while(fast!=null&&fast.next!=null)
		{
			slow=slow.next;
			fast=fast.next.next;
			if(slow==fast) break;
		}
		if(fast==null||slow==null)
			return null;
		slow=head;
		while(slow!=fast)
		{
			slow=slow.next;
			fast=fast.next;
		}
		return slow;
	}
	/*
	 * 如何在不知道头指针的情况下删除指定节点
	 * 1.若待删除的节点为链表尾指针，则无法删除，由于删除后无法使其前驱节点的next指针为空
	 * 2.若待删除的节点不是尾节点，则可能通过交换这个节点与其后继节点的值，然后删除后继节点
	 */
	public boolean deleteNode(Node node)
	{
		if(node==null||node.next==null)
		{
			return false;
		}
		int temp=node.data;
		node.data=node.next.data;
		node.next.data=temp;
		node.next=node.next.next;
		return true;
	}
	//给定链表的头指针和一个节点指针，在O（1）时间内删除该节点
	public void deleteNodewithnode(Node head,Node node)
	{
		if(head==null)
		{
			return;
		}
		
		if(node.next==null)
		{
			Node cur=head;
			while(cur.next!=node)
			{
				cur=cur.next;
			}
			cur.next=null;
			deleteNode(node);
			node=null;
		}else
		{
			Node cur=node.next;
			int temp=cur.data;
			cur.data=node.data;
			node.data=temp;
			node.next=cur.next;
			deleteNode(cur);
			cur=null;
		}
	}
	
	
	/*
	 * 如何判断两个链表是否相交
	 * 分别遍历两个链表，记录它们的尾节点，如果它们的尾节点相同，那么着两个链表相交，否则不相交
	 */
	public boolean isIntersect(Node h1,Node h2)
	{
		if(h1==null||h2==null)
		{
			return false;
		}
		Node tail1=h1;
		//找到链表h1的最后一个节点
		while(tail1.next!=null)
		{
			tail1=tail1.next;
		}
		Node tail2=h2;
		//找到链表h2的最后一个节点
		while(tail2.next!=null)
		{
			tail2=tail2.next;
		}
		return tail1==tail2;
	}
	/*
	 * 合并有序链表
	 */
	public Node mergeList(Node n1,Node n2)
	{
		if(n1==null)
			return n2;
		if(n2==null)
			return n1;
		Node temp1=n1;
		Node temp2=n2;
		Node head=new Node(0);
		Node headtemp=head;
		while(temp1!=null&&temp2!=null)
		{
			if(temp1.data<temp2.data)
			{
				head.next=temp1;
				head=head.next;
				temp1=temp1.next;
			}else {
				head.next=temp2;
				head=head.next;
				temp2=temp2.next;
			}
		}
		while(temp1!=null)
		{
			head.next=temp1;
			head=head.next;
			temp1=temp1.next;
		}
		while(temp2!=null)
		{
			head.next=temp2;
			head=head.next;
			temp2=temp2.next;
		}
		head=headtemp.next;
		return head;
		
	}
	
	public static void main(String[] args) {
		MyLinkedList TEMP = new MyLinkedList();
		
		MyLinkedList list = new MyLinkedList();
		list.addNode(1);
		list.addNode(2);
		list.addNode(3);
		list.printList();
	 
		list.ReverseIteratively(list.head);
		list.printList();
		//TEMP.head=TEMP.mergeList(list.head, list1.head);
		//TEMP.printList(TEMP.head); 
	}
}

class Node {
	Node next = null;
	int data;

	public Node(int data) {
		this.data = data;
	}
}