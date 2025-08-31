package epi;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class QueueWithMax {
    public QueueWithMax(){
        this.queue = new LinkedList<>();
        this.aux = new Stack<>();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /* If we could store the numbers in descending order in the aux,
    we are good.
    */
    /* T.C - O(1) because we evict elements in the aux. Evicting n elements in aux happens once a while.
    S.C - O(n)
     */
    public void add(Integer i) {
        queue.add(i);
        while (!aux.isEmpty() && aux.peek() < i) {
            aux.pop();
        }
        aux.push(i);
    }

    /* T.C - O(1).
    S.C - O(n)
     */
    public Integer poll() {
        if (aux.get(0).equals(queue.peek())) {
            aux.remove(0);
        }
        return queue.poll();
    }

    public Integer max() {
        return aux.get(0);
    }

    public void print() {
        System.out.print("\nQueue: ");
        for (Integer integer : queue) {
            System.out.print(integer + ", ");
        }
        System.out.print("; Max: ");
        for (Integer integer : aux) {
            System.out.print(integer + ", ");
        }
    }

    private Queue<Integer> queue;
    private Stack<Integer> aux;
}
