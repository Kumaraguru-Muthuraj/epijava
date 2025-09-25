package epi.util;

import java.util.*;

public class ListUtil {
    public static List<Integer> getList(int size) {
        List<Integer> l = new LinkedList<>();
        Random r = new Random();
        while (size-- > 0) {
            l.add(r.nextInt(100));
        }
        return l;
    }
    public static List<Integer> getSortedList(int size) {
        List<Integer> l = getList(size);
        Collections.sort(l);
        return l;
    }
    public static void print(List<Integer> l) {
        System.out.print("\nPrinting list - ");
        for (Integer i : l) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }
    public static List<Integer> getKIncreasingDecreasingList() {
        List<Integer> l = new LinkedList<>();
        l.add(40);l.add(41);l.add(42);l.add(43);l.add(45);
        l.add(39);l.add(38);l.add(37);
        l.add(50);l.add(52);l.add(55);
        l.add(49);l.add(48);l.add(47);
        return l;
    }
    public static List<Integer> getKSortedList() {
        List<Integer> l = new LinkedList<>();
        l.add(0);l.add(2);l.add(-1);l.add(1);l.add(4);
        l.add(3);l.add(6);l.add(5);
        l.add(8);l.add(7);l.add(10);
        return l;
    }
    public static List<Integer> getKSortedList(int k) {
        List<Integer> l = getSortedList(10);
        print(l);
        Random r = new Random();
        int idx = 0;
        int len = l.size();
        for (int i = 0; i < len; i++) {
            idx = i % (r.nextInt(k) + 1);
            Collections.swap(l, i, idx);
        }
        return l;
    }
    public static List<Integer> getHeapInArray(int k) {
        List<Integer> ret = new LinkedList<>();
        Comparator<Integer> c = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        };
        Random r = new Random();
        PriorityQueue<Integer> heap = new PriorityQueue<>(16, c);
        while (k-- >= 0) {
            heap.add(r.nextInt(100));
        }
        while (heap.peek() != null) {
            ret.add(heap.poll());
        }
        return ret;
    }

    public static void main(String[] args) {
        //print(getList(10));
       // print(getSortedList(10));
        //print(getKSortedList(3));
        getHeapInArray(10);
    }
}
