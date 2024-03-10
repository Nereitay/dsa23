package es.kiwi.algorithm.binarysearch;

/**
 * Given a sorted array of distinct integers and a target value,
 * return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10⁴
 * -10⁴ <= nums[i] <= 10⁴
 * nums contains distinct values sorted in ascending order.
 * -10⁴ <= target <= 10⁴
 */
public class E02Leetcode35 {
    /**
     * java 版
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert1(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while(i <= j) {
            int m = (i + j) >>> 1;
            if (target < nums[m]) {
                j = m - 1;
            } else if (nums[m] < target) {
                i = m + 1;
            } else {
                return m;
            }
        }

        return i;
    }

    /**
     * leftmost 版
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert2(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while(i <= j) {
            int m = (i + j) >>> 1;
            if (target <= nums[m]) {
                j = m - 1;
            } else{
                i = m + 1;
            }
        }

        return i;
    }
}
