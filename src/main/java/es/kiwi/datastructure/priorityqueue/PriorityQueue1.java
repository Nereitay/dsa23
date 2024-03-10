package es.kiwi.datastructure.priorityqueue;

import es.kiwi.datastructure.queue.Queue;

/**
 * 基于<b>无序数组</b>实现
 *
 * @param <E> 队列中元素类型, 必须实现 Priority 接口
 */
public class PriorityQueue1<E extends Priority> implements Queue<E> {

    Priority[] array;
    private int size;

    public PriorityQueue1(int capacity) {
        array = new Priority[capacity];
    }

    /**
     * 向队列尾插入值. 有的习惯命名为 enqueue
     * O(1)
     * @param value 待插入值
     * @return 插入成功返回 true, 插入失败返回 false
     */
    @Override
    public boolean offer(E value) {
        if (isFull()) return false;

        array[size++] = value;
        return true;
    }

    /**
     * 从对列头获取值, 并移除. 有的习惯命名为 dequeue
     * O(n)
     * @return 如果队列非空返回对头值, 否则返回 null
     */
    @Override
    public E poll() {
        if (isEmpty()) return null;

        int max = selectMax();
        E e = (E) array[max];
        remove(max);

        return e;
    }

    private void remove(int index) {
        if (index < size - 1) {
            System.arraycopy(array, index + 1, array, index, size - 1 - index);
        }
        array[--size] = null; // help GC
    }

    /**
     * 从对列头获取值, 不移除
     *
     * @return 如果队列非空返回对头值, 否则返回 null
     */
    @Override
    public E peek() {
        return null;
    }

    /**
     * @return 优先级最高的索引值
     */
    private int selectMax() {
        int max = 0;
        for (int i = 0; i < size; i++) {
            if (array[i].priority() > array[max].priority()) {
                max = i;
            }
        }
        return max;
    }

    /**
     * 检查队列是否为空
     *
     * @return 空返回 true, 否则返回 false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 检查队列是否已满
     *
     * @return 满返回 true, 否则返回 false
     */
    @Override
    public boolean isFull() {
        return size == array.length;
    }
}
