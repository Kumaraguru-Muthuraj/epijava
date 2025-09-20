package epi;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTree {
    public BinarySearchTree() {
        root = null;
    }
    public Node2 getNodeFor(Integer i) {
        if (root == null) {
            return root;
        }
        Node2 cur = root;
        while (cur != null) {
            if (cur.value.equals(i))
                return cur;
            cur = cur.value < i ? cur.right : cur.left;
        }
        return cur;
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

    public boolean delete(Integer i) {
        //Find the node.
        Node2 parent = null;
        Node2 delNode = this.root;
        while (delNode != null) {
            /* Don't move parent pointer here.
            If you move and if delNode exists, both parent
            and delNode will point to the same, causing
            issues in the actual deletion block.
            */
            if (i.equals(delNode.value)) {
                break;
            }
            parent = delNode;
            if (i < delNode.value) {
                delNode = delNode.left;
            } else {
                delNode = delNode.right;
            }
        }
        //Return if not exists.
        if (delNode == null) {
            return false;
        }
        //3 Cases - cur is leaf, cur has 1 child, cur has 2 children.
        // Case3 - 2 Children -> It doesn't matter if left child is null.
        if (delNode.right != null) {
            //This covers for root node too.
            /*Get successor. Need to track parent because rNode.left can be null.
            Handled in Marker1
             */
            Node2 rNParent = delNode;
            Node2 rNode = delNode.right;
            while (rNode.left != null) {
                rNParent = rNode;
                rNode = rNode.left;
            }
            delNode.value = rNode.value;
            if (rNParent.left == rNode) {
                rNParent.left = rNode.right;
            } else {
                // Marker1
                rNParent.right = rNode.right;
            }
            //To prevent memory leak / technically complete and correct.
            rNode.right = null;
        } else {
            //Root node
            if (delNode == root) {
                root = root.left;
            } else {
                //Case2 - Right child is null. delNode could be right or left child of its parent.
                //This also covers Leaf node - Case1.
                if (parent.left == delNode) {
                    parent.left = delNode.left;
                } else {
                    parent.right = delNode.left;
                }
                delNode.left = null;
            }
        }

        //Case 1 - Leaf
        if (delNode.left == null && delNode.right == null) {
            if (delNode == parent.left) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }


        return true;
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
        _printInorder(this.root);
    }

    public void _printInorder(Node2 cur) {
        Queue<Node2> pre = getInorder(cur);
        for (Node2 n : pre) {
            System.out.print(n.value + ", ");
        }
    }

    public Queue<Node2> getInorder(Node2 cur) {
        Queue<Node2> inOrder = new LinkedList<>();
        getInorderHelper(cur, inOrder);
        return inOrder;
    }
    private void getInorderHelper(Node2 cur, Queue<Node2> inOrder) {
        if (cur != null) {
            getInorderHelper(cur.left, inOrder);
            inOrder.add(cur);
            getInorderHelper(cur.right, inOrder);
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

    public Queue<Node2> getPreorderWithMarkers(Node2 cur) {
        Queue<Node2> preOrder = new LinkedList<>();
        getPreorderHelperWithMarkers(cur, preOrder);
        return preOrder;
    }
    private void getPreorderHelperWithMarkers(Node2 cur, Queue<Node2> preOrder) {
        if (cur == null) {
            preOrder.add(null);
        } else {
            preOrder.add(cur);
            getPreorderHelperWithMarkers(cur.left, preOrder);
            getPreorderHelperWithMarkers(cur.right, preOrder);
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

    public List<Integer> getAsList(Queue<Node2> nodes) {
        List<Integer> q = new LinkedList<>();
        for (Node2 n : nodes) {
            if (n == null) {
                q.add(null);
            } else {
                q.add(n.value);
            }
        }
        return q;
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

    public void levelOrderPrint() {
        Queue<Node2> q = new LinkedList<>();
        Node2 cur = root;
        q.add(cur);
        System.out.println("\nLevel-order print");
        while (!q.isEmpty()) {
            cur = q.poll();
            System.out.print(cur.value + ", ");
            if (cur.left != null) {
                q.add(cur.left);
            }
            if (cur.right != null) {
                q.add(cur.right);
            }
        }
    }

    public void traverseSibling() {
        Node2 leftMost = root;
        System.out.println("\nSibling traversal by level");
        while (leftMost != null) {
            Node2 sibling = leftMost;
            while (sibling != null) {
                System.out.print(sibling.value + ", ");
                sibling = sibling.rightSibling;
            }
            leftMost = leftMost.left;
        }
    }

    /* 15.10
    Note that you CANNOT DO a level-order traversing and
    set the sibling, without the level or depth.
    Need to use the recommendation in the book.
    This algorithm works for ONLY Perfect binary tree.
    */
    public void setRightSibling() {
        Node2 cur = root;
        while (cur != null) {
            _setRightSibling(cur);
            cur = cur.left;
        }
    }
    private void _setRightSibling(Node2 leftMostNode) {
        Node2 cur = leftMostNode; // Don't iterate directly, assign to cur and move.
        while (cur != null) {
            if (cur.left != null) {
                cur.left.rightSibling = cur.right;
            }
            if (cur.rightSibling != null && cur.right != null) {
                cur.right.rightSibling = cur.rightSibling.left;
            }
            cur = cur.rightSibling;
        }
    }

    public Node2 root;
}
