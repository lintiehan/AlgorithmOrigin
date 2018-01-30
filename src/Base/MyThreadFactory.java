package Base;

import java.security.AlgorithmConstraints;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyThreadFactory implements ThreadFactory {

	private final String poolName;

	public MyThreadFactory(String poolName) {
		// TODO Auto-generated constructor stub
		this.poolName = poolName;
	}

	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		return new MyAppThread(r, poolName);
	}

}

class MyAppThread extends Thread {
	public static final String DEFAULT_NAME = "MyAppThread";
	private static volatile boolean debugLifeCycle = false;
	private static final AtomicInteger created = new AtomicInteger();
	private static final AtomicInteger alive = new AtomicInteger();
	private static final Logger log = Logger.getAnonymousLogger();

	public MyAppThread(Runnable r) {
		// TODO Auto-generated constructor stub
		this(r, DEFAULT_NAME);
	}

	public MyAppThread(Runnable r, String name) {
		// TODO Auto-generated constructor stub
		super(r, name + "-" + created.incrementAndGet());
		setUncaughtExceptionHandler(new UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread t, Throwable e) {
				// TODO Auto-generated method stub
				log.log(Level.SEVERE, "Uncaught INI THREAD " + t.getName(), e);
			}
		});
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		// 复制debug标志以确保一直的值
		boolean debug = debugLifeCycle;
		if (debug)
			log.log(Level.FINE, "CREATED " + getName());
		try {
			alive.incrementAndGet();
			super.run();
		} finally {
			alive.decrementAndGet();
			if (debug) {
				log.log(Level.FINE, "EXITING " + getName());
			}
		}
	}

	public static String getDefaultName() {
		return DEFAULT_NAME;
	}

	public static AtomicInteger getCreated() {
		return created;
	}

	public static AtomicInteger getAlive() {
		return alive;
	}

	public static Logger getLog() {
		return log;
	}
}