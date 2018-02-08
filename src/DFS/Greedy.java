package DFS;



import java.util.Arrays;
import java.util.Scanner;

/*
 * [背包问题]有一个背包，背包容量是M=150。有7个物品，物品可以分割成任意大小。
    要求尽可能让装入背包中的物品总价值最大，但不能超过总容量。
    物品 A B C D E F G
    重量 35 30 60 50 40 10 25
    价值 10 40 30 50 35 40 30
 */
public class Greedy {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("输入物品数量：");
		int n = in.nextInt();
		int[] w = new int[n];// 物品重量数组
		int[] v = new int[n];// 物品价值数组 
		System.out.println("现在输入这些物品的重量：");
		for (int i = 0; i < n; i++) {
			w[i] = in.nextInt();
		}
		System.out.println("现在输入这些物品的价值：");
		for (int i = 0; i < n; i++) {
			v[i] = in.nextInt();
		}
		System.out.println("现在输入背包的容量：");
		int c = in.nextInt();

		double[] r = new double[n];//单位重量价值
		int[] index = new int[n];
		for (int i = 0; i < n; i++) {
			r[i] = (double) v[i] / (double) w[i];
			index[i] = i;
		}
		double temp = 0;
		/*
		 * 按单位重量价值r[i]=v[i]/w[i]降序排列 排序用选择排序
		 */
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (r[i] < r[j]) {
					temp = r[i];
					r[i] = r[j];
					r[j] = temp;
					int x = index[i];
					index[i] = index[j];
					index[j] = x;
				}
			}
		}

		// 排序后的重量和价值分别存在w1[]和v1[]中
		int[] w1 = new int[n];
		int[] v1 = new int[n];
		for (int i = 0; i < n; i++) {	
			w1[i] = w[index[i]];
			v1[i] = v[index[i]];
		}
		int maxValue = 0;
		// 初始化解向量
		int[] x = new int[n];
		for (int i = 0; i < n; i++) {
			if (w1[i] < c) {
				x[i] = 1;
				c = c - w1[i];
				maxValue += v1[i];
			} else {
				x[i] = c / w[index[i]];
				maxValue += x[i] * v[index[i]];
				// break; 去掉这个就好
			}
		}
		System.out.println("解向量为:" + Arrays.toString(x));
		System.out.println("背包最大价值为:" + maxValue);

	}
}
