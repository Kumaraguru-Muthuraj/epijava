package flagship;

/**
 * **Problem: Count Subarrays With Fixed Bounds**
 *
 * **Statement**
 * Given an integer array `nums` and two integers `minK` and `maxK`, return the number of fixed-bound subarrays.
 *
 * A subarray in `nums` is called a fixed-bound subarray if it satisfies the following conditions:
 *
 * * The smallest value in the subarray equals `minK`.
 * * The largest value in the subarray equals `maxK`.
 *
 * **Note**
 *
 * A subarray is a contiguous sequence of elements within an array.
 *
 * **Constraints**
 *
 * * `2 ≤ nums.length ≤ 10^3`
 * * `1 ≤ nums[i], minK, maxK ≤ 10^3`
 */
public class SubarrayWithBounds {


}
/*
public class CountSubarraysFixedBounds {

    public long countSubarrays(int[] nums, int minK, int maxK) {
        long count = 0;

        int lastMin = -1;
        int lastMax = -1;
        int lastInvalid = -1;

        for (int i = 0; i < nums.length; i++) {

            int val = nums[i];

            // If value is outside bounds → reset window
            if (val < minK || val > maxK) {
                lastInvalid = i;
            }

            if (val == minK) {
                lastMin = i;
            }

            if (val == maxK) {
                lastMax = i;
            }

            // Number of valid subarrays ending at i
            int validStart = Math.min(lastMin, lastMax);

            if (validStart > lastInvalid) {
                count += (validStart - lastInvalid);
            }
        }

        return count;
    }

    // Demo
    public static void main(String[] args) {
        CountSubarraysFixedBounds obj = new CountSubarraysFixedBounds();

        int[] nums = {1, 2, 3, 2, 1};
        int minK = 1;
        int maxK = 3;

        System.out.println(obj.countSubarrays(nums, minK, maxK)); // 2
    }
}
 */