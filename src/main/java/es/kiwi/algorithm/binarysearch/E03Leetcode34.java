package es.kiwi.algorithm.binarysearch;

import java.security.InvalidParameterException;

/**
 * Given an array of integers nums sorted in non-decreasing order,
 * find the starting and ending position of a given target value.
 * <p>
 * If target is not found in the array, return [-1, -1].
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * Constraints:
 * 0 <= nums.length <= 10⁵
 * -10⁹ <= nums[i] <= 10⁹
 * nums is a non-decreasing array.
 * -10⁹ <= target <= 10⁹
 */
public class E03Leetcode34 {

    public int[] searchRange(int[] nums, int target) {
        int x = leftOrRight(nums, target, "L");
        if (x == -1) {
            return new int[]{-1, -1};
        } else {
            return new int[]{x, leftOrRight(nums, target, "R")};
        }
    }

    private int leftOrRight(int[] nums, int target, String leftOrRight) {
        int i = 0, j = nums.length - 1;
        int candidate = -1;

        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < nums[m]) {
                j = m - 1;
            } else if (nums[m] < target) {
                i = m + 1;
            } else {
                candidate = m;
                if ("L".equals(leftOrRight)) {
                    j = m - 1;
                } else if ("R".equals(leftOrRight)) {
                    i = m + 1;
                }
            }
        }

        return candidate;
    }

    private int right(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        int candidate = -1;

        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < nums[m]) {
                j = m - 1;
            } else if (nums[m] < target) {
                i = m + 1;
            } else {
                candidate = m;
                i = m + 1;
            }
        }

        return candidate;
    }


    private int left(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        int candidate = -1;

        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < nums[m]) {
                j = m - 1;
            } else if (nums[m] < target) {
                i = m + 1;
            } else {
                candidate = m;
                j = m - 1;
            }
        }

        return candidate;
    }
}
