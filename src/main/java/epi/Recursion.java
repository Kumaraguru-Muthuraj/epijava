package epi;

public class Recursion {
    /** 16.11 - Diameter of a tree, may not me through root.
     */
    public static int[] diameter(Node2 root) {
        int[] hAndD = {0, 0};
        if (root == null) {
            return hAndD;
        }
        int[] lSub = diameter(root.left);
        int[] rSub = diameter(root.right);

        int height = Math.max(lSub[0], rSub[0]) + 1;
        int diamThrouRoot = lSub[0] + rSub[0];
        int lSubDiam = lSub[1];
        int rSubDiam = rSub[1];
        int diam = Math.max(diamThrouRoot,
                            Math.max(lSubDiam, rSubDiam));
        hAndD[0] = height;
        hAndD[1] = diam;
        return hAndD;
    }

    public static void testDiameter() {
        BinarySearchTree bst = BinaryTrees.getCustomBSTForDiameterTest();
        int[] hAndD = diameter(bst.root);
        System.out.println("\nHeight - " + hAndD[0] + ", Diam - " + hAndD[1]);
    }


    public static void main(String[] args) {
        testDiameter();
    }
}
