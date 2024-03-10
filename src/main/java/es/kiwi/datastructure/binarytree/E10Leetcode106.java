package es.kiwi.datastructure.binarytree;

import java.util.Arrays;

/**
 * 根据中序与后序遍历结果构造二叉树
 */
public class E10Leetcode106 {

    /*
        inOrder = {4,2,1,6,3,7}
        postOrder = {4,2,6,7,3,1}

        根 1
           in        post
        左 4,2       4,2
        右 6,3,7     6,7,3
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0) return null;

        int rootValue = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(rootValue);

        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootValue) {
                int[] inLeft = Arrays.copyOfRange(inorder, 0, i);
                int[] inRight = Arrays.copyOfRange(inorder, i + 1, inorder.length);

                int[] postLeft = Arrays.copyOfRange(postorder, 0, i);
                int[] postRight = Arrays.copyOfRange(postorder, i, postorder.length - 1);

                root.left = buildTree(inLeft, postLeft);
                root.right = buildTree(inRight, postRight);
                break;
            }
        }
        return root;
    }
}
