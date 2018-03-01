package Java8;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
interface supplier<T> {
	T get();
}
class Car{
	
	public static Car create(final supplier<Car> supplier)
	{
		return supplier.get();
	}
	
	public static void collide(final Car car)
	{
		System.out.println("Collided "+car.toString());
	}
	
	public void follow(final Car another)
	{
		System.out.println("Following the "+another.toString());
	}
	
	public void repair()
	{
		System.out.println("Repaired "+this.toString());
	}
}
public class FunctionReferenceTest{
	public static void main(String[] args) {
		List names=new ArrayList<>();
	
		names.add("Google");
		names.add("Google1");
		names.add("Google2");
		
		names.forEach(System.out::println);
	}
}