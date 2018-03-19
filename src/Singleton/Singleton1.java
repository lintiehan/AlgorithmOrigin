package Singleton;
/*
 * 单例模式之饿汉式(线程安全)
      在类加载时就初始化一个类对象，使用静态加载，但是在类加载时就实例化对象，单例对象较大的时候会影响系统加载速度。
 */
public class Singleton1 {
	private Singleton1() {
	}
	private static final Singleton1 instance=new Singleton1();
	
	public static Singleton1 getInstance()
	{
		return instance;
	}
	
}
