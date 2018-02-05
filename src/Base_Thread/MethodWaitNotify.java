package Base_Thread;
/*
 * “编写两个线程，
 * 一个线程打印1~25，另一个线程打印字母A~Z，
 * 打印顺序为12A34B56C……5152Z，
 * 要求使用线程间的通信。”
 * http://edisonxu.org/2017/03/02/java-thread-communication.html
 */


//利用最基本的synchronized、notify、wait
public class MethodWaitNotify {
	private final ThreadToGo threadToGo=new ThreadToGo();
	class ThreadToGo{
		int value=1;
	}
	public Runnable newThreadOne()
	{
		final String []intputArr=Helper.buildNoArr(52);
		return new Runnable() {
			private String []arr=intputArr;
			@Override
			public void run() {
				try {
					// TODO Auto-generated method stub
					for(int i=0;i<arr.length;i+=2)
					{
						synchronized (threadToGo) {
							while(threadToGo.value==2)
							{
								threadToGo.wait();
							}
							Helper.print(arr[i],arr[i+1]);
							threadToGo.value=2;
							threadToGo.notify();
						}
					}
				}catch (Exception e) {
					// TODO: handle exception
					System.out.println("OOps");
				}
				 
			}
		};
	}

	public Runnable newThreadTwo()
	{
		final String []intputArr=Helper.buildCharArr(26);
		return new Runnable() {
			private String []arr=intputArr;
			
			@Override
			public void run() {
				try {
					// TODO Auto-generated method stub
					for(int i=0;i<arr.length;i++)
					{
						synchronized (threadToGo) {
							while(threadToGo.value==1)
							{
								threadToGo.wait();
							}
							Helper.print(arr[i]);
							threadToGo.value=1;
							threadToGo.notify();
						}
					}
				}catch (Exception e) {
					// TODO: handle exception
					System.out.println("OOps");
				}
				 
			}
		};
	}
	public static void main(String args[]) throws InterruptedException {
		MethodWaitNotify one = new MethodWaitNotify();
        Helper.instance.run(one.newThreadOne());
        Helper.instance.run(one.newThreadTwo());
        Helper.instance.shutdown();
    }

}
