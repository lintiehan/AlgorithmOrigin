package Base;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnLockBankTest {
	public static final int NACCOUNTS = 100;
	public static final double INITIAL_BALANCE = 1000;

	public static void main(String[] args) {
		Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
		int i;
		for ( i = 0; i < NACCOUNTS; i++)
		{
			TransferRunnable r=new TransferRunnable(b, i, INITIAL_BALANCE);
			Thread t=new Thread(r);
			t.start();
		}
	}
}

class Bank {
	private final double[] accounts;
	private Lock bankLock=new ReentrantLock();
	public Bank(int n, double initialBalance) {
		accounts = new double[n];
		for (int i = 0; i < accounts.length; i++) {
			accounts[i] = initialBalance;
		}
	}

	public  void transfer(int from, int to, double amount) {
		bankLock.lock();
		try {
			if (accounts[from] < amount) {
				return;
			}
			System.out.println(Thread.currentThread());
			accounts[from] -= amount;
			System.out.printf("%10.2f from %d to %d", amount, from, to);
			accounts[to] += amount;
			System.out.printf("Total Balance: %10.2f%n" , getTotalBalance());
		}finally {
			bankLock.unlock();
		}
	 }

	public double getTotalBalance() {
		double sum = 0;
		for (double a : accounts) {
			sum += a;
		}
		return sum;
	}

	public int size() {
		return accounts.length;
	}
}

class TransferRunnable implements Runnable {
	private Bank bank;
	private int fromAccount;
	private double maxAccount;
	private int DELAY = 10;

	public TransferRunnable(Bank b, int from, double max) {
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
