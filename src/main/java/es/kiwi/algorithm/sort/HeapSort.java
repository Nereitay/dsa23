package es.kiwi.algorithm.sort;

import java.util.Arrays;

/**
 * <h3>堆排序</h3>
 * <p>建立大顶堆</p>
 * <p>每次将堆顶元素（最大值）交换到末尾，调整堆顶元素，让它重新符合大顶堆特性</p>
 */
public class HeapSort {

    public static void sort(int[] a) {
        heapify(a, a.length);
        for (int right = a.length - 1; right > 0; right--) {
            swap(a, 0, right);
            down(a, 0, right);
        }
    }

    /**
     * 建堆 O(n)
     * @param array
     * @param size
     */
    private static void heapify(int[] array, int size) {
        for (int i = size / 2 - 1; i >= 0; i--) {
            down(array, i, size);
        }
    }

    /**
     * 下潜
     * @param array
     * @param parent
     * @param size
     */
    private static void down(int[] array, int parent, int size) {
        // leetcode 上数组排序题目用堆排序求解，非递归实现比递归实现大约快 6ms
        while (true) {
            int left = parent * 2 + 1;
            int right = left + 1;
            int max = parent;
            if (left < size && array[left] > array[max]) {
                max = left;
            }
            if (right < size && array[right] > array[max]) {
                max = right;
            }
            if (max == parent) break;

            swap(array, max, parent);
            parent = max;
        }

    }

    /**
     * 交换
     * @param a
     * @param i
     * @param j
     */
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 1, 7, 6, 4, 5};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
