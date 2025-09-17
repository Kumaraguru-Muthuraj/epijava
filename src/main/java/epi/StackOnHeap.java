package epi;

import java.util.Comparator;
import java.util.PriorityQueue;

public class StackOnHeap {
    public StackOnHeap() {
        comp = new Comparator<IntegerWithTS>() {
            @Override
            public int compare(IntegerWithTS o1, IntegerWithTS o2) {
                return o2.timeStamp.compareTo(o1.timeStamp);
            }
        };
        heap = new PriorityQueue<>(16, comp);
        timeStamp = 0;
    }
    public void push(Integer k) {
        heap.add(new IntegerWithTS(k, timeStamp++));
    }
    public Integer pop() {
        return heap.isEmpty() ? null : heap.poll().value;
    }
    public Integer peek() {
        return heap.isEmpty() ? null : heap.peek().value;
    }
    private PriorityQueue<IntegerWithTS> heap;
    private Comparator<IntegerWithTS> comp;
    private int timeStamp;
}

class IntegerWithTS {
    public IntegerWithTS(Integer k, Integer ts) {
        value = k;
        timeStamp = ts;
    }
    public Integer value;
    public Integer timeStamp;
}