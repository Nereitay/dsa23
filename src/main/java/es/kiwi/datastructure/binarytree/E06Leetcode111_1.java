package es.kiwi.datastructure.binarytree;
/**
 * 二叉树最小深度 - 后序遍历实现
 */
public class E06Leetcode111_1 {
    /*
           深度2
            1
           /
          2
     */
    public int minDepth(TreeNode node) {
        if (node == null) return 0;
        int dl = minDepth(node.left);
        int dr = minDepth(node.right);

        // 当右子树为null,返回左子树深度+1
        if (dr == 0) return dl + 1;
        // 当左子树为null,返回右子树深度+1
        if (dl == 0) return dr + 1;

        return Integer.min(dl, dr) + 1;
    }
}
