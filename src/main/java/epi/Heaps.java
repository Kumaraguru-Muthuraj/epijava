package epi;


import epi.util.ListUtil;

import java.util.*;
import java.util.LinkedList;

import static epi.util.ListUtil.getKIncreasingDecreasingList;

/* Heaps related problems.
 */
public class Heaps {

    //11.1 - Merge sorted files.
    public static void testMergeSortedFiles(int k) {
        int size = 7;
        List<List<Integer>> lists = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            lists.add(ListUtil.getSortedList(size));
            size *= 1.5;
        }
        for (int i = 0; i < k; i++) {
            ListUtil.print(lists.get(i));
        }
        List<Integer> merged = mergeSortedFiles1(lists);
        ListUtil.print(merged);
        return;
    }

    static class IntegerWithID {
        public IntegerWithID(Integer k, Integer id) {
            this.value = k;
            this.id = id;
        }
        public Integer value;
        public Integer id;
    }

    public static List<Integer> mergeSortedFiles1(List<List<Integer>> lists) {
        List<Integer> merged = new LinkedList<>();
        Comparator<IntegerWithID> comparator = new Comparator<IntegerWithID>() {
            @Override
            public int compare(IntegerWithID o1, IntegerWithID o2) {
                return o1.value.compareTo(o2.value);
            }
        };
        PriorityQueue<IntegerWithID> heap = new PriorityQueue<>(lists.size(), comparator);

        int id = 0;
        for (List<Integer> list: lists) {
            if (!list.isEmpty()) {
                heap.add(new IntegerWithID(list.remove(0), id++));
            }
        }
        while (!heap.isEmpty()) {
            IntegerWithID integerWithID = heap.poll();
            merged.add(integerWithID.value);

            List<Integer> l = lists.get(integerWithID.id);
            if (!l.isEmpty()) {
                heap.add(new IntegerWithID(l.remove(0), integerWithID.id));
            }
        }

        return merged;
    }

    /***** THIS IS ACTUALLY WRONG. YOU NEED TO TRACK THE LIST FROM WHICH ITS PICKED UP
    AND ADD THE NUMBER FROM THE NEXT LIST
    *****/
    public static List<Integer> mergeSortedFiles0(List<List<Integer>> lists) {
        List<Integer> merged = new LinkedList<>();
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        PriorityQueue<Integer> heap = new PriorityQueue<>(lists.size(), comparator);

        for (List<Integer> list: lists) {
            if (!list.isEmpty()) {
                heap.add(list.remove(0));
            }
        }

        while (!heap.isEmpty()) {
            for (List<Integer> list: lists) {
                if (!list.isEmpty()) {
                    heap.add(list.remove(0));
                }
            }
            if (!heap.isEmpty()) {
                merged.add(heap.poll());
            }
        }

        return merged;
    }

    //11.2 - Sort an increasing-decreasing array.
    public static void testSortIncDecArray() {
        List<Integer> l3 = getKIncreasingDecreasingList();
        ListUtil.print(l3);
        List<Integer> merged = sortIncDecArray(l3);
        ListUtil.print(merged);
    }
    enum Order {INC, DEC};
    public static List<Integer> sortIncDecArray(List<Integer> list) {
        Order ord = null;
        if (list.get(0) < list.get(1)) {
            ord = Order.INC;
        } else {
            ord = Order.DEC;
        }
        List<List<Integer>> lists = new LinkedList<>();
        List<Integer> sub = new LinkedList<>();
        int begin = 0, i = 1;
        for (; i <= list.size(); i++) {
            if (i == list.size() || (list.get(i - 1) < list.get(i) && ord.equals(Order.DEC)) ||
                    (list.get(i - 1) >= list.get(i) && ord.equals(Order.INC))) {
                sub = list.subList(begin, i);
                begin = i;
                if (ord.equals(Order.DEC)) {
                    Collections.reverse(sub);
                }
                List<Integer> dest = new LinkedList<>(sub);
                lists.add(dest);
                ord = ord.equals(Order.INC) ? Order.DEC : Order.INC;
            }
        }

        return mergeSortedFiles1(lists);
    }

    //11.3 - Sort K-Sorted list
    public static void testSortKSortedList() {
       List<Integer> l = ListUtil.getKSortedList();
       ListUtil.print(l);
       l = sortKSortedList(l, 2);
       ListUtil.print(l);
    }
    public static List<Integer> sortKSortedList(List<Integer> l, int k) {
        k++;
        Comparator<Integer> c = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        List<Integer> retList = new LinkedList<>();
        PriorityQueue<Integer> heap = new PriorityQueue<>(16, c);
        for (int i = 0; i < k; i++) {
            heap.add(l.remove(0));
        }
        while (heap.peek() != null) {
            retList.add(heap.poll());
            if (!l.isEmpty()) {
                heap.add(l.remove(0));
            }
        }
        return retList;
    }

    //11.5 - Compute Median on online data
    public static void testMedianOfOnlineData() {
        List<Integer> l = ListUtil.getList(10);
        ListUtil.print(l);
        ListUtil.printDouble(getRunningMedian(l));
    }
    public static List<Double> getRunningMedian(List<Integer> l) {
        Comparator<Integer> min = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        Comparator<Integer> max = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        };
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(16, min);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(16, max);

        List<Double> medians = new LinkedList<>();
        double median;
        for (Integer x : l) {
            if (minHeap.isEmpty()) {
                minHeap.add(x);
            } else {
                if (!minHeap.isEmpty() && minHeap.peek() <= x) {
                    minHeap.add(x);
                } else {
                    maxHeap.add(x);
                }
            }

            int minMinusMax = minHeap.size() - maxHeap.size();
            /* As a policy we retain 1 more in minHeap than maxHeap
            This is why we check > 1, not > 0
             */
            if (minMinusMax > 1) {
                maxHeap.add(minHeap.poll());
            } else if (minMinusMax < 0) {
                minHeap.add(maxHeap.poll());
            }
            median = minHeap.size() == maxHeap.size() ? 0.5 * (minHeap.peek() + maxHeap.peek()) : minHeap.peek();
            medians.add(median);
        }
        return medians;
    }

    //11.6 - Get the k-largest elements from an array-based max heap
    public static void testKLargestFromMaxHeap() {
        List<Integer> heapInArr = ListUtil.getHeapInArray(10);
        ListUtil.print(heapInArr);
        List<Integer> kLargest = kLargestFromMaxHeap(heapInArr, 5);
        System.out.print("K-Largest - ");
        ListUtil.print(kLargest);
    }
    public static List<Integer> kLargestFromMaxHeap(List<Integer> maxHeapInArr, int k) {
        List<Integer> ret = new LinkedList<>();
        Comparator<IntegerWithID> c = new Comparator<IntegerWithID>() {
            @Override
            public int compare(IntegerWithID o1, IntegerWithID o2) {
                return o2.value.compareTo(o1.value);
            }
        };

        PriorityQueue<IntegerWithID> heap = new PriorityQueue<>(16, c);
        IntegerWithID ele = new IntegerWithID(maxHeapInArr.get(0), 0);
        heap.add(ele);

        while (heap.peek() != null && (k-- > 0)) {
            ele = heap.poll();
            ret.add(ele.value);

            int lChild = 2 * ele.id + 1;
            if (lChild < maxHeapInArr.size()) {
                heap.add(new IntegerWithID(maxHeapInArr.get(lChild), lChild));
            }

            int rChild = 2 * ele.id + 2;
            if (rChild < maxHeapInArr.size()) {
                heap.add(new IntegerWithID(maxHeapInArr.get(rChild), rChild));
            }
        }

        return ret;
    }

    //11.7 - Stack using heap.
    public static void testStackUsingHeap(int k) {
        Random r = new Random();
        StackOnHeap stack  = new StackOnHeap();
        while (k-- > 0) {
            Integer i = r.nextInt(100);
            System.out.print(i + ", ");
            stack.push(i);
        }
        System.out.println("StackOnHeap");
        while (stack.peek() != null) {
            System.out.print(stack.pop() + ", ");
        }
    }

    public static void main(String[] args) {
        //11.5 - Compute Median on online data
        testMedianOfOnlineData();

        if (true) {
            return;
        }
        //11.1 - Merge sorted files.
        testMergeSortedFiles(3);

        //11.2 - testSortIncDecArray
        testSortIncDecArray();

        //11.3 - Sort K-Sorted list
        testSortKSortedList();

        //11.6 - Get the k-largest elements from an array-based max heap
        testKLargestFromMaxHeap();

        //11.7 - Stack using heap.
        testStackUsingHeap(10);

    }
}
