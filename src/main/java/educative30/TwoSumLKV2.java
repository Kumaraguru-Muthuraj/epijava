package educative30;

import java.util.Arrays;

/**
 * Given an array of integers, nums, and an integer k, find the maximum sum of two elements in nums less than k.
 * Otherwise, return −1, if no such pair exists.
 */
public class TwoSumLK {
    public static int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        epi.Arrays.print(nums);
        int min = 0, max = nums.length - 1;
        int sum = 0;
        int prevSum = 0;
        boolean found = false;
        while (min < max) {
            int minInt = nums[min];
            int maxInt = nums[max];
            sum = minInt + maxInt;
            if (sum < k) {
                prevSum = Math.max(sum, prevSum);
                found = true;
                min++;
            } else {
                prevSum = Math.min(sum, prevSum);
                max--;
            }
        }
        return found ? prevSum : -1;
    }

    public static void main(String[] args) {
        //int[] a = {1, 2, 3, 4, 8, 9, 10};
        //int[] a = {4,2,11,2,5,3,5,8};
        int[] a = {13,22,28,47,52,66,79,99,109,126,135,139,143,153,153,155,183,207,208,209,215,220,224,226,245,273,274,285,288,289,326,331,338,344,349,361,378,380,393,419,440,444,466,489,490,496,498,503,508,513,529,556,557,567,570,587,589,590,607,615,616,618,624,631,669,691,700,702,722,753,766,769,772,788,790,792,812,848,861,862,865,886,900,901,914,918,919,925,953,954,955,960,967,971,978,985,987,988,989,991};
        System.out.println(twoSumLessThanK(a, 749));
        //[4,2,11,2,5,3,5,8] , 7 - Exp - 6
        //[34,23,1,24,75,33,54,8] , 60 - Exp - 58
        /**
         * int[] a = {13,22,28,47,52,66,79,99,109,126,135,139,143,153,153,155,183,207,208,209,215,220,224,226,245,273,274,285,288,289,326,331,338,
         * 344,349,361,378,380,393,419,440,444,466,489,490,496,498,503,508,513,529,556,557,567,570,587,589,590,607,615,616,618,624,631,669,691,700,702,
         * 722,753,766,769,772,788,790,792,812,848,861,862,865,886,900,901,914,918,919,925,953,954,955,960,967,971,978,985,987,988,989,991};
         * 749
         * Res - 748
         */
    }
}
