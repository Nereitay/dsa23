package es.kiwi.datastructure.blockingqueue;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
    1. synchronized 关键字, 功能少
    2. ReentrantLock 可重入锁, 功能多
 */
public class TestThreadReentranLock {

    private final String[] array = new String[10];
    private int tail = 0;

    private int size = 0;

    ReentrantLock lock = new ReentrantLock(); // 锁对象
    Condition tailWaits = lock.newCondition(); // 条件变量对象 （集合）

    public void offer(String e) throws InterruptedException {
//        lock.lock(); //加锁 会一直等待，不能打断
        lock.lockInterruptibly(); //加锁 (可以在阻塞状态随时打断)
        try{
            while (isFull()) { // if 替换成 while 避免虚假唤醒
                // 满了该做的事,offer 线程阻塞（暂停）
                tailWaits.await(); // 当前线程加入tailWaits集合，线程阻塞 唤醒 =》signal();
            }
            array[tail] = e;
            if (++tail == array.length) tail = 0;

            size++;
        } finally {
            lock.unlock(); //解锁
        }

    }

    private boolean isFull() {
        return size == array.length;
    }
    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public static void main(String[] args) throws InterruptedException {
        TestThreadReentranLock queue = new TestThreadReentranLock();
        for (int i = 0; i < 10; i++) {
            queue.offer("e" + i);
        }

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName() + "添加元素之前");
                queue.offer("e10");
                System.out.println(Thread.currentThread().getName() + "添加元素成功");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "t1").start();

        new Thread(()->{
            System.out.println("开始唤醒");
            try {
                queue.lock.lockInterruptibly();
                queue.tailWaits.signal();// 配合锁一起使用
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                queue.lock.unlock();
            }
        }, "t2").start();
    }
}
