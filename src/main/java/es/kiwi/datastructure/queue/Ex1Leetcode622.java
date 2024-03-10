package es.kiwi.datastructure.queue;

/**
 * 实现队列,基于链表
 */
public class Ex1Leetcode622 {

    private static class Node {
        int value;
        Node next;

        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private final Node head = new Node(-1, null);
    private Node tail = head;
    private int size = 0;
    private int capacity = 0;

    {
        tail.next = head;
    }

    public Ex1Leetcode622(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Inserts an element into the circular queue.
     * @param value an element
     * @return true if the operation is successful
     */
    public boolean enQueue(int value) {
        if (isFull()) return false;

        Node last = new Node(value, head);
        tail.next = last;
        tail = last;

        size++;
        return true;
    }

    /**
     * Deletes an element from the circular queue.
     * @return true if the operation is successful.
     */
    public boolean deQueue() {
        if (isEmpty()) return false;

        Node first = head.next;
        head.next = first.next;

        if (first == tail) tail = head;

        size--;
        return true;
    }

    /**
     * Gets the front item from the queue. If the queue is empty, return -1
     * @return
     */
    public int Front() {
        if (isEmpty()) return -1;

        return head.next.value;
    }

    /**
     * Gets the last item from the queue. If the queue is empty, return -1
     * @return
     */
    public int Rear() {
        if (isEmpty()) return -1;

        return tail.value;
    }

    /**
     * Checks whether the circular queue is empty or not
     * @return
     */
    public boolean isEmpty() {
        return head == tail;
    }

    /**
     * Checks whether the circular queue is full or not
     * @return
     */
    public boolean isFull() {
        return size == capacity;
    }
}
