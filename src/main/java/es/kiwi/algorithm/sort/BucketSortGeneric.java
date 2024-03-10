package es.kiwi.algorithm.sort;

import es.kiwi.datastructure.array.DynamicArray;

import java.util.Arrays;

/**
 * <h3>桶排序(改进), 把原始元素加入桶内</h3>
 */
public class BucketSortGeneric {

    public static void main(String[] args) {
        int[] ages = {9, 0, 5, 1, 3, 2, 4, 6, 8, 7};
        System.out.println(Arrays.toString(ages));
//        sort(ages);
        sort(ages, 3);
        System.out.println(Arrays.toString(ages));
    }

    public static void sort(int[] a) {
        int range = 1;
        sort(a, range);
    }

    /*
        0   0,1,2
        1   3,4,5
        2   6,7,8
        3   9
     */
    public static void sort(int[] a, int range) {
        int max = a[0];
        int min = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
            if (a[i] < min) {
                min = a[i];
            }
        }

        // 1. 准备桶
        DynamicArray[] buckets = new DynamicArray[(max - min) / range + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new DynamicArray();
        }

        // 2. 放入数据
        for (int i : a) {
            buckets[(i - min) / range].addLast(i);
        }

        int k = 0;
        for (DynamicArray bucket : buckets) {
            // 3. 排序桶内元素
            int[] array = bucket.array();
            InsertionSort.sort(array);
            System.out.println(Arrays.toString(array));
            // 4. 把每个桶排序好的内容，依次放入原始数组
            for (int v : array) {
                a[k++] = v;
            }
        }
    }
}
