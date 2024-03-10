package es.kiwi.algorithm.recursion_single;

import java.util.Arrays;

public class E04BubbleSort {
    public static void sort(int[] a) {
//       bubble(a, a.length - 1);
       bubbleImproved(a, a.length - 1);
    }

    private static void bubble(int[] a, int j) {// j代表未排序区域的右边界
        if (j == 0) return;

        for (int i = 0; i < j; i++) {
            if (a[i] > a[i + 1]) {
                int temp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = temp;
            }
        }

        bubble(a, j - 1);
    }

    private static void bubbleImproved(int[] a, int j) {// j代表未排序区域的右边界
        if (j == 0) return;
        int x = 0;
        for (int i = 0; i < j; i++) {
            if (a[i] > a[i + 1]) {
                int temp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = temp;
                x = i;
            }
        }

        bubble(a, x);
    }


}
