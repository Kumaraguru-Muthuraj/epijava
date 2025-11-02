package educative30;

import java.util.ArrayList;
import java.util.TreeSet;

/*
LeetCode 683
Description
You have n bulbs in a row, numbered from 1 to n.
All bulbs are initially off.
You are given an integer array bulbs of length n, where bulbs[i] = x means that on day (i + 1), the bulb at position x is turned on.
Given an integer k, return the earliest day such that there exist two bulbs that are on, and there are exactly k bulbs that are off between them.
If no such day exists, return -1.
 */
public class Bulbs {
    //We should return index - 1, but its okay we return the right day.
    public static int getIndexesBetweenOffBulbsV1(ArrayList<Integer> days, int k) {
        TreeSet<Integer> bulbs = new TreeSet<>();

        for (int dayIdx = 0; dayIdx < days.size(); dayIdx++) {
            Integer bulb = days.get(dayIdx);
            bulbs.add(bulb);
            int diff = 0;
            Integer prevBulb = bulbs.lower(bulb);
            if (prevBulb != null) {
                diff = bulb - prevBulb - 1;
                if (k == diff) {
                    return dayIdx;
                }
            }
            Integer nextBulb = bulbs.higher(bulb);
            if (nextBulb != null) {
                diff = nextBulb - bulb - 1;
                if (k == diff) {
                    return dayIdx;
                }
            }
        }
        return -1;
    }
    public static ArrayList<Integer> getIndexesBetweenOffBulbsV2() {
        return null;

    }
    public static void main(String[] args) {
        ArrayList<Integer> days = new ArrayList<>();
        days.add(10);
        days.add(7);
        days.add(1);
        days.add(5);
        days.add(8);
        days.add(6);
        days.add(4);
        days.add(3);
        days.add(2);
        days.add(9);
        System.out.println(getIndexesBetweenOffBulbsV1(days, 3));
    }
}

