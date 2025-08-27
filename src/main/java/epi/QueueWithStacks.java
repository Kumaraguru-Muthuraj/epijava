package epi;

import java.util.Stack;

/* At every call, say add and poll, you can list the elements and
what should be held in aux to demonstrate why this would work.
 */
public class QueueWithStacks {
    public QueueWithStacks() {
        this.addStack = new Stack<>();
        this.pollStack = new Stack<>();
    }
    public void add(int i) {
        addStack.add(i);
    }
    public Integer poll() {
        if (pollStack.isEmpty()) {
            while (!addStack.isEmpty()) {
                Integer popped = addStack.pop();
                pollStack.push(popped);
            }
        }
        if (!pollStack.isEmpty()) {
            return pollStack.pop();
        }
        return null;
    }

    private Stack<Integer> addStack;
    private Stack<Integer> pollStack;
}
