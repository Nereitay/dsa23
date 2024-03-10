package es.kiwi.datastructure.binarytree;

/**
 * 递归遍历二叉树
 */
public class TreeTraversal {
    public static void main(String[] args) {
         /*
                1
               / \
              2   3
             /   / \
            4   5   6
         */

        TreeNode root = new TreeNode(
                1,
                new TreeNode(2,new TreeNode(4),  null),
                new TreeNode(3,new TreeNode(5),  new TreeNode(6))
        );

        preOrder(root);
        System.out.println();

        inOrder(root);
        System.out.println();

        postOrder(root);
        System.out.println();
    }
    /**
     * <h3>后序遍历</h3>
     * @param node 节点
     */
    private static void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.left); // 左
        postOrder(node.right); // 右
        System.out.print(node.val + "\t"); // 值
    }

    /**
     * <h3>中序遍历</h3>
     * @param node 节点
     */
    private static void inOrder(TreeNode node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.val + "\t");
        inOrder(node.right);
    }

    /**
     * <h3>前序遍历</h3>
     * @param node 节点
     */
    private static void preOrder(TreeNode node) {
        if (node == null) return;

        System.out.print(node.val + "\t");
        preOrder(node.left);
        preOrder(node.right);
    }
}
