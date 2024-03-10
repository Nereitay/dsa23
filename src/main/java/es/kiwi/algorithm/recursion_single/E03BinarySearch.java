package es.kiwi.algorithm.recursion_single;

public class E03BinarySearch {

    public static int search(int[] nums, int target) {
        return bs(nums, target, 0, nums.length - 1);
    }

    private static int bs(int[] nums, int target, int i, int j) {
        if (i > j) {
            return -1;
        }

        int m = (i + j) >>> 1;
        if (target < nums[m]) {
            return bs(nums, target, i, m - 1);
        } else if (nums[m] < target) {
            return bs(nums, target, m + 1, j);
        } else {
            return m;
        }
    }

}
