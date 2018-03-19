package Base;

import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		String s=scanner.nextLine();
		String string = gets(s);
		System.out.println(string);
	}

	public static String gets(String temp) {
		char[] cs = temp.toCharArray();
		char[] ne = new char[cs.length];
		int count=0;
		for (char c : cs) {
			if (isString(String.valueOf(c))) {
				c = (char) (c + 1);
				ne[count++]=c;
			} else {
				ne[count++]=c;				 
			}
		}
		return String.valueOf(ne);
	}

	public static boolean isString(String str) {
		Pattern pattern = Pattern.compile("[a-zA-Z]*");
		return pattern.matcher(str).matches();
	 	
	}
	
}
