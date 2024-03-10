package es.kiwi.datastructure.priorityqueue;

import es.kiwi.datastructure.queue.Queue;

/**
 * 基于<b>有序数组</b>实现
 *
 * @param <E> 队列中元素类型, 必须实现 Priority 接口
 */
@SuppressWarnings("all")
public class PriorityQueue2<E extends Priority> implements Queue<E> {

    Priority[] array;
    int size;

    public PriorityQueue2(int capacity) {
        array = new Priority[capacity];
    }

    /**
     * 向队列尾插入值. 有的习惯命名为 enqueue
     * O(n)
     * @param value 待插入值
     * @return 插入成功返回 true, 插入失败返回 false
     */
    @Override
    public boolean offer(E value) {
        if (isFull()) return false;
        insert(value);
        size++;
        return true;
    }

    private void insert(E e) { // 一轮插入排序
        int i = size -1;
        while (i >= 0 && array[i].priority() > e.priority()) {
            array[i + 1] = array[i];
            i--;
        }
        array[i + 1] = e;
    }

    /**
     * 从对列头获取值, 并移除. 有的习惯命名为 dequeue
     * O(1)
     * @return 如果队列非空返回对头值, 否则返回 null
     */
    @Override
    public E poll() {
        if (isEmpty()) return null;
        E e = (E) array[size - 1];

        array[--size] = null; // help GC
        return e;
    }

    /**
     * 从对列头获取值, 不移除
     *
     * @return 如果队列非空返回对头值, 否则返回 null
     */
    @Override
    public E peek() {
        if (isEmpty()) return null;
        return (E) array[size - 1];
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
