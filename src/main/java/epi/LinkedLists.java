package epi;

import java.util.*;

/* Chapter 8.
***** USE DUMMY HEAD AND TAIL, CALLED SENTINEL NODES, TO AVOID NULL CHECK ERRORS. *****
 */
public class LinkedLists {
    public static void print(Node<Integer> cur) {
        for (; cur != null; cur = cur.next) {
            System.out.print(cur.value + ", ");
        }
        System.out.println();
    }
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

    public static LinkedList<Integer> getLinkedListWithCycle(int n) {
        int cnt = 5;
        LinkedList<Integer> l = getNewLinkedList(n);
        l.print();
        Node<Integer> cycleStart = null;
        Node<Integer> cur = l.head;
        while (cur.next != null) {
            if (cnt == 0) {
                cycleStart = cur;
            }
            cur = cur.next;
            --cnt;
        }
        cur.next = cycleStart;
        return l;
    }

    /* 8.3 - Find cycles in a list.
    ****** TC Pending ******
    * */
    public static Node<Integer> findCycle(LinkedList<Integer> l) {
        int cycleLen = 0;
        Node<Integer> slow = l.head;
        Node<Integer> fast = l.head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            //Check if fast and slow meet
            if (slow == fast) {
                //We are in the cycle. Get the length
                do {
                    ++cycleLen;
                    fast = fast.next;
                } while (slow != fast);

                /*Allow a new Iter to go cycleLen from head.
                Slow will touch fast and that is the start of the cycle.
                */
                Node<Integer> cycleStart = l.head, newSlow = l.head;
                while (cycleLen-- > 0) {
                    cycleStart = cycleStart.next;
                }
                while (newSlow != cycleStart) {
                    newSlow = newSlow.next;
                    cycleStart = cycleStart.next;
                }
                return cycleStart;
            }
        }
        return null;
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

    /* 8.9 - Cyclic right shift by k, 0 <= k, k can be > n too. - SLLs.
    T.C - O(n) if length is not known. O(len - k) if len is known.
    S.C - O(1)
     */
    public static void rightShift(LinkedList<Integer> ll, int k) {
        ll.print();
        //Get length and k % n
        Node<Integer> dH = new Node<>(0);
        dH.next = ll.getHead();
        Node<Integer> cur = dH;
        int len = 0;
        while (cur.next != null) {
            len++;
            cur = cur.next;
        }

        //Get the current head and tail.
        Node<Integer> nH = ll.getHead();
        Node<Integer> nT = cur;
        //Connect tail to head and close the cycle.
        cur.next = ll.getHead();

        /*Difference between right and left shift
        Just adjust k.
         */
        k %= len;
        k = len - k;
        while (k-- > 0) {
            nH = nH.next;
            nT = nT.next;
        }
        nT.next = null;
        ll.head = nH;
        ll.print();
    }

    public static void leftShift(LinkedList<Integer> ll, int k) {
        ll.print();
        //Get length and k % n
        Node<Integer> dH = new Node<>(0);
        dH.next = ll.getHead();
        Node<Integer> cur = dH;
        int len = 0;
        while (cur.next != null) {
            len++;
            cur = cur.next;
        }

        Node<Integer> nH = ll.getHead();
        Node<Integer> nT = cur;
        cur.next = ll.getHead();

        k %= len;
        while (k-- > 0) {
            nH = nH.next;
            nT = nT.next;
        }
        nT.next = null;
        ll.head = nH;
        ll.print();
    }

    /* 8.10 - Even - Odd merge. Even nodes followed by odd.
    O(n).
    O(1).
     */
    public static void evenOddMerge(LinkedList<Integer> ll) {
        ll.print();
        Node<Integer> evenCur = new Node<>(Integer.MIN_VALUE);
        Node<Integer> oddCur = new Node<>(Integer.MIN_VALUE);
        Node<Integer> evenHead = evenCur;
        Node<Integer> oddHead = oddCur;

        Node<Integer> dH = new Node<>(Integer.MIN_VALUE);
        dH.next = ll.getHead();

        int evenOddFlip = 0;
        Node<Integer> cur = dH.next;
        while (cur != null) {
            if (evenOddFlip == 0) {
                evenCur.next = cur;
                evenCur = cur;
            } else {
                oddCur.next = cur;
                oddCur = cur;
            }
            cur = cur.next;
            evenOddFlip ^= 1;
        }
        evenCur.next = null;
        oddCur.next = null;

        print(evenHead);
        print(oddHead);

        evenCur.next = oddHead.next;
        print(evenHead.next);
    }

