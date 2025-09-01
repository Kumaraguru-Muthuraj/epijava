package epi;

import java.util.Random;

public class BinaryTrees {
    /* 10.1 - Balanced check?
     */
    public static void testHeightCheck() {
        BinarySearchTree t = getBST(15);
        System.out.println("\nHeight - " + t.height(t.root));
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
