package Problem;

import java.util.Scanner;
import java.util.Vector;

/*��k���ַ�����������ǵ������ǰ׺(LCP)
����
�� "ABCD" "ABEF" �� "ACEF" ��,  LCP Ϊ "A"

�� "ABCDEFG", "ABCEFG", "ABCEFA" ��, LCP Ϊ "ABC"
*/
public class TheLongestPublicPrefix {
	 
	public static String longestCommonPrefiex(Vector<String>strs) {
		if(strs.size()==0)
			return "";
		String ret=strs.get(0);
		for(int i=1;i<strs.size();i++)
		{
			String cur=strs.get(i);
			System.out.println("cur-->"+cur);
			String temp=ret;
			System.out.println("temp-->"+temp);
			ret="";
			for(int j=0;j<Math.min(temp.length(), cur.length());j++)
			{
				if(temp.charAt(j)==cur.charAt(j))
				{
					ret+=cur.charAt(j);
					System.out.println("ret-->"+ret);
				}else {
					break;
				}
			}
		}
		return ret;
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Vector<String> strs=new Vector<>();
		for(int i=0;i<3;i++)
		{
			String temp=sc.nextLine();
			strs.add(temp);
		}
		String res=longestCommonPrefiex(strs);
		System.out.println(res);
	}
}