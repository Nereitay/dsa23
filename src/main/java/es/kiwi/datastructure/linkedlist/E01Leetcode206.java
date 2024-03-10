package es.kiwi.datastructure.linkedlist;


public class E01Leetcode206 {
    /*
    方法1 构造一个新链表，从 旧链表 依次拿到每个节点，创建新节点添加至 新链表 头部，
    完成后新链表即是倒序的
     */
    public ListNode reverseList1(ListNode head) {
        ListNode newHead = null;
        ListNode p = head;
        while (p != null) {
            newHead = new ListNode(p.val, newHead);
            p = p.next;
        }

        return newHead;
    }

    /*
     方法2 与方法1 类似，构造一个新链表，从 旧链表头部 移除节点，添加到 新链表头部，完成后新链表即是倒序的，
     区别在于原题目未提供节点外层的容器类，这里提供一个，另外一个区别是并不去构造新节点
     */
    public ListNode reverseList2(ListNode head) {

        List list1 = new List(head);
        List list2 = new List(null);

        while (true) {
            ListNode first = list1.removeFirst();
            if (first == null) break;
            list2.addFirst(first);
        }

        return list2.head;

    }

    static class List {
        ListNode head;

        public List(ListNode head) {
            this.head = head;
        }

        public void addFirst(ListNode first) {
            first.next = head;
            head = first;
        }

        public ListNode removeFirst() {
            ListNode first = head;
            if (first != null) {
                head = first.next;
            }
            return first;
        }
    }

    // 方法3 递归
    public ListNode reverseList3(ListNode p) {
        if (p == null || p.next == null) return p;
        ListNode last = reverseList3(p.next);
        p.next.next = p;
        p.next = null;
        return last;
    }

    /*
     方法4 从链表每次拿到第二个节点，将其从链表断开，插入头部，直至它为 null 结束
     */
    public ListNode reverseList4(ListNode o1) {
        // 1. 空链表  2. 一个元素
        if (o1 == null || o1.next == null) return o1;

        ListNode n1 = o1;
        ListNode o2 = o1.next;

        while (o2 != null) {
            // 2.断开o2
            o1.next = o2.next;
            // 3. o2移到n1头部
            o2.next = n1;
            // 4. n1指向o2
            n1 = o2;
            // 5. o2继续指向o1的下个节点
            o2 = o1.next;
        }

        return n1;
    }

    /*
    方法5（面向过程）， 类似方法2（面向对象）把链表分成两部分，思路就是不断从链表2的头，往链表1的头搬移
     */
    public ListNode reverseList5(ListNode o1) {
        if (o1 == null || o1.next == null) {
            return o1;
        }

        ListNode n1 = null;
        while (o1 != null) {
            ListNode o2 = o1.next;
            o1.next = n1;
            n1 = o1;
            o1 = o2;
        }

        return n1;
    }

    public static void main(String[] args) {
        ListNode o5 = new ListNode(5, null);
        ListNode o4 = new ListNode(4, o5);
        ListNode o3 = new ListNode(3, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);
        System.out.println(o1);
        ListNode n1 = new E01Leetcode206().reverseList3(o1);
        System.out.println(n1);
    }
}
