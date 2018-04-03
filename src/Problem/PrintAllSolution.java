package Problem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

/*
 * 用1、2、2、3、4、5这六个数字，打印出所有不同的排列,要求'4'不能在第三位，'3'与'5'不能相连
 */
public class PrintAllSolution {
	static TreeSet<String> ts = new TreeSet<String>();

	public static void main(String[] args) {
		String s[] = { "1", "2", "2", "3", "4", "5" };
		List<String> list = new ArrayList<String>();
		StringBuilder rs = new StringBuilder();
		for (int i = 0; i < s.length; i++) {
			list.add(s[i]);
		}
		permutation(list, rs);
		Iterator<String> iterator = ts.iterator();
		while (iterator.hasNext())
			System.out.println(iterator.next());

		System.out.println("总数是：" + ts.size());
	}

	private static void permutation(List<String> list, StringBuilder rs) {
		if (list.size() == 1) {
			rs.append(list.get(0));
			if (rs.indexOf("4") != 2 && (!rs.toString().contains("35") && !rs.toString().contains("53"))) {
				ts.add(rs.toString());
			}
			rs.delete(rs.length() - 1, rs.length());
		} else {
			for (int i = 0; i < list.size(); i++) {
				rs.append(list.get(i));
				List<String> temp = new ArrayList<>();
				for (String a : list) {
					temp.add(a);
				}
				temp.remove(i);

				permutation(temp, rs);

				rs.delete(rs.length() - 1, rs.length());
			}
		}
	}
}
