package Problem;

import java.io.BufferedReader; 
import java.io.FileReader;
/**
 * 要求从多个的数据中查找出前K个最小或最大值
 * @author student02
 *方法1：先定义容量为k的数组，从源数据中取出前k个填充此数组，调整此数组的最大值maxValue到首位，
 *然后对剩下的n-k个数据迭代，对于每个遍历到的数字x,如果x < maxValue,用x把maxValue替换掉，然后调整数组最大值的位置。
 *基于方法1的思路，维护容量为k的堆，从源数据中取出前k个填充实例化堆，调整此堆中的最大值maxValue到堆顶，
 *然后对剩下的n-k个数据迭代，对于每个遍历到的数字x,如果x < maxValue,用x把maxValue替换掉，然后调整堆最大值的位置。
 */
public class FindNum {
	public int [] findMinNumIncludedTopN(int k) throws Exception
	{
		Long start=System.nanoTime();
		int []heap=new int[k];
		int index=0;
		
		//从文件中导入数据
		BufferedReader reader=new BufferedReader(new FileReader("11.txt"));
		String text=null;
		
		do {
			text=reader.readLine();
			if(text!=null)
			{
				heap[index]=Integer.parseInt(text);
			}
			index++;
		}while(text!=null&&index<=k-1);
		buildHeap(heap);// 建堆，并调整最大值的位置为首位
		// 遍历文件中剩余的n（文件数据容量，假设为无限大）-k条数据，如果读到的数据比heap[0]小，就替换之，同时更新堆
		while(text!=null)
		{
			text = reader.readLine();
			if (text != null && !"".equals(text.trim())) {
				if (Integer.parseInt(text) < heap[0]) {
				heap[0] = Integer.parseInt(text);
					maxHeap(heap);
				}
			}
		}
		long end=System.nanoTime();
		long time=end-start;
		System.out.println("用時："+time+"納秒");
		return heap;
	}
	/**
	* 构建堆
	* 
	* @param heap
	*/
	public void buildHeap(int[] heap) {
		maxHeap(heap);
	}
	private void maxHeap(int[] heap) {
		// TODO Auto-generated method stub
		int max=heap[0];
		int largeIndex=0;
		//找出最大索引位置，从第二个位置开始
		for(int i=1;i<heap.length;i++)
		{
			if(heap[i]>max)
			{
				max=heap[i];
				largeIndex=i;
			}
		}
		swap(heap,largeIndex);
	}
	/**
	* 数据交换
	* 
	* @param heap
	* @param largeIndex
	*/
	private void swap(int[] heap, int largeIndex) {
		int temp;
		temp=heap[0];
		heap[0]=heap[largeIndex];
		heap[largeIndex]=temp;
	}
}
