package epi;

public class Node<T> {
    public Node(T val) {
        this.value = val;
        this.next = null;
    }
    
    public T value;
    public Node<T> next;
}