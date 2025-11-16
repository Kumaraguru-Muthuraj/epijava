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
 * Use elimination. It works well here.The algorithm doesn't work. WRONG!
 */
public class LongestSelfContainedStringV1 {
    public static int maxSubstringLength (String str) {
        String s1 = str.substring(0, str.length() - 1);
        String c1 = str.substring(str.length() - 1);

        String s2 = str.substring(1);
        String c2 = str.substring(0, 1);

        int l1 = getSplitFindMaxSubsLen(s1, c1);
        int l2 = getSplitFindMaxSubsLen(s2, c2);
        return Math.max(l1, l2);
    }

    public static int getSplitFindMaxSubsLen(String str, String checkChar) {
        if (str.isEmpty()) {
            return -1;
        } else if (str.length() == 1) {
            return 1;
        }
        String[] strsWOCheckChar = str.split(checkChar);
        if (strsWOCheckChar.length == 1) {
            return strsWOCheckChar[0].length();
        }
        List<String> _1LenStrs = new ArrayList<>();
        Set<String> duplicates = new HashSet<>();
        TreeSet<String> treeSet = new TreeSet<>(cmp);
        for (String strWOCheckChar : strsWOCheckChar) {
            boolean added = treeSet.add(strWOCheckChar);
            if (!added) {
                duplicates.add(strWOCheckChar);
            }
            if (strWOCheckChar.length() == 1) {
                _1LenStrs.add(strWOCheckChar);
            }
        }

        for (String dup : duplicates) {
            treeSet.remove(dup);
        }

        if (_1LenStrs.isEmpty()) {
            if (!treeSet.isEmpty()) {
                String f = treeSet.first();
                treeSet.remove(f);
                return f.length();
            } else {
                return -1;
            }
        }
        int candLen = -1;
        while (!treeSet.isEmpty()) {
            String maxLenStr = treeSet.first();
            treeSet.remove(maxLenStr);

            for (String _1LenStr : _1LenStrs) {
                candLen = Math.max(candLen, getSplitFindMaxSubsLen(maxLenStr, _1LenStr));
                if (candLen == maxLenStr.length()) {
                    return candLen;
                }
            }
        }

        return -1;
    }

    public static Comparator<String> cmp = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            if (o1.length() < o2.length()) {
                return 1;
            } else if (o1.length() > o2.length()) {
                return -1;
            } else {
                return o1.compareTo(o2);
            }
        }
    };




    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "aabccbdd";
        String s3 = "abhcdehfgi";
        String s4 = "xyzxy";
        String s5 = "abcabc";
        String s6 = "ktyoeotteykokey";


        System.out.println(maxSubstringLength(s6));

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
