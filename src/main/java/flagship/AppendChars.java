package flagship;

/**
 * **Problem: Append Characters to String to Make Subsequence**
 *
 * **Statement**
 * You’re given two strings, `source` and `target`, made up of lowercase English letters.
 * Your task is to determine the minimum number of characters that must be appended to the end of `source` so that
 * `target` becomes a subsequence of the resulting string.
 *
 * **Note**
 *
 * A subsequence is formed by deleting zero or more characters from a string without changing the order of the remaining characters.
 *
 * **Constraints**
 *
 * * `1 ≤ source.length, target.length ≤ 10^3`
 * * `source` and `target` consist only of lowercase English letters.
 */
public class AppendChars {
    /** Using alphabet count index will not work as the sequence of alphabets matter.
     * cba - abc pair answers the question.
     * a, b and c are in the source, but the order is wrong, hence we need bc to be added at the end.
    */
    public static int appendCharacters(String source, String target) {
        int j = 0;
        for (int i = 0; i < source.length() && j < target.length(); i++) {
            if (source.charAt(i) == target.charAt(j)) {
                j++;
            }
        }
        return target.length() - j;
    }

    public static void main(String[] args) {
        System.out.println(appendCharacters("cba", "abc"));
        System.out.println(appendCharacters("a", "a"));
        System.out.println(appendCharacters("a", "b"));
        System.out.println(appendCharacters("z", "zzzz"));
        System.out.println(appendCharacters("abba", "aaaaa"));
        System.out.println(appendCharacters("abcde", "ace"));
    }
}
