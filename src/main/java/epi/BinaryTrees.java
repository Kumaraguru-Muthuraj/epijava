package epi;

import java.util.*;
import java.util.LinkedList;

import static epi.Arrays.print;

/* Level starts from 0. Height = the deepest level.
Check the book for the diagram in the first page.
 */
public class BinaryTrees {
    /* 10.1 - Balanced check? Every node should be balanced.
     */
    public static void testHeightCheck() {
        BinarySearchTree t = getCustomBST(); //getBST(10);
        System.out.println("\nHeight - " + t.height(t.root));
        System.out.println(("Balanced check - " + balancedCheck(t.root).balanced));
    }

    /* 10.2 - Check if tree is symmetric.
     */
    public static void testSymmetric() {
        BinarySearchTree t = getSymmetricTree();
        System.out.println(symmetric(t.root));
    }

    public static boolean symmetric(Node2 root) {
        if (root != null) {
            return checkSymmetry(root.left, root.right);
        }
        return true;
    }
    public static boolean checkSymmetry(Node2 tree0, Node2 tree1) {
        if (tree0 != null && tree1 != null) {
            boolean dataMatch = tree0.value.equals(tree1.value);
            if (!dataMatch)
                return false;
            boolean outerSymm = checkSymmetry(tree0.left, tree1.right);
            if (!outerSymm)
                return false;
            return checkSymmetry(tree0.right, tree1.left);
        }
        return true;
    }
    public static BinarySearchTree getSymmetricTree() {
        BinarySearchTree t = getCustomBST();
        t.print();
        Node2 mT = getMirrorTree(t.root);
        t._printInorder(mT);
        BinarySearchTree symm = new BinarySearchTree();
        symm.add(10000);
        symm.root.left = t.root;
        symm.root.right = mT;
        return symm;
    }

    public static Node2 getMirrorTree(Node2 root) {
        Node2 nRoot = null;
        if (root != null) {
            nRoot = new Node2(root.value);
            nRoot.left = getMirrorTree(root.right);
            nRoot.right = getMirrorTree(root.left);
        }
        return nRoot;
    }

    /* This works because when null, we return (-1, true)
    and when this goes up, a simple test yields right values.
     */
    public static TreeBalancedStatus balancedCheck(Node2 root) {
        if (root == null) {
            return new TreeBalancedStatus(-1, true);
        }
        TreeBalancedStatus lB = balancedCheck(root.left);
        if (!lB.balanced)
            return lB;
        TreeBalancedStatus rB = balancedCheck(root.right);
        if (!rB.balanced)
            return rB;
        int height = Math.max(lB.height, rB.height) + 1;
        boolean balanced = Math.abs(lB.height - rB.height) <= 1;
        return new TreeBalancedStatus(height, balanced);
    }

    /* 10.4 - LCA when nodes have parent pointers.
     */
    public static Node2 lcaWithParent() {
        BinarySearchTree b = getBST(15); //getCustomBST();
        System.out.println();
        List<Node2> nodes = get2RandomNodes(b.root);
        Node2 n0 = nodes.get(0);
        Node2 n1 = nodes.get(1);
        int n0Cnt = 0;
        int n1Cnt = 0;
        while (n0 != b.root) {
            n0 = n0.parent;
            n0Cnt++;
        }
        while (n1 != b.root) {
            n1 = n1.parent;
            n1Cnt++;
        }

        int offSet = Math.abs(n0Cnt - n1Cnt);
        n0 = nodes.get(0);
        n1 = nodes.get(1);
        if (n0Cnt < n1Cnt) {
            while(offSet > 0) {
                n1 = n1.parent;
                offSet--;
            }
        } else if (n1Cnt < n0Cnt) {
            while(offSet > 0) {
                n0 = n0.parent;
                offSet--;
            }
        }

        while (n0 != b.root && n0 != n1) {
            n0 = n0.parent;
            n1 = n1.parent;
        }
        return n0;
    }

    public static List<Node2> get2RandomNodes(Node2 root) {
        Random r = new Random();
        List<Node2> nodes = new LinkedList<>();
        get2RandomNodes(r, nodes, root, root);
        return nodes;
    }

    public static void get2RandomNodes(Random r, List<Node2> nodes, Node2 cur, Node2 root) {
        if (cur != null && nodes.size() < 2) {
            if (r.nextBoolean() && cur != root) {
                nodes.add(cur);
            }
            get2RandomNodes(r, nodes, cur.left, root);
            get2RandomNodes(r, nodes, cur.right, root);
        }
    }
    public static void get2Nodes(List<Node2> nodes, Node2 root, int val1, int val2) {
        nodes.add(getNode(root, val1));
        nodes.add(getNode(root, val2));
    }
    public static Node2 getNode(Node2 root, int val) {
        if (root != null) {
            if (root.value == val) {
                return root;
            }
            Node2 vNode = getNode(root.left, val);
            if (vNode == null) {
                vNode = getNode (root.right, val);
            }
            return vNode;
        }
        return null;
    }

