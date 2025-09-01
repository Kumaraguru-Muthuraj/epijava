package epi;

import java.util.Random;

/* Level starts from 0. Height = the deepest level.
Check the book for the diagram in the first page.
 */
public class BinaryTrees {
    /* 10.1 - Balanced check? Every node should be balanced.
     */
    public static void testHeightCheck() {
        BinarySearchTree t = getBST(10);
        System.out.println("\nHeight - " + t.height(t.root));
        System.out.println(("Balanced check - " + balancedCheck(t.root).balanced));
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

    public static void main(String[] args) {
        // 10.1 - Balanced?
        testHeightCheck();
    }
}
