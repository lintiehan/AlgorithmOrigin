package LinkList;

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
			nextNode=curNode.next;
			while(nextNode!=null)
			{
				if(curNode.data>nextNode.data)
				{
					temp=curNode.data;
					curNode.data=nextNode.data;
					nextNode.data=temp;
				}
				nextNode=nextNode.next;
			}
			curNode=curNode.next;
		}
		return head;
	}
	
	public void printList()
	{
		Node temp=head;
		while(temp!=null)
		{
			System.out.println(temp.data);
			temp=temp.next;
		}
	}
	
	public static void main(String[] args) {
		MyLinkedList list=new MyLinkedList();
		list.addNode(5);
		list.addNode(3);
		list.addNode(1);
		list.addNode(5);
		System.out.println("length "+list.length());
		System.out.println("before order:");
		list.printList();
		list.orderList();
		System.err.println("after order: ");
		list.printList();
	}
}

class Node {
	Node next = null;
	int data;

	public Node(int data) {
		this.data = data;
	}
}