    /* 8.11 - Test if SLL is palindromic.
    O(n)
    O(n/2) = O(n) Even though we don't allocate memory, we use pointers to refer to elements.
     */
    public static boolean palindrome(LinkedList<Integer> ll) {
        ll.print();

        Node<Integer> slow = ll.getHead();
        Node<Integer> fast = ll.getHead();

        Stack<Node<Integer>> sk = new Stack<>();
        while (fast != null && fast.next != null) {
            sk.push(slow);
            slow = slow.next;
            fast = fast.next.next;
        }

        Node<Integer> higherPtr = null;
        if (fast != null) {
            higherPtr = slow.next;
        } else {
            higherPtr = slow;
        }
        while (higherPtr != null) {
            Node<Integer> el = sk.pop();
            if (!el.value.equals(higherPtr.value)) {
                return false;
            }
            higherPtr = higherPtr.next;
        }

        return true;
    }

    /* 8.12 - List pivoting. Given k, arrange all i, such that i < k, i = k , i > k.
    O(n);
    O(1);
    This is a variant of even-odd merge.
     */
    public static void pivotTheList(LinkedList<Integer> l, int k) {
        l.print();
        Node<Integer> cur = l.head;
        Node<Integer> lessHead = new Node<>(Integer.MIN_VALUE);
        Node<Integer> equalHead = new Node<>(Integer.MIN_VALUE);
        Node<Integer> greaterHead = new Node<>(Integer.MIN_VALUE);

        Node<Integer> lessPtr = lessHead;
        Node<Integer> equalPtr = equalHead;
        Node<Integer> greaterPtr = greaterHead;

        while (cur != null) {
            if (cur.value < k) {
                lessPtr.next = cur;
                lessPtr = lessPtr.next;
            } else if (cur.value == k) {
                equalPtr.next = cur;
                equalPtr = equalPtr.next;
            } else {
                greaterPtr.next = cur;
                greaterPtr = greaterPtr.next;
            }
            cur = cur.next;
        }
        lessPtr.next = null;
        equalPtr.next = null;
        greaterPtr.next = null;

        print(lessHead);
        print(equalHead);
        print(greaterHead);

        lessPtr.next = equalHead.next;
        equalPtr.next = greaterHead.next;
        l.head = lessHead.next;
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

        if (true) {
            return;
        }
        // 8.3 - Find cycles in a list.
        Node<Integer> cycleStart = findCycle(getLinkedListWithCycle(15));

        // 8.12 - List pivoting. Given k, arrange all i, such that i < k, i = k , i > k.
        LinkedList<Integer> pivList = new LinkedList<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        List<Integer> pivI = List.of(10, 80, 20, 10, 40, 45, 10, 20, 50, 20, 10, 80);
        for (Integer i : pivI) {
            pivList.add(i);
        }
        pivotTheList(pivList, 20);

        //8.11 - Palindrome
        LinkedList<Integer> sl1 = new LinkedList<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        List<Integer> is = List.of(1, 2, 3, 4, 4, 3, 2, 0);
        for (Integer i : is) {
            sl1.add(i);
        }
        palindrome(sl1);

        ///8.10 - Even - Odd merge. Even nodes followed by odd.
        evenOddMerge(getNewLinkedList(10));

        //8.9 - Right and left shift
        rightShift(getNewLinkedList(6), 3);
        LinkedList<Integer> sl = new LinkedList<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        List<Integer> palLst = List.of(15, 16, 20, 22, 27, 31, 49, 56, 66, 71, 75, 75, 83, 86, 88, 88, 98, 105, 112, 112);
        for (Integer i : palLst) {
            sl.add(i);
        }

        //8.8 - Delete duplicates
        deleteDuplicatesV0(sl);

        // 8.7 - Delete kth last
        deleteKthLast(getNewLinkedList(5), 5);

        //8.1 - Merge 2 sorted lists
        merge(getNewSortedLinkedList(3), getNewSortedLinkedList(5));

        //8.6 - Delete current
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
