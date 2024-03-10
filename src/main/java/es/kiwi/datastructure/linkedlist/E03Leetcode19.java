package es.kiwi.datastructure.linkedlist;

public class E03Leetcode19 {
    // 方法1 递归
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode s = new ListNode(-1, head);
        recursion(s, n);

        return s.next;
    }

    private int recursion(ListNode p, int n) {
        if (p == null) return 0;
        int nth = recursion(p.next, n); // 下一个节点倒数位置
//        System.out.println(p.val + " " + nth);
        if (nth == n) {
            // p = 3 p.next = 4 p.next.next = 5
            p.next = p.next.next;
        }
        return nth + 1;
    }

    /**
     * 方法2 快慢指针
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode s = new ListNode(-1, head);
        ListNode p1 = s;
        ListNode p2 = s;

        for (int i = 0; i < n + 1; i++) {
            p2 = p2.next;
        }

        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        p1.next = p1.next.next;

        return s.next;
    }

    public static void main(String[] args) {

        ListNode head = ListNode.of(1, 2, 3, 4, 5);
//        ListNode head = ListNode.of(1,2);
//        new E03Leetcode19().recursion(head, 2);
        System.out.println(head);
        System.out.println(new E03Leetcode19()
                .removeNthFromEnd2(head, 5));
    }
}
