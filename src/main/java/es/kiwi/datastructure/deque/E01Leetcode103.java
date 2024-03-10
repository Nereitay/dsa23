package es.kiwi.datastructure.deque;

import es.kiwi.datastructure.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树 Z 字层序遍历
 */
public class E01Leetcode103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int el = 1;
        boolean odd = true;
        while (!queue.isEmpty()) {
            LinkedList<Integer> level = new LinkedList<>();
            int il = 0;
            for (int i = 0; i < el; i++) {
                TreeNode tn = queue.poll();

                if (odd) level.offerLast(tn.val);
                else level.offerFirst(tn.val);

                if (tn.left != null) {
                    queue.offer(tn.left);
                    il++;
                }
                if (tn.right != null) {
                    queue.offer(tn.right);
                    il++;
                }
            }
            odd = !odd;
            result.add(level);
            el = il;
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                1,
                new TreeNode(
                        2,
                        new TreeNode(4),
                        new TreeNode(5)
                ),
                new TreeNode(
                        3,
                        new TreeNode(6),
                        new TreeNode(7)
                )
        );
        List<List<Integer>> lists = new E01Leetcode103().zigzagLevelOrder(root);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
}
