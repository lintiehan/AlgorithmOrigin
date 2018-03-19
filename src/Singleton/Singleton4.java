package Singleton;

import java.util.HashMap;
import java.util.Map;

/*
 *  采用Map配置多个单例
 */
public class Singleton4 {
	// 设置静态变量，直接创建实例
	private static Map<String, Singleton4> map = new HashMap<String, Singleton4>();

	static {
		Singleton4 singleton4 = new Singleton4();
		map.put(singleton4.getClass().getName(), singleton4);
	}

	// 受保护的构造函数，不能私有，但子类可以直接访问构造方法
	// 解决方式：把单例类放在一个外在的包中，以便在其他包种的类无法实例化一个单例类
	protected Singleton4() {
	}

	// 开放一个共有方法，判断是否已经存在实例，有返回，没有则新建一个再返回
	public static Singleton4 getInstance(String name) {
		if (name == null) {
			name = Singleton4.class.getName();
			System.out.println("-->name不存在，name赋值等于" + Singleton4.class.getName());
		}
		if (map.get(name) == null) {
			try {
				System.out.println("-->name对应的值不存在，开始创建");
				map.put(name, (Singleton4) Class.forName(name).newInstance());
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {
			System.out.println("-->name对应的值存在");
		}
		return map.get(name);
	}

	public Map<String, Singleton4> getMap() {
		return map;
	}

	public static void main(String[] args) {
		System.out.println("-----------------登记式单例模式----------------");
		System.out.println("第一次取得实例（登记式）");
		Singleton4 s1 = Singleton4.getInstance(null);
		System.out.println(s1);

		System.out.println("第二次取得实例（登记式）");
		Singleton4ChildA s3 = Singleton4ChildA.getInstance();
		System.out.println(s3);
		System.out.println(s3.about());
	}
}

class Singleton4ChildA extends Singleton4 {
	public static Singleton4ChildA getInstance() {
		return (Singleton4ChildA) Singleton4ChildA
				.getInstance("Algorithm.src.Singleton.Singleton4ChildA.java");
	}

	// 随便写一个测试的方法
	public String about() {
		return "---->我是Singleton4的第一个子类Singleton4ChildA";
	}
}

class Singleton4ChildB extends Singleton4 {
	public static Singleton4ChildB getInstance() {
		return (Singleton4ChildB) Singleton4ChildB
				.getInstance("Algorithm.src.Singleton.Singleton4.Singleton4ChildB.java");
	}

	// 随便写一个测试的方法
	public String about() {
		return "---->我是Singleton4的第一个子类Singleton4ChildB";
	}
}