package es.kiwi.datastructure.queue;
/**
 * 实现队列,基于数组(未考虑正整数越界)
 */
public class Ex3Leetcode622 {

    public static void main(String[] args) {
        Ex3Leetcode622 queue = new Ex3Leetcode622(3);
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        System.out.println(queue.Rear());
    }

    private int head = 0;
    private int tail = 0;

    private final int capacity;

    private final int[] array;

    /**
     * Initializes the object with the size of the queue to be k
     *
     * @param capacity capacity
     */
    public Ex3Leetcode622(int capacity) {
        this.capacity = capacity;
        array = new int[this.capacity];
    }

    /**
     * Inserts an element into the circular queue.
     *
     * @param value an element
     * @return true if the operation is successful
     */
    public boolean enQueue(int value) {
        if (isFull()) return false;

        array[tail++ % capacity] = value;
        return true;
    }

    /**
     * Deletes an element from the circular queue.
     *
     * @return true if the operation is successful.
     */
    public boolean deQueue() {
        if (isEmpty()) return false;

        head++;
        return true;
    }

    /**
     * Gets the front item from the queue. If the queue is empty, return -1
     *
     * @return
     */
    public int Front() {
        if (isEmpty()) return -1;
        return array[head % capacity];
    }

    /**
     * Gets the last item from the queue. If the queue is empty, return -1
     *
     * @return
     */
    public int Rear() {
        if (isEmpty()) return -1;

        return array[(tail - 1) % capacity];
    }

    /**
     * Checks whether the circular queue is empty or not
     *
     * @return
     */
    public boolean isEmpty() {
        return head == tail;
    }

    /**
     * Checks whether the circular queue is full or not
     *
     * @return
     */
    public boolean isFull() {
        return tail - head == capacity;
    }

}
