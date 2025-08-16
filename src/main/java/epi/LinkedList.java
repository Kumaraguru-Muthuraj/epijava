package epi;


public class LinkedList<T> {
    public LinkedList() {}
    public Node<T> add(T val) {
        if (head == null) {
            head = new Node<>(val);
            return head;
        }
        Node<T> cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        Node<T> node = new Node<>(val);
        cur.next = node;
        return node;
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
}