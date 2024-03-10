package es.kiwi.datastructure.blockingqueue;

import java.util.Arrays;
/*
    1. synchronized 关键字, 功能少
    2. ReentrantLock 可重入锁, 功能多
 */
public class TestThreadUnsafe {

    private final String[] array = new String[10];
    private int tail = 0;

    public void offer(String e) {
        array[tail] = e;
        tail++;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public static void main(String[] args) {
        TestThreadUnsafe queue = new TestThreadUnsafe();
        /*
                     tail
            [e1, e2, null, null, null, null, null, null, null, null]
             0   1    2     3     4     5     6     7     8      9
         */
        /*queue.offer("e1");
        queue.offer("e2");*/

        /*
            t1                              t2
            array[tail] = e;
                                            array[tail] = e;
                                            tail++;
            tail++;
         */
        new Thread(() -> queue.offer("e1")).start();
        new Thread(() -> queue.offer("e2")).start();
    }
}
