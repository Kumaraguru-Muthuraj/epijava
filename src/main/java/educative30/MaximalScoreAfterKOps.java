package educative30;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * You are given a 0-indexed array of integer nums and an integer k. Your task is to maximize a score through a series of operations. Initially, your score is set to 0.
 * In each operation:
 * Select an index i (where 0≤ 0 ≤ i <<nums.length).
 * Add the value of nums[i] to your score.
 * Replace nums[i] with ceil(nums[i] / 3).
 * Repeat this process exactly k times and return the highest score you can achieve.
 * The ceiling function ceil(value) is the least integer greater than or equal to value.
 */
/**
 * T.C - Prep = O(n.log(n))
 *      K ops -> O(k.log(n))
 * S.C - O(n)
 *
 * If we use binary search to get the k largest elements? There is no data prep, the run time is O(k.log(n))
 */
public class MaximalScoreAfterKOps {
    public static int maxScore (int[] nums, int k) {
        PriorityQueue<Integer> he = new PriorityQueue<>(cmp);
        for (int i : nums) {
            he.add(i);
        }
        int score = 0;
        int max = Integer.MIN_VALUE;
        while (k > 0) {
            if (!he.isEmpty()) {
                max = he.poll();
                score += max;
                int nMax = max / 3;
                if (max % 3 > 0) {
                    nMax++;
                }
                he.add(nMax);
            }
            k--;
        }
        return score;
    }
    public static Comparator<Integer> cmp = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    };
    public static void main(String[] args) {
        int[] a1 = {6, 9, 15};
        int[] a2 = {5, 12, 7, 3, 10};
        int[] a3 = {10, 20, 30, 40, 50};
        System.out.println(maxScore(a3, 4));
    }
}
