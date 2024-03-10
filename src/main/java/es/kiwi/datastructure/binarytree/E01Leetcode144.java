package es.kiwi.datastructure.binarytree;

import es.kiwi.datastructure.stack.LinkedListStack;
import es.kiwi.datastructure.stack.Stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树前序遍历(值,左,右)
 */
public class E01Leetcode144 {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root; // 代表当前节点
        TreeNode pop = null; // 最近一次弹栈的元素

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {// 待处理左子树
                stack.push(curr);
                result.add(curr.val);
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();

                if (peek.right == null) { // 没有右子树
                    pop = stack.pop();
                } else if (peek.right == pop) {// 右子树处理完成
                    pop = stack.pop();
                } else { // 待处理右子树
                    curr = peek.right;
                }
            }
        }

        return result;
    }


    public static void main(String[] args) {
        /*TreeNode root = new TreeNode(
                new TreeNode(2), 1, new TreeNode(3)
        );*/

        TreeNode root = new TreeNode(
                1,
                new TreeNode(2,new TreeNode(4),  new TreeNode(7)),
                new TreeNode(3,new TreeNode(5),  new TreeNode(6))
        );
        System.out.println(new E01Leetcode144().preorderTraversal(root));

        LinkedList<TreeNode> stack = new LinkedList<>();// 记住来时的路

        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
//                System.out.println("ir: " + curr.val);
                colorPrintln("ir: " + curr.val, 34);
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode pop = stack.pop();
//                System.out.println("volver: " + pop.val);
                colorPrintln("volver: " + pop.val, 36);
                curr = pop.right;
            }
        }
    }

    /*
        31 红
        32 绿
        33 黄
        34 橙
        35 紫
        36 蓝
     */
    public static void colorPrintln(String origin, int color) {
        System.out.printf("\033[%dm%s\033[0m%n", color, origin);
    }

}
