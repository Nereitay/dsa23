package es.kiwi.datastructure.queue;

import java.util.Iterator;

/**
 * 仅用 head, tail 判断空满, head, tail 即为索引值, tail 停下来的位置不存储元素
 *
 * @param <E> 队列中元素类型
 */
public class ArrayQueue1<E> implements Queue<E>, Iterable<E> {

    private final E[] array;
    private int head = 0;
    private int tail = 0;

    @SuppressWarnings("all")
    public ArrayQueue1(int capacity) {
        array = (E[]) new Object[capacity + 1];
    }

    /**
     * 向队列尾插入值. 有的习惯命名为 enqueue
     *
     * @param value 待插入值
     * @return 插入成功返回 true, 插入失败返回 false
     */
    @Override
    public boolean offer(E value) {
        if (isFull()) return false;
        
        array[tail] = value;
        tail = (tail + 1) % array.length;
        return true;
    }

    /**
     * 从对列头获取值, 并移除. 有的习惯命名为 dequeue
     *
     * @return 如果队列非空返回对头值, 否则返回 null
     */
    @Override
    public E poll() {
        if (isEmpty()) return null;

        E value = array[head];
        head = (head + 1) % array.length;
        return value;
    }

    /**
     * 从对列头获取值, 不移除
     *
     * @return 如果队列非空返回对头值, 否则返回 null
     */
    @Override
    public E peek() {
        if (isEmpty()) return null;

        return array[head];
    }

    /**
     * 检查队列是否为空
     *
     * @return 空返回 true, 否则返回 false
     */
    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    /**
     * 检查队列是否已满
     *
     * @return 满返回 true, 否则返回 false
     */
    @Override
    public boolean isFull() {
        return (tail + 1) % array.length == head;
    }


    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            int p = head;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                E value = array[p];
                p = (p + 1) % array.length;
                return value;
            }
        };
    }
}
