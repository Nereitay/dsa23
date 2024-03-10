package es.kiwi.datastructure.array;

import java.util.Arrays;

/**
 * 合并有序数组 - 对应 Leetcode 88 [改动版]
 */
public class E01Merge2SortedArray {

    /*
        将数组内两个区间内的有序元素合并 [1, 5, 6, 2, 4, 10, 11]
        可以视作两个有序区间 [1, 5, 6] 和 [2, 4, 10, 11]
        合并后，结果仍存储于原有空间 [1, 2, 4, 5, 6, 10, 11]

     */
    /**
     * 方法 2
     *
     * @param o      原始数组
     * @param ai     第一个有序区间的起点
     * @param ae     第一个有序区间的终点
     * @param bi     第二个有序区间的起点
     * @param be     第二个有序区间的终点
     * @param d      结果数组
     *
     **/
    public static void merge(int[] o, int ai, int ae, int bi, int be,
                             int[] d) {
        int dIndex = ai;
        while (ai <= ae && bi <= be) {
            if (o[ai] < o[bi]) {
                d[dIndex] = o[ai];
                ai++;
            } else {
                d[dIndex] = o[bi];
                bi++;
            }
            dIndex++;
        }

        if (ai > ae) {
            System.arraycopy(o, bi, d, dIndex, be - bi + 1);
        }

        if (bi > be) {
            System.arraycopy(o, ai, d, dIndex, ae - ai + 1);
        }
    }

    /**
     * 方法 1 递归
     *
     * @param o      原始数组
     * @param ai     第一个有序区间的起点
     * @param ae     第一个有序区间的终点
     * @param bi     第二个有序区间的起点
     * @param be     第二个有序区间的终点
     * @param d      结果数组
     * @param dIndex 结果数组使用的索引
     */
    public static void merge(int[] o, int ai, int ae, int bi, int be,
                             int[] d, int dIndex) {
        if (ai > ae) {
            System.arraycopy(o, bi, d, dIndex, be - bi + 1);
            return;
        }

        if (bi > be) {
            System.arraycopy(o, ai, d, dIndex, ae - ai + 1);
            return;
        }

        if (o[ai] < o[bi]) {
            d[dIndex] = o[ai];
            merge(o, ai + 1, ae, bi, be, d, dIndex + 1);
        } else {
            d[dIndex] = o[bi];
            merge(o, ai, ae, bi + 1, be, d, dIndex + 1);
        }
    }

    public static void main(String[] args) {
        int[] a1 = {1, 5, 6, 2, 4, 10, 11};
        int[] a2 = new int[a1.length];
//        merge(a1, 0, 2, 3, 6, a2, 0);
        merge(a1, 0, 2, 3, 6, a2);
        System.out.println(Arrays.toString(a2));
        System.arraycopy(a2, 0, a1, 0, a2.length);
        System.out.println(Arrays.toString(a1));
    }
}
