package es.kiwi.datastructure.binarysearchtree;

import es.kiwi.datastructure.binarytree.TreeNode;

/**
 * <h3>根据前序遍历构造二叉搜索树</h3>
 * 题目说明
 * <ol>
 *     <li>preorder 长度 >=1</li>
 *     <li>preorder 没有重复值</li>
 * </ol>
 */
public class E06Leetcode1008 {
    // 3. 分治法
    /*
        8, 5, 1, 7, 10, 12
        root 8
        left 5, 1, 7 -> root 5  left 1 right 7
        right 10, 12 -> root 10 left n right 12

     */
    public TreeNode bstFromPreorder(int[] preorder){
        return partition(preorder, 0, preorder.length - 1);
    }
    private TreeNode partition(int[] preorder, int start, int end) {
         if (start > end) return null;

        TreeNode root = new TreeNode(preorder[start]);
        int index = start + 1;
        while (index <= end) {
            if (preorder[index] > preorder[start]) {
                break;
            }
            index++;
        }
        // index 就是右子树的起点
        root.left = partition(preorder, start + 1, index - 1);
        root.right = partition(preorder, index, end);
        return root;

    }

    //2. 上限法 O(n)
    /*
        1. 遍历数组中每一个值，根据值创建节点
            - 每个节点若成功创建都有：左孩子上限，右孩子上限
        2. 处理下一个值时，如果超过此上限，那么
            - 将null作为上个节点的孩子
            - 不能创建节点对象
            - 直到不超过上限为止
        3. 重复 1， 2 两步
     */
    public TreeNode bstFromPreorder2(int[] preorder) {
        return insert2(preorder, Integer.MAX_VALUE);
    }

    /*
        依次处理preorder中每个值，返回创建好的节点或null,
            1. 如果超过上限，返回null作为孩子返回
            2. 如果没超过上限，创建节点，并设置其左右孩子，
               左右孩子完整后返回
     */
    int i = 0;

    private TreeNode insert2(int[] preorder, int max) {
        if (i == preorder.length) return null;

        int val = preorder[i];
        if (val > max) {
            return null;
        }
        TreeNode node = new TreeNode(val);
        i++;
        node.left = insert2(preorder, val);
        node.right = insert2(preorder, max);
        return node;
    }

    // 1.  递归 直接插入 n * log(n)
    public TreeNode bstFromPreorder1(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            int val = preorder[i];
            insert1(root, val);
        }
        return root;
    }

    private TreeNode insert1(TreeNode node, int val) {
        if (node == null) return new TreeNode(val);

        if (val < node.val) {
            node.left = insert1(node.left, val);
        } else if (node.val < val) {
            node.right = insert1(node.right, val);
        }
        return node;
    }

    public static void main(String[] args) {
        /*
                8
               / \
              5   10
             / \   \
            1   7  12
         */
        TreeNode t1 = new E06Leetcode1008().bstFromPreorder(new int[]{8, 5, 1, 7, 10, 12});
//        TreeNode t1 = new E06Leetcode1008().bstFromPreorder(new int[]{8, 5, 7});
        TreeNode t2 = new TreeNode(8, new TreeNode(5, new TreeNode(1), new TreeNode(7)), new TreeNode(10, null, new TreeNode(12)));
        System.out.println(isSameTree(t1, t2));
    }

    public static boolean isSameTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        if (t1.val != t2.val) {
            return false;
        }
        return isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right);
    }
}
