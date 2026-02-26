package flagship;

import epi.Arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * **Problem: Partition Labels**
 *
 * **Statement**
 * You are given a string `s`. Your task is to divide the string into as many parts as possible such that each letter
 * appears in at most one part.
 *
 * In other words, no character should occur in more than one partition. After concatenating all parts in order,
 * the result should be the original string `s`.
 *
 * For example:
 *
 * * Input: `s = "bcbcdd"`
 * * Output: `[4, 2]`
 * * Explanation: A valid partition is `["bcbc", "dd"]`.
 *
 * Partitions like `["bcb", "cdd"]` or `["bc", "bc", "dd"]` are invalid because some letters appear in multiple parts.
 *
 * Return a list of integers representing the sizes of these partitions.
 *
 * **Constraints**
 *
 * * `1 ≤ s.length ≤ 500`
 * * `s` consists of lowercase English letters.
 */
// FLAGSHIP PARTITIONLABEL
public class PartitionLabels {
    public static List<Integer> partitionLabels(String s)
    {
        List<Integer> lst = new ArrayList<>();
        int[] alphaIdx = new int[26];
        java.util.Arrays.fill(alphaIdx, -1);
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            alphaIdx[idx] = i;
        }

        int st = 0;
        int en = 0;
        for (int i = 0; i < s.length(); i++) {
            en = Math.max(en, alphaIdx[s.charAt(i) - 'a']);
            if (en == i) {
                lst.add(en - st + 1);
                st = i + 1;
            }
        }

        return lst;
    }
    public static void main(String[] args) {
        Arrays.print(partitionLabels("abac"));
        Arrays.print(partitionLabels("aaaaa"));
    }
}
