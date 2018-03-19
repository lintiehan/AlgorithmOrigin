package Thread;

public class SequenceNumber {
	private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
		public Integer initialValue() {
			return 0;
		}
	};

	public int getNextNum() {
		seqNum.set(seqNum.get() + 1);
		return seqNum.get();
	}

	public static void main(String[] args) {
		SequenceNumber snNumber = new SequenceNumber();
		TestClient t1 = new TestClient(snNumber);
		TestClient t2 = new TestClient(snNumber);
		TestClient t3 = new TestClient(snNumber);

		t1.start();
		t2.start();
		t3.start();
		System.out.println("-----" + Thread.currentThread().getName() + ":" + snNumber.getNextNum());
		System.out.println("-----" + Thread.currentThread().getName() + ":" + snNumber.getNextNum());
		System.out.println("-----" + Thread.currentThread().getName() + ":" + snNumber.getNextNum());
	}

	private static class TestClient extends Thread {
		private SequenceNumber sn;

		public TestClient(SequenceNumber sn) {
			this.sn = sn;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			// 每个线程打出3个序列值
			for (int i = 0; i < 3; i++) {
				System.out.println("thread[" + Thread.currentThread().getName() + "] sn[" + sn.getNextNum() + "]");
			}
		}
	}
}
