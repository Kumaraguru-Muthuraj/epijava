package epi;

import java.util.*;
import java.util.LinkedList;

public class Hashing {
    /** 13.1 - Palindromic permutation check.
    T.C - O(n)
     S.C - O(c), where c is the number of distinct characters.
    */
    public static boolean checkPalindromic(String chars) {
        HashMap<Character, Character> charCnt = new HashMap<>();
        for (int i = 0; i < chars.length(); i++) {
            Character ch = chars.charAt(i);
            if (charCnt.containsKey(ch)) {
                charCnt.remove(ch);
            } else {
                charCnt.put(ch, ch);
            }
        }
        return charCnt.size() <= 1;
    }
    public static void testCheckPalindrome() {
        System.out.println(checkPalindromic("kumara"));
        System.out.println(checkPalindromic("madam"));
        System.out.println(checkPalindromic("mabbamccddeef"));
    }

    /** 13.4 - LCA in BT, optimizing for T.C. Traverse only till the CA, not till the root.
     T.C - O(k1 + k1), ki is the distance of the ni from the LCA.
     S.C - O(h) - h is the height of the tree.
     */
    public static Node2 LCAOptimized(Node2 n0, Node2 n1) {
        Set<Node2> touched = new HashSet<>();
        while (n0 != null || n1 != null) {
            if (n0 != null) {
                if (!touched.add(n0)) {
                    return n0;
                }
                n0 = n0.parent;
            }
            if (n1 != null) {
                if (!touched.add(n1)) {
                    return n1;
                }
                n1 = n1.parent;
            }
        }
        return null;
    }
    public static void testLCAOptimized() {
        BinarySearchTree b = BinaryTrees.getCustomBST();
        System.out.println();
        List<Node2> nodes = new LinkedList<>();
        BinaryTrees.get2Nodes(nodes, b.root, 80, 100);
        Node2 n0 = nodes.get(0);
        Node2 n1 = nodes.get(1);
        System.out.println("Find LCA of - " + n0.value + ", " + n1.value);
        Node2 an = LCAOptimized(n0, n1);
        if (an != null) {
            System.out.println("LCA - " + an.value);
        } else {
            System.out.println("No");
        }
    }

    public static void main(String[] args) {
        //13.4 - LCA in BT
        testLCAOptimized();
        if (true) {
            return;
        }
        //13.1 - Test for palindromic permutations
        testCheckPalindrome();

    }
}
