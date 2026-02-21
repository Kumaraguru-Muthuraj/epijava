package flagship;

import epi.LinkedList;
import epi.LinkedLists;
import epi.Node;

/**
 * **Problem: Remove Nth Node From End of List**
 *
 * **Statement**
 * Given the head of a singly linked list and an integer `n`, remove the `n`th node from the end of the list and return
 * the head of the modified list.
 *
 * **Constraints**
 *
 * * The number of nodes in the list is `k`.
 * * `1 ≤ k ≤ 10^3`
 * * `-10^3 ≤ Node.value ≤ 10^3`
 * * `1 ≤ n ≤ k`
 */
public class RemoveNthFromEnd {
    //Use SENTINEL.
    public static Node<Integer> removeNthLastNode(Node<Integer> head, int n) {
        if (n < 0) {
            return head;
        }
        Node<Integer> sentinel = new Node<>(Integer.MIN_VALUE);
        sentinel.next = head;

        Node<Integer> slow = sentinel;
        Node<Integer> fast = sentinel.next;

        while (n > 0) {
            fast = fast.next;
            n--;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        head = sentinel.next;


        return head;
    }

    // Doesn't work for deleting the second element
    public static Node<Integer> removeNthLastNodeV0(Node<Integer> head, int n) {
        Node<Integer> first = head;
        Node<Integer> second = head;
        for (int i = 0; i < n && second.next != null; i++) {
             second = second.next;
        }

        while (second.next != null) {
            first = first.next;
            second = second.next;
        }
        if (first == head) {
            head = head.next;
            return head;
        }

        first.next = first.next.next;

        return head;
    }
    public static void main(String[] args) {
        LinkedList<Integer> l = LinkedLists.getNewLinkedList(9);
        l.print();
        Node<Integer> head = RemoveNthFromEnd.removeNthLastNode(l.getHead(), 9);
        l.print();
    }
}
