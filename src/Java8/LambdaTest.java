package Java8;

import javax.print.attribute.standard.Media;

/*
 * 
 * 
Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）。
使用 Lambda 表达式可以使代码变的更加简洁紧凑。

语法
lambda 表达式的语法格式如下：
(parameters) -> expression
或
(parameters) ->{ statements; }

Lambda 表达式主要用来定义行内执行的方法类型接口，例如，一个简单方法接口。在上面例子中，
我们使用各种类型的Lambda表达式来定义MathOperation接口的方法。然后我们定义了sayMessage的执行。

Lambda 表达式免去了使用匿名方法的麻烦，并且给予Java简单但是强大的函数化的编程能力。
 */
public class LambdaTest {
	public static void main(String[] args) {
		LambdaTest test = new LambdaTest();

		// 类型声明
		MathOperation m = (int a, int b) -> a + b;

		// 不用类型声明
		MathOperation m1 = (a, b) -> a - b;

		// 大括号中的返回语句
		MathOperation m2 = (int a, int b) -> {
			return a * b;
		};

		// 大括号中的返回语句
		MathOperation m3 = (int a, int b) -> a / b;
		
		System.out.println("10+5="+test.operate(10, 5, m));
		System.out.println("10-5="+test.operate(10, 5, m1));
		System.out.println("10*5="+test.operate(10, 5, m2));
		System.out.println("10/5="+test.operate(10, 5, m3));
		
		//不用括号
		GreetingService greetingService=message->
		System.out.println("Hello "+message);
		
		//用括号
		GreetingService greetingService1=(message)->
		System.out.println("Hello "+message);
		
		greetingService.sayMessage("A");
		greetingService1.sayMessage("B");
		
	}

	interface MathOperation {
		int operation(int a, int b);
	}

	interface GreetingService {
		void sayMessage(String message);
	}

	private int operate(int a, int b, MathOperation m) {
		return m.operation(a, b);
	}
}
