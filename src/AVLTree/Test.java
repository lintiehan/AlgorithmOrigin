package AVLTree;

public class Test {
	  private static int arr[]= {3,2,1,4,5,6,7,16,15,14,13,12,11,10,8,9};

	    public static void main(String[] args) {
	        int i;
	        AVLTree<Integer> tree = new AVLTree<Integer>();

	        System.out.printf("== �������: ");
	        for(i=0; i<arr.length; i++) {
	            System.out.printf("%d ", arr[i]);
	            tree.insert(arr[i]);
	        } 

	        System.out.printf("\n== ǰ�����: ");
	        tree.preOrder();

	        System.out.printf("\n== �������: ");
	        tree.inOrder();

	        System.out.printf("\n== �������: ");
	        tree.postOrder();
	        System.out.printf("\n");

	        System.out.printf("== �߶�: %d\n", tree.height());
	        System.out.printf("== ��Сֵ: %d\n", tree.minimum());
	        System.out.printf("== ���ֵ: %d\n", tree.maximum());
	        System.out.printf("== ������ϸ��Ϣ: \n");
	        tree.print();

	        i = 8;
	        System.out.printf("\n== ɾ�����ڵ�: %d", i);
	        tree.removeNode(i);

	        System.out.printf("\n== �߶�: %d", tree.height());
	        System.out.printf("\n== �������: ");
	        tree.inOrder();
	        System.out.printf("\n== ������ϸ��Ϣ: \n");
	        tree.print();

	        // ���ٶ�����
	        tree.destroy();
	    }
}
