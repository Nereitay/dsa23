package es.kiwi.datastructure.queue;

import java.util.Iterator;

/**
 * 基于单向环形链表实现
 *
 * @param <E> 队列中元素类型
 */
public class LinkedListQueue<E> implements Queue<E>, Iterable<E> {// 带泛型好处：可以是null值，可以是自定义类型

    private static class Node<E>{
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node<E> head = new Node<>(null, null);
    private Node<E> tail = head;

    private int size;
    private int capacity = Integer.MAX_VALUE;

    {
        tail.next = head;
    }

    public LinkedListQueue() {
//        tail.next = head;
    }

    public LinkedListQueue(int capacity) {
        this.capacity = capacity;
//        tail.next = head;
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

        Node<E> added = new Node<>(value, head);
        tail.next = added;
        tail = added;
        size++;
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
        Node<E> first = head.next;
        head.next = first.next;
        // 特殊情况，只剩一个节点时，删除后要调整tail的指向
        if (first == tail) {
            tail = head;
        }
        size--;
        return first.value;
    }

    /**
     * 从对列头获取值, 不移除
     *
     * @return 如果队列非空返回对头值, 否则返回 null
     */
    @Override
    public E peek() {
        if (isEmpty()) return null;
        return head.next.value;
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
        return size == capacity;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;

            @Override
            public boolean hasNext() {
                return p != head;
            }

            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };
    }
}
