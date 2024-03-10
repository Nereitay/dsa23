package es.kiwi.datastructure.blockingqueue;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 单锁实现
 * offer 和 poll共享一把锁，会互相影响
 *
 * @param <E> 元素类型
 */
@SuppressWarnings("all")
public class BlockingQueue1<E> implements BlockingQueue<E> {

    private final E[] array;
    private int head;
    private int tail;
    private int size;

    public BlockingQueue1(int capacity) {
        array = (E[]) new Object[capacity];
    }

    private ReentrantLock lock = new ReentrantLock();
    private Condition headWaits = lock.newCondition();
    private Condition tailWaits = lock.newCondition();

    private boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == array.length;
    }


    @Override
    public void offer(E e) throws InterruptedException {

        lock.lockInterruptibly();
        try {
            while (isFull()) {
                tailWaits.await();
            }
            array[tail] = e;
            if (++tail == array.length) tail = 0;

            size++;
            headWaits.signal(); // 唤醒 poll方法中等待非空的线程
        } finally {
            lock.unlock();
        }

    }

    /**
     * 在有限时间内等待
     * @param e
     * @param timeout
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            long t = TimeUnit.MILLISECONDS.toNanos(timeout);//毫秒转纳秒
            while (isFull()) {
                t = tailWaits.awaitNanos(t);// 最多等待多少纳秒 返回值是等待的剩余时间
                if (t <= 0) return false;
            }
            array[tail] = e;
            if (++tail == array.length) tail = 0;

            size++;
            headWaits.signal();
            return true;
        } finally {
            lock.unlock();
        }

    }

    @Override
    public E poll() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (isEmpty()) {
                headWaits.await();
            }
            E e = array[head];
            array[head] = null;
            if (++head == array.length) head = 0;
            size--;
            tailWaits.signal();
            return e;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue1<String> queue = new BlockingQueue1<>(3);
        queue.offer("任务1");

        new Thread(()->{
            try {
                queue.offer("任务2");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "offer").start();

        new Thread(()->{
            try {
                System.out.println(queue.poll());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "poll").start();
    }
}
