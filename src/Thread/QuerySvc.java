package Thread;

/*
 * 在Java的多线程编程中，为保证多个线程对共享变量的安全访问，
 * 通常会使用synchronized来保证同一时刻只有一个线程对共享变量进行操作。 
 * 但在有些情况下，synchronized不能保证多线程对共享变量的正确读写。例如类有一个类变量，
 * 该类变量会被多个类方法读写，当多线程操作该类的实例对象时，如果线程对类变量有读取、写入操作就会发生类变量读写错误，
 * 即便是在类方法前加上synchronized也无效，因为同一个线程在两次调用方法之间时锁是被释放的，
 * 这时其它线程可以访问对象的类方法，读取或修改类变量。 这种情况下可以将类变量放到ThreadLocal类型的对象中，
 * 使变量在每个线程中都有独立拷贝，不会出现一个线程读取变量时而被另一个线程修改的现象。
 */
public class QuerySvc {
	private String sql;
	
	private static ThreadLocal sqlHolder=new ThreadLocal<>();
	
	public QuerySvc()
	{
		
	}
	
	public void execute()
	{
		// System.out.println("Thread "+Thread.currentThread().getId()+"SQL is"+sql);
		  System.out.println("Thread "+Thread.currentThread().getId()+" Thread Local variable Sql is " + sqlHolder.get());	
	}
	public String getSql()
	{
		return sql;
	}
	public  void setSql(String sql)
	{
		this.sql=sql;
		sqlHolder.set(sql);
	}
	
	public static void main(String[] args) {
		QuerySvc qSvc=new QuerySvc();
		for(int i=0;i<10;i++)
		{
			String sql="select * from table where id ="+i;
			new Work(qSvc, sql).start();
		}
	}
}
class Work extends Thread{
	private QuerySvc querySvc;
	private String sql;
	public Work(QuerySvc querySvc,String sql)
	{
		this.querySvc=querySvc;
		this.sql=sql;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		querySvc.setSql(sql);
		querySvc.execute();
	}
}