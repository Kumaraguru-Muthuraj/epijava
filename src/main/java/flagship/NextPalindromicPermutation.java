package flagship;

import epi.Arrays;

/**
 * **Problem: Next Palindrome Using Same Digits**
 *
 * **Statement**
 * Given a numeric string `numStr`, representing a palindrome (composed only of digits), return the smallest palindrome
 * larger than `numStr` that can be created by rearranging its digits. If no such palindrome exists, return an empty string `""`.
 *
 * Consider the following example to understand the expected output:
 *
 * * Input: `"123321"`
 * * Valid palindromes formed using the same digits: `"213312"`, `"231132"`, `"312213"`, `"132231"`, `"321123"`
 * * Output: `"132231"`
 *
 * We return `"132231"` because it is the smallest palindrome larger than the input string `"123321"`.
 *
 * **Constraints**
 *
 * * `1 ≤ numStr.length ≤ 10^5`
 * * `numStr` is a palindrome.
 */
public class NextPalindromicPermutation {
    public static void reverse(char[] perm, int st, int en) {
        while (st < en) {
            MinMoves2Palindrome.swap(perm, st, en);
            st++;
            en--;
        }
    }
    public static boolean nextPerm(char[] perm, int st, int en) {
        int k = en;
        for (; st < k && perm[k - 1] >= perm[k]; k--) {
        }
        int pIdx = k - 1;
        if (pIdx < 0) {
            return false;
        }

        int j = en;
        for (; perm[pIdx] >= perm[j] ; j--) {
        }
        MinMoves2Palindrome.swap(perm, pIdx, j);

        reverse(perm, pIdx + 1, en);
        return true;
    }

    public static void reflect(char[] perm) {
        int from = 0;
        int to = perm.length - 1;
        while (from <= to) {
            perm[to] = perm[from];
            from++;
            to--;
        }
    }
    public static String findNextPalindrome(String numStr) {
        char[] palin = numStr.toCharArray();
        int en = palin.length / 2 - 1;
        if (nextPerm(palin, 0, en)) {
            reflect(palin);
            return String.valueOf(palin);
        }
        // Replace the following return statement with your code
        return "";
    }

    public static void main(String[] args) {
        System.out.println(findNextPalindrome("1234321"));
        System.out.println(findNextPalindrome("12344321"));
        System.out.println(findNextPalindrome("5"));
        System.out.println(findNextPalindrome("12321"));
        System.out.println(findNextPalindrome("45544554"));
    }

}
