package educative30;

import java.util.Arrays;

/**
 * Given an array of integers, nums, and an integer k, find the maximum sum of two elements in nums less than k.
 * Otherwise, return −1, if no such pair exists.
 */
public class TwoSumLKV2 {
    private static int search(int[] nums, int target, int start) {
        int left = start, right = nums.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    public int twoSumLessThanK(int[] nums, int k) {
        int maximumSum = -1;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int j = search(nums, k - nums[i], i + 1);

            if (j > i) {
                maximumSum = Math.max(maximumSum, nums[i] + nums[j]);
            }
        }

        return maximumSum;
    }

    // Driver code
    public static void main(String[] args) {
        int[][] nums = {
                {4, 5, 2, 6, 2},
                {4, 9, 5, 2, 5, 6, 8, 1, 4, 7, 9},
                {10, 20, 30},
                {34, 23, 1, 24, 75, 33, 54, 8},
                {5, 5, 5, 5, 5, 5, 5, 5}
        };

        int[] k = {12, 14, 15, 54, 10};

        TwoSumLKV2 obj = new TwoSumLKV2();
        for (int i = 0; i < nums.length; i++) {
            System.out.print((i + 1) + ".\tnums: [");
            for (int j = 0; j < nums[i].length; j++) {
                System.out.print(nums[i][j]);
                if (j < nums[i].length - 1) System.out.print(", ");
            }
            System.out.println("]\n\tk: " + k[i]);
            System.out.println("\n\tTwo sum less than k: " + obj.twoSumLessThanK(nums[i], k[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
