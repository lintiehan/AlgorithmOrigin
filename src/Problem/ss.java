package Problem;

public class ss {

    static class BinaryTreeNode {
        int mVal;
        BinaryTreeNode mLeft;
        BinaryTreeNode mRight;

        public BinaryTreeNode() {
            this(0);
        }

        public BinaryTreeNode(int val) {
            this.mVal = val;
            this.mLeft = null;
            this.mRight = null;
        }

    }

    /**
     * 
     * @param preOrder ǰ���������
     * @param inOrder �����������
     * @param length ���鳤��
     * @return �����
     */
    public static BinaryTreeNode Construct(int[] preOrder, int[] inOrder,
            int length) {
        if (preOrder == null || inOrder == null || length <= 0) {
            return null;
        }
        try {
            return ConstructCore(preOrder, 0, preOrder.length - 1, inOrder, 0,
                    inOrder.length - 1);
        } catch (InvalidPutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 
     * @param PreOrder
     *            ǰ���������
     * @param startPreIndex
     *            ǰ�����п�ʼλ��
     * @param endPreIndex
     *            ǰ�����н���λ��
     * @param InOrder
     *            �����������
     * @param startInIndex
     *            �������п�ʼλ��
     * @param endInIndex
     *            �������н���λ��
     * @return �����
     * @throws InvalidPutException
     */
    public static BinaryTreeNode ConstructCore(int[] preOrder,
            int startPreIndex, int endPreIndex, int[] inOrder,
            int startInIndex, int endInIndex) throws InvalidPutException {

        int rootValue = preOrder[startPreIndex];
        System.out.println("rootValue = " + rootValue);
        BinaryTreeNode root = new BinaryTreeNode(rootValue);

        // ֻ��һ��Ԫ��
        if (startPreIndex == endPreIndex) {
            if (startInIndex == endInIndex
                    && preOrder[startPreIndex] == inOrder[startInIndex]) {
                System.out.println("only one element");
                return root;
            } else {
                throw new InvalidPutException();
                // System.out.println("invalid put");

            }
        }

        // ������������ҵ�����������
        int rootInIndex = startInIndex;

        while (rootInIndex <= endInIndex && inOrder[rootInIndex] != rootValue) {
            ++rootInIndex;
        }

        if (rootInIndex == endInIndex && inOrder[rootInIndex] != rootValue) {
            throw new InvalidPutException();

        }

        int leftLength = rootInIndex - startInIndex;

        int leftPreOrderEndIndex = startPreIndex + leftLength;

        if (leftLength > 0) {
            // ����������
            root.mLeft = ConstructCore(preOrder, startPreIndex + 1,
                    leftPreOrderEndIndex, inOrder, startInIndex,
                    rootInIndex - 1);
        }

        if (leftLength < endPreIndex - startPreIndex) {
            // ��������Ԫ��,����������
            root.mRight = ConstructCore(preOrder, leftPreOrderEndIndex + 1,
                    endPreIndex, inOrder, rootInIndex + 1, endInIndex);
        }
        return root;

    }

    static class InvalidPutException extends Exception {

        private static final long serialVersionUID = 1L;

    }

    public static void printPreOrder(BinaryTreeNode root) {
        if (root == null) {
            return;
        } else {
            System.out.print(root.mVal + " ");
        }

        if (root.mLeft != null) {
            printPreOrder(root.mLeft);
        }

        if (root.mRight != null) {
            printPreOrder(root.mRight);
        }
    }

    public static void main(String[] args) {

        int[] preOrder = new int[] { 1, 2, 4, 7, 3, 5, 6, 8 };
        int[] inOrder = new int[] { 4, 7, 2, 1, 5, 3, 8, 6 };

        printPreOrder(Construct(preOrder, inOrder, preOrder.length));

    }

}