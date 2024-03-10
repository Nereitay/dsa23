package es.kiwi.datastructure.stack;

import java.util.Iterator;

public class ArrayStack<E> implements Stack<E>, Iterable<E> {

    private final E[] array;
    private int top; // 栈顶指针

    @SuppressWarnings("all")
    public ArrayStack(int capacity) {
        array = (E[]) new Object[capacity];
    }

    /**
     * 向栈顶压入元素
     *
     * @param value 待压入值
     * @return 压入成功返回 true, 否则返回 false
     */
    @Override
    public boolean push(E value) {
        if (isFull()) return false;
        /*array[top] = value;
        top++;*/

        array[top++] = value;
        return true;
    }

    /**
     * 从栈顶弹出元素
     *
     * @return 栈非空返回栈顶元素, 栈为空返回 null
     */
    @Override
    public E pop() {
        if (isEmpty()) return null;

        return array[--top];
    }

    /**
     * 返回栈顶元素, 不弹出
     *
     * @return 栈非空返回栈顶元素, 栈为空返回 null
     */
    @Override
    public E peek() {
        if (isEmpty()) return null;

        return array[top - 1];
    }

    /**
     * 判断栈是否为空
     *
     * @return 空返回 true, 否则返回 false
     */
    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    /**
     * 判断栈是否已满
     *
     * @return 满返回 true, 否则返回 false
     */
    @Override
    public boolean isFull() {
        return top == array.length;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = top;
            @Override
            public boolean hasNext() {
                return p > 0;
            }

            @Override
            public E next() {
                return array[--p];
            }
        };
    }
}
