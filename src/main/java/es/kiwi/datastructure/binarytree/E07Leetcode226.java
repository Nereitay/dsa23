package es.kiwi.datastructure.binarytree;
/**
 * 翻转二叉树
 */
public class E07Leetcode226 {
    public TreeNode invertTree(TreeNode root) {
        invert(root);
        return root;
    }

    private void invert(TreeNode node) {
        if (node == null) return;

        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;

        invert(node.left);
        invert(node.right);
    }


}
