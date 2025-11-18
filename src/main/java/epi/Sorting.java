package epi;

import java.util.*;
import java.util.Arrays;

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
    /**
     * 14.3 - Remove first name duplicates.
     */
    public static void removeFNameDups(List<Name> names) {
        Collections.sort(names);
        for (Name name : names) {
            System.out.println(name);
        }
        int writeIdx = 0;
        int readIdx = 1;
        for (; readIdx < names.size(); readIdx++) {
            if (!names.get(writeIdx).fName.equals(names.get(readIdx).fName)) {
                names.set(++writeIdx, names.get(readIdx));
            }
        }
        System.out.println("\nDup fNames removed");
        if (names.size() > writeIdx + 1) {
            names.subList(writeIdx + 1, names.size()).clear();
        }
        for (Name name : names) {
            System.out.println(name);
        }
    }
    static class Name implements Comparable<Name> {
        public Name(String fName, String lName) {
            this.fName = fName;
            this.lName = lName;
        }
        @Override
        public int compareTo(Name o) {
            if (this.fName.compareTo(o.fName) != 0) {
                return this.fName.compareTo(o.fName);
            }
            return this.lName.compareTo(o.lName);
        }

        @Override
        public String toString() {
            return this.fName + ", " + this.lName;
        }
        public String fName;
        public String lName;
    }
    public static void testRemoveFNameDups() {
        List<Name> names = new ArrayList<>();

        names.add(new Name("Bob", "Smith"));
        names.add(new Name("Alice", "Johnson"));
        names.add(new Name("David", "Clark"));
        names.add(new Name("Alice", "Brown"));
        names.add(new Name("Charlie", "Adams"));
        names.add(new Name("Eva", "Miller"));
        names.add(new Name("Bob", "Taylor"));
        names.add(new Name("Charlie", "Wells"));
        names.add(new Name("David", "Reed"));
        names.add(new Name("Eva", "Turner"));

        removeFNameDups(names);

    }

    /**
     * 14.5 - Merge intervals.
     * Given sorted range and a new interval, merge the new interval into the sorted range.
     *
     */
    public static void merge(List<Interval> list, Interval newIvl) {
        List<Interval> result = new ArrayList<>();

        //Step 1
        int i = 0;
        Interval ivl = list.get(i);
        while (i < list.size() && ivl.r < newIvl.l) {
            result.add(ivl);
            i++;
            ivl = list.get(i);
        }

        //Step 2
        Interval outIvl = null;
        while (i < list.size() && newIvl.r >= ivl.l) {
            /**
             * Remember this logic. Overlapping intervals
             * [a, b] [c, d] = [min(a,c), max(b,d)]
             */
            outIvl = new Interval(Math.min(newIvl.l, ivl.l),
                                Math.max(newIvl.r, ivl.r));
            newIvl = outIvl;
            ivl = list.get(++i);
        }
        result.add(newIvl);

        //Step 3 - Copy over the rest
        result.addAll(list.subList(i, list.size()));

        System.out.println("Merged");
        for (Interval it : result) {
            System.out.println(it);
        }


    }
    public static class Interval {
        public Interval(int l, int r) {
            this.l = l;
            this.r = r;
        }
        public int l;
        public int r;
        public String toString() {
            return "[" + l + ", " + r + "]";
        }
    }
    public static void testMergeInterval() {
        List<Interval> list = new ArrayList<>();

        list.add(new Interval(0, 3));
        list.add(new Interval(6, 9));
        list.add(new Interval(12, 15));
        list.add(new Interval(18, 21));
        list.add(new Interval(24, 26));
        list.add(new Interval(30, 35));

        System.out.println("List");
        for (Interval i : list) {
            System.out.println(i);
        }

        merge(list, new Interval(13, 23));
    }

    /**
     * 14.6 - Union of intervals.
     * Given a list of intervals, closed/open on eother sides, find the union of the intervals.
     *
     */

    public static void main(String[] args) {

        if (true) {
            return;
        }
        //14.5
        testMergeInterval();

        //14.3 - Remove Duplicate fNames
        testRemoveFNameDups();

        // 14.2 - Intersect
        testMerge();

        // 14.1 - Intersect
        testIntersect();

    }

}
