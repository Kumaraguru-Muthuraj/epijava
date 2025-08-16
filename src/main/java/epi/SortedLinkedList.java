package epi;


import java.util.Comparator;

public class SortedLinkedList<T> extends LinkedList<T> {
    public SortedLinkedList(Comparator<T> c) {
        this.comparator = c;
    }
    public Node<T> add(T val) {
        if (head == null) {
            head = new Node<>(val);
            tail = head;
            return head;
        }
        Node<T> cur = head, prev = head;
        while (cur != null && comparator.compare(val, cur.value) > 0) {
            prev = cur;
            cur = cur.next;
        }
        Node<T> node = new Node<>(val);
        if (cur == head) {
            node.next = head;
            head = node;
        } else {
            node.next = cur;
            prev.next = node;
            if (tail.next == node) {
                tail = node;
            }
        }
        return node;
    }

    private Comparator<T> comparator;
}