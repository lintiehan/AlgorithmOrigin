package Base_Thread;
 

public class MethodVolatile {
	class ThreadToGo {
		int value = 1;
	}

	private volatile ThreadToGo threadToGo = new ThreadToGo();

	public Runnable ThreadOne() {
		final String[] inputArr = Helper.buildNoArr(52);
		return new Runnable() {
			private String[]arr=inputArr;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<arr.length;i+=2)
				{
					while(threadToGo.value==2)
					{
						
					}
					Helper.print(arr[i],arr[i+1]);
					threadToGo.value=2;
				}
			}
		};
	}
	
 
	public Runnable ThreadTwo() {
		final String[] inputArr = Helper.buildCharArr(26);
		return new Runnable() {
			private String[]arr=inputArr;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<arr.length;i++)
				{
					while(threadToGo.value==1)
					{
						
					}
					Helper.print(arr[i],arr[i+1]);
					threadToGo.value=1;
				}
			}
		};
	}
	public static void main(String[] args) {
		MethodVolatile three=new MethodVolatile();
		Helper.instance.run(three.ThreadOne());
		Helper.instance.run(three.ThreadTwo());
		Helper.instance.shutdown();
	}
}
