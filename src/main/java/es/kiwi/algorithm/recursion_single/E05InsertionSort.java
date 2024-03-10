package es.kiwi.algorithm.recursion_single;

public class E05InsertionSort {

    public static void sort(int[] a) {
        insertionImproved(a, 1);
    }

    private static void insertion(int[] a, int low) {
        if (low == a.length) return;

        int temp = a[low];
        int i = low - 1; // 已排序区域指针
        while (i >= 0 && a[i] > temp) {// 没有找到插入位置
            a[i + 1] = a[i]; //空出插入位置
            i--;
        }
        //找到插入位置
        a[i + 1] = temp;

        insertion(a, low + 1);
    }

    private static void insertionImproved(int[] a, int low) {
        if (low == a.length) return;

        int temp = a[low];
        int i = low - 1; // 已排序区域指针
        while (i >= 0 && a[i] > temp) {// 没有找到插入位置
            a[i + 1] = a[i]; //空出插入位置
            i--;
        }
        //找到插入位置
        if ((i + 1) != low) {// 减少一次多余的赋值
            a[i + 1] = temp;
        }

        insertionImproved(a, low + 1);
    }

    private static void insertion2(int[] a, int low) {
        if (low == a.length) return;

        int i = low - 1;
        while (i >= 0 && a[i] > a[i + 1]) {// 赋值次数更多
            int temp = a[i];
            a[i] = a[i + 1];
            a[i + 1] = temp;
            i--;
        }

        insertion2(a, low + 1);
    }
}
