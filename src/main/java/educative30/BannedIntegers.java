package educative30;

import java.util.HashMap;

/*
LeetCode 2554
Description
You are given an integer array banned and two integers n and maxSum.
You are allowed to choose some integers that satisfy all of the following conditions:
Each chosen integer is in the range [1, n].
Each integer can be chosen at most once.
No chosen integer is in the array banned.
The sum of all chosen integers is not greater than maxSum.
Return the maximum number of integers you can choose while satisfying all the above conditions.
 */
public class BannedIntegers {
    public static int maxCount(int[] banned, int n, int maxSum) {
        HashMap<Integer, Integer> bn = new HashMap<>();
        for (int i = 0; i < banned.length; i++) {
            bn.put(banned[i], banned[i]);
        }

        int count = 0;
        int gSum = 0;
        for (int i = 1; i <= n; i++) {
            if (!bn.containsKey(i)) {
                gSum += i;
                count++;
                if (gSum > maxSum) {
                    return count < 0 ? 0 : count -1;
                }
            }
        }

        return count;
    }
    public static void main(String[] args) {
        int[] banned = {2, 4};
        System.out.println(maxCount(banned, 5, 10));
    }
}