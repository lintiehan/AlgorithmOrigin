package Offer;

/*
 * KMP重新认识
 */
public class Test11 {

	public static void main(String[] args) {
		String aString = "BBC ABCDAB ABCDABCDABDE";
		String bString = "ABCDABD";
		System.out.println(getMatchKmp(aString.toCharArray(), bString.toCharArray()));
	}

	public static int getMatch(char[] a, char[] b) {
		int alen = a.length;
		int blen = b.length;
		int i = 0, j = 0;
		while (i < alen && j < blen) {
			if (a[i] == b[j]) {
				i++;
				j++;
			} else {
				i = i - j + 1;
				j = 0;
			}
		}
		System.out.println(i + " " + j);
		if (j == blen) {
			return i - j;
		} else
			return -1;
	}

	public static int[] getNext(char[] s) {
		int len = s.length;
		int next[] = new int[len];
		next[0] = -1;
		int k = -1;
		int j = 0;
		while (j < len - 1) {
			if (k == -1 || s[j] == s[k]) {
				next[++j] = ++k;
			} else {
				k = next[k];
			}
		}
		return next;
	}

	public static int getMatchKmp(char[] a, char[] b) {
		int i = 0, j = 0;
		int alen = a.length;
		int blen = b.length;
		int[] next = getNext(b);
		while (i < alen && j < blen) {
			if (j == -1 || a[i] == b[j]) {
				i++;
				j++;
			} else {
				j = next[j];
			}
		}
		if (j == blen)
			return i - j;
		else
			return -1;

	}
}
