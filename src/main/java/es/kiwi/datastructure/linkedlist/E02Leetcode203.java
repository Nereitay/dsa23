package es.kiwi.datastructure.linkedlist;

public class E02Leetcode203 {
    // 方法1
    public ListNode removeElements1(ListNode head, int val) {
        ListNode s = new ListNode(-1, head);
        ListNode p1 = s;
        ListNode p2;
        while((p2 = p1.next) != null) {
            if (p2.val == val) {
                p1.next = p2.next;
            } else {
                p1 = p1.next;
            }
        }

        return s.next;
    }

    // 方法2 递归
    public ListNode removeElements2(ListNode p, int val) {
        if (p == null) return null;
        if (p.val == val) {
            return removeElements2(p.next, val);
        } else {
            p.next = removeElements2(p.next, val);
            return p;
        }

    }

    public static void main(String[] args) {
        ListNode head = ListNode.of(1, 2, 6, 3, 6);
//        ListNode head = ListNode.of(7, 7, 7, 7);
        System.out.println(head);
        System.out.println(new E02Leetcode203()
                .removeElements1(head, 6));
    }

}
