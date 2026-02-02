package educative2;
import java.util.*;

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
 */
//20260202
public class Tiles {

    public static int numTilePossibilities(String tiles) {

        return - 1;
    }
}
