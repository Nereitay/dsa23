package es.kiwi.datastructure.binarytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class E06Leetcode111_1Test {
    @Test
    public void test1() {
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        assertEquals(2, new E06Leetcode111_1().minDepth(root));
    }

    @Test
    public void test2() {
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3, null, new TreeNode(4)));
        assertEquals(2, new E06Leetcode111_1().minDepth(root));
    }

    @Test
    public void test3() {
        TreeNode root = new TreeNode(1);
        assertEquals(1, new E06Leetcode111_1().minDepth(root));
    }

    @Test
    public void test4() {
        TreeNode root = new TreeNode(1, new TreeNode(2), null);
        assertEquals(2, new E06Leetcode111_1().minDepth(root));
    }

}