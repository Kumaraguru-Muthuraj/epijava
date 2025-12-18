package epi;

import java.util.*;
import java.util.LinkedList;

public class Recursion {
    /** 16.7 - Palindromic decompositions
     *
     */
    List<List<String>> results = new ArrayList<>();
    static Set<String> palins = new HashSet<>();
    public static void testPalindromicDecompose() {
        palindromePartitioning("0204451881");
        palins.clear();
        palindromePartitioning("abbacabba");
    }
    public static List<List<String>> palindromePartitioning(String input) {
        List<String> partialPart = new ArrayList<>();
        directedPalindromePartitioning(input, 0, partialPart);
        System.out.println(palins);
        return null;
    }
    public static void directedPalindromePartitioning(String input, int offset,
                                                      List<String> partialPartition) {
        if (offset == input.length()) {
            System.out.println(partialPartition);
        }
        for (int i = offset + 1; i <= input.length() ; i++) {
            String prefix = input.substring(offset, i);
            if (isPalindrome(prefix)) {
                if (prefix.length() > 1) {
                    palins.add(prefix);
                }
                partialPartition.add(prefix);
                directedPalindromePartitioning(input, i, partialPartition);
                partialPartition.remove(partialPartition.size() - 1);
            }
        }
    }
    public static boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1 ; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    /** 16.8 - Distinct binary trees.
     * Time and Space - ??
     */
    public static List<Node2> distinctBinaryTrees(int numNodes) {
        List<Node2> trees = new LinkedList<>();
        Random r = new Random();
        if (numNodes == 0) {
            trees.add(null);
        }
        for (int numLTrees = 0; numLTrees < numNodes; numLTrees++) {
            int numRTrees = numNodes - 1 - numLTrees;
            List<Node2> lTrees = distinctBinaryTrees(numLTrees);
            List<Node2> rTrees = distinctBinaryTrees(numRTrees);
            for (Node2 lTree : lTrees) {
                for (Node2 rTree : rTrees) {
                    Node2 root = new Node2(r.nextInt(50));
                    root.left = lTree;
                    root.right = rTree;
                    trees.add(root);
                }
            }
        }

        return trees;
    }
    public static void testBinTrees() {
        List<Node2> trees = distinctBinaryTrees(4);
        int treeCnt = 1;
        for (Node2 tree : trees) {
            BinarySearchTree bst = new BinarySearchTree();
            bst.root = tree;
            System.out.println("\n\nTree Cnt - " + treeCnt++);
            bst.print();
        }
    }


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

    //16.5 - N Choose K
    public static List<String> subsets = new ArrayList<>();
    public static void generateNChooseK(StringBuilder partialSet, int n, int k, int offset) {
        partialSet.append(offset);
        if (partialSet.length() == k) {
            subsets.add(partialSet.toString());
        } else {
            for (int i = offset + 1; i <= n; i++) {
                generateNChooseK(partialSet, n, k, i);
            }
        }
        partialSet.deleteCharAt(partialSet.length() - 1);
    }

    public static void testNChooseK() {
        StringBuilder partial = new StringBuilder();
        int n = 5;
        int k = 3;
        for (int offset = 1; offset <= n - k + 1; offset++) {
            generateNChooseK(partial, n, k, offset);
        }
    }


    public static void generateNChooseKV2(StringBuilder partialSet, int n, int k, int offset) {
        if (partialSet.length() == k) {
            subsets.add(partialSet.toString());
        } else {
            // Limiting the offset to n minus k is not done here.
            for (int i = offset; i <= n; i++) {
                partialSet.append(i);
                generateNChooseKV2(partialSet, n, k, i + 1);
                partialSet.deleteCharAt(partialSet.length() - 1);
            }
        }
    }

    public static void testNChooseKV2() {
        StringBuilder partial = new StringBuilder();
        int n = 5;
        int k = 3;
        //for (int offset = 1; offset <= n - k + 1; offset++) {
            generateNChooseKV2(partial, n, k, 1);
        //}
        System.out.println("Size - " + subsets.size());
        for (String str : subsets) {
            System.out.println(str);
        }
    }

    //16.3 - Permutations, TC pending.
    public static void testPermutations() {
        List<Character> sequence = new ArrayList<>();
        sequence.add('a');
        sequence.add('b');
        sequence.add('c');
        sequence.add('d');
        sequence.add('e');
        generatePermutation(sequence, 0);
        System.out.println();
        for (List<Character> seq : permResults) {
            System.out.println(seq.toString());
        }
    }
    public static void generatePermutation(List<Character> sequence, int i) {
        if (i == sequence.size() - 1) {
            permResults.add(new ArrayList<>(sequence));
            return;
        }
        //i is fixed and j changes to swap.
        for (int j = i; j < sequence.size(); j++) {
            Collections.swap(sequence, i, j);
            generatePermutation(sequence, i+1);
            Collections.swap(sequence, i, j);
        }
    }
    public static List<List<Character>> permResults = new ArrayList<>();

    public static void main(String[] args) {
        //16.3 - Permutations, TC pending.
        testPermutations();
        if (true) {
            return;
        }

        //16.5 - N Choose K
        testNChooseKV2();

        //16.7 - Palindromic decomposition
        testPalindromicDecompose();

        //16.8 - BInary Trees
        testBinTrees();

        //16.10 - Gary code
        testGaryCode();

        //16.11 - Diameter
        testDiameter();
    }
}
