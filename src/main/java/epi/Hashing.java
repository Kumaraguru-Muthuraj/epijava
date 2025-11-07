package epi;

import java.util.HashMap;

public class Hashing {
    // 13.1 - Palindromic permutation check.
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
    public static void main(String[] args) {
        //13.1 - Test for palindromic permutations
        System.out.println(checkPalindromic("kumara"));
        System.out.println(checkPalindromic("madam"));
        System.out.println(checkPalindromic("mabbam"));
    }
}
