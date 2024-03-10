package es.kiwi.datastructure.binarytree;

import es.kiwi.datastructure.stack.LinkedListStack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static es.kiwi.datastructure.binarytree.E01Leetcode144.colorPrintln;

/**
 * 二叉树中序遍历(左,值,右)
 */
public class E02Leetcode94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root; // 代表当前节点
        TreeNode pop = null; // 最近一次弹栈的元素

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {// 待处理左子树
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();

                if (peek.right == null) { // 没有右子树
                    result.add(peek.val);
                    pop = stack.pop();
                } else if (peek.right == pop) {// 右子树处理完成
                    pop = stack.pop();
                } else { // 待处理右子树
                    result.add(peek.val);
                    curr = peek.right;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                1,
                new TreeNode(2,new TreeNode(4),  new TreeNode(7)),
                new TreeNode(3,new TreeNode(5),  new TreeNode(6))
        );
        System.out.println(new E02Leetcode94().inorderTraversal(root));

        LinkedListStack<TreeNode> stack = new LinkedListStack<>();

        TreeNode curr = root; // 代表当前节点
        while (curr != null || !stack.isEmpty()) {
            if(curr != null) {
//                colorPrintln("去: " + curr.val,31);
                stack.push(curr); // 压入栈，为了记住回来的路
                curr = curr.left;
            } else {
                TreeNode pop = stack.pop();
                colorPrintln("回: " + pop.val,35);
                curr = pop.right;
            }
        }
    }
}
