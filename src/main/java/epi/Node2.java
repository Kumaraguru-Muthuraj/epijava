package epi;

import java.util.Objects;

public class Node2 {
    public Node2(int value) {
        this.value = value;
        left = null;
        right = null;
        parent = null;
        rightSibling = null;
        count = 1;
    }
    public Integer value;
    public Node2 left;
    public Node2 right;
    public Node2 parent;
    public Node2 rightSibling;
    public Integer count;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node2)) return false;
        Node2 node = (Node2) o;
        return value.equals(node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
