package flagship;

/*
### 🧩 Problem: **3Sum**
⏱️ **Time Limit:** 30 minutes
💡 **Hint:** Try sorting and using the two-pointer technique.
---
### 📘 Statement
Given an integer array `nums`, find and return **all unique triplets**
```
[nums[i], nums[j], nums[k]]
```
such that:
* `i ≠ j`, `i ≠ k`, and `j ≠ k`, and
* `nums[i] + nums[j] + nums[k] = 0`
---
### 📝 Note
* The **order of the triplets** in the output does **not** matter.
* The solution must **not contain duplicate triplets**.
---
### 📌 Constraints
* `3 ≤ nums.length ≤ 500`
* `-10³ ≤ nums[i] ≤ 10³`
*/
import java.util.*;
//FLAGSHIP1
public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> sums = new ArrayList<>();
        if (nums.length < 3) {
            return null;
        }
        Arrays.sort(nums);
        int sum = Integer.MIN_VALUE;
        int target = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    List<Integer> sL = List.of(nums[i], nums[left], nums[right]);
                    sums.add(sL);

                    /** Handle duplicates
                     * Since the array is sorted, the adjacent similar elements can be safely skipped
                     */
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right - 1] == nums[right]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return sums;
    }

    /*
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length < 3) {
            return result;
        }

        Arrays.sort(nums);

        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {

            // Skip duplicate anchors
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = n - 1;

            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {

                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicates for left
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }

                    // Skip duplicates for right
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;

                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }*/

    // Simple test
    public static void main(String[] args) {
        /**  -> (-2, 0 , 2), (-1, 0, 1)
        -3, -1, -1, 0, 1, 2, 3, 3 ->
        * */
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        int[] nums2 = {-2, 0, 2, -2, 1, -1};
        int[] nums3 = {-3, -1, -1, 0, 1, 2, 3, 3};
        int[] nums4 = {0, 0, 0};

        System.out.println(threeSum(nums3));
        /*System.out.println(threeSum(nums2));
        System.out.println(threeSum(nums3));
        System.out.println(threeSum(nums4));*/
    }
}

