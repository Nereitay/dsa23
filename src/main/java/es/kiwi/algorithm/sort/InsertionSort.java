package es.kiwi.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 将数组分为两部分 [0 .. low-1] [low .. a.length-1]
 *   左边[0 .. low-1]是已排序部分
 *   右边[low .. a.length-1]是未排序部分
 * 每次从未排序区域取出low位置的元素, 插入到已排序区域
 */
public class InsertionSort {

    public static void sort(int[] a) {
        for (int low = 1; low < a.length; low++) {
            int temp = a[low];
            int i = low - 1;
            // 自右向左找插入位置，如果比待插入元素大，则不断右移，空出插入位置
            while (i >= 0 && temp < a[i]) {
                a[i + 1] = a[i];
                i--;
            }
            // 找到插入位置
            if (i != low - 1) {
                a[i + 1] = temp;
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
