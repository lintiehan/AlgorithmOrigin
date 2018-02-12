package Base_Future;

public class FutureData<T> {
	private boolean isReady=false;
	private T data;
	
	public synchronized void setData(T data)
	{
		isReady=true;
		this.data=data;
		notifyAll();
	}
	public synchronized T getData()
	{
		while(!isReady)
		{
			try {
				wait();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return data;
	}
}
