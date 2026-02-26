package flagship;

import epi.Arrays;

import java.util.Collections;

/** **Problem: Minimum Number of Moves to Make Palindrome**
 **Statement**
 Given a string `s`, return the minimum number of moves required to transform `s` into a palindrome. In each move, you can swap any two adjacent characters in `s`.
 **Note**
 * The input string is guaranteed to be convertible into a palindrome.
 **Constraints**
 * `1 ≤ s.length ≤ 2000`
 * `s` consists only of lowercase English letters.
 * `s` is guaranteed to be converted into a palindrome in a finite number of moves.
 */
public class MinMoves2Palindrome {
    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static int minMovesToMakePalindrome(String s) {
        char[] pali = s.toCharArray();
        int moves = 0;
        int left = 0;
        int right = pali.length - 1;
        while (left < right) {
            int k = right;
            while (left < k && pali[left] != pali[k]) {
                k--;
            }
            if (left == k) {
                swap(pali, left, left + 1);
                moves++;
            } else {
                for (; k < right; k++) {
                    swap(pali, k, k + 1);
                    moves++;
                }
                left++;
                right--;
            }
        }

        return moves;
    }
    public static void main(String[] args) {
        System.out.println(minMovesToMakePalindrome("ccxx"));
        System.out.println(minMovesToMakePalindrome("arcacer"));
        System.out.println(minMovesToMakePalindrome("xyzyx"));
    }
}
