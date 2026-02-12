package flagship;

/**
 * You are given a string tiles consisting of uppercase English letters.
 * You may arrange the tiles into sequences of any length from 1 up to tiles.length.
 * Each sequence can use each tile at most once (i.e., you cannot reuse a character position from the input more than once in a sequence).
 * Your task is to return the number of non-empty unique sequences that can be formed using the letters in tiles.
 * Two sequences are considered different if they differ in length or in at least one position.
 * tiles = "AAB"
 * Output - "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"
 *
 * tiles = EDUCE
 * Output - 170 outputs
 *
 *  I see this as NChooseK, but the chars are duplicated. We can use a Set to prevent duplicates,
 *  but ChatGPT gave an algorithm that uses counts.
 *
 *  Alternate - Create a regular nChooseK-permutation algorithm and add the values to a set.
 */
//20260202
public class Tiles {

    public static int numTilePossibilities(String tiles) {
        int[] alphaFreq = new int[26];
        for (char c : tiles.toCharArray()) {
            alphaFreq[c - 'A']++;
        }
        return recurse(alphaFreq);
    }

    private static int recurse(int[] alphaFreq) {
        int count = 0;

        for (int i = 0; i < 26; i++) {
            if (alphaFreq[i] == 0) continue;

            // choose this character
            count++;
            alphaFreq[i]--;

            // extend the sequence further
            count += recurse(alphaFreq);

            // revert
            alphaFreq[i]++;
        }

        return count;
    }
    public static void main(String[] args) {
        System.out.println(numTilePossibilities("ABCDE"));
    }
}
