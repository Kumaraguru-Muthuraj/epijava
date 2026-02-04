package educative2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
# ✅ Problem Title
**Substring with Concatenation of All Words**
---
## 1. Problem Statement
You are given a string `s` and an array of strings `words`.
All strings in `words` have the same length.
A **concatenated string** is defined as a string that contains **all the words in `words` exactly once**, in **any order**, and **without any intervening characters**.
Formally, a concatenated string is a permutation of all the strings in `words` joined together.
Your task is to return **all starting indices** of substrings in `s` that are concatenated strings.
You may return the indices in any order.
---
## 2. Input / Output
### Input
* A string `s`
* An array of strings `words`
### Output
* A list of integers representing the starting indices of substrings in `s`
that are concatenations of all words in `words`.
---
## 3. Constraints
* (1 \le s.length \le 10^3)
* (1 \le words.length \le 1000)
* (1 \le words[i].length \le 30)
* All strings in `words` have the same length.
* `s` and each `words[i]` consist of lowercase English letters.
---
## 4. Rules / Clarifications
* Each word in `words` must be used **exactly once**.
* The order of words in the concatenation may be **any permutation**.
* The words must be concatenated **contiguously** (no gaps).
* A valid substring must have length:
```
words.length * words[0].length
```
---
## 5. Example (conceptual)
If:
```
s = "barfoothefoobarman"
words = ["foo", "bar"]
```
Then the valid starting indices are:
```
[0, 9]
```
because:
* `s[0..5] = "barfoo"`
* `s[9..14] = "foobar"`
Both are concatenations of all words exactly once.
*/
//20260204 - This worked and got accepted in Educative.
public class SubstringConcatenated {
    public static long orderlessHash(String[] strs) {
        long hash = 0;
        for (String s : strs) {
            int h = s.hashCode();
            int sqrH = h * h;
            hash += 31 * h + sqrH;
        }
        return hash;
    }
    public static long orderlessHash(String str, int sIdx, int subLen, int countSub) {
        long hash = 0;
        for (int idx = sIdx; countSub > 0; idx += subLen, countSub--) {
            String subStr = str.substring(idx, idx + subLen);
            int h = subStr.hashCode();
            int sqrH = h * h;
            hash += 31 * h + sqrH;
        }
        return hash;
    }
    public static List<Integer> findSubstring (String s, String[] words) {
        List<Integer> ret = new ArrayList<>();
        long inputHash = orderlessHash(words);
        for (int i = 0; i <= s.length() - (words.length * words[0].length()); ) {
            if (inputHash == orderlessHash(s, i, words[0].length(), words.length)) {
                ret.add(i);
                i += words[0].length();
            } else {
                i++;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        String s = "barfoothefoobar";
        String[] words = {"bar", "foo"};
        List<Integer> ints = findSubstring(s, words);
        for (Integer i : ints) {
            System.out.print(i + ", ");
        }
    }
}
