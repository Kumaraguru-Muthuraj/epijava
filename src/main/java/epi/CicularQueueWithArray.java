package epi;

import java.util.List;

public class CicularQueueWithArray {
    public CicularQueueWithArray(int capacity) {
        array = new Integer[capacity];
        numElements = 0;
        head = 0;
        tail = 0;
    }

    public void add(int n) {
        if (numElements >= array.length) {
            List<Integer> old = List.of(array);
            array = new Integer[array.length * 2];
            for (int idx = 0; idx < numElements; idx++) {
                array[idx] = old.get((head + idx) % array.length);
            }
            head = 0;
            tail = numElements;
        }
        tail = (tail + 1) % array.length;
        array[tail] = n;
        ++numElements;
    }

    public Integer poll() throws Exception {
        if (numElements > 0) {
            --numElements;
            Integer ret = array[head];
            head = (head + 1) % array.length;
            return ret;
        }
        throw new Exception("Empty queue");
    }

    private Integer[] array;
    private int numElements;
    private int head;
    private int tail;
}
