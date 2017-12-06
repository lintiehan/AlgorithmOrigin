package Problem;

/**
 * 
 * 维护一个K长度的数组a[]，先读取源数据中的前K个放入数组，对该数组进行升序排序，再依次读取源数据第K个以后的数据，
 * 和数组中最小的元素（a[0]）比较，如果小于a[0]直接pass，大于的话，就丢弃最小的元素a[0]，利用二分法找到其位置，然后该位置前的数组元素整体向前移位，直到源数据读取结束。
 * 
 * @author student02
 *
 */
public class TopK {
	  
	public static void main(String[] args) {
		 // 源数据  
       int[] data = {56,275,12,6,45,478,41,1236,456,12,546,45};  
    // 获取Top5  
       int[] top5 = topK(data, 5);  
         
       for(int i=0;i<5;i++)  
       {  
           System.out.println(top5[i]);  
       }  
   }
	private static int[] topK(int[] data, int k) {
		int []topk=new int[k];
		for(int i=0;i<k;i++)
		{
			topk[i]=data[i]; 
		}  
		
		//转化成最小堆 
		MinHeap heap = new MinHeap(topk);  
		 // 从k开始，遍历data  
        for(int i= k;i<data.length;i++)  
        {  
            int root = heap.getRoot();               
            // 当数据大于堆中最小的数（根节点）时，替换堆中的根节点，再转换成堆  
            if(data[i] > root)  
            {  
                heap.setRoot(data[i]);  
            }  
        }           
        return topk;  
	}
}
class MinHeap {
	// 堆的存储结构 数组
	private int[] data;

	// 将一个数组传入构造方法，并转换成一个小根堆
	public MinHeap(int[] data) {
		this.data = data;
		buildHeap();
	}

	// 将数组转换成最小堆
	private void buildHeap() {
		// 完全二叉树只有数组下标小于或等于 (data.length) / 2 - 1 的元素有孩子结点，遍历这些结点。
		for (int i = (data.length) / 2 - 1; i >= 0; i--) {
			heapify(i);
		}
	}

	private void heapify(int i) {
		// TODO Auto-generated method stub
		int l = 2 * (i+1);
		int r = 2 * (i+1) - 1;

		// 这是一个临时变量，表示根节点，左节点，右节点最小值节点的下标
		int smallest = i;

		// 存在左节点，且左节点的值小于根节点的值
		if (l < data.length && data[l] < data[i])
			smallest = l;
		
		// 存在右节点，且右节点的值小于以上比较的较小值
		if (r < data.length && data[r] < data[smallest])
			smallest = r;
		
		// 左右节点的值都大于根节点，直接return 不做任何操作
		if (i==smallest)
			return;
		
		 // 交换根节点和左右结点中最小的那个值，把根节点的值替换下去  
		swap(i,smallest);
		 // 由于替换后左右子树会被影响，所以要对受影响的子树再进行heapify  
        heapify(smallest);  
	}

	 // 交换元素位置  
    private void swap(int i, int j)   
    {    
        int tmp = data[i];    
        data[i] = data[j];    
        data[j] = tmp;    
    }  
    
 // 获取对中的最小的元素，根元素  
    public int getRoot()  
    {  
            return data[0];  
    }  
  
    // 替换根元素，并重新heapify  
    public void setRoot(int root)  
    {  
        data[0] = root;  
        heapify(0);  
    }  
}