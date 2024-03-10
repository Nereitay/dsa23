package es.kiwi.algorithm.sort;

import java.util.Arrays;

/**
 * <h3>冒泡排序 - 非递归实现</h3>
 * <p>每轮冒泡不断地比较相邻的两个元素，如果它们是逆序的，则交换它们的位置</p>
 * <p>下一轮冒泡，可以调整未排序的右边界，减少不必要比较</p>
 */
public class BubbleSort {

    private static void bubble(int[] a) {
        int j = a.length - 1;
        do {
            int x = 0;
            for (int i = 0; i < j; i++) {
                if (a[i] > a[i + 1]) {
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    x = i;
                }
            }
            j = x;
        } while (j != 0);
    }

    public static void main(String[] args) {
        int[] a = {6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(a));
        bubble(a);
        System.out.println(Arrays.toString(a));
    }
}
