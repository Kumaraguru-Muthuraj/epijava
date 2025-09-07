package epi;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BinarySearchTrees {
    /* 15.1 - Validate BST property.
    There are 3 versions to this problem.
    *** PRACTISE ALL THE 3 VERSIONS. ***
     */
    private static Integer prev;
    public static void testBSTProperty() {
        prev = Integer.MIN_VALUE;
        BinarySearchTree b = BinaryTrees.getInvalidBST();
        System.out.println();
        System.out.println(testBST(b.root));
    }
    public static boolean testBST(Node2 cur) {
        if (cur != null) {
            if (!testBST(cur.left)) {
                return false;
            }
            boolean valid = false;
            if (prev <= cur.value) {
                System.out.println(prev + "-" + cur.value + ", ");
                valid = true;
                prev = cur.value;
            }
            return valid && testBST(cur.right);
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
        testBSTProperty();

        if (true) {
            return;
        }

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
