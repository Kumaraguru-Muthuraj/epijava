package educative30;

import epi.Arrays;

/**
 * You are given a sorted array of integers, nums, where all integers appear twice except for one. Your task is to find and return the single integer that appears only once.
 *
 * The solution should have a time complexity of
 * O(log(n)) or better and a space complexity of O(1)
 * The solution is not taking into consideration the even and odd lengths,
 * accordingly we can go right or left.
 * Rewrite this.
 */
public class UniqueNum {
    public static int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int idx = searchUnique(nums, 0, nums.length - 1);
        return nums[idx];
    }
    public static int searchUnique(int[] nums, int lo, int hi) {
        if (lo <= hi) {
            int mid = lo + (hi - lo)/2;
            boolean dup = duplicated(nums, mid);
            if (!dup) {
                return mid;
            }
            int uIdx = searchUnique(nums, lo, mid - 1);
            if (uIdx == -1) {
                uIdx = searchUnique(nums, mid + 1, hi);
            }
            return uIdx;
        }
        return -1;
    }
    public static boolean duplicated(int[] nums, int idx) {
        if (idx == 0) {
            return nums[idx] == nums[idx + 1];
        }
        if (idx == nums.length - 1) {
            return nums[idx - 1] == nums[idx];
        }
        if (nums[idx] == nums[idx + 1]) {
            return true;
        }
        if (nums[idx - 1] == nums[idx]) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {2, 2, 5, 5, 6, 6, 7, 7, 8, 8, 9};
        singleNonDuplicate(arr);
    }
}
