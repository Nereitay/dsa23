package es.kiwi.algorithm.binarysearch;

public class BinarySearch {

    public static int binarySearchBasic(int[] a, int target) {
        int i = 0, j = a.length - 1; // 设置指针和初值

        while (i <= j) { // 范围内有东西
//            int m = (i + j) / 2;
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else if (a[m] < target) {
                i = m + 1;
            } else { // 找到了
                return m;
            }
        }

        return -1;
    }

    public static int binarySearchAlternative(int[] a, int target) {
        int i = 0, j = a.length;

        while (i < j) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m;
            } else if (a[m] < target) {
                i = m + 1;
            } else {
                return m;
            }
        }

        return -1;
    }

    public static int binarySearchBalance(int[] a, int target) {
        int i = 0, j = a.length;

        while (1 < j - i) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m;
            } else {
                i = m;
            }
        }

        return a[i] == target ? i : -1;

    }

    public static int binarySearchLeftmost1(int[] a, int target) {
        int i = 0, j = a.length - 1;
        int candidate = -1;

        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else if (a[m] < target) {
                i = m + 1;
            } else {
              candidate = m;
              j = m - 1;
            }
        }

        return candidate;
    }

    public static int binarySearchRightmost1(int[] a, int target) {
        int i = 0, j = a.length - 1;
        int candidate = -1;

        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else if (a[m] < target) {
                i = m + 1;
            } else {
                candidate = m;
                i = m + 1;
            }
        }

        return candidate;
    }

    public static int binarySearchLeftmost2(int[] a, int target) {
        int i = 0, j = a.length - 1;

        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target <= a[m]) {
                j = m - 1;
            } else {
                i = m + 1;
            }
        }

        return i;
    }

    public static int binarySearchRightmost2(int[] a, int target) {
        int i = 0, j = a.length - 1;

        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else {
                i = m + 1;
            }
        }

        return i - 1;
    }
}
