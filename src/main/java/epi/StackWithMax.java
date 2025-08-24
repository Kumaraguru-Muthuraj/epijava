package epi;

import java.util.Iterator;
import java.util.Stack;

public class StackWithMax {
    public StackWithMax(){
        this.stack = new Stack<>();
        this.aux = new Stack<>();
    }
    public void push(Integer i) {
        stack.push(i);
        if (aux.isEmpty()) {
            Tuple t = new Tuple();
            t.value = i;
            t.count = 1;
            aux.push(t);
            return;
        }

        Tuple curMax = aux.peek();
        if (curMax.value == i) {
            curMax.count++;
        } else if (curMax.value < i) {
            Tuple t = new Tuple();
            t.value = i;
            t.count = 1;
            aux.push(t);
        }
    }
    public Integer pop() {
        if (!stack.isEmpty()) {
            Integer retVal = stack.pop();
            Tuple curMax = aux.peek();
            if (retVal == curMax.value) {
                --curMax.count;
                if (curMax.count == 0) {
                    aux.pop();
                }
            }
            return retVal;
        }
        return null;
    }
    public Integer peek() {
        return stack.peek();
    }
    public Integer max() {
        return aux.peek() != null ? aux.peek().value : Integer.MIN_VALUE;
    }
    public boolean isEmpty() {
        return stack.isEmpty();
    }
    public void print() {
        System.out.print("Stack: ");
        for (Integer integer : stack) {
            System.out.print(integer + ", ");
        }
        System.out.print("\nMax: ");
        for (Tuple t : aux) {
            System.out.print(t.value + "-" + t.count + ", ");
        }
    }


    private Stack<Integer> stack;
    private Stack<Tuple> aux;
}

class Tuple{
    int value;
    int count;
}