package epi;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

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
        t._print(mT);
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
        // 10.6 - targetSumExists
        targetSumExistsTest();

        if (true) {
            return;
        }
        // 10.5 - Compute sums from root to leaf.
        computeSumsFromRootToLeaf();

        // 10.1 - Balanced?
        testHeightCheck();

        //10.2 - Check for mirror
        testSymmetric();

        // 10.4 - lcaWithParent
        lcaWithParent();

        // 10.7 - Inorder
        inorder(15);

        // 10.8 - Preorder
        preorder(10);

        // 10.14 - computeLeaves
        computeLeaves();
    }
}
