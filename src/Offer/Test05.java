package Offer;

import java.util.Stack;
/*
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小素的min 函数。
 * 在该栈中，调用min、push 及pop的时间复杂度都是0(1)
 */
public class Test05 {
	public static class StackMin<T extends Comparable<T>> {
		private Stack<T> dataStack;
		private Stack<Integer> minStack;

		public StackMin() {
			this.dataStack = new Stack<>();
			this.minStack = new Stack<>();
		}

		public T pop() {
			if (dataStack.isEmpty()) {
				throw new RuntimeException("empty");
			}
			// 如果有数据，最小数位置栈和数据栈必定有相同的元素个数
			// 两个栈同时出栈
			minStack.pop();
			return dataStack.pop();
		}

		public void push(T t) {
			if (t == null)
				throw new RuntimeException("no Element");
			// 如果数据栈是空的，直接将元素入栈，同时更新最小数栈中的数据
			if (dataStack.isEmpty()) {
				dataStack.push(t);
				minStack.push(0);
			}
			// 数据栈中有数据
			else {
				// 获取数据栈中的最小元素——未插入元素之前
				T e = dataStack.get(minStack.peek());
				// 将t入栈
				dataStack.push(t);
				// 如果插入的数据比栈中的最小元素小
				if (t.compareTo(e) < 0) {
					// 将新的最小元素的位置入最小栈
					minStack.push(dataStack.size() - 1);
				} else {
					// 插入的元素不必原来的最小元素小，复制最小栈栈顶元素，将其入栈
					minStack.push(minStack.peek());
				}
			}
		}

		public T min() { // 如果最小数公位置栈已经为空（数据栈中已经没有数据了），则抛出异常
			if (minStack.isEmpty()) {
				throw new RuntimeException("No element in stack.");
			}

			// 获取数据栈中的最小元素，并且返回结果
			return dataStack.get(minStack.peek());
		}
	}

	public static void main(String[] args) {
		Stack<String> stack = new Stack<>();
		stack.push("dasda");
		stack.push("dasd1a");
		stack.push("dasd2a");

		System.out.println(stack.get(0));
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}
}
