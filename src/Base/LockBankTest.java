package Base;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockBankTest {
	public static final int NACCOUNTS = 100;
	public static final double INITIAL_BALANCE = 1000;

	public static void main(String[] args) {
		Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
		int i;
		for (i = 0; i < NACCOUNTS; i++) {
			TransferRunnable r = new TransferRunnable(b, i, INITIAL_BALANCE);
			Thread t = new Thread(r);
			t.start();
		}
	}
}

class LockBank {
	private final double[] accounts;
	private Lock bankLock;
	private Condition sufficientFunction;

	public LockBank(int n, double initialBalance) {
		accounts = new double[n];
		for (int i = 0; i < accounts.length; i++) {
			accounts[i] = initialBalance;
		}
		bankLock = new ReentrantLock();
		sufficientFunction = bankLock.newCondition();
	}

	public void transfer(int from, int to, double amount) {
		bankLock.lock();
		try {
			while (accounts[from] < amount) {
				//将该线程放到条件的等待集中
				sufficientFunction.await();  
			}
			System.out.println(Thread.currentThread());
			accounts[from] -= amount;
			System.out.printf("%10.2f from %d to %d", amount, from, to);
			accounts[to] += amount;
			System.out.printf("Total Balance: %10.2f%n", getTotalBalance());
			//从该条件中的等待集中的所有线程的阻塞状态
			sufficientFunction.signalAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			bankLock.unlock();
		}
	}

	public double getTotalBalance() {
		bankLock.lock();
		try {
			double sum = 0;
			for (double a : accounts) {
				sum += a;
			}
			return sum;
		}finally {
			bankLock.unlock();
		}
	}

	public int size() {
		return accounts.length;
	}
}

class LockTransferRunnable implements Runnable {
	private Bank bank;
	private int fromAccount;
	private double maxAccount;
	private int DELAY = 10;

	public LockTransferRunnable(Bank b, int from, double max) {
		bank = b;
		fromAccount = from;
		maxAccount = max;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				int toAccount = (int) (bank.size() * Math.random());
				double amount = maxAccount * Math.random();
				bank.transfer(fromAccount, toAccount, amount);
				Thread.sleep((int) (DELAY * Math.random()));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
