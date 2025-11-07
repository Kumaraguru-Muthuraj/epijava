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

    /** 13.6 - Find the nearest repeated entries in an array.
     T.C - O(n).
     S.C - O(n) / O(d) -> d is the number of distinct entries.
     */
    public static void testNearestRepeatedEntries() {
        int[] ar = {5, 6, 7, 3, 7, 16, 3, 8, 9, 3, 10, 11, 3, 3};
        System.out.println(nearestRepeatedEntryDistance(ar));
    }
    public static int nearestRepeatedEntryDistance(int[] ar) {
        int closestDist = Integer.MAX_VALUE;
        HashMap<Integer, Integer> lastIdxOfElement = new HashMap<>();
        for (int i = 0; i < ar.length ; i++) {
            Integer lastIdx = lastIdxOfElement.get(ar[i]);
            if (lastIdx != null) {
                closestDist = Math.min(closestDist, i - lastIdx);
            }
            lastIdxOfElement.put(ar[i], i);
        }
        return closestDist;
    }

    /** 13.10 - Find the longest interval in an array.
     * T.C - O(n)
     * S.C - O(n)
     */
    public static void testLongestInterval() {
        System.out.println(longestInterval(List.of( 0, 6, 8, 5, 4, -1, -3, 20, 7, 30)));
        System.out.println(longestInterval(List.of( 1, 2, 3, 4, 5)));
        System.out.println(longestInterval(List.of( 10, 5, 6, 7, 8)));
        System.out.println(longestInterval(List.of( 100, 4, 200, 1, 3, 2)));
        System.out.println(longestInterval(List.of( 1, 9, 3, 10, 2, 20)));
        System.out.println(longestInterval(List.of( 5)));
        System.out.println(longestInterval(List.of( 1, 2, 2, 3)));
        System.out.println(longestInterval(List.of( 0, -1, -2, -3, 5, 6)));
        System.out.println(longestInterval(List.of( 10, 30, 20)));
    }
    public static int longestInterval(List<Integer> arr) {
        HashSet<Integer> lookUp = new HashSet<>(arr);
        Integer diff = Integer.MIN_VALUE;

        while (lookUp.size() > 0) {
            Iterator<Integer> iter = lookUp.iterator();
            Integer cur = Integer.MIN_VALUE;
            Integer lo = Integer.MAX_VALUE;
            Integer hi = Integer.MIN_VALUE;
            if (iter.hasNext()) {
                cur = iter.next();
                lookUp.remove(cur);

                lo = cur;
                hi = lo + 1;
                while (lookUp.contains(hi)) {
                    diff = Math.max(diff, hi - lo);
                    lookUp.remove(hi);
                    hi++;
                }
                hi--;

                lo = lo - 1;
                while (lookUp.contains(lo)) {
                    diff = Math.max(diff, hi - lo);
                    lookUp.remove(lo);
                    lo--;
                }
            }
        }
         return diff;
    }

    public static void main(String[] args) {
        //13.10 - Find the longest interval in an array.
        testLongestInterval();

        if (true) {

            return;
        }
        //13.1 - Test for palindromic permutations
        testCheckPalindrome();

        //13.4 - LCA in BT
        testLCAOptimized();

        //13.6 - Nearest repeated entry.
        testNearestRepeatedEntries();
    }
}
