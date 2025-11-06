package educative30;

import epi.Arrays;

/* Statement - Given an integer array, nums, find a subarray that has the largest product, and return the product.

 */
public class MaximumProduct {

    public static int maxProduct_Wrong(int [] nums) {
        int curRunProd = 1;
        int maxProd = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (curRunProd <= 0) {
                    maxProd = Math.max(maxProd, 0);
                } else {
                    maxProd = Math.max(maxProd, curRunProd);
                }
                curRunProd = 1;
            } else {
                curRunProd *= nums[i];
            }
        }
        maxProd = Math.max(maxProd, curRunProd);
        return maxProd;
    }

    public static int maxProduct(int [] nums) {
        int res = nums[0];
        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i];
            if (cur < 0) {
                int temp = min;
                min = max;
                max = temp;
            }
            min = Math.min(cur, cur * min);
            max = Math.max(cur, cur * max);

            res = Math.max(max, res);
        }
        return res;
    }

    public static void main(String[] args) {
        // My algo doesn't work for [2,3,-2,4] when there are non-zero numbers
        int[] nums = {2,3,-2,4}; //{1, 20, 0, 3, 5, 0, 2, 5};
//        {-1,-1,-5,7,1,-3,-5,0,-6,2,-2,7,1,7,8,-3,1,-9,-6,-9,4,-1,-3,0,
//                10,10,6,-1,0,-8,6,-6,-5,-10,-6,0,6,1,-1,10,-5,-2,5,-10,0,3,-2,3,-9,2,5,
//                -7,9,0,-8,0,1,-3,0,2,-2,8,6,-9,9,-1,-6,-7,1,-1,-6,0,10,8,1,6,-6,-8,1,10,0,
//                -5,-1,4,10,-6,6,-3,-9,8,0,9,8,-1,6,7,8,2,-5,-9};
        System.out.println(maxProduct(nums));
    }

}
