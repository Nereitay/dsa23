package es.kiwi.datastructure.binarytree;

/**
 * <h3>树节点类</h3>
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    /*
     二叉树结构：
                1
               / \
              2   3
             /\   /\
            4  5 6  7
     */
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.left = left;
        this.val = val;
        this.right = right;
    }

    @Override
    public String toString() {
        return String.valueOf(this.val);
    }
}