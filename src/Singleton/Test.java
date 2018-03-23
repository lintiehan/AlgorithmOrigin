package Singleton;

public class Test implements Runnable{
	int   num=1000;
	public  synchronized  void add()
	{
		for(int i=0;i<100000;i++)
		{
			num++;
		}
	}
	public    void delete()
	{
		for(int i=0;i<100000;i++)
		{
			num--;
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if("t1".equals(Thread.currentThread().getName()))
		{
			add();
		}else
		{
			delete();
		}
	}
	public static void main(String[] args) throws InterruptedException {
		Test test=new Test();
		Thread t1=new Thread(test,"t1");
		Thread t2=new Thread(test,"t2");
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(test.num);
	}

 
}
