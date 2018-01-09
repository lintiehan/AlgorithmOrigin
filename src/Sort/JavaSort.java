package Sort;
/*
 * �������ͣ����õ��ŵĿ�������
 * Դ���еĿ���������Ҫ�������¼���������Ż���
����1����������������е�Ԫ�ظ�������ʱ��Դ���еķ�ֵΪ7�����õ��ǲ�������
���ܲ��������ʱ�临�Ӷ�Ϊ0(n^2)�����ǵ�����Ԫ�ؽ���ʱ�������������ڿ���������Ϊ��ʱ��������ĵݹ����Ӱ�����ܡ�
����2���Ϻõ�ѡ���˻���Ԫ����׼Ԫ�أ����ܹ�������ֳɴ���������ȵĲ��֣����������������
���統��������ĵ�����£�ѡ���һ��Ԫ����Ϊ����Ԫ����ʹ���㷨��ʱ�临�ӶȴﵽO(n^2).
����Դ����ѡ�񻮷�Ԫ�ķ���:
���������������СΪ size=7 ʱ ��ȡ�����м�Ԫ����Ϊ����Ԫ��int n=m>>1;(�˷���ֵ�ý��)
���������������С 7<size<=40ʱ��ȡ�ס��С�ĩ����Ԫ���м��С��Ԫ����Ϊ����Ԫ��
���������������С size>40 ʱ ���Ӵ��������нϾ��ȵ�ѡ��9��Ԫ�أ�ѡ��һ��α������Ϊ����Ԫ��
����3�����ݻ���Ԫ v ���γɲ���ʽ v* (<v)* (>v)* v*
������ͨ�Ŀ��������㷨������һ�λ��ֺ󣬽�����Ԫ�ŵ�������м��λ�ã���ߵ�Ԫ��С�ڻ���Ԫ���ұߵ�Ԫ�ش��ڻ���Ԫ��
��û�н��뻮��Ԫ��ȵ�Ԫ�ط����丽������һ�㣬��Arrays.sort()�еõ��˽ϴ���Ż���
����������15��93��15��41��6��15��22��7��15��20
������  7<size<=40,������15��6����20 ��ѡ��v = 15 ��Ϊ����Ԫ��
��������һ�λ��ֺ� 15��15��7��6��41��20��22��93��15��15. �뻮��Ԫ��ȵ�Ԫ�ض��Ƶ�����������ߡ�
�������������뻮��Ԫ��ȵ�Ԫ���Ƶ������м������γɣ�7��6��15��15��15��15��41��20��22��93.
�������ݹ�����������������[7��6]��[41��20��22��93].
 */
public class JavaSort {
	private JavaSort() {}
	
	/*
	 * ��ָ����int�������鰴���������������
	 */
	public static void sort(int []a)
	{
		sort(a,0,a.length);
	}
	/*
	 * ��ָ�� int �������ָ����Χ�����������������
	 */
	public static void sort(int []a,int fromIndex,int toIndex)
	{
		 rangeCheck(a.length, fromIndex, toIndex);
		 sort1(a, fromIndex, toIndex - fromIndex);
	}
	/*
	 * ��������������е�Ԫ�ظ���С�� 7 ʱ�����ò������� ��
	 ���ܲ��������ʱ�临�Ӷ�ΪO(n^2),���ǵ�����Ԫ�ؽ���ʱ�� �����������ڿ�������
	 ��Ϊ��ʱ��������ĵݹ����Ӱ�����ܡ�
	 */
	private static void sort1(int x[], int off, int len) {
		if(len<7)
		{
			for(int i=off;i<len+off;i++)
			{
				for(int j=i;j>off&&x[j-1]>x[j];j--)
				{
					swap(x,j,j-1);
				}
			}
			return ;
		}
		
		/*
		 * ��������������е�Ԫ�ظ������ڻ����7ʱ�����ÿ�������
		 * ѡȡһ������Ԫ
		 * �Ϻõ�ѡ���˻���Ԫ����׼Ԫ�أ����ܹ�������ֳɴ���������ȵĲ��֣��������������
		 * ������������������
		 * ѡ���һ��Ԫ����Ϊ����Ԫ����ʹ���㷨��ʱ�临�ӶȴﵽO(n^2)
		 */
		//�������СΪ7<size<40ʱ��ȡ����ĩ����Ԫ���м��С��Ԫ����Ϊ����Ԫ
		int m = off + (len >> 1);    //ȡ������м�λ��
		if(len>7)
		{
			int l=off;      //ͷ
			int n=off+len -1;		//β
			/*
			 * �������С����40ʱ���Ӵ����������нϾ��ȵ�ѡ��9��Ԫ�أ�
			 * ѡ��һ��α��λ����Ϊ����Ԫ
			 */			
			if(len>40)
			{
				int s=len/8;
				l=med3(x,1,1+s,1+2*s);
				m=med3(x,m-s,m,m+s);
				n=med3(x,n-2*s,n-s,n);
			}
			//ȡ���м��С��Ԫ�ص�λ��
			 m = med3(x, l, m, n); // Mid-size, med of 3
		}
		
		//�õ�����ԪV
		int v=x[m];
		// Establish Invariant: v* (<v)* (>v)* v*
		int a=off,b=a,c=off+len-1,d=c;
		while(true)
		{
			while(b<=c&&x[b]<=v)
			{
				if(x[b]==v)
				{
					swap(x,a++,b);
				}
				b++;
			}
			while(c>=b&&x[c]>=v)
			{
				if(x[c]==v)
				{
					swap(x,c,d--);
				}
				c--;
			}
			if(b>c)
			{
				break;
			}
			swap(x,b++,c--);
		}
		
		// Swap partition elements back to middle
		int s,n=off+len;
		s=Math.min(a-off, b-1);
		vecswap(x, off, b - s, s);
		s = Math.min(d - c, n - d - 1);
		vecswap(x, b, n - s, s);
		// Recursively sort non-partition-elements
		if ((s = b - a) > 1)
			sort1(x, off, s);
		if ((s = d - c) > 1)
			sort1(x, n - s, s);
	}
	
	private static void vecswap(int x[],int a,int b,int n)
	{
		for(int i=0;i<n;i++,a++,b++)
		{
			swap(x,a,b);
		}
	}
	
	private static int med3(int x[],int a,int b,int c)
	{
		return (x[a]<x[b]?(x[b] < x[c] ? b : x[a] < x[c] ? c : a)
				 : (x[b] > x[c] ? b : x[a] > x[c] ? c : a));
	}
	
	private static void rangeCheck(int arrayLen,int fromIndex,int toIndex)
	{
		if(fromIndex>toIndex)
		{
			throw new IllegalArgumentException("fromIndex(" + fromIndex
            + ") > toIndex(" + toIndex + ")");
		}
		if (fromIndex < 0)
			throw new ArrayIndexOutOfBoundsException(fromIndex);
		if (toIndex > arrayLen)
			 throw new ArrayIndexOutOfBoundsException(toIndex);
	}
	private static void swap(int []x,int a,int b)
	{
		int t=x[a];
		x[a]=x[b];
		x[b]=t;
	}
}
