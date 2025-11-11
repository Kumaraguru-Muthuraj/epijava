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
    public static void main(String[] args) {
        testIntersect();
    }

}
