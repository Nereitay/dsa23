package es.kiwi.algorithm.binarysearch;

/**
 * Given an array of integers nums which is sorted in ascending order, and an integer target,
 * write a function to search target in nums.
 * If target exists, then return its index. Otherwise, return -1.
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10⁴
 * -10⁴ < nums[i], target < 10⁴
 * All the integers in nums are unique.
 * nums is sorted in ascending order.
 */
public class E01Leetcode704 {
    /**
     * 基础版
     * @param nums
     * @param target
     * @return
     */
    public int search1(int[] nums, int target) {
        int i = 0, j = nums.length - 1;

        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < nums[m]) {
                j = m - 1;
            } else if (nums[m] < target) {
                i = m + 1;
            } else {
                return m;
            }
        }

        return -1;

    }

    /**
     * 改动版
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        int i = 0, j = nums.length;

        while (i < j) {
            int m = (i + j) >>> 1;
            if (target < nums[m]) {
                j = m;
            } else if (nums[m] < target) {
                i = m + 1;
            } else {
                return m;
            }
        }

        return -1;

    }

    /**
     * 平衡版
     * @param nums
     * @param target
     * @return
     */
    public int search3(int[] nums, int target) {
        int i = 0, j = nums.length;

        while (1 < j - i) {
            int m = (i + j) >>> 1;
            if (target < nums[m]) {
                j = m;
            } else {
                i = m;
            }
        }

        return nums[i] == target ? i : -1;

    }
}
