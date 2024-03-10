package es.kiwi.datastructure.queue;

import es.kiwi.datastructure.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class E01Leetcode102 {

    /*
        [
            [1]
            [2,3]
            [4,5,6,7]
        ]
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        LinkedListQueue<TreeNode> queue = new LinkedListQueue<>();
        queue.offer(root);

        int c1 = 1;
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>(c1);
            int c2 = 0;
            for (int i = 0; i < c1; i++) {
                TreeNode n = queue.poll();
               level.add(n.val);
                if (n.left != null) {
                    queue.offer(n.left);
                    c2++;
                }

                if (n.right != null) {
                    queue.offer(n.right);
                    c2++;
                }
            }
            result.add(level);
            c1 = c2;
        }

        return result;
    }

    /*
                 1
                / \
               2   3
              /\   /\
             4  5 6  7

             头 [] 尾

             1 2 3 4 5 6 7
    */
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
        List<List<Integer>> lists = new E01Leetcode102().levelOrder(root);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }

        /*LinkedListQueue<TreeNode> queue = new LinkedListQueue<>();
        queue.offer(root);

        int c1 = 1;
        while (!queue.isEmpty()) {
            int c2 = 0;
            for (int i = 0; i < c1; i++) {
                TreeNode n = queue.poll();
                System.out.print(n + " ");
                if (n.left != null) {
                    queue.offer(n.left);
                    c2++;
                }

                if (n.right != null) {
                    queue.offer(n.right);
                    c2++;
                }
            }
            System.out.println();
            c1 = c2;
        }*/
    }
}
