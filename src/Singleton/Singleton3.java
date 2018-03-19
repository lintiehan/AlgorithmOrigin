package Singleton;

/*
 * 单例模式之静态内部类
 * 既能延迟加载，又能保证线程安全
 */
public class Singleton3 {
	private Singleton3() {
	}

	private static class SingletonHolder{
		private static Singleton3 instance=new Singleton3();
	}

	public static Singleton3 getInstance()
	{
		return SingletonHolder.instance;
	}
}
