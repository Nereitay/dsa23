package es.kiwi.datastructure.deque;

import java.util.Iterator;
/**
 * 基于双向环形链表实现
 *
 * @param <E> 队列中元素类型
 */
public class LinkedListDeque<E> implements Deque<E>, Iterable<E> {

    private static class Node<E> {
        Node<E> prev;
        E value;
        Node<E> next;

        public Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    private int capacity;
    private int size;
    private Node<E> sentinel = new Node<>(null, null, null);

    public LinkedListDeque(int capacity) {
        this.capacity = capacity;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    @Override
    public boolean offerFirst(E e) {
        if (isFull()) return false;

        Node<E> prev = sentinel;
        Node<E> next = sentinel.next;
        Node<E> first = new Node<>(prev, e, next);

        prev.next = first;
        next.prev = first;

        size++;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if (isFull()) return false;

        Node<E> prev = sentinel.prev;
        Node<E> next = sentinel;
        Node<E> first = new Node<>(prev, e, next);

        prev.next = first;
        next.prev = first;

        size++;
        return true;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) return null;

        Node<E> prev = sentinel;
        Node<E> first = sentinel.next;
        Node<E> next = first.next;

        prev.next = next;
        next.prev = prev;

        size--;
        return first.value;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) return null;

        Node<E> last = sentinel.prev;
        Node<E> prev = last.prev;
        Node<E> next = sentinel;

        prev.next = next;
        next.prev = prev;

        size--;
        return last.value;
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) return null;
        return sentinel.next.value;
    }

    @Override
    public E peekLast() {
        if (isEmpty()) return null;
        return sentinel.prev.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

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
            Node<E> node = sentinel.next;

            @Override
            public boolean hasNext() {
                return node != sentinel;
            }

            @Override
            public E next() {
                E value = node.value;
                node = node.next;
                return value;
            }
        };
    }
}