    /* 10.5 - Sum of all <root to leaf> that is binary.
    **** SUPER TRICKY ****
     */
    public static void computeSumsFromRootToLeaf() {
        BinarySearchTree b = getCustomBSTWith01();
        b.print();
        System.out.println(computeSumsBinary(0, b.root));
    }
    public static Integer computeSumsBinary(Integer partialSum, Node2 cur) {
        if (cur == null) {
            return 0;
        }
        partialSum = partialSum * 2 + cur.value;
        if (cur.left == null && cur.right == null) {
            return partialSum;
        }
        Integer l1S = computeSumsBinary(partialSum, cur.left);
        Integer l2S = computeSumsBinary(partialSum, cur.right);
        partialSum = l1S + l2S;
        return partialSum;
    }

    /* 10.6 - Target Sum from root to LEAF****
    Variant, what if the sum is till any node?
     **** SUPER TRICKY ****
     */
    public static void targetSumExistsTest() {
        System.out.println(targetSumExists(280));
    }
    public static boolean targetSumExists(Integer target) {
        BinarySearchTree b = getCustomBST();
        b.print();
        return targetSumExists(0, target, b.root);
    }
    public static boolean targetSumExists(Integer partialSum, Integer target, Node2 cur) {
        if (cur == null) {
            return false;
        }
        partialSum += cur.value;
        if (cur.left == null && cur.right == null) {
            return partialSum.equals(target);
        }

        boolean lExists = targetSumExists (partialSum, target, cur.left);
        boolean rExists = targetSumExists (partialSum, target, cur.right);
        return lExists || rExists;
    }

    /* 10.10 - Inorder successor
     */
    //Parent pointer available.
    public static void testInorderSuccessorv1() {
        BinarySearchTree bst = getCustomBST();
        List<Node2> node2s = get2RandomNodes(bst.root);

        Node2 suc = getSuccessor(node2s.get(0));
        System.out.println("\nSuccessor of " + node2s.get(0).value + " - " + (suc != null ? suc.value : "NULL"));
        suc = getSuccessor(node2s.get(1));
        System.out.println("\nSuccessor of " + node2s.get(1).value + " - " + (suc != null ? suc.value : "NULL"));

    }
    public static Node2 getSuccessor(Node2 node) {
        if (node == null) {
            return null;
        }
        //Case 1 - Node has right sub-tree, get the smallest in the left-tree of right
        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
        /* Case 2 - Node has no right sub-tree.
        Traverse up until we get a parent whose right child is we.
        */
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }


    /* 10.14 - Compute the leaves of a BT.
    * */
    public static void computeLeaves() {
        BinarySearchTree b = getCustomBST();//getBST(10);
        System.out.println();
        List<Node2> l = b.getLeavesAsList();
        for (Node2 n : l) {
            System.out.print(n.value + ", ");
        }
    }

