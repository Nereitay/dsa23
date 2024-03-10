package es.kiwi.datastructure.binarytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class E04Leetcode101Test {

    @Test
    public void test1() {
        TreeNode root = new TreeNode(
                1,
                new TreeNode(2,new TreeNode(3),  new TreeNode(4)),
                new TreeNode(2,new TreeNode(4),  new TreeNode(3))
        );
        assertTrue(new E04Leetcode101().isSymmetric(root));
    }

    @Test
    public void test2() {
        TreeNode root = new TreeNode(
                1,
                new TreeNode(2,null,  new TreeNode(3)),
                new TreeNode(2,null,  new TreeNode(3))
        );
        assertFalse(new E04Leetcode101().isSymmetric(root));
    }
}