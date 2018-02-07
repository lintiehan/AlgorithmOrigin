package Base_NIO;

import java.util.Scanner;
/*
 * http://www.jb51.net/article/131810.htm
 */

public class Test {
	public static void main(String[] args) throws  Exception {
		Server.start();
		Thread.sleep(1000);
		Client.start();
		while(Client.sendMsg(new Scanner(System.in).nextLine()));
	}
}
