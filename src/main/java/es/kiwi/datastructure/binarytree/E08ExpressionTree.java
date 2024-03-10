package es.kiwi.datastructure.binarytree;

import java.util.LinkedList;

/**
 * 根据后缀表达式构造表达式树
 */
public class E08ExpressionTree {


    /*
        中缀表达式           (2-1)*3
        后缀（逆波兰）表达式   21-3*

        1.遇到数字入栈
        2.遇到运算符, 出栈两次, 与当前节点建立父子关系, 当前节点入栈

        21-3*
        步骤：
        栈
        |   |       |   |       |   |       |   |       |   |
        |   |       | 1 |       | 3 |       |   |       |   |
        |   |       | 2 |       | - |       |   |       |   |
                      ⇓           ⇓
               '-'.right = 1   '*'.right = 3
               '-'.left = 1    '*'.left = '-'
        _____

        表达式树
            *
           / \
          -   3
         / \
        2   1


     */
    public TreeNode constructExpressionTree(String[] tokens) {// 前提：合法的双元运算
        LinkedList<TreeNode> stack = new LinkedList<>();

        for (String t : tokens) {
            switch (t) {// 运算符
                case "+", "-", "*", "/" -> {
                    TreeNode right = stack.pop();
                    TreeNode left = stack.pop();
                    TreeNode parent = new TreeNode(left, t, right);
                    stack.push(parent);
                }
                // 数字 直接入栈
                default -> stack.push(new TreeNode(t));
            }
        }
        return stack.peek();
    }

    static class TreeNode {
        public String val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(String val) {
            this.val = val;
        }

        public TreeNode(TreeNode left, String val, TreeNode right) {
            this.left = left;
            this.val = val;
            this.right = right;
        }

        @Override
        public String toString() {
            return this.val;
        }
    }
}
