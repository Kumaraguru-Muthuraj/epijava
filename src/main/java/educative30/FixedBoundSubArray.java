package educative30;

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
