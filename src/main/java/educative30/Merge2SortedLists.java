package educative30;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Merge2SortedLists {
    static class LinkedListNode {
        public int data;
        public LinkedListNode next;
        public LinkedListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }
    public static LinkedListNode getList(int[] l) {
        LinkedListNode head = new LinkedListNode(l[0]);
        LinkedListNode cur = head;
        for (int i = 1; i < l.length; i++) {
            cur.next = new LinkedListNode(l[i]);
            cur = cur.next;
        }
        return head;
    }
    public static void print(LinkedListNode n) {
        System.out.println();
        while (n != null) {
            System.out.print(n.data + ", ");
            n = n.next;
        }
    }
    public static LinkedListNode mergeTwoLists(LinkedListNode head1, LinkedListNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        LinkedListNode merged = null, cur = null;
        if (head1.data < head2.data) {
            merged = head1;
            head1 = head1.next;
        } else {
            merged = head2;
            head2 = head2.next;
        }
        cur = merged;

        while (head1 != null && head2 != null) {
            if (head1.data < head2.data) {
                cur.next = head1;
                cur = cur.next;
                head1 = head1.next;
            } else {
                cur.next = head2;
                cur = cur.next;
                head2 = head2.next;
            }
        }

        LinkedListNode h = head1 == null ? head2 : head1;
        cur.next = h;

        return merged;
    }
    public static void main(String[] args) {
        int[] l1 = {1}; //{2, 4, 11, 19, 19, 24};
        LinkedListNode head1 = getList(l1);
        print(head1);
        int[] l2 = {2, 6, 7, 11, 17};
        LinkedListNode head2 = getList(l2);
        print(head2);
        print(mergeTwoLists(head1, head2));
    }
}



