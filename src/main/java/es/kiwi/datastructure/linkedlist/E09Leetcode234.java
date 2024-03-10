package es.kiwi.datastructure.linkedlist;

public class E09Leetcode234 {

    // 优化
    public boolean isPalindrome(ListNode head) {

        ListNode p1 = head; // 快
        ListNode p2 = head; // 慢
        ListNode o1 = head; // 旧
        ListNode n1 = null; // 新

        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;

            // 反转链表
            o1.next = n1;
            n1 = o1;
            o1 = p1;
        }

        // 奇数列处理
        if (p2 != null) p1 = p1.next;

        while (n1 != null) {
            if (n1.val != p1.val) return false;
            p1 = p1.next;
            n1 = n1.next;
        }

        return true;

    }

    public boolean isPalindrome1(ListNode head) {

        ListNode middle = middle(head);

        ListNode newHead = reverse(middle);

        while (newHead != null) {
            if (newHead.val != head.val) return false;
            newHead = newHead.next;
            head = head.next;
        }

        return true;

    }

    private ListNode reverse(ListNode o1) {
        ListNode n1 = null;
        while (o1 != null) {
            ListNode o2 = o1.next;

            o1.next = n1;
            n1 = o1;

            o1 = o2;
        }

        return n1;
    }

    private ListNode middle(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;

        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }

        return p1;
    }

    public static void main(String[] args) {
        System.out.println(new E09Leetcode234()
                .isPalindrome(ListNode.of(1, 2, 2, 1)));
        System.out.println(new E09Leetcode234()
                .isPalindrome(ListNode.of(1, 2, 3, 2, 1)));
    }
}
