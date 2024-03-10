package es.kiwi.datastructure.binarytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class E05Leetcode104_3Test {
    private final E05Leetcode104_3 e05 = new E05Leetcode104_3();

    @Test
    public void test1() {
        TreeNode root = new TreeNode(1,new TreeNode(2),  new TreeNode(3));
        assertEquals(2, e05.maxDepth(root));
    }

    @Test
    public void test2() {
        TreeNode root = new TreeNode(1,new TreeNode(2),  new TreeNode(3,null,  new TreeNode(4)));
        assertEquals(3, e05.maxDepth(root));
    }

    @Test
    public void test3() {
        TreeNode root = new TreeNode(1);
        assertEquals(1, e05.maxDepth(root));
    }

}