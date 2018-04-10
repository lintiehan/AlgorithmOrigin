package Problem;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

/*
 * 茉莉邀请她的朋友参加周末派对，茉莉买了3种颜色的气球，
 * 现在她要有这些气球来装饰餐桌，每个餐桌只用恰好3个气球来装饰，
 * 要求3个气球颜色不能完全一样，可以是2或3种颜色，
 * 茉莉想知道这些气球最多能装饰多少张餐桌。

输入
第一行一个数T（T<=100）,表示数据组数。 
对于每组数据，第一行3个整数r,g,b，分别表示三种颜色的气球个数（0<=r,g,b<=2*10^9）

输出
对于每组数据，输出一行，一个整数表示最多能装饰的餐桌数量。

样例输入
2 
5 4 3 
2 3 3

样例输出
4 
2
 */
public class DivideBalloon {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int[] colors = new int[3];
		for (int i = 0; i < 3; i++) {
			colors[i] = scanner.nextInt();
		}
		Arrays.sort(colors);

		if (colors[0] + colors[1] > (colors[2] / 2)) {

			int sum = colors[0] + colors[1] + colors[2];
			System.out.println(sum / 3);
		} else {

			System.out.println(colors[0] + colors[1]);
		}
	}
}
