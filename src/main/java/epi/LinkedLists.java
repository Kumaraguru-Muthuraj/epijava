package epi;

import java.util.Comparator;

public class LinkedLists {



    public static void main(String[] args) {
        SortedLinkedList<Integer> l = new SortedLinkedList<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        l.add(23);
        l.add(34);
        l.add(1);
        l.add(-200);
        l.print();

    }
}
