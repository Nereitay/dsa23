package es.kiwi.algorithm.recursion_multi;

import java.util.Arrays;


//多路递归：每个递归函数包含多个自身调用
public class E01Fibonacci {

    public static int fibonacci(int n) {// Memoization优化
        int[] cache = new int[n + 1];
        Arrays.fill(cache, -1);
        cache[0] = 0;
        cache[1] = 1;

        return m(n, cache);
    }

    private static int m(int n, int[] cache) {
        if (cache[n] != -1) {
            return cache[n];
        }

        int x = m(n - 1, cache);
        int y = m(n - 2, cache);
        cache[n] = x + y; // 存入数组
        return cache[n];
    }


    public static int f(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int x = f(n - 1);
        int y = f(n - 2);

        return x + y;
    }

    public static void main(String[] args) {
        int f = f(8);
        System.out.println(f);
    }


}
