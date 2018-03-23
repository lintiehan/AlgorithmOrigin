package DP;

/*
 * 一个旅行者有一个最多能装m公斤的背包，现在有n中物品，每件的重量分别是W1、W2、……、Wn，
 * 每件物品的价值分别为C1、C2、……、Cn， 
 * 需要将物品放入背包中，要怎么样放才能保证背包中物品的总价值最大？
 */
public class Package0_1 {
	// 所有的物品
	private Thing[] bags;
	// 物品的数量
	private int n;
	// 背包总重
	private int totalWeight;
	// 第一维：当前第几个物品 第二维 ： 当前背包承重 值：当前背包最大价值
	private int[][] bestValues;
	//
	private int bestValue;

	public Package0_1(Thing[] bags, int totalWeight) {
		this.bags = bags;
		this.totalWeight = totalWeight;
		this.n = bags.length;
		if (bestValues == null) {
			// 考虑0的状态+1，防止数据下标越界
			bestValues = new int[n + 1][totalWeight + 1];
		}
	}

	public void solve() {
		// 遍历背包的承重
		for (int j = 0; j <= totalWeight; j++) {
			// 遍历指定物品
			for (int i = 0; i <= n; i++) {

				// 当背包不放入物品或承重为0时，最大价值为0
				if (i == 0 || j == 0) {
					bestValues[i][j] = 0;
				} else {
					// 如果第 i个物品小于于总承重，最优解不变
					if (bags[i - 1].getWeight() > j) {
						bestValues[i][j] = bestValues[i - 1][j];
					} else {
						// 如果第 i个物品不大于总承重，则最优解要么是包含第 i个背包的最优解，
						// 要么是不包含第 i个背包的最优解， 取两者最大值
						int weight = bags[i - 1].getWeight();
						int value = bags[i - 1].getValue();
						bestValues[i][j] = Math.max(bestValues[i - 1][j], value + bestValues[i - 1][j - weight]);
					}
				}

			}
		}

		for (int i = 0; i < bestValues.length; i++) {
			for (int j = 0; j < bestValues[i].length; j++) {
				System.out.print(bestValues[i][j] + " ");
			}
			System.out.println();
		}
		bestValue = bestValues[n][totalWeight];
	}

	public int getBestValue() {
		return bestValue;
	}

	public static void main(String[] args) {
		Thing[] bags = new Thing[] { new Thing(2, 13), 
				new Thing(1, 10), 
				new Thing(3, 24), 
				new Thing(2, 15),
				new Thing(4, 28), 
				new Thing(5, 33), 
				new Thing(3, 20), 
				new Thing(1, 8) };
		int totalWeight = 12;
		Package0_1 problem = new Package0_1(bags, totalWeight);

		problem.solve();
		System.out.println(problem.getBestValue());
	}
}

class Thing {
	/** 物品重量 */
	private int weight;
	/** 物品价值 */
	private int value;

	public Thing(int weight, int value) {
		this.weight = weight;
		this.value = value;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}