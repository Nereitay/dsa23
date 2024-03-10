package es.kiwi.algorithm.recursion_multi;

import java.util.LinkedList;

public class E02HanoiTower {


    static LinkedList<Integer> a = new LinkedList<>();
    static LinkedList<Integer> b = new LinkedList<>();
    static LinkedList<Integer> c = new LinkedList<>();

    // Bottom - 3 2 1 - Top
    static void init (int n) {
        for (int i = n; i > 0; i--) {
            a.addLast(i);
        }
    }

    /**
     * 移动圆盘
     * @param n 圆盘个数
     * @param a resource
     * @param b assistant
     * @param c target
     */
    static void move(int n, LinkedList<Integer> a, LinkedList<Integer> b, LinkedList<Integer> c) {
        if (n == 0) return;

        move(n - 1, a, c, b);
        c.addLast(a.removeLast()); // 中间步骤
        print();
        move(n - 1, b, a, c);

        // 时间复杂度：T(n) = 2T(n - 1) + c, T(1) = c => O(2^n)
    }

    public static void main(String[] args) {
        /*init(3);
        print();
        b.addLast(a.removeLast());
        print();*/

        init(3);
        print();
        move(3, a, b, c);
    }

    private static void print() {
        System.out.println("-----------");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
