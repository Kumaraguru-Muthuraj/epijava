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

    /* 15.5 - Build BST from traversal data. Only preorder traversed data can help.
    *** NOTE: DON'T REMOVE THE ELEMENTS, IF YOU DO THEN THE RECURSION WILL LOOSE THE ELEMENTS TO ADD.
    * USE ONLY INDEX TO SCROLL.
    * The reason this algorithm works is for pre order 78, 50, 25, 65, 60, 100, 90, 80, 110
    * When a number is touched, we check by min > rootVal || rootVal > max to see if it can be a
    * child based on the constraint passed by previous node. If it fails, we unwind and since index still
    * is fixed, we are able to maintain the check.
     */
    private static int rootIdx = 0;
    public static void testGetBSTFromPreorder() {
        BinarySearchTree b = BinaryTrees.getCustomBST();
        b.printPreorder();
        Queue<Node2> nList = b.getPreorder(b.root);
        List<Integer> iLst = b.getAsList(nList);
        Node2 tree = getBSTFromPreorder(iLst, Integer.MIN_VALUE, Integer.MAX_VALUE);
        b._print(tree);
    }
    public static Node2 getBSTFromPreorder(List<Integer> lst, Integer min, Integer max) {
        if (rootIdx == lst.size() - 1) {
            return null;
        }
        Integer rootVal = lst.get(rootIdx);
        if (min > rootVal || rootVal > max) {
            return null;
        }
        rootIdx++; // This is critical.
        Node2 root = new Node2(rootVal);
        root.left = getBSTFromPreorder(lst, min, root.value);
        root.right = getBSTFromPreorder(lst, root.value, max);

        return root;
    }

    /* 15.6 - Closest entries in 3 sorted arrays.
     */
    static class ArrayInteger implements Comparable<ArrayInteger> {
        public ArrayInteger(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
        Integer val;
        Integer idx;

        @Override
        public int compareTo(ArrayInteger o) {
            return val.compareTo(o.val);
        }
    }

    public static void testClosestEntries() {
        List<ArrayInteger> l1 = getSortedList(7, 0);
        List<ArrayInteger> l2 = getSortedList(5, 1);
        List<ArrayInteger> l3 = getSortedList(9, 2);

        LinkedList<List<ArrayInteger>> lists = new LinkedList<>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);

        List<ArrayInteger> entries = closestEntries(lists);
        System.out.print("\nEntries - ");
        for (ArrayInteger i : entries) {
            System.out.print(i.val + ", ");
        }
    }
    public static List<ArrayInteger> closestEntries(List<List<ArrayInteger>> lists) {
        Comparator<ArrayInteger> cmp = new Comparator<>() {
            @Override
            public int compare(ArrayInteger o1, ArrayInteger o2) {
                return o1.compareTo(o2);
            }
        };
        PriorityQueue<ArrayInteger> heap = new PriorityQueue<>(cmp);

        //Add first 3 elements from each list.
        for (List<ArrayInteger> list : lists) {
            heap.add(list.remove(0));
        }

        while (true) {
            ArrayInteger small = heap.peek();
            Integer arrayIdx = small.idx;
            List<ArrayInteger> list = lists.get(arrayIdx);
            if (list.isEmpty()) {
                break;
            }
            heap.poll();
            ArrayInteger nextSmall = list.remove(0);
            heap.add(nextSmall);
        }

        LinkedList<ArrayInteger> a = new LinkedList<>(heap);
        return a;
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

    public static List<ArrayInteger> getSortedList(int k, int index) {
        List<Integer> l = new LinkedList<>();
        Random r = new Random();
        for (int i = 0; i < k; i++) {
            l.add(r.nextInt(100));
        }
        Collections.sort(l);

        List<ArrayInteger> al = new LinkedList<>();
        System.out.println();
        for (Integer i : l) {
            al.add(new ArrayInteger(i, index));
            System.out.print(i + ", ");
        }
        return al;
    }


    public static void main(String[] args) {
        testClosestEntries();
        if (true) {
            return;
        }

        //15.5 - testGetBSTFromPreorder
        testGetBSTFromPreorder();

        //15.1 - testBSTProperty
        testBST3Property();
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
