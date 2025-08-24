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

    public static LinkedList<Integer> getLinkedListWithCycle(int cycleIdx, int n) {
        int cnt = cycleIdx;
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
        l.cycleIdx = cycleStart;
        l.randomNodeInCycle = cycleStart.next.next;
        return l;
    }

    /* 8.2 - Reverse a sublist.
    index starts at 0.
     */
    public static void reverseSublist(LinkedList<Integer> l, int start, int end) {
        if (start < 0)
            return;
        l.print();
        Node<Integer> dH = new Node<>(Integer.MIN_VALUE);
        dH.next = l.head;

        Node<Integer> prev = dH;
        Node<Integer> cur = l.head;
        Node<Integer> next = null;
        int idx = 0;
        while (cur != null && idx < start) {
            prev = cur;
            cur = cur.next;
            idx++;
        }

        if (cur == null) {
            return;
        }

        Node<Integer> connector = prev;
        while (cur != null && idx < end) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            idx++;
        }
        connector.next.next = next;
        connector.next = prev;
        if (dH == connector) {
            l.head = dH.next;
        }

        l.print();
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

    public static List<LinkedList<Integer>> getOverlappingLinkedLists(int n) {
        LinkedList<Integer> l1 = getNewLinkedList(n);
        l1.print();
        LinkedList<Integer> l2 = getNewLinkedList(2 * n);
        l2.print();

        l1.tail.next = l2.head.next.next.next;
        System.out.println("After overlapping");
        l1.print();
        l2.print();

        ArrayList<LinkedList<Integer>> two = new ArrayList<>();
        two.add(l1);two.add(l2);
        return  two;
    }

    public static List<LinkedList<Integer>> getOverlapBeforeCycle(int n1) {
        int cnt = n1 - 4;
        LinkedList<Integer> l1 = getLinkedListWithCycle(cnt, n1);
        l1.printCycleNode();
        int n2 = n1/3;
        LinkedList<Integer> l2 = getNewLinkedList(n2);
        l2.printCycleNode();

        Node<Integer> l1Cur = l1.head;
        Node<Integer> cur = l2.head;
        while (cur.next != null){
            l1Cur = l1Cur.next;
            cur = cur.next;
        }
        cur.next = l1Cur;
        l2.cycleIdx = l1.cycleIdx;

        ArrayList<LinkedList<Integer>> two = new ArrayList<>();
        two.add(l1);two.add(l2);
        return  two;
    }

    public static List<LinkedList<Integer>> getOverlapInsideCycle(int n1) {
        int cnt = n1 - 6;
        LinkedList<Integer> l1 = getLinkedListWithCycle(cnt, n1);
        l1.printCycleNode();
        int n2 = n1/3;
        LinkedList<Integer> l2 = getNewLinkedList(n2);
        l2.print();

        Node<Integer> cur = l2.head;
        while (cur.next != null){
            cur = cur.next;
        }
        cur.next = l1.randomNodeInCycle;
        l2.cycleIdx = l1.randomNodeInCycle;

        ArrayList<LinkedList<Integer>> two = new ArrayList<>();
        two.add(l1);two.add(l2);
        return  two;
    }

    /* 8.4 - Find the point of overlap between 2 SLLs. There are no cycles.
    Consider lengths are not known.
    Traverse twice the whole length - O(n).
    Space - O(1).
     */
    public static Node<Integer> tandemTraverse(Node<Integer> longer, Node<Integer> shorter, int travLen) {
        while (travLen-- > 0) {
            longer = longer.next;
        }
        while (longer != shorter) {
            longer = longer.next;
            shorter = shorter.next;
        }
        return longer;
    }

    public static Node<Integer> getOverlappingNode(List<LinkedList<Integer>> lists) {
        Node<Integer> l1head = lists.get(0).head;
        Node<Integer> l2head = lists.get(1).head;
        Node<Integer> curl1 = new Node<>(Integer.MIN_VALUE); curl1.next = l1head;
        Node<Integer> curl2 = new Node<>(Integer.MIN_VALUE); curl2.next = l2head;
        //Get lengths and check if the tails are same
        int l1Len = 0;
        int l2Len = 0;
        while (curl1.next != null && curl2.next != null) {
            l1Len++;
            l2Len++;
            curl1 = curl1.next;
            curl2 = curl2.next;
        }
        if (curl1.next == null) {
            while (curl2.next != null) {
                l2Len++;
                curl2 = curl2.next;
            }
        } else if (curl2.next == null) {
            while (curl1.next != null) {
                l1Len++;
                curl1 = curl1.next;
            }
        }

        //Tails don't match
        if (curl1 != curl2) {
            return null;
        }
        int travLen = 0;
        if (l1Len < l2Len) {
            travLen = l2Len - l1Len;
            return tandemTraverse(l2head, l1head, travLen);
        } else {
            travLen = l1Len - l2Len;
            return tandemTraverse(l1head, l2head, travLen);
        }
    }



    /* 8.5 - Detect Overlapping node between SLLs. Lists can have cycles.
    Check notebook for the diagram.
     */
    public static Node<Integer> getCommonNodeThatHaveCycles(List<LinkedList<Integer>> lists) {
        LinkedList<Integer> l1 = lists.get(0);
        LinkedList<Integer> l2 = lists.get(1);
        Node<Integer> c1 = findCycle(l1);
        Node<Integer> c2 = findCycle(l2);

        // Case 3
        if ((c1 == null && c2 != null) || (c1 != null && c2 == null)) {
            return null;
        }
        // Case 1 & 2
        if (c1 == null && c2 == null) {
            Node<Integer> confluence = getOverlappingNode(lists);
            return confluence;
        }
        if (c1 != null && c2 != null) {
            // Case 5
            if (c1 == c2) {
                return c1;
            }
            // Case 6
            boolean overLap = checkIfCyclesOverlap(c1, c2);
            if (overLap) {
                return c1; // can return both c1 and c2
            }
            // Case 4
            return null;
        }
        return null;
    }

    public static boolean checkIfCyclesOverlap(Node<Integer> c1, Node<Integer> c2) {
        if (c1 == c2) {
            return true;
        }
        Node<Integer> c2Iter = c2.next;
        while (c2Iter != c2) {
            if (c2Iter == c1) {
                return true;
            }
            c2Iter = c2Iter.next;
        }
        return false;
    }

    public static void getCommonNodeThatHaveCyclesTests() {
        List<LinkedList<Integer>> lists = new ArrayList<>();
        //Case 1
        LinkedList<Integer> l1 = getNewLinkedList(5);
        LinkedList<Integer> l2 = getNewLinkedList(5);
        lists.add(l1);
        lists.add(l2);
        Node<Integer> oNode = getCommonNodeThatHaveCycles(lists);
        lists.clear();

        //Case 2
        lists = getOverlappingLinkedLists(5);
        oNode = getCommonNodeThatHaveCycles(lists);
        lists.clear();

        //Case 3
        lists.add(getNewLinkedList(5));
        lists.add(getLinkedListWithCycle(4, 8));
        oNode = getCommonNodeThatHaveCycles(lists);
        lists.clear();

        //Case 4
        l1 = getLinkedListWithCycle(4, 8);
        l2 = getLinkedListWithCycle(3, 9);
        lists.add(l1);
        lists.add(l2);
        l1.printCycleNode();
        l2.printCycleNode();
        oNode = getCommonNodeThatHaveCycles(lists);
        lists.clear();

        //Case 5
        lists = getOverlapBeforeCycle(15);
        lists.get(0).printCycleNode();
        lists.get(1).printCycleNode();
        oNode = getCommonNodeThatHaveCycles(lists);
        lists.clear();

        //Case 6
        lists = getOverlapInsideCycle(15);
        lists.get(0).printCycleNode();
        lists.get(1).printCycleNode();
        oNode = getCommonNodeThatHaveCycles(lists);
        lists.clear();

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

    /* 8.13 - Add list based integers.
    O(max(n1.len, n2.len))
    O(max(n1.len, n2.len))
     */
    public static LinkedList<Integer> add(LinkedList<Integer> n1, LinkedList<Integer> n2) {
        n1.print();
        n2.print();
        int carry = 0, sumVal = 0;
        LinkedList<Integer> sum = getNewLinkedList(0);
        Node<Integer> c1 = n1.head;
        Node<Integer> c2 = n2.head;
        while (c1 != null && c2 != null) {
            sumVal = c1.value + c2.value + carry;
            carry = sumVal / 10;
            sumVal = sumVal % 10;
            sum.add(sumVal);

            c1 = c1.next;
            c2 = c2.next;
        }
        Node<Integer> cur = c1 != null ? c1 : c2;
        if (cur == null && carry > 0) {
            sum.add(carry);
        } else {
            while (cur != null) {
                sumVal = cur.value + carry;
                carry = sumVal / 10;
                sumVal = sumVal % 10;
                sum.add(sumVal);

                cur = cur.next;
            }
        }
        sum.print();
        return sum;
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

    public static LinkedList<Integer> getSingleDigitLinkedList(int n) {
        LinkedList<Integer> ll = new LinkedList<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        Random r = new Random();
        for (int i = 0; i < n; i++) {
            ll.add(r.nextInt(10));
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
        // 8.5 - Detect Overlapping node between SLLs. Lists can have cycles.
        getCommonNodeThatHaveCyclesTests();

        if (true) {
            return;
        }

        // 8.13 - Add list based integers.
        LinkedList<Integer> sum = add(getSingleDigitLinkedList(2), getSingleDigitLinkedList(2));

        // 8.2 - Reverse a sublist.
        reverseSublist(getNewLinkedList(5), 5, 5);

        //8.4 - Find the point of overlap between 2 SLLs. There are no cycles.
        List<LinkedList<Integer>> ol = getOverlappingLinkedLists(6);
        ol.get(0).print();
        ol.get(1).print();

        getOverlappingNode(ol);

        // 8.3 - Find cycles in a list.
        Node<Integer> cycleStart = findCycle(getLinkedListWithCycle(5, 15));

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
