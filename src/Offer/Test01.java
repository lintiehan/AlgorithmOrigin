package Offer;
/*
 * 两个栈实现队列
 */

import java.util.Stack;

import javax.management.RuntimeErrorException;

public class Test01<T> {
	private Stack<T> stack1=new Stack<>();
	private Stack<T> stack2=new Stack<>();
	
	public void appendHead(T t)
	{
		stack1.add(t);
	}
	 
	public T deleteHead()
	{
		if(stack2.isEmpty())
		{
			while(!stack1.isEmpty())
			{
				stack2.add(stack1.pop());
			}
		}
		
		if(stack2.isEmpty())
		{
			throw new RuntimeException( "no more element");
		}
		
		return stack2.pop();
	}
}
