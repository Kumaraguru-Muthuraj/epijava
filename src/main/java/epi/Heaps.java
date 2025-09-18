package epi;


import epi.util.ListUtil;

import java.util.*;
import java.util.LinkedList;

import static epi.util.ListUtil.getKSortedList;

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
        List<Integer> l3 = getKSortedList();
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
        if (true) {
            return;
        }
        //11.1 - Merge sorted files.
        testMergeSortedFiles(3);

        //11.2 - testSortIncDecArray
        testSortIncDecArray();

        //11.7 - Stack using heap.
        testStackUsingHeap(10);



    }
}
