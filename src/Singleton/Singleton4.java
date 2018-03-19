package Singleton;

import java.util.HashMap;
import java.util.Map;

/*
 *  
 */
public class Singleton4 {
	private static Map<String, Singleton4>map=new HashMap<String,Singleton4>();
	
	static {
		Singleton4 singleton4=new Singleton4();
		map.put(singleton4.getClass().getName(), singleton4);
	}
	protected Singleton4() {}
	
	public static Singleton4 getInstance(String name)
	{
		if(name==null)
		{
			name=Singleton4.class.getName();
		}
		if(map.get(name)==null)
		{
			try {
				map.put(name, (Singleton4)Class.forName(name).newInstance());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return map.get(name);
	}
}