    /* 10.7 - Inorder with Stack.
    EXTREMELY SUCCINT CODE. JUST GET THIS ITS SIMPLE.
    OBSERVE HOW THE RECURSIVE CODE WORKS. THIS SIMULATES IT.
     */
    public static void inorder(int n) {
        BinarySearchTree t = getBST(n);
        List<Node2> res = new LinkedList<>();
        Stack<Node2> stack = new Stack<>();
        Node2 cur = t.root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                res.add(cur);
                cur = cur.right;
            }
        }
        System.out.println();
        for (Node2 node : res) {
            System.out.print(node.value + ", ");
        }
    }

    /* 10.8 - Preorder with Stack.
    EXTREMELY SUCCINT CODE. JUST GET THIS ITS SIMPLE.
    OBSERVE HOW THE RECURSIVE CODE WORKS. THIS SIMULATES IT.
     */
    public static void preorder(int n) {
        BinarySearchTree t = getCustomBST();//getBST(n);
        List<Node2> res = new LinkedList<>();
        Stack<Node2> stack = new Stack<>();
        Node2 cur = t.root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                res.add(cur);
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                cur = cur.right;
            }
        }
        System.out.println();
        for (Node2 node : res) {
            System.out.print(node.value + ", ");
        }
    }

    /* 10.9 - kth node in inorder traversal.
     */
    public static Node2 testKthElementInorder(int k) {
        BinarySearchTree b = BinaryTrees.getCustomBST();
        b.print();
        Node2 kthNode = kthElementInorder(b, k);
        System.out.println("Kth Node - (" + k + ") - "+ kthNode.value);
        return kthNode;
    }
    public static Node2 kthElementInorder(BinarySearchTree b, int k) {
        /* We check the number of nodes in left tree + 1 for the current node.
         */
        Node2 cur = b.root;
        while (cur != null) {
            int lCnt = cur.left != null ? cur.left.count : 0;
            if (lCnt + 1 < k) {
                k = k - (lCnt + 1);
                cur = cur.right;
            } else if (lCnt + 1 == k) {
                return cur; // Target count achieved.
            } else {
                cur = cur.left;
            }
        }
        return null;
    }

    /* 10.12 - Construct BT from preorder data supported by inorder.
     */
    public static void testBTReconstruction() {
        BinarySearchTree bt = getCustomBST();
        //makeBT(bt.root, new Random());
        List<Integer> preOrder = bt.getAsList(bt.getPreorder(bt.root));
        List<Integer> inOrder = bt.getAsList(bt.getInorder(bt.root));
        print(preOrder);
        print(inOrder);
        Map<Integer, Integer> elemIdxMap = new HashMap<>();
        for (Integer i : inOrder) {
            elemIdxMap.put(i, inOrder.indexOf(i));
        }
        Node2 root = buildBST(preOrder, 0, preOrder.size() - 1,
                                0, inOrder.size() - 1,
                                elemIdxMap);
        System.out.println("\nBuild completed");
        bt._printInorder(root);
    }

    /*
    VERY TRICKY PROBLEM.
    Use the indexes of either of the trees to get the next call's boundary.
     */
    public static Node2 buildBST(List<Integer> preOrder, int preSIdx, int preEIdx,
                                 int inSIdx, int inEIdx,
                                 Map<Integer, Integer> elemIdxMap) {
        if (preSIdx > preEIdx || inSIdx > inEIdx) {
            return null;
        }

        Node2 root = new Node2(preOrder.get(preSIdx)); // Use the first element as root
        Integer inRootIdx = elemIdxMap.get(root.value);

        int numLeft = inRootIdx - inSIdx;
        int numRight = inEIdx - inRootIdx;

        int sLTree = preSIdx + 1; // Start from next element since 1st was used as root.
        int eLTree = sLTree + numLeft - 1; // From Start, skip numLeft elements in the left tree.
        int sRTree = eLTree + 1; // Start 1 after the end of left tree.
        int eRTree = sRTree + numRight - 1; // Skip numRight from start of right tree to get the right tree boundary.

        root.left = buildBST(preOrder, sLTree, eLTree, inSIdx, inRootIdx - 1, elemIdxMap);
        root.right = buildBST(preOrder, sRTree, eRTree, inRootIdx + 1, inEIdx, elemIdxMap);
        return root;
    }

    //10.13 - Reconstruct BT from preorder traversal with markers
    public static void testGetPreorderWithMarkers() {
        BinarySearchTree bst = getBST(9); //getCustomBST();
        Queue<Node2> pre = bst.getPreorderWithMarkers(bst.root);
        List<Integer> pLst = bst.getAsList(pre);
        System.out.println("\nPreorder with markers");
        for (Integer n : pLst) {
            if (n == null) {
                System.out.print("null, ");
            } else {
                System.out.print(n + ", ");
            }
        }
        Node2 bt = constructTreeWithMarkedPreorder(pLst);
        System.out.println("\nInorder");
        bst._printInorder(bt);
    }
    private static int nIdx = 0;
    public static Node2 constructTreeWithMarkedPreorder(List<Integer> pre) {
        if (nIdx > pre.size() - 1) {
            return null;
        }
        Integer cur = pre.get(nIdx);
        nIdx++;
        if (cur == null) {
            return null;
        } else {
            Node2 node = new Node2(cur);
            node.left = constructTreeWithMarkedPreorder(pre);
            node.right = constructTreeWithMarkedPreorder(pre);
            return node;
        }
    }

    //10.15 - Compute the exterior
    public static void testExteriorOfBT() {
        BinarySearchTree bst = getPerfectBST();
        List<Node2> exterior = new LinkedList<>();
        exterior.add(bst.root);
        exteriorOfLeftBT(exterior, bst.root.left, true);
        exteriorOfRightBT(exterior, bst.root.right, true);
        System.out.println("\nExterior");
        for (Node2 n : exterior) {
            System.out.print(n.value + ", ");
        }
    }
    // ****** PREORDER TRAVERSAL AND CONDITION CHECK ******
    public static void exteriorOfLeftBT(List<Node2> exterior, Node2 nod, boolean boundary) {
        if (nod == null) {
            return;
        }
        if (boundary || isLeaf(nod)) {
            exterior.add(nod);
        }
        exteriorOfLeftBT(exterior, nod.left, boundary);
        exteriorOfLeftBT(exterior, nod.right, false);
    }
    // ****** POSTORDER TRAVERSAL AND CONDITION CHECK ******
    public static void exteriorOfRightBT(List<Node2> exterior, Node2 nod, boolean boundary) {
        if (nod == null) {
            return;
        }
        exteriorOfRightBT(exterior, nod.left, false);
        exteriorOfRightBT(exterior, nod.right, boundary);
        if (boundary || isLeaf(nod)) {
            exterior.add(nod);
        }
    }
    public static boolean isLeaf(Node2 n) {
        return n != null && n.left == null && n.right == null;
    }


    // 10.16 - Right sibling tree
    public static void testRightSiblingTree() {
        BinarySearchTree bst = getPerfectBST();
        bst.levelOrderPrint();
        bst.setRightSibling();
        bst.traverseSibling();
    }


    public static void makeBT(Node2 cur, Random r) {
        if (cur != null) {
            makeBT(cur.left, r);
            cur.value = r.nextInt(100);
            makeBT(cur.right, r);
        }
    }

    public static BinarySearchTree getBST(int n) {
        BinarySearchTree bst = new BinarySearchTree();
        Integer val;
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            val = r.nextInt(1000) + 1;
            System.out.println("Adding " + val);
            bst.add(val);
        }
        bst.print();
        bst.printPreorder();
        return bst;
    }

    public static BinarySearchTree getPerfectBST() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(78);
        bst.add(50);
        bst.add(100);
        bst.add(25);
        bst.add(65);
        bst.add(90);
        bst.add(110);
        bst.add(60);
        bst.add(80);
        bst.add(12);
        bst.add(37);
        bst.add(72);
        bst.add(95);
        bst.add(105);
        bst.add(120);

        bst.print();
        bst.printPreorder();
        return bst;
    }

    public static BinarySearchTree getCustomBST() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(78);
        bst.add(50);
        bst.add(100);
        bst.add(25);
        bst.add(65);
        bst.add(90);
        bst.add(110);
        bst.add(60);
        bst.add(80);
        bst.add(95);
        bst.add(105);
        bst.print();
        bst.printPreorder();
        return bst;
    }

    public static BinarySearchTree getCustomBSTForDiameterTest() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(78);
        bst.add(50);
        bst.add(25);
        bst.add(500);
        bst.add(250);
        bst.add(510);
        bst.add(200);
        bst.add(400);
        bst.add(505);
        bst.add(525);
        bst.add(150);
        bst.add(225);
        bst.add(502);
        bst.add(508);
        bst.add(520);
        bst.add(535);
        bst.add(100);
        bst.add(175);
        bst.add(530);
        bst.add(545);

        bst.print();
        bst.printPreorder();
        return bst;
    }

    public static BinarySearchTree getInvalidBST() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(78);
        bst.add(50);
        bst.add(100);
        bst.add(25);
        bst.add(65);
        bst.add(90);
        bst.add(110);
        bst.add(60);
        bst.add(80);
        Node2 cur = bst.root;
        while (cur.right != null) {
            cur = cur.right;
        }
        cur.value = 0;
        bst.root.value = -1;
        bst.print();
        bst.printPreorder();
        return bst;
    }
    public static BinarySearchTree getCustomBSTWith01() {
        BinarySearchTree t = getCustomBST();
        set01(t.root);
        return t;
    }
    public static void set01(Node2 root) {
        Random r = new Random();
        set01(r, root);
    }
    public static void set01(Random r, Node2 cur) {
        if (cur != null) {
            if (r.nextBoolean()) {
                cur.value = 1;
            } else {
                cur.value = 0;
            }
            set01(r, cur.left);
            set01(r, cur.right);
        }
    }

    public static void main(String[] args) {
        //10.10 - testInorderSuccessorv1
        testInorderSuccessorv1();

        if (true) {
            return;
        }
        // 10.1 - Balanced?
        testHeightCheck();

        //10.2 - Check for mirror
        testSymmetric();

        // 10.4 - lcaWithParent
        lcaWithParent();

        // 10.5 - Compute sums from root to leaf.
        computeSumsFromRootToLeaf();

        // 10.6 - targetSumExists
        targetSumExistsTest();

        // 10.7 - Inorder
        inorder(15);

        // 10.8 - Preorder
        preorder(10);

        // 10.9 - testKthElementInorder
        testKthElementInorder(3);

        //10.12 - Construct BT from traversal data.
        testBTReconstruction();

        //10.13 - Construct BT with preorder markers.
        testGetPreorderWithMarkers();

        // 10.14 - computeLeaves
        computeLeaves();

        //10.15 - Compute the exterior
        testExteriorOfBT();

        // 10.16 - Right sibling tree
        testRightSiblingTree();
    }
}
