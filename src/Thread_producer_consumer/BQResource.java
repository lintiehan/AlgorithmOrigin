package Thread_producer_consumer;

import java.util.concurrent.BlockingQueue;

public class BQResource {

	public void produce() {
		// TODO Auto-generated method stub

	}

	public void consume() {
		// TODO Auto-generated method stub

	}

}

class BQProducer extends Thread {

	// 所在放置的仓库
	private BQResource resource;

	// 构造函数，设置仓库
	public BQProducer(BQResource resource) {
		this.resource = resource;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		resource.produce();
	}
}

class BQConsumer extends Thread {

	// 所在放置的仓库
	private BQResource resource;

	// 构造函数，设置仓库
	public BQConsumer(BQResource resource) {
		this.resource = resource;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		resource.consume();
	}
}