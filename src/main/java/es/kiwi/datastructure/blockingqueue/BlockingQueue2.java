package es.kiwi.datastructure.blockingqueue;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 双锁实现, 比单锁性能更高
 *
 * @param <E> 元素类型
 */
@SuppressWarnings("all")
public class BlockingQueue2<E> implements BlockingQueue<E> {

    private final E[] array;
    private int head;
    private int tail;
    //    private int size;
    private AtomicInteger size = new AtomicInteger();

    public BlockingQueue2(int capacity) {
        array = (E[]) new Object[capacity];
    }

    private ReentrantLock tailLock = new ReentrantLock();
    private ReentrantLock headLock = new ReentrantLock();
    private Condition headWaits = headLock.newCondition();
    private Condition tailWaits = tailLock.newCondition();

    private boolean isEmpty() {
        return size.get() == 0;
    }

    private boolean isFull() {
        return size.get() == array.length;
    }


    @Override
    public void offer(E e) throws InterruptedException {
        int c = 0;
        tailLock.lockInterruptibly();
        try {
            while (isFull()) {
                tailWaits.await();
            }
            array[tail] = e;
            if (++tail == array.length) tail = 0;
 /*
                size = 6
             */
//            size++; //线程不安全
            c = size.getAndIncrement();
            /*
                1. 读取成员变量size的值  5
                2. 自增 6
                3. 结果写回成员变量size 6
             */
            if (c + 1 < array.length) {// 还有剩余空位
                tailWaits.signal();
            }

        } finally {
            tailLock.unlock();
        }

        // 两个加锁要写成平级格式，不能相互嵌套，否则会产生 死锁现象  jps jstack
        if (c == 0) {
            headLock.lock();
            try {
                headWaits.signal(); //单独使用会报错，要配合配对的锁一起使用
            } finally {
                headLock.unlock();
            }
        }

    }


    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {
        return false;
    }

    @Override
    public E poll() throws InterruptedException {
        E e = null;
        int c = 0;
        headLock.lockInterruptibly();
        try {
            while (isEmpty()) {
                headWaits.await();
            }
            e = array[head];
            array[head] = null;
            if (++head == array.length) head = 0;

            /*
                1. 读取成员变量size的值 5
                2. 自减 4
                3. 结果写回成员变量size 4
             */
//            size--;
            c = size.getAndDecrement();
            // 3->2   2->1   1->0
            // poll_1 poll_2 poll_3
            if (c > 1) {
                headWaits.signal();
            }

        } finally {
            headLock.unlock();
        }

        // 4. 队列从满->不满时 由poll唤醒等待不满的 offer 线程
        if (c == array.length) {
            tailLock.lock();
            try {
                tailWaits.signal(); //单独使用会报错，要配合配对的锁一起使用
            } finally {
                tailLock.unlock();
            }
        }

        return e;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue2<String> queue = new BlockingQueue2<>(3);
        queue.offer("元素1");
        queue.offer("元素2");

        new Thread(() -> {
            try {
                queue.offer("元素3");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "offer").start();

        new Thread(() -> {
            try {
                queue.poll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "poll").start();
    }
}
