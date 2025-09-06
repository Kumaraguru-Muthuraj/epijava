package epi;

public class Node2 {
    public Node2(int value) {
        this.value = value;
        left = null;
        right = null;
        parent = null;
        count = 1;
    }
    public Integer value;
    public Node2 left;
    public Node2 right;
    public Node2 parent;
    public Integer count;
}
