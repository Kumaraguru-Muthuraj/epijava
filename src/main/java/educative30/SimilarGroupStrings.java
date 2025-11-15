package educative30;

import java.util.Arrays;

/**
 * Two strings x and y are considered similar if they are either exactly the same or can be made identical by swapping
 * at most two different characters in string x. We define a similarity group as a set of strings where each string is
 * similar to at least one other string in the group. A string doesn't need to be directly similar to every other string
 * in the group — it just needs to be connected to them through a chain of similarities.
 * Given a list of strings strs, where each string is an anagram of the others, your task is to determine how many
 * such similarity groups exist in the list.
 */
public class SimilarGroupStrings {
    public static int numSimilarGroupsWRONG(String[] strs) {
        int groups = strs.length;
        int[] parent = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            parent[i] = i;
        }
        for (int i = 0 ; i < strs.length - 1; i++) {
            for (int j = i + 1; j < strs.length; j++) {
                if (parent[j] == j && similar(strs[i], strs[j])) {
                    --groups;
                    parent[j] = i;
                }
            }
        }

        return groups;
    }

    public static int numSimilarGroups(String[] strs) {
        boolean[] visited = new boolean[strs.length];
        Arrays.fill(visited, false);
        int groups = 0;
        for (int i = 0 ; i < strs.length; i++) {
            if (!visited[i]) {
                groups++;
                dfs(i, strs, visited);
            }
        }

        return groups;
    }

    public static void dfs(int i, String[] strs, boolean[] visited) {
        visited[i] = true;
        for (int j = 0; j < strs.length; j++) {
            if (!visited[j] && similar(strs[i], strs[j])) {
                dfs(j, strs, visited);
            }
        }
    }


    public static boolean similar(String s1, String s2) {
        int diffCnt = 0;
        int len = s1.length();
        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffCnt++;
                if (diffCnt > 2) {
                    return false;
                }
            }
        }
        return (diffCnt == 0 || diffCnt == 2);
    }

    public static void checkSimilar(String[] strs) {
        for (int i = 0 ; i < strs.length - 1; i++) {
            for (int j = i + 1; j < strs.length; j++) {
                if (similar(strs[i], strs[j])) {
                    System.out.println("Idx - " + i + " - " + j);
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] strs = {"abcd", "kijh", "pqrs", "kihj", "pqsr", "khij", "psqr", "ikhj"};
        String[] strs1 = {"nqqqhidshfsdldpxcrxybbeoldoyqmxiplpvbwetwuqlaqnuqcfegslkyszgoigdjaqwcga",
                        "nqqqhidshfsdldpxcrxybbeopdoyqmxipllvbwgtwuqlaqnuqcfegslkyszeoigdjaqwcga",
                        "nqqqhidshfsdldpxcrxybbeoldoyqmxiplpvbwgtwuqlaqnuqcfegslkyszeoigdjaqwcga",
                        "nqqqhidshfsdldpxcrxybbeoldoyqmxiplpvkwgtwuqlaqnuqcfegslbyszeoigdjaqwagc",
                        "nqqqhidshfsdldpxcrxybbeoldoyqmxiplpvkwgtwuqlaqnuqcfegslbyszeoigdjaqwcga",
                        "oqqqhidshfsdldpxcrxybbeoldnyqmxiplpvkwgtwuqlaqnuqcfegslbyszeoigdjaqwcga"};
        System.out.println(numSimilarGroups(strs1));
        //checkSimilar(strs1);
    }
}
