package es.kiwi.algorithm.sort;

import java.util.Arrays;

/**
 * 希尔排序
 * 简单的说，就是分组实现插入，每组元素间隙称为 gap
 * 每轮排序后 gap 逐渐变小，直至 gap 为 1 完成排序
 * 对插入排序的优化，让元素更快速地交换到最终位置
 */
public class ShellSort {

    public static void sort(int[] a) {
        for (int gap = a.length >> 1; gap >= 1; gap = gap >> 1) {
            for (int low = gap; low < a.length; low++) {
                int temp = a[low];
                int i = low - gap;
                while (i >= 0 && temp < a[i]) {
                    a[i + gap] = a[i];
                    i -= gap;
                }
                if (i != low - gap) {
                    a[i + gap] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {9, 3, 7, 2, 5, 8, 1, 4};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
