package es.kiwi.datastructure.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static es.kiwi.datastructure.binarytree.E01Leetcode144.colorPrintln;

/**
 * 二叉树后序遍历(左,右,值)
 */
public class E03Leetcode145 {
    public List<Integer> postorderTraversal(TreeNode root) {
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
                    result.add(peek.val);
                    pop = stack.pop();
                } else { // 待处理右子树
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
        System.out.println(new E03Leetcode145().postorderTraversal(root));
        LinkedList<TreeNode> stack = new LinkedList<>();

        TreeNode curr = root; // 代表当前节点
        TreeNode pop = null; // 最近一次弹栈的元素
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                // 待处理左子树
                colorPrintln("前:" + curr.val, 31);
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                // 没有右子树
                if (peek.right == null) {
                    colorPrintln("中:" + peek.val, 35);
                    pop = stack.pop();
                    colorPrintln("后:" + pop.val, 34);
                }
                // 右子树处理完成
                else if (peek.right == pop) {
                    pop = stack.pop();
                    colorPrintln("后:" + pop.val, 34);
                }
                // 待处理右子树
                else {
                    colorPrintln("中:" + peek.val, 35);
                    curr = peek.right;
                }
            }
        }
    }
}
