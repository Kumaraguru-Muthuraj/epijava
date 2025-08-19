package epi;

import java.util.Comparator;
import java.util.Random;

/* Chapter 8.
Use dummy head and tail, called Sentinel nodes, to avoid null check errors.
*/
public class LinkedLists {

    /* 8.6 - Delete current node. Limitation - Cannot delete if its last node.
    If we have a dummy tail, then its possible to delete the last node.
    O(1), O(1)
     */
    public static void deleteCurrent() {
        LinkedList<Integer> sl = getNewLinkedList(10);
        sl.print();
        if (sl.getHead() != null) {
            System.out.println(sl.deleteCurrent(sl.getRandomNode()));
            sl.print();
        }
    }

    public static LinkedList<Integer> getNewLinkedList(int n) {
        LinkedList<Integer> ll = new LinkedList<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        Random r = new Random();
        for (int i = 0; i < n; i++) {
            ll.add(r.nextInt(120) + 1);
        }
        return ll;
    }

    public static void main(String[] args) {
        deleteCurrent();
        if (true) {
            return;
        }
        LinkedList<Integer> ll = new LinkedList<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        ll.add(23);
        ll.delete(23);
        ll.add(34);
        ll.add(1);
        ll.delete(1);
        ll.add(-200);
        ll.delete(34);
        ll.add(10);
        ll.add(50);
        ll.print();
        System.out.println("\n\n");

        SortedLinkedList<Integer> l = new SortedLinkedList<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        l.add(23);
        l.delete(23);
        l.add(34);
        l.add(1);
        l.delete(1);
        l.add(-200);
        l.delete(34);
        l.add(10);
        l.add(50);
        l.print();
        System.out.println("\n\n");
    }
}
