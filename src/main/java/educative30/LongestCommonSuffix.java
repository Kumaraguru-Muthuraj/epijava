package educative30;

import epi.Arrays;

/**
 * You are given two arrays of strings, wordsContainer and wordsQuery.
 * For each string wordsQuery[i], find the string in wordsContainer that shares the longest common suffix with it.
 * If multiple strings in wordsContainer share the same longest suffix, choose the one with the smallest length.
 * If two or more such strings have the same smallest length, choose the string that appears earliest in wordsContainer.
 * Return an array of integers ans, where ans[i] is the index of the chosen string in wordsContainer for the query wordsQuery[i].
 */

/** I am using full substring search, its about common suffix from back. So the match needs to change.*/
public class LongestCommonSuffix {
    public static int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        int[] res = new int[wordsQuery.length];
        int[] hashCodes = new int[wordsQuery.length];
        for (int k = 0; k < wordsQuery.length; k++) {
            hashCodes[k] = wordsQuery[k].hashCode();
        }

        int idxOfMinLenWord = 0;
        int minLen = Integer.MAX_VALUE;
        int[] wordsLen = new int[wordsContainer.length];
        for (int k = 0; k < wordsContainer.length; k++) {
            wordsLen[k] = wordsContainer[k].length();
            if (wordsLen[k] < minLen) {
                minLen = wordsLen[k];
                idxOfMinLenWord = k;
            }
        }

        for (int queryIdx = 0; queryIdx < wordsQuery.length; queryIdx++) {
            int queryLen = wordsQuery[queryIdx].length();
            int minLenSofar = Integer.MAX_VALUE;
            res[queryIdx] = -1;
            int hashCode = hashCodes[queryIdx];
            for (int wordIdx = 0; wordIdx < wordsContainer.length; wordIdx++) {
                if (match(wordsContainer[wordIdx], queryLen, hashCode)) {
                    if (wordsLen[wordIdx] < minLenSofar) { //Not <=, its <
                        res[queryIdx] = wordIdx;
                        minLenSofar = wordsLen[wordIdx];
                    }
                }
            }
            if (res[queryIdx] == -1) {
                res[queryIdx] = idxOfMinLenWord;
            }
        }

        return res;
    }

    public static boolean match(String word, int len, int hashCode) {
        if (len > word.length()) {
            return false;
        }
        String str = word.substring(word.length() - len);
        //System.out.println(str + " - " + hashCode);
        return str.hashCode() == hashCode;
    }


    public static void main(String[] args) {
        //System.out.println(match("kumaraguru", "gurus".length(), "gurus".hashCode()));
        String[] words = {"mango", "ango", "xango"};
        String[] query = {"go", "ango", "xyz"};
        String[] words1 = {"flight", "night", "tight", "light"};
        String[] query1 = {"ight", "t", "zzz"};
        String[] words2 = {"hello", "yellow", "mellow", "fellow"};
        String[] query2 = {"low", "ellow", "wow"};
        String[] words3 = {"cart", "start", "part", "art"};
        String[] query3 = {"art", "rt", "xyz"};
        String[] words4 = {"abcdefgh","poiuygh","ghghgh"};
        String[] query4 = {"acbfgh"};
        int[] res = stringIndices(words4, query4);
        Arrays.print(res);
    }
}
