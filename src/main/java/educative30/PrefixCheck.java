package educative30;

/**
 * Statement
 * You are given a sentence containing words separated by single spaces and a searchWord. Your task is to determine whether searchWord is a prefix of any word in the sentence.
 * Return the 1-based index of the first word in which searchWord appears as a prefix.
 * If searchWord is a prefix of multiple words, return the index of the earliest such word.
 * If no word starts with searchWord, return −1. A prefix of a string is any contiguous substring that begins at the first character
 */
public class PrefixCheck {
    public static int isPrefixOfWord(String sentence, String searchWord) {
        int idx = 1;
        boolean found = false;
        int wrdLen = searchWord.length();
        char c;

        if (wrdLen > sentence.length() || searchWord.contains(" ")) {
            return -1;
        }

        String subStr = sentence.substring(0, wrdLen);
        if (subStr.hashCode() == searchWord.hashCode()
                && subStr.equals(searchWord)) {
            return 1;
        }

        for (int i = 0; i < sentence.length(); i++) {
            c = sentence.charAt(i);
            if (i > sentence.length() - wrdLen) {
                break;
            }
            if (c == ' ') {
                idx++;
                i++;
                subStr = sentence.substring(i, i + wrdLen);
                if (subStr.hashCode() == searchWord.hashCode()
                        && subStr.equals(searchWord)) {
                    found = true;
                    break;
                }
            }
        }
        return found ? idx : -1;
    }
    public static void main(String[] args) {
        String w = "xehcqyderc";
        String s = "qx xo";
        int i = isPrefixOfWord(s, w);
        System.out.println(i);
    }
}
