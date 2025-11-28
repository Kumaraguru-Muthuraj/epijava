package educative30;

import epi.LinkedList;
import epi.LinkedLists;
import epi.Node;
import epi.util.ListUtil;

import java.util.List;

/**
 * Statement
 * Given a singly linked list with n nodes and two positions, left and right, the objective is to reverse the
 * nodes of the list from left to right. Return the modified list.
 * 1 based index.
 */
public class LLReversal {
    public static void reverse(LinkedList<Integer> l, int s, int e) {
        int i = s;
        int j = e;
        if (i >= j) {
            return;
        }
        l.print();

        //Validations
        Node<Integer> prev = null;
        Node<Integer> cur = l.getHead();
        while (cur != null && i > 1) {
            prev = cur;
            cur = cur.next;
            i--;
        }
        if (cur == null) {
            return;
        }
        LinkedLists.print(cur);
        j = e - s;
        Node<Integer> start = prev;
        Node<Integer> next = null;
        while (j >= 0 && cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            j--;
        }

        if (start == null) {

        } else {
            start.next.next = cur;
            start.next = prev;
        }

        l.print();
    }

    public static Node<Integer> reverseBetween(Node<Integer> head, int s, int e)
    {
        Node<Integer> sentinel = head;
        int i = s;
        int j = e;
        if (i >= j) {
            return head;
        }
        LinkedLists.print(head);

        //Validations
        Node<Integer> prev = null;
        Node<Integer> cur = head;
        while (cur != null && i > 1) {
            prev = cur;
            cur = cur.next;
            i--;
        }
        if (cur == null) {
            return head;
        }
        LinkedLists.print(cur);
        j = e - s;
        Node<Integer> start = prev;
        Node<Integer> next = null;
        while (j >= 0 && cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            j--;
        }
        if (start == null) {
            head = prev;
            sentinel.next = cur;
        } else {
            start.next.next = cur;
            start.next = prev;
        }
        LinkedLists.print(head);
        return head;
    }

    public static void main(String[] args) {
        LinkedList<Integer> l1 = LinkedLists.getNewLinkedList(5);
        reverseBetween(l1.getHead(), 1, 5);

    }
}
