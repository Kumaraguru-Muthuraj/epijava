package flagship;

/**
 * **Problem: Valid Word Abbreviation**
 *
 * **Statement**
 * Given a string `word` and an abbreviation `abbr`, return `TRUE` if the abbreviation matches the given string. Otherwise, return `FALSE`.
 *
 * An abbreviation can replace any non-adjacent, non-empty substrings of the original word with their lengths. Replacement lengths must not contain leading zeros.
 *
 * For example, the word `"calendar"` can be abbreviated as follows:
 *
 * * `"cal3ar"` → `"cal" + "end" (length = 3) + "ar"`
 * * `"c6r"` → `"c" + "alenda" (length = 6) + "r"`
 *
 * The word `"internationalization"` can also be abbreviated as `"i18n"` (skipping 18 characters and keeping the first and last letters).
 *
 * The following are **not** valid abbreviations:
 *
 * * `"c06r"` → contains leading zeros
 * * `"cale0ndar"` → replaces an empty substring
 * * `"c24r"` → replaced substrings are adjacent
 *
 * **Constraints**
 *
 * * `1 ≤ word.length ≤ 20`
 * * `word` consists only of lowercase English letters
 * * `1 ≤ abbr.length ≤ 10`
 * * `abbr` consists of lowercase English letters and digits
 * * All integers in `abbr` fit in a 32-bit integer
 */
//FLAGSHIP04
public class ValidAbbreviation {

    public static boolean validWordAbbreviation(String word, String abbr) {
        int wLen = word.length();
        int abLen = abbr.length();

        int wI = 0;
        int abI = 0;

        while (wI < wLen && abI < abLen) {
            if (Character.isDigit(abbr.charAt(abI))) {
                if (abbr.charAt(abI) == '0') {
                    return false;
                }
                int num = 0;
                while (abI < abLen && Character.isDigit(abbr.charAt(abI))) {
                    num *= 10;
                    num += abbr.charAt(abI) - '0';
                    abI++;
                }
                wI += num;
            } else {
                while (wI < wLen && abI < abLen &&
                        word.charAt(wI) == abbr.charAt(abI)) {
                    wI++;
                    abI++;
                }
                if (abI < abLen &&
                        !Character.isDigit(abbr.charAt(abI))) {
                    return false;
                }
            }
        }
        return (wI == wLen && abI == abLen);
    }

    public static boolean validWordAbbreviationV1(String word, String abbr) {
        int wLen = word.length();
        int abLen = abbr.length();

        int wI = 0;
        int abI = 0;
        while (wI < wLen && abI < abLen &&
                word.charAt(wI) == abbr.charAt(abI)) {
            wI++;
            abI++;
        }
        if (abI == abLen) {
            return false;
        }

        if (abbr.charAt(abI) == '0') {
            return false;
        }
        int num = 0;
        while (abI < abLen && Character.isDigit(abbr.charAt(abI))) {
            num *= 10;
            num += abbr.charAt(abI) - '0';
            abI++;
        }

        wI += num;
        while (wI < wLen && abI < abLen) {
            if (word.charAt(wI) != abbr.charAt(abI)) {
                return false;
            }
            wI++;
            abI++;
        }

        return (wI == wLen && abI == abLen);
    }

    public static void main(String[] args) {
        System.out.println(validWordAbbreviation("internationalization", "13iz4n"));
        System.out.println(validWordAbbreviation("helloworld", "4orworld"));
        System.out.println(validWordAbbreviation("minimum", "min2um"));
        System.out.println(validWordAbbreviation("subsequences", "3sequ3es"));
        System.out.println(validWordAbbreviation("computation", "compu03on"));

    }

}
