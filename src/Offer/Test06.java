package Offer;

import java.util.Stack;

/*
 * 输入两个整数序列，第一个序列表示栈的压入顺序，
 * 请判断二个序列是否为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。

 */
public class Test06<T> {
	public static boolean isPopOrder(int[] push, int[] pop) {
		// 输入校验，参数不能为空，并且两个数组中必须有数字，并且两个数组中的数字个数相同
		// 否则返回false
		if (push == null || pop == null || pop.length == 0 || push.length == 0 || push.length != pop.length) {
			return false;
		}
		// 经过上面的参数校验，两个数组中一定有数据，且数据数目相等
		// 用于存放入栈时的数据
		Stack<Integer> stack = new Stack<>();

		int pushIndex = 0;
		int popIndex = 0;

		while (popIndex < pop.length) {
			// 入栈元素还未全部入栈的条件下，如果栈为空，或者栈顶的元素不与当前处理的相等，则一直进行栈操作，
			// 直到入栈元素全部入栈或者找到了一个与当出栈元素相等的元素
			while (pushIndex < push.length && (stack.isEmpty() || stack.peek() != pop[popIndex])) {
				// 入栈数组中的元素入栈
				stack.push(push[pushIndex]);
				// 指向下一个要处理的入栈元素
				pushIndex++;
			}
			// 如果在上一步的入栈过程中找到了与出栈的元素相等的元素
			if (stack.peek() == pop[popIndex]) {
				// 将元素出栈
				stack.pop();
				// 处理下一个出栈元素
				popIndex++;
			}
			// 如果没有找到与出栈元素相等的元素，说明这个出栈顺序是不合法的
			// 就返回false
			else {
				return false;
			}
		}
		// 下面的语句总是成立的
		// return stack.isEmpty();

		// 为什么可以直接返回true：对上面的外层while进行分析可知道，对每一个入栈的元素，
		// 在stack栈中，通过一些入栈操作，总可以在栈顶上找到与入栈元素值相同的元素，
		// 这就说明了这个出栈的顺序是入栈顺序的一个弹出队列，这也可以解释为什么stack.isEmpty()
		// 总是返回true，所有的入栈元素都可以进栈，并且可以被匹配到，之后就弹出，最后栈中就无元素。
		return true;
	}

	public static void main(String[] args) {
		int[] push = { 1, 2, 3, 4, 5 };
		int[] pop1 = { 4, 5, 3, 2, 1 };
		int[] pop2 = { 3, 5, 4, 2, 1 };
		int[] pop3 = { 4, 3, 5, 1, 2 };
		int[] pop4 = { 3, 5, 4, 1, 2 };

		System.out.println("true: " + isPopOrder(push, pop1));
		System.out.println("true: " + isPopOrder(push, pop2));
		System.out.println("false: " + isPopOrder(push, pop3));
		System.out.println("false: " + isPopOrder(push, pop4));
	}
}
