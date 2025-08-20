package epi;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

/* Chapter 8.
***** USE DUMMY HEAD AND TAIL, CALLED SENTINEL NODES, TO AVOID NULL CHECK ERRORS. *****
 */
public class LinkedLists {
    /* 8.1 - Merge 2 sorted lists.
    O(m + n)
    O(1)
     */
    // If it were a doubly linked list, first write this code and fix the reverse pointer.
    public static Node<Integer> merge(LinkedList<Integer> l1, LinkedList<Integer> l2) {
        l1.print();
        l2.print();
        Node<Integer> m = new Node<>(Integer.MAX_VALUE);
        Node<Integer> mh = m;
        Node<Integer> c1 = l1.getHead();
        Node<Integer> c2 = l2.getHead();
        while (c1 != null && c2 != null) {
            if (c1.value < c2.value) {
                m.next = c1;
                c1 = c1.next;
            } else {
                m.next = c2;
                c2 = c2.next;
            }
            m = m.next;
        }
        m.next = c1 == null ? c2 : c1;

        for (Node<Integer> cur = mh; cur != null; cur = cur.next) {
            System.out.print(cur.value + ", ");
        }
        System.out.println();
        return m;
    }

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

    /* 8.7 - Remove the kth last element from a LL.
    O(n)
    O(1)
     */
    public static boolean deleteKthLast(LinkedList<Integer> l, int k) {
        l.print();
        Node<Integer> dummyHead = new Node<>(Integer.MIN_VALUE);
        dummyHead.next = l.getHead();
        Node<Integer> slow = dummyHead, fast = l.getHead();
        while (k > 0) {
            fast = fast.next;
            k--;
        }
        if (k < 0)
            return false;

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;

        //Set this to make sure the LL has a new head if deleted 1st element.
        l.head =  dummyHead.next;
        l.print();
        return true;
    }

    /* 8.8 - Remove duplicates from a LL.
     ********** VERY TRICKY ALGORITHM **********
     */
    public static void deleteDuplicates(LinkedList<Integer> l) {
        //Null checks for l
        l.print();
        Node<Integer> cur = l.getHead();
        Node<Integer> nextNode = cur.next;
        while (cur != null) {
            while (nextNode != null && cur.value.equals(nextNode.value)) {
                nextNode = nextNode.next;
            }
            cur.next = nextNode;
            cur = nextNode;
        }

        l.print();
    }
    public static void deleteDuplicatesV0(LinkedList<Integer> l) {
        //Null checks for l
        l.print();
        Node<Integer> prev = l.getHead();
        Node<Integer> cur = prev.next;
        while(cur != null) {
            while (cur != null && prev.value.equals(cur.value)) {
                cur = cur.next;
                prev.next = cur;
            }
            if (cur != null) {
                cur = cur.next;
                prev = prev.next;
            }
        }
        l.print();
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

    public static LinkedList<Integer> getNewSortedLinkedList(int n) {
        SortedLinkedList<Integer> ll = new SortedLinkedList<>(new Comparator<Integer>() {
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
        LinkedList<Integer> sl = new LinkedList<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        List<Integer> is = List.of(15, 16, 20, 22, 27, 31, 49, 56, 66, 71, 75, 75, 83, 86, 88, 88, 98, 105, 112, 112);
        for (Integer i : is) {
            sl.add(i);
        }
        deleteDuplicatesV0(sl);
        if (true) {
            return;
        }
        deleteKthLast(getNewLinkedList(5), 5);

        merge(getNewSortedLinkedList(3), getNewSortedLinkedList(5));

        deleteCurrent();

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
