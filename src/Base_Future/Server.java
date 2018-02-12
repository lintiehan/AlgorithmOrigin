package Base_Future;

public class Server {
	public FutureData<String> getString() {
		final FutureData<String> data = new FutureData<>();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				data.setData("world");
			}
		}).start();
		return data;
	}

	public static void main(String[] args) {
		Server server = new Server();
		FutureData<String> futureData = server.getString();
		String hello="hello ";
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(hello+ futureData.getData());
	}
}
