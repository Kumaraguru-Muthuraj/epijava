package epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sorting {
    /**
     * 14.1 - Intersection of 2 arrays. No duplicates to be returned.
     */
    public static List<Integer> intersect(List<Integer> l1, List<Integer> l2) {
        List<Integer> inter = new ArrayList<>();
        int i1 = 0;
        int i2 = 0;
        while (i1 < l1.size() && i2 < l2.size()) {
            if (l1.get(i1).equals(l2.get(i2)) && !l1.get(i1 - 1).equals(l1.get(i1))) {
                inter.add(l1.get(i1));
                i1++;
                i2++;
            } else if (l1.get(i1) < l2.get(i2)) {
                i1++;
            } else {
                i2++;
            }
        }
        return inter;
    }
    public static void testIntersect() {
        List<Integer> l1 = Arrays.asList(2, 4, 6, 7, 8, 8, 9);
        List<Integer> l2 = Arrays.asList(3, 5, 8, 9, 12, 15);
        List<Integer> inter =  intersect(l1, l2);
        epi.Arrays.print(inter);
    }

    /**
     * 14.2 - Merge 2 sorted arrays.
     */
    public static List<Integer> merge2SortedArray(List<Integer> a, List<Integer> b) {
        int aLen = a.size(), bLen = b.size();
        int mLen = aLen + bLen;
        int tempMLen = bLen;
        while (tempMLen-- > 0) {
            a.add(Integer.MIN_VALUE);
        }
        System.out.println("aLen - " + a.size());

        int wIdx = mLen - 1;
        int aIdx = aLen - 1;
        int bIdx = bLen - 1;
        while (bIdx >= 0) {
            if (a.get(aIdx) < b.get(bIdx)) {
                a.set(wIdx, b.get(bIdx));
                bIdx--;
            } else {
                a.set(wIdx, a.get(aIdx));
                aIdx--;
            }
            wIdx--;
        }

        return a;
    }
    public static void testMerge() {
        List<Integer> l1 = new ArrayList<Integer>(Arrays.asList(2, 4, 6, 7, 8, 8, 9));
        List<Integer> l2 = Arrays.asList(3, 5, 8, 9, 12, 15);
        List<Integer> merged =  merge2SortedArray(l1, l2);
        epi.Arrays.print(merged);
    }

    public static void main(String[] args) {
        // 14.2 - Intersect
        testMerge();

        if (true) {
            return;
        }
        // 14.1 - Intersect
        testIntersect();

    }

}
