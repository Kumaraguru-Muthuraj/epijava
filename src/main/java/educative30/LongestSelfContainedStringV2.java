package educative30;

/**
 * You are given a string, s, consisting of lowercase English letters. Your task is to find the length of the longest self-contained substring of s.
 * A substring t of s is called self-contained if:
 * t is not equal to the entire string s.
 * Every character in t does not appear anywhere else in s (outside of t).
 * In other words, all characters in t are completely unique to that substring within the string s.
 * Return the length of the longest self-contained substring. If no such substring exists, return -1.
 */

import java.util.*;

/**
 * From Educative.io working code. All others from Google and ChatGPT didn't work.
 */
public class LongestSelfContainedStringV2 {
    public static int maxSubstringLength(String s) {
        Map<Character, Integer> first = new HashMap<>();
        Map<Character, Integer> last = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!first.containsKey(c)) {
                first.put(c, i);
            }
            last.put(c, i);
        }

        int maxLen = -1;

        for (char c1 : first.keySet()) {
            int start = first.get(c1);
            int end = last.get(c1);
            int j = start;

            while (j < s.length()) {
                char c2 = s.charAt(j);

                if (first.get(c2) < start) {
                    break;
                }

                end = Math.max(end, last.get(c2));

                if (j == end && end - start + 1 != s.length()) {
                    maxLen = Math.max(maxLen, end - start + 1);
                }

                j++;
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "aabccbdd";
        String s3 = "abhcdehfgi";
        String s4 = "xyzxy";
        String s5 = "abcabc";
        String s6 = "ktyoeotteykokey";


        System.out.println(maxSubstringLength(s4));

//        TreeSet<String> set = new TreeSet<>(cmp);
//        set.add("cat");
//        set.add("hi");
//        set.add("apple");
//        set.add("hi");    // duplicate → ignored
//        set.add("dog");
//        set.add("bat");
//        set.add("bat2");
//        System.out.println(set);
//
//        while (!set.isEmpty()) {
//            String f = set.first();
//            System.out.println(f);
//            set.remove(f);
//        }
    }
}
