package epi;


import epi.util.ListUtil;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

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
        List<Integer> merged = mergeSortedFiles(lists);
        ListUtil.print(merged);
        return;
    }
    public static List<Integer> mergeSortedFiles(List<List<Integer>> lists) {
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

    public static void main(String[] args) {
        //11.1 - Merge sorted files.
        testMergeSortedFiles(3);
        if (true) {
            return;
        }
    }
}
