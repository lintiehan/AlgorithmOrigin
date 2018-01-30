package Base;

import java.util.Date;

import javax.swing.plaf.SliderUI;

public class CloneTest {
	public static void main(String[] args) throws  Exception {
		A a = new A("aaaa", 16, new Date());
		System.out.println(a);
		A b = (A) a.clone();
		
	 	b.age = 50;
		b.name="bbb";
		b.data = new Date();
		System.out.println(a);
		System.out.println(b);
	}
}

class A implements Cloneable
{
	public A(String name, int age, Date data) {
		this.name = name;
		this.data = data;
		this.age = age;
	}

	int age;
	String name;
	Date data;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name:" + name + " age:" + age + "\n data:" + data.toString();
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}