package es.kiwi.datastructure.linkedlist;

public class E07Leetcode23 {

    // 方法1： 递归
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;

        return split(lists, 0, lists.length - 1);
    }

    /**
     * 返回合并后的链表
     * @param lists
     * @param i 左边界
     * @param j 有边界
     * @return
     */
    private ListNode split(ListNode[] lists, int i, int j) {
        // 数组内只有一个链表
        if (i == j) return lists[i];

        int m = i + j >>> 1;
        ListNode left = split(lists, i, m);
        ListNode right = split(lists, m + 1, j);
        return mergeTwoLists(left, right);
    }

    // leetcode 21 合并两个有序链表 选择非递归实现
    private ListNode mergeTwoLists(ListNode p1, ListNode p2) {
        ListNode s = new ListNode(-1, null);
        ListNode p = s;

        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }

        if (p1 == null) {
            p.next = p2;
        }

        if (p2 == null) {
            p.next = p1;
        }

        return s.next;

    }

    public static void main(String[] args) {
        ListNode[] lists = {
                ListNode.of(1, 4, 5),
                ListNode.of(1, 3, 4),
                ListNode.of(2, 6),
        };
        ListNode m = new E07Leetcode23().mergeKLists(lists);
        System.out.println(m);
    }
}
