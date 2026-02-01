package educative2;


import java.util.*;

/**
**Summary Ranges from a Data Stream**

You are given a stream of non-negative integers
( a1, a2, .., an ).

At any point in time, you must summarize all numbers seen so far as a list of **disjoint intervals**.
You need to implement a class called **`SummaryRanges`** that supports the following operations:
* **Constructor**
Initializes the data structure with an empty stream.
* **`addNum(int value)`**
Adds the integer `value` to the stream.

* **`getIntervals()`**
Returns the current summary of the numbers seen so far as a list of disjoint intervals
([start_i, end_i]), sorted by `start_i`.

Each number must belong to **exactly one interval**.

Intervals must be merged whenever newly added numbers connect or extend existing intervals.
Duplicate insertions must **not** change the summary.

Input - 1, 3, 7, 2, 6
Output -
 [1,1]
 [1,1], [3,3]
 [1,1], [3,3], [7,7]
 [1,3], [7,7]
 [1,3], [6,7]

 */

//20260201
public class SummaryRanges {
    public SummaryRanges() {
        map = new TreeMap<>(Integer::compare);
    }

    public void addNum(int value) {
        Map.Entry<Integer, Interval> l =  map.floorEntry(value);
        Map.Entry<Integer, Interval> r = map.ceilingEntry(value);

        Interval left = l == null ? null : l.getValue();
        Interval right = r == null ? null : r.getValue();

        //Case 1
        if (left != null && left.l <= value && value <= left.h) {
            return;
        }
        boolean mergeLeft = left != null && left.h + 1 == value;
        boolean mergeRight = right != null && value == right.l - 1;

        //Case 2
        if (mergeLeft && mergeRight) {
            left.h = right.h;
            map.remove(right.h);
        } else if (mergeLeft) {
            //Case 3
            left.h = value;
        } else if (mergeRight) {
            //Case 4
            map.remove(right.l);
            map.put(value, new Interval(value, right.h));
        } else {
            //Case 5
            map.put(value, new Interval(value, value));
        }
    }

    public int[][] getIntervals() {
        int[][] result = new int[map.size()][2];
        int i = 0;
        for (Interval interval : map.values()) {
            result[i][0] = interval.l;
            result[i][1] = interval.h;
            i++;
        }
        return result;
    }


    TreeMap<Integer, Interval> map;
    class Interval {
        public Interval(Integer l, Integer h) {
            this.l = l;
            this.h = h;
        }
        Integer l;
        Integer h;
    }
    public static void main(String[] args) {
        SummaryRanges d = new SummaryRanges();
        int[] a = {1, 3, 7, 2, 6};
        for (int i : a) {
            d.addNum(i);
        }
        int[][] ints = d.getIntervals();
        for (int[] ivl : ints) {
            System.out.println("[" + ivl[0] + "," +  ivl[1] + "]");
        }
    }
}
