package epi;

import java.util.*;
import java.util.Arrays;
import java.util.LinkedList;

public class BinarySearchTrees {
    /* 15.1 - Validate BST property.
    There are 3 versions to this problem.
    *** PRACTISE ALL THE 3 VERSIONS. ***
     */
    private static Integer prev;
    public static void testBST0Property() {
        prev = Integer.MIN_VALUE;
        BinarySearchTree b = BinaryTrees.getInvalidBST();
        System.out.println();
        System.out.println(testBST0(b.root));
    }
    public static boolean testBST0(Node2 cur) {
        if (cur != null) {
            if (!testBST0(cur.left)) {
                return false;
            }
            boolean valid = false;
            if (prev <= cur.value) {
                System.out.println(prev + "-" + cur.value + ", ");
                valid = true;
                prev = cur.value;
            }
            return valid && testBST0(cur.right);
        }
        return true;
    }
    public static void testBST1Property() {
        BinarySearchTree b = BinaryTrees.getInvalidBST();
        System.out.println();
        System.out.println(testBST1(b.root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }
    public static boolean testBST1(Node2 cur, Integer min, Integer max) {
        if (cur != null) {
            if (min <= cur.value && cur.value <= max) {
                if (!testBST1(cur.left, min, cur.value)) {
                    return false;
                }
                return testBST1(cur.right, cur.value, max);
            } else {
                return false;
            }
        }
        return true;
    }
    static class NodeWithRange {
        public NodeWithRange(Node2 n, Integer min, Integer max) {
            this.node = n;
            this.min = min;
            this.max = max;
        }
        Node2 node;
        Integer min, max;
    }
    public static void testBST3Property() {
        BinarySearchTree b = BinaryTrees.getInvalidBST();
        System.out.println();
        Queue<NodeWithRange> q = new LinkedList<>();
        q.add(new NodeWithRange(b.root, Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println(testBST3(q));
    }
    public static boolean testBST3(Queue<NodeWithRange> q) {
        //This is with BFS
        while (!q.isEmpty()) {
            NodeWithRange head = q.poll();
            Node2 node = head.node;
            Integer min = head.min;
            Integer max = head.max;
            if (min <= node.value && node.value <= max) {
                if (node.left != null) {
                    q.add(new NodeWithRange(node.left, min, node.value));
                }
                if (node.right != null) {
                    q.add(new NodeWithRange(node.right, node.value, max));
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /* 15.2 - First Key greater than k.
     */
    public static void testGreaterThanK(int k) {
        BinarySearchTree b = BinaryTrees.getCustomBST();
        Node2 n = firstGreaterThanK(b, k);
        System.out.println("First greater than " + k + " - " + n.value);
    }
    public static Node2 firstGreaterThanK(BinarySearchTree b, int k) {
        Node2 cur = b.root;
        Node2 soFar = null;
        while (cur != null) {
            if (cur.value > k) {
                soFar = cur;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return soFar;
    }

    /* 15.3 - K largest elements in BST. Reverse inorder traversal.
     */
    public static void testKLargestElements(int k) {
        BinarySearchTree b = BinaryTrees.getCustomBST();
        List<Node2> l = new LinkedList<>();
        kLargestElements(l, b.root, k);
        System.out.println(k + " - largest elements");
        for (Node2 n : l) {
            System.out.println(n.value + ", ");
        }
    }
    public static void kLargestElements(List<Node2> kNodes, Node2 cur, int k) {
        if (cur != null && kNodes.size() < k) {
            kLargestElements(kNodes, cur.right, k);
            if (kNodes.size() < k) {
                // We prevent the calls to left node too if the target k size is achieved.
                kNodes.add(cur);
                kLargestElements(kNodes, cur.left, k);
            }
        }
    }

    /* 15.4 - Compute LCA for BST
     */
    public static void testLCA(int s, int b) {
        // s < t
        BinarySearchTree bst = BinaryTrees.getCustomBST();
        Node2 lca = getLCA(bst.root, s, b);
    }
    public static Node2 getLCA(Node2 cur, int s, int b) {
        Node2 parent = null;
        while (cur != null && (cur.value < s || b < cur.value)) {
            while (b < cur.value) {
                parent = cur;
                cur = cur.left;
            }
            while (cur.value < s) {
                parent = cur;
                cur = cur.right;
            }
        }
        //Check if s & b exist in the respective subtrees. NOT DONE.
        if (s == b) {
            return parent;
        }
        if (cur != null && (s <= cur.value && cur.value <= b)) {
            return cur;
        }
        return null;
    }

    /* 15.9 - Minimum height BST from a sorted array.
     */
    public static BinarySearchTree minimumHeightBST(int n) {
        Random r = new Random();
        Integer[] lst = new Integer[n];
        for (int i = 0; i < n; i++) {
            lst[i] = r.nextInt(500);
        }
        Arrays.sort(lst);

        BinarySearchTree b = new BinarySearchTree();
        minHeightBSTBuilder(b, lst, 0, lst.length - 1);
        b.print();
        return b;
    }
    public static void minHeightBSTBuilder(BinarySearchTree b, Integer[] lst, int minIdx, int maxIdx) {
        if (minIdx <= maxIdx) {
            int nextRootIdx = minIdx + (maxIdx - minIdx) / 2;
            Integer nextRoot = lst[nextRootIdx];
            b.add(nextRoot);
            minHeightBSTBuilder(b, lst, minIdx, nextRootIdx - 1);
            minHeightBSTBuilder(b, lst, nextRootIdx + 1, maxIdx);
        }
    }


    public static void main(String[] args) {
        //15.1 - testBSTProperty
        testBST3Property();

        if (true) {
            return;
        }
        testBST0Property();
        testBST1Property();

        //15.3 - K largest elements in BST.
        testKLargestElements(7);

        //15.2 - first after value k
        testGreaterThanK(111);

        //15.4 - LCA
        testLCA(60, 60);

        //15.9 - minimumHeightBST
        minimumHeightBST(11);
    }

}
