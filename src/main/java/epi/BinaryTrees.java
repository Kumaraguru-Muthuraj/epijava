package epi;

import java.util.Random;

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
        return bst;
    }



    public static void main(String[] args) {
        if (true) {
            return;
        }
        // 10.1 - Balanced?
        testHeightCheck();

        //10.2 - Check for mirror
        testSymmetric();
    }
}
