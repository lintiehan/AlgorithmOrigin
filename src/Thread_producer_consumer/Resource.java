package Thread_producer_consumer;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.jar.Pack200;
/*

 阻塞队列
put方法用来向队尾存入元素，如果队列满，则等待；

take方法用来从队首取元素，如果队列为空，则等待；

offer方法用来向队尾存入元素，如果队列满，则等待一定的时间，
当时间期限达到时，如果还没有插入成功，则返回false；否则返回true；

poll方法用来从队首取元素，如果队列空，则等待一定的时间，当时间期限达到时，如果取到，则返回null；
否则返回取得的元素；

*/
public class Resource {
	// 仓库储存的载体 容量为6
	private LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<Object>(6);

	public void produce() {
		int number = 0;
		while (number < 10) {
			try {
				list.put(number);
				System.out.println("生产1个\t【库存量】" + list.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
			number++;
		}
	}

	public void consume() {
		while (true) {
			try {
				// 消费产品，容量为空的时候自动阻塞
				System.out.println("消费1个:" + list.take() + "\t【库存量】" + list.size());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	public static void main(String[] args) {
		Resource resource=new Resource();
		ResourceProducer p1=new ResourceProducer(resource);
		ResourceConsumer c1=new ResourceConsumer(resource);
		p1.start();
		c1.start();
	}
}
class ResourceProducer extends Thread {  
	  
    // 所在放置的仓库  
    private Resource resource;  
  
    // 构造函数，设置仓库  
    public ResourceProducer(Resource resource) {  
        this.resource = resource;  
    }  
  
    @Override  
    public void run() {  
        // TODO Auto-generated method stub  
    	resource.produce();  
    }  
}  
class ResourceConsumer extends Thread {  
	  
	 // 所在放置的仓库  
    private Resource resource;  
  
    // 构造函数，设置仓库  
    public ResourceConsumer(Resource resource) {  
        this.resource = resource;  
    }  
  
    @Override  
    public void run() {  
        // TODO Auto-generated method stub  
    	resource.consume();  
    }  
}  