package es.kiwi.datastructure.priorityqueue;

import es.kiwi.datastructure.linkedlist.ListNode;

public class E01Leetcode23 {
    public ListNode mergeKLists(ListNode[] lists) {
        MinHeap heap = new MinHeap(lists.length);

        // 1. 把链表头节点加入小顶堆
        for (ListNode h : lists) {
            if (h != null) {
                heap.offer(h);
            }
        }

        // 2. 不断从堆顶移除最小元素，加入新链表尾部
        ListNode s = new ListNode(-1, null);
        ListNode t = s;
        while (!heap.isEmpty()) {
            ListNode min = heap.poll();
            t.next = min;
            t = min;

            if (min.next != null) heap.offer(min.next);
        }

        return s.next;
    }

    public static void main(String[] args) {
        ListNode[] lists = {
                ListNode.of(1, 4, 5),
                ListNode.of(1, 3, 4),
                ListNode.of(2, 6),
                null,
        };
        ListNode m = new E01Leetcode23().mergeKLists(lists);
        System.out.println(m);
    }
}
