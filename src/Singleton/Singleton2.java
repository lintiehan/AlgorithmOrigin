package Singleton;

/*
 * 单例模式之饱汉式
 * 只有在访问到单例对象的时候才去检查和实例化单例对象，满足延迟加载，
 * 多线程访问时需要加线程同步，影响访问效率
 */
public class Singleton2 {
	private Singleton2() {
	}

	private static Singleton2 instance = new Singleton2();

	public synchronized static Singleton2 getInstance() {
		if (instance == null) {
			instance = new Singleton2();
		}
		return instance;
	}

}
