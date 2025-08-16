package epi;


import java.util.Comparator;

public class LinkedList<T> {
    public LinkedList(Comparator<T> c) {
        this.comparator = c;
    }
    public Node<T> add(T val) {
        if (head == null) {
            head = new Node<>(val);
            tail = head;
            return head;
        }
        Node<T> cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        Node<T> node = new Node<>(val);
        cur.next = node;
        tail = node;
        return node;
    }
    public boolean delete(T val) {
        Node<T> cur = head, prev = head;
        while (cur != null) {
            if (this.comparator.compare(val, cur.value) == 0) {
                if (head == tail) {
                    head = null;
                    tail = null;
                } else if (cur == head) {
                    head = cur.next;
                } else if (cur == tail) {
                    tail = prev;
                    prev.next = null;
                } else {
                    prev.next = cur.next;
                }
                return true;
            }
            prev = cur;
            cur = cur.next;
        }

        return false;
    }

    public Node<T> getHead() {
        return head;
    }
    public Node<T> getTail() {
        return tail;
    }
    public void print() {
        for (Node<T> cur = head; cur != null; cur = cur.next) {
            System.out.print(cur.value + ", ");
        }
    }

    protected Node<T> head;
    protected Node<T> tail;
    protected Comparator<T> comparator;
}