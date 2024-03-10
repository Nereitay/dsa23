package es.kiwi.algorithm.binarysearch;

public class LinearSearch {

    public static int linearSearch(int[] a, int target) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == target) {
                return i;
            }
        }

        return -1;
    }
}
