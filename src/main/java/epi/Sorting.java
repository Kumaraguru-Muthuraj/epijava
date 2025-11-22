package epi;

import epi.util.ListUtil;

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
     * Given a list of intervals, closed/open on either sides, find the union of the intervals.
     *
     */
    public static void testUnionIntervals() {
        List<IntervalV2> intervals = new ArrayList<>();
        Boundary l = new Boundary(2, true);
        Boundary r = new Boundary(4, true);
        IntervalV2 i = new IntervalV2(l, r);
        intervals.add(i);

        l = new Boundary(1, true);
        r = new Boundary(1, true);
        i = new IntervalV2(l, r);
        intervals.add(i);

        l = new Boundary(3, true);
        r = new Boundary(4, false);
        i = new IntervalV2(l, r);
        intervals.add(i);

        l = new Boundary(0, false);
        r = new Boundary(3, false);
        i = new IntervalV2(l, r);
        intervals.add(i);

        l = new Boundary(8, true);
        r = new Boundary(11, false);
        i = new IntervalV2(l, r);
        intervals.add(i);

        l = new Boundary(7, true);
        r = new Boundary(8, false);
        i = new IntervalV2(l, r);
        intervals.add(i);

        l = new Boundary(5, true);
        r = new Boundary(7, false);
        i = new IntervalV2(l, r);
        intervals.add(i);

        l = new Boundary(9, false);
        r = new Boundary(11, true);
        i = new IntervalV2(l, r);
        intervals.add(i);

        l = new Boundary(13, false);
        r = new Boundary(15, false);
        i = new IntervalV2(l, r);
        intervals.add(i);

        l = new Boundary(12, false);
        r = new Boundary(16, true);
        i = new IntervalV2(l, r);
        intervals.add(i);

        l = new Boundary(12, true);
        r = new Boundary(14, true);
        i = new IntervalV2(l, r);
        intervals.add(i);

        l = new Boundary(16, false);
        r = new Boundary(17, false);
        i = new IntervalV2(l, r);
        intervals.add(i);

        Collections.sort(intervals);
        System.out.println();
        for (IntervalV2 ivl : intervals) {
            System.out.print(ivl + ", ");
        }
        List<IntervalV2> union = unionOfIntervals(intervals);
        System.out.println();
        for (IntervalV2 ivl : union) {
            System.out.print(ivl + ", ");
        }
    }
    public static List<IntervalV2> unionOfIntervals(List<IntervalV2> list) {
        List<IntervalV2> union = new ArrayList<>();
        IntervalV2 wind = list.get(0);
        int i = 1;
        while (i < list.size()) {
            IntervalV2 cur = list.get(i);
            if (wind.r.val < cur.l.val) {
                union.add(wind);
                wind = cur;
            } else if (wind.r.val == cur.l.val) {
                wind.r = cur.r;
            } else { //(wind.r.val > cur.l.val)
                if (wind.r.val == cur.r.val) {
                    wind.r.closed = wind.r.closed || cur.r.closed;
                } else {
                    wind.r = wind.r.val < cur.r.val ? cur.r : wind.r;
                }
            }
            i++;
        }
        union.add(wind);

        return union;
    }
    public static class IntervalV2 implements Comparable<IntervalV2> {
        public IntervalV2(Boundary l, Boundary r) {
            this.l = l;
            this.r = r;
        }
        public Boundary l;
        public Boundary r;
        public String toString() {
            String lB = l.closed ? "[" : "(";
            String rB = r.closed ? "]" : ")";
            return lB + l.val + ", " + r.val + rB;
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof IntervalV2)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            IntervalV2 that = (IntervalV2) obj;
            return (this.l.val == that.l.val && this.r.val == that.r.val &&
                    this.l.closed == that.l.closed && this.r.closed == that.r.closed);
        }

        /** Use only the left interval to decide the order.
         * Closed left comes first if there is a tie.
         */
        @Override
        public int compareTo(IntervalV2 o) {
            Boundary l1 = this.l;
            Boundary l2 = o.l;
            if (l1.val < l2.val) {
                return -1;
            } else if (l1.val > l2.val) {
                return 1;
            }
            //If equal
            if (l1.closed && !l2.closed) {
                return -1;
            } else if (l2.closed && !l1.closed) {
                return 1;
            }
            return 0;
        }

    }
    public static class Boundary {
        public Boundary(int val, boolean closed) {
            this.val = val;
            this.closed = closed;
        }
        public int val;
        public boolean closed;
    }

    /**
     * 14.7 - Group elements with same value, with high frequency.
     * Sorting is an overkill.
     */
    public static void testSortArrayWithHighFreqElem() {
        int[] arr = epi.Arrays.getArrayWithHighFreq(20);
        epi.Arrays.print(arr);
        sortArrayWithHighFreqElem(arr);
        epi.Arrays.print(arr);
    }

    public static void sortArrayWithHighFreqElem(int[] arr) {
        HashMap<Integer, Integer> elemToCount = new HashMap<>();
        for (int e : arr) {
            if (elemToCount.get(e) != null) {
                elemToCount.put(e, elemToCount.get(e) + 1);
            } else {
                elemToCount.put(e, 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : elemToCount.entrySet()) {
            System.out.print("<" + entry.getKey() + ", " + entry.getValue() + ">, ");
        }

        int offSet = 0;
        HashMap<Integer, Integer> elemToOffset = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : elemToCount.entrySet()) {
            Integer elem = entry.getKey();
            elemToOffset.put(elem, offSet);

            offSet += entry.getValue();
        }
        System.out.println();
        for (Map.Entry<Integer, Integer> entry : elemToOffset.entrySet()) {
            System.out.print("<" + entry.getKey() + ", " + entry.getValue() + ">, ");
        }


        while (elemToCount.size() > 0) {
            List<Integer> elems = new ArrayList<>(elemToCount.keySet());
            while (elems.size() > 0) {
                Integer elem = elems.get(0);
                int idx = elemToOffset.get(elem);
                int num = arr[idx];
                int count = -1;
                if (num == elem) {
                    elemToOffset.put(elem, (elemToOffset.get(elem) + 1));
                    count = elemToCount.get(elem) - 1;
                    if (count == 0) {
                        elemToCount.remove(elem);
                        elems = new ArrayList<>(elemToCount.keySet());
                    } else {
                        elemToCount.put(elem, count);
                    }
                } else {
                    int toIdx = elemToOffset.get(num);
                    epi.Arrays.swap(arr, idx, toIdx);
                    elemToOffset.put(num, elemToOffset.get(num) + 1);
                    count = elemToCount.get(num) - 1;
                    if (count == 0) {
                        elemToCount.remove(num);
                        elems = new ArrayList<>(elemToCount.keySet());
                    } else {
                        elemToCount.put(num, count);
                    }
                }
            }
        }
    }

    /**
     * 14.8 - Merge sort - Stable sorting algorithm, that is fast.
     * Use Floyd's 2 ptr approach, split the list to 2 and
     * merge with LinkedLists.merge().
     */
    public static Node<Integer> stableMergeSort(Node<Integer> node) {
        print("List to be split", node);
        Node<Integer> slow = node;
        Node<Integer> fast = slow;
        Node<Integer> preSlow = null;
        while (fast != null && fast.next != null) {
            preSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        Node<Integer> l1 = slow;
        if (preSlow != null) {
            preSlow.next = null;
        }
        Node<Integer> l2 = node;
        // Just 1 node left.
        if (l1 == l2) {
            return l1;
        }
        Node<Integer> sortedL1 = stableMergeSort(l1);
        Node<Integer> sortedL2 = stableMergeSort(l2);
        Node<Integer> mergedSorted = LinkedLists.merge(sortedL1, sortedL2);
        return mergedSorted;
    }

    public static void print(String message, Node<Integer> head) {
        System.out.print("\n" + message + " - ");
        for (Node<Integer> cur = head; cur != null; cur = cur.next) {
            System.out.print(cur.value + ", ");
        }
    }

    public static void testStableSort() {
        LinkedList<Integer> l = LinkedLists.getNewLinkedList(9);
        l.print();
        Node<Integer> n = stableMergeSort(l.getHead());
        print("Merged", n);
    }

    public static void main(String[] args) {
        //14.7 - Sort elem with high freq
        testSortArrayWithHighFreqElem();

        if (true) {
            return;
        }

        //14.8 - Merge sort
        testStableSort();

        //14.6
        testUnionIntervals();

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
