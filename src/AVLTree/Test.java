package AVLTree;

public class Test {
	  private static int arr[]= {3,2,1,4,5,6,7,16,15,14,13,12,11,10,8,9};

	    public static void main(String[] args) {
	        int i;
	        AVLTree<Integer> tree = new AVLTree<Integer>();

	        System.out.printf("插入数据");
	        for(i=0; i<arr.length; i++) {
	            System.out.printf("%d ", arr[i]);
	            tree.insert(arr[i]);
	        } 

	        System.out.printf("前序遍历：");
	        tree.preOrder();

	        System.out.printf("\n中序遍历： ");
	        tree.inOrder();

	        System.out.printf("\n后序遍历：");
	        tree.postOrder();
	        System.out.printf("\n");

	        System.out.printf("树高度 ： %d\n", tree.height());
	        System.out.printf("最小值 ： %d\n", tree.minimum());
	        System.out.printf("最大值: %d\n", tree.maximum());
	        System.out.printf("打印所有节点: \n");
	        tree.print();
	        tree.showRowbyRow();

	        // 移除树
	        tree.destroy();
	    }
}
