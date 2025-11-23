package epi;

import java.util.ArrayList;
import java.util.List;

public class Recursion {
    /** 16.10 - Gray code. Successive numbers have just 1 bit flipped.
     * n - Number of bits.
     */
    public static List<Integer> grayCode(int n){
        List<Integer> grayLst = new ArrayList<>();
        if (n == 0) {
            throw new RuntimeException("Error");
        }
        if (n == 1) {
            grayLst.add(0);
            grayLst.add(1);
            return grayLst;
        }
        grayLst = grayCode(n-1);

        int mask = 1 << n-1;
        List<Integer> newLst = new ArrayList<>();
        for (int i = grayLst.size() - 1; i >= 0 ; i--) {
            Integer elem = grayLst.get(i);
            newLst.add(elem ^ mask);
        }
        grayLst.addAll(newLst);
        return grayLst;
    }
    public static void testGaryCode() {
        int k = 4;
        List<Integer> result = grayCode(k);
        for (Integer r : result) {
            System.out.printf("%d - %s%n", r, String.format("%" + k + "s", Integer.toBinaryString(r)).replace(' ', '0'));
        }
    }

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

        if (true) {
            return;
        }
        //16.10 - Gary code
        testGaryCode();

        //16.11 - Diameter
        testDiameter();
    }
}
