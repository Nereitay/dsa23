package es.kiwi.algorithm.recursion_single;

public class E06Sum {

    public static long sum(long n) {
        if (n == 1) return 1;
        return n + sum(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(sum(100));
//        System.out.println(sum(15000)); // java.lang.StackOverflowError
    }
}
