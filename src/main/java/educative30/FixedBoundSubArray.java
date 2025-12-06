package educative30;

/**
 * Given an integer array, nums, and two integers minK and maxK, return the number of fixed-bound subarrays.
 * A subarray in nums is called a fixed-bound subarray if it satisfies the following conditions:
 * The smallest value in the subarray equals minK. The largest value in the subarray equals maxK.
 * Note: A subarray is a contiguous sequence of elements within an array.
 *
 * Check Book-2, Page 8 for a simple solution.
 */
public class FixedBoundSubArray {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long count = 0;
        int minPos = -1, maxPos = -1, leftBound = -1;
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if (val < minK || val > maxK) {
                minPos = -1;
                maxPos = -1;
                leftBound = i;
                continue;
            }
            if (val == minK) {
                minPos = i;
            }
            if (val == maxK) {
                maxPos = i;
            }
            if (minPos != -1 && maxPos != -1) {
                count += Math.min(minPos, maxPos) - leftBound;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        FixedBoundSubArray fb = new FixedBoundSubArray();
        int[] nums = {1, 3, 5, 2, 7, 5};
        System.out.println(fb.countSubarrays(nums, 1, 5)); // Output: 2
        /**
         * [1,3,5,2,7,5] , 1 , 5
         * [1,5] , 1 , 5
         * [1,1,1,1,1,1,1,1,1,1] , 1 , 1
         * [1,2,3,4] , 2 , 5
         * [1,3,1,3,1,3] , 1 , 3
         */
    }
}
