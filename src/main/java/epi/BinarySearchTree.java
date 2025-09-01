package epi;

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
        return n;
    }

    public Integer height(Node2 root) {
        if (root != null) {
            Integer lH = 1 + height(root.left);
            Integer rH = 1 + height(root.right);
            return Math.max(lH, rH);
        }
        return -1;
    }

    public void print() {
        System.out.println();
        _print(this.root);
    }

    private void _print(Node2 cur) {
        if (cur != null) {
            _print(cur.left);
            System.out.print(cur.value + ", ");
            _print(cur.right);
        }
    }

    public Node2 root;
}
