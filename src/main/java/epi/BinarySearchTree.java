package epi;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTree {
    public BinarySearchTree() {
        root = null;
    }
    public Node2 add(Integer i) {
        if (root == null) {
            root = new Node2(i);
            return root;
        }
        Node2 parent = root;
        Node2 cur = root;
        while (cur != null) {
            parent = cur;
            cur.count++;
            if (i < cur.value) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        Node2 n = new Node2(i);
        if (i < parent.value) {
            parent.left = n;
        } else {
            parent.right = n;
        }
        n.parent = parent;
        return n;
    }

    public Integer height(Node2 root) {
        if (root != null) {
            Integer lH = height(root.left);
            Integer rH = height(root.right);
            return 1 + Math.max(lH, rH);
        }
        return -1;
    }

    public void print() {
        System.out.println("\nInorder");
        _print(this.root);
    }

    public void _print(Node2 cur) {
        if (cur != null) {
            _print(cur.left);
            System.out.print(cur.value + ", ");
            _print(cur.right);
        }
    }

    public void printPreorder() {
        System.out.println("\nPreorder");
        _printPreorder(this.root);
    }

    public void _printPreorder(Node2 cur) {
        Queue<Node2> pre = getPreorder(cur);
        for (Node2 n : pre) {
            System.out.print(n.value + ", ");
        }
    }

    public Queue<Node2> getPreorder(Node2 cur) {
        Queue<Node2> preOrder = new LinkedList<>();
        getPreorderHelper(cur, preOrder);
        return preOrder;
    }
    private void getPreorderHelper(Node2 cur, Queue<Node2> preOrder) {
        if (cur != null) {
            preOrder.add(cur);
            getPreorderHelper(cur.left, preOrder);
            getPreorderHelper(cur.right, preOrder);
        }
    }


    public List<Node2> getLeavesAsList() {
        leaves = new LinkedList<>();
        getLeaves(root);
        return leaves;
    }
    public void getLeaves(Node2 cur) {
        if (cur != null) {
            if (cur.left == null && cur.right == null) {
                leaves.add(cur);
            }
            getLeaves(cur.left);
            getLeaves(cur.right);
        }
    }
    public List<Node2> leaves;

    public Node2 root;
}
