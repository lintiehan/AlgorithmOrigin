package Singleton;
//Double Checked Lock
public class DCLSingleton {
	private static volatile DCLSingleton instance=null;
	
	private DCLSingleton() {}
	
	public static DCLSingleton getInstance()
	{
		if(instance==null)
		{
			synchronized (DCLSingleton.class) {
				if (instance==null) {
					instance=new DCLSingleton();
				}
			}
		}
		return instance;
	}
}
