package es.kiwi.algorithm.recursion_single;

public class E02ReversePrintString {

    public static void f(int n, String str) {
        if (n == str.length()) {
            return;
        }
        f(n + 1, str);
        System.out.print(str.charAt(n));
    }

    public static void print(String str) {
        if (str.length() == 0) return;
        System.out.print(str.charAt(str.length() - 1));
        print(str.substring(0, str.length() - 1));
    }

    public static void main(String[] args) {
        String str = "abcd";
        f(0, str);

//        print(str);
    }
}
