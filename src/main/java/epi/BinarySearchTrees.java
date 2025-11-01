package epi;

import com.sun.source.tree.BinaryTree;
import epi.util.ABSqrt2;
import epi.util.ListUtil;

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
        b._printInorder(tree);
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

    /* 15.7 - Generate k entries of the form a + b * sqrt(2)
    log k insertions x 2
    log k remove
    This is done for k elements.
    T.C - O(k.log(k)), S.C - O(k)
     */
    public static void testGenerateKEntriesOfaplusbsqrt2_V1(int k) {
        List<ABSqrt2> result = new LinkedList<>();
        TreeSet<ABSqrt2> treeSet = new TreeSet<>();

        ABSqrt2 a = new ABSqrt2(0, 0);
        treeSet.add(a);

        while (k-- > 0) {
            ABSqrt2 first = treeSet.first();
            result.add(first);

            treeSet.add(new ABSqrt2(first.a + 1, first.b));
            treeSet.add(new ABSqrt2(first.a, first.b + 1));

            treeSet.remove(first);
            System.out.println(treeSet.size());
        }
        for (ABSqrt2 e : result) {
            System.out.println(e.toString());
        }
        System.out.println("Tree");
        for (ABSqrt2 e: treeSet) {
            System.out.println(e);
        }

    }

    public static void testGenerateKEntriesOfaplusbsqrt2_V2(int k) {
        List<ABSqrt2> result = new ArrayList<>();
        ABSqrt2 e = new ABSqrt2(0, 0);
        result.add(e);

        int i = 0;
        int j = 0;

        while (k-- > 0) {
            ABSqrt2 iPlus1 = new ABSqrt2(result.get(i).a + 1, result.get(i).b);
            ABSqrt2 jPlusSqrt2 = new ABSqrt2(result.get(j).a, result.get(j).b + 1);

            if (iPlus1.compareTo(jPlusSqrt2) < 0) {
                result.add(iPlus1);
                ++i;
            } else if (iPlus1.compareTo(jPlusSqrt2) > 0) {
                result.add(jPlusSqrt2);
                ++j;
            } else {
                result.add(iPlus1);
                ++i;
                ++j;
            }
        }

        for (ABSqrt2 el : result) {
            System.out.println(el);
        }

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

    /* 15.10 - Add and Delete in a BST.
    * */
    public static void testAddAndDelete() {
        BinarySearchTree bst = BinaryTrees.getCustomBST();
        bst.print();
        bst.delete(78);
        bst.print();
    }

    /* 15.11 - Check if nodes are ordered.
     */
    public static void testNodesOrdered(){
        BinarySearchTree bst = BinaryTrees.getPerfectBST();
        Node2 l = bst.getNodeFor(78);
        Node2 m = bst.getNodeFor(90);
        Node2 r = bst.getNodeFor(80);
        System.out.println("\nNodes ordered - " + pairIncludesAncAndDescOfM(l, r, m));
    }
    public static boolean pairIncludesAncAndDescOfM(Node2 pAOrD0, Node2 pAOrD1, Node2 m) {
        Node2 s0 = pAOrD0;
        Node2 s1 = pAOrD1;
        while (s0 != pAOrD1 && s0 != m
            && s1 != pAOrD0 && s1 != m
            && (s0 != null || s1 != null)) {
            if (s0 != null) {
                s0 = s0.value > m.value ? s0.left : s0.right;
            }
            if (s1 != null) {
                s1 = s1.value > m.value ? s1.left : s1.right;
            }
        }
        if (s0 == pAOrD1 || s1 == pAOrD0 || (s0 != m && s1 != m)) {
            return false;
        }
        return s0 == m ? searchTarget(m, pAOrD1) : searchTarget(m, pAOrD0);

    }
    public static boolean searchTarget(Node2 from, Node2 target) {
        while (from != null && from != target) {
            from = from.value > target.value ? from.left : from.right;
        }
        return from == target;
    }

    /* 15.12 - Get nodes in a range.
     */
    public static void testNodesInRange() {
        BinarySearchTree bst = BinaryTrees.getCustomBST();
        List<Integer> nodes = new LinkedList<>();
        getNodesInRange(nodes, bst.root, 25, 60);
        ListUtil.print(nodes);
    }
    public static void getNodesInRange(List<Integer> nodes, Node2 root, int s, int b) {
        if (root != null) {
            if (root.value < s) {
                getNodesInRange(nodes, root.right, s, b);
            } else if (b < root.value) {
                getNodesInRange(nodes, root.left, s, b);
            } else if (s <= root.value && root.value <= b) {
                getNodesInRange(nodes, root.left, s, b);
                nodes.add(root.value);
                getNodesInRange(nodes, root.right, s, b);
            }
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
        //15.7
        testGenerateKEntriesOfaplusbsqrt2_V2(10);

        if (true) {
            return;
        }

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

        //15.5 - testGetBSTFromPreorder
        testGetBSTFromPreorder();

        //15.6
        testClosestEntries();

        //15.9 - minimumHeightBST
        minimumHeightBST(11);

        //15.10
        testAddAndDelete();

        //15.11 - testNodesOrdered
        testNodesOrdered();

        //15.12 - testNodesInRange
        testNodesInRange();

    }

}
