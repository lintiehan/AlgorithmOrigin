package Base;
 
import java.util.Arrays;

public class MyStack<E> {
	private Object[] stack;
	private int size;//数组的存储元素的个数
	public MyStack()
	{
		stack=new Object[10];
	}
	//判断堆栈是否为空
	public boolean isEmpty()
	{
		return size==0;
	}
	
	public E peek()
	{
		if(isEmpty())
			return null;
		return (E)stack[size-1];
	}
	public E pop()
	{
		E e=peek();
		stack[size-1]=null;
		size--;
		return e;
	}

	public E push(E item)
	{
		ensureCapacity(size+1);//检查容量
		stack[size++]=item;
		return item;
	}
	//判断数据器是否已满，若已满，则扩充数组空间
	private void ensureCapacity(int size) {
		// TODO Auto-generated method stub
		int len=stack.length;
		if(size>len)//数据已满
		{
			int newLen=10+len;//每次数组扩容容量
			stack=Arrays.copyOf(stack, newLen);
		}
	}
	public static void main(String[] args) {
		MyStack<Integer> stack=new MyStack<>();
		
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(6);
		stack.push(7);
		stack.push(8);
		stack.push(9);
		stack.push(10);
		
		System.out.println("size"+stack.size);
		stack.push(10);stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(6);
		stack.push(7);
		stack.push(8);
		stack.push(9);
		stack.push(10);
		System.out.println("size"+stack.size);
	}
}

