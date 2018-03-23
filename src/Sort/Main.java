package Sort;

public class Main {
	public static void main(String[] args) {
		int []nums= {49,38,65,97,76,13,27};
		show(nums);
		 QuickSort(nums,0,nums.length-1);
		 //SelectSort(nums);
		show(nums);
		int []test= {11,34,13,14,100,1,12,3,14,45};
		MergeSort(test);
		show(test);
	}
	
	//�ϲ�����
	static int[]auk;
	private static void MergeSort(int []array)
	{
		auk=new int[array.length];
		MergeSort(array,0,array.length-1);
	}
	/**
	 * �鲢����
	 * ���:�����������������ϣ������ϲ���һ���µ������ ���Ѵ��������з�Ϊ���ɸ������У�ÿ��������������ġ�Ȼ���ٰ����������кϲ�Ϊ������������
	 * ʱ�临�Ӷ�ΪO(nlogn)
	 * �ȶ�����ʽ
	 * @param nums ����������
	 * @return �����������
	 */ 
	private static int[] MergeSort(int []array,int low,int high)
	{	
		int mid=(low+high)/2;
		if(low<high)
		{
			//���
			MergeSort(array,low,mid);
			//�ұ�
			MergeSort(array,mid+1,high);
			//���ҹ鲢
			Merge(array,low,mid,high);
		}
		return array;
	}
	/**
	 * ��������low��highλ�õ�����������
	 * @param array ����������
	 * @param low ���ŵĿ�ʼλ��
	 * @param mid �����м�λ��
	 * @param high ���Ž���λ��
	 */
	private static void Merge(int []array,int low,int mid,int high)
	{
		int[]temp=new int[high-low+1];
		int i=low;
		int j=mid+1;
		int k=0;
		//�ѽ�С�������Ƶ���������
		while(i<=mid&&j<=high)
		{
			if(array[i]<array[j]) {
				temp[k++]=array[i++];
			}else
			{
				temp[k++]=array[j++];
			}
		}
		
		//�����ʣ�������������
		while(i<=mid)
		{
			temp[k++]=array[i++];
		}
		//���ұ�ʣ�������������
		while(j<=high)
		{
			temp[k++]=array[j++];
		}
		
		//���������е�������arrays����
		for(int k2=0;k2<temp.length;k2++)
		{
			array[k2+low]=temp[k2];
		}
	}

	/**  
	 * ��������
	 * 
	 * �ӵ�һ��Ԫ�ؿ�ʼ����Ԫ�ؿ�����Ϊ�Ѿ�������
	 * ȡ����һ��Ԫ�أ����Ѿ������Ԫ�������дӺ���ǰɨ�� 
	 * �����Ԫ�أ������򣩴�����Ԫ�أ�����Ԫ���Ƶ���һλ��  
	 * �ظ�����3��ֱ���ҵ��������Ԫ��С�ڻ��ߵ�����Ԫ�ص�λ��  
	 * ����Ԫ�ز��뵽��λ����  
	 * �ظ�����2  
	 * @param numbers  ����������
	 */  
	public static void insertSort(int[] numbers)
	{
	int size = numbers.length;
	int temp = 0 ;
	int j =  0;

	for(int i = 0 ; i < size ; i++)
	{
	    temp = numbers[i];
	    //����temp��ǰ���ֵС����ǰ���ֵ����
	    for(j = i ; j > 0 && temp < numbers[j-1] ; j --)
	    {
	    	numbers[j] = numbers[j-1];
	    }
	    numbers[j] = temp;
	}
	}

