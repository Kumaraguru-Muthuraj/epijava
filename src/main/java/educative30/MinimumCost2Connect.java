package educative30;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Statement
 * You are given a set of sticks with positive integer lengths represented as an array, sticks, where sticks[i]
 * denotes the length of the ith stick.
 * You can connect any two sticks into one stick at a cost equal to the sum of their lengths.
 * Once two sticks are combined, they form a new stick whose length is the sum of the two original sticks.
 * This process continues until there is only one stick remaining.
 * Your task is to determine the minimum cost required to connect all the sticks into a single stick.
 */
public class MinimumCost2Connect {
    public static int connectSticks (int[] sticks) {
        if (sticks.length == 1) {
            return 0;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.naturalOrder());
        for (int i : sticks) {
            heap.add(i);
        }
        int cost = 0;
        while (!heap.isEmpty()) {
            Integer m1 = heap.poll();
            Integer m2 = null;
            if (!heap.isEmpty()) {
                m2 = heap.poll();
                int sum = m1 + m2;
                cost += sum;
                heap.add(sum);
            }
        }

        return cost;
    }
    public static void main(String[] args) {
        int[] arr = {5, 120, 7, 30, 10};
        System.out.println(connectSticks(arr));
    }
}
