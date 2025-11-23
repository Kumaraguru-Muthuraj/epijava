package educative30;

import java.util.Arrays;

/**
 * Statement
 * You are given an integer, n, representing the number of computers, and a 0-indexed integer array, batteries, where batteries[i] denotes the number of minutes the
 * ith battery can power a computer.
 * Your goal is to run all n computers simultaneously for the maximum possible number of minutes using the available batteries.
 * Initially, you may assign at most one battery to each computer. After that, at any moment, you may remove a battery from a
 * computer and replace it with another battery—either an unused battery or one taken from another computer.
 * This replacement process can be repeated any number of times and takes no time. Each battery can power any computer
 * multiple times, but only until it is completely drained. Batteries cannot be recharged. Return the maximum number
 * of minutes you can run all n computers simultaneously.
 */
public class MaximumRunningTime {
    public static int maxRunTime(int[] batteries, int n) {
        // You have to use long inside and get back int outside.
        Arrays.sort(batteries);
        long sumPower = 0;
        for (int power : batteries) {
            sumPower += power;
        }
        long maxPower = sumPower / n;
        long minPower = 0;
        long mid = 0;
        while (minPower < maxPower) {
            mid = maxPower - (maxPower - minPower) / 2;
            if (validTime(mid, n, batteries)) {
                minPower = mid;
            } else {
                maxPower = mid - 1;
            }
        }
        return (int) minPower;
    }

    public static boolean validTime(long candidateTime, int n, int[] batteries) {
        long timeSum = 0;
        for (int power : batteries) {
            timeSum += Math.min(power, candidateTime);
        }
        return timeSum >= n * candidateTime;
    }

    public static void main(String[] args) {
        int[] bat = {3, 4, 3, 4, 5, 5, 8, 2};
        //This test case fails.
        System.out.println(maxRunTime(bat, 3));
    }


}
