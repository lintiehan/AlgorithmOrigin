package Problem;

import java.awt.event.MouseWheelEvent;
import java.math.BigInteger;
import java.util.Scanner;

public class BigData {
	 public static void main(String[] args){  
	        Scanner input = new Scanner(System.in); 
	        int T;
	        T = input.nextInt();
	        int modd = 10000003;
	        int n, m;  
	        while (input.hasNext()) {  
	            n = input.nextInt();  
	            m = input.nextInt();
	            BigInteger ans = BigInteger.ONE;  
	            for (int i = m + 1; i <= n; i++) {  
	                ans = ans.multiply(BigInteger.valueOf(i));
	            }
	            for (int i = 1; i <= n - m; i++) {
	                ans = ans.divide(BigInteger.valueOf(i));
	            }
	            System.out.println(ans.mod(BigInteger.valueOf(modd)));  
	        }  
	 }
}
