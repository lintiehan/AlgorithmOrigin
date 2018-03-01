package Base_Thread;
 
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ParkingCar_Semaphore {
	private static final int NUMBER_OF_CARS=30;
	private static final int NUMBER_OF_PARKING_SLOT=10;
	public static void main(String[] args) {
		Semaphore parkingSlot=new Semaphore(NUMBER_OF_PARKING_SLOT);
		ExecutorService service=Executors.newCachedThreadPool();
		
		for(int i=1;i<=NUMBER_OF_CARS;i++)
		{
			service.execute(new Car(parkingSlot, i));
		}
		sleep(3000);
		service.shutdown();
		//输出还有几个可以用的资源数
		System.out.println(parkingSlot.availablePermits()+" 个停车位可以使用");
	}
	
	
	private static void sleep(long millis)
	{
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class Car implements Runnable{
		private final Semaphore parkingSlot;
		private int carNo;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			parkingSlot.acquire();
			parking();
			sleep(300);
			parkingSlot.release();
			leaving();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	public Car(Semaphore parkingSlot,int carNo) {
		// TODO Auto-generated constructor stub
		this.parkingSlot=parkingSlot;
		this.carNo=carNo;
	}
	
	private void parking()
	{
		System.out.println(String.format("%d号车泊车",carNo));
	}
	
	private void leaving()
	{
		System.out.println(String.format("%d号车离开车位",carNo));
	}
	private static void sleep(long millis)
	{
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}