	 /**
	 * ѡ�������㷨
	 * ��δ�����������ҵ���СԪ�أ���ŵ��������е���ʼλ��  
	 * �ٴ�ʣ��δ����Ԫ���м���Ѱ����СԪ�أ�Ȼ��ŵ���������ĩβ�� 
	 * �Դ����ƣ�ֱ������Ԫ�ؾ�������ϡ� 
	 * @param numbers
	 */
	public static void SelectSort(int []nums)
	{
		int size=nums.length;
		int temp=0;
		for (int i=0;i<size;i++)
		{
			int k=i;
			for(int j=size-1;j>i;j--)
			{
				if(nums[j]<nums[k])
				{
					k=j;
				}
			}
			temp=nums[i];
			nums[i]=nums[k];
			nums[k]=temp;
		}
	}
	/**
	 * ��������
	 * ����������ͨ������Ϊ��ͬ��������O(nlog2n)�������򷽷���ƽ��������õġ�
	 * ������ʼ���а��ؼ���������������ʱ�������򷴶��ɻ�Ϊð������
	 * Ϊ�Ľ�֮��ͨ���ԡ�����ȡ�з�����ѡȡ��׼��¼��
	 * ������������������˵����е�������¼�ؼ�����еĵ���Ϊ֧���¼��
	 * ����������һ�����ȶ������򷽷���
	 */
	//1.�������ᣨ���λ��Ϊ���ᣩ����λ�ã�
	public static int GetMiddle(int []nums,int low ,int high)
	{
		int temp=nums[low];
		while(low<high)
		{
			while(low<high&&nums[high]>=temp) //������С�ļ�¼�Ƶ��Ͷ�
			{
				high--;
			}
			nums[low]=nums[high];
			while(low<high&&nums[low]<=temp)//�������ļ�¼�Ƶ��߶�
			{
				low++;
			}
			nums[high]=nums[low];
			//show(nums);
		}
		nums[low]=temp; //�����¼��β
	//	System.out.println(low);
		return low;//��������λ�á�
	}
	//2�� �ݹ���ʽ�ķ��������㷨��
	public static void QuickSort(int []nums,int low,int high)
	{
		if(low<high)
		{
			int middle=GetMiddle(nums, low, high);
			QuickSort(nums,low,middle-1);//�Ե��ֶα���еݹ�����
			QuickSort(nums,middle+1,high);//�Ը��ֶα���еݹ�����
		}
	}
		
	/**
	 * ð������
	 * @param numbers
	 */
	public static void BubbleSort(int [] numbers)
	{
		int temp=0;
		int size =numbers.length;
		for(int i=0;i<size-1;i++)
		{
			for(int j=0;j<size-1-i;j++)
			{
				if(numbers[j]>numbers[j+1])
				{
					temp=numbers[j];
					numbers[j]=numbers[j+1];
					numbers[j+1]=temp;
				}
			}
		}
	}
	/**ϣ�������ԭ��:���������������Ҫ����Ӵ�С���У��������Ƚ�������з��飬
	 * Ȼ�󽫽ϴ�ֵ�Ƶ�ǰ�棬��Сֵ�Ƶ����棬�������������в�������
	 * ��������һ��ʼ���ò���������������ݽ������ƶ��Ĵ���������˵ϣ�������Ǽ�ǿ��Ĳ�������
	 * ������5, 2, 8, 9, 1, 3��4��˵�����鳤��Ϊ7����incrementΪ3ʱ�������Ϊ��������
	 * 5��2��8��9��1��3��4����һ������9��5�Ƚϣ�1��2�Ƚϣ�3��8�Ƚϣ�4�ͱ����±�ֵСincrement������ֵ��Ƚ�
	 * �������ǰ��մӴ�С���У����Դ�Ļ�����ǰ�棬��һ�����������Ϊ9, 2, 8, 5, 1, 3��4
	 * ��һ�κ�increment��ֵ��Ϊ3/2=1,��ʱ��������в�������
	 *ʵ������Ӵ�С��
	 *
	 *ʱ�临�Ӷȣ�O��n^2��.
	 */ 
 	public static void sheelSort(int []data)
 	{
 		int j=0;
 		int temp=0;
 		//ÿ�ν���������Ϊԭ����һ��
 		for(int increment=data.length/2;increment>0;increment/=2)
 		{
 			for(int i=increment;i<data.length;i++)
 			{
 				temp =data[i];
 				for(j=i;j>=increment;j-=increment)
 				{
 					if(temp>data[j-increment])
 					{
 						data[j]=data[j-increment];
 					}else {
 						break;
 					}
 				}
 				data[i]=temp;
 			}
 		}
 	}
	
	public static void show(int [] numbers)
	{
		for(int i=0;i<=numbers.length-1;i++)
		{
			System.out.print(numbers[i]+" ");
		}
		System.out.println();
	}
}
