package epi;

public class Strings {
    /* 7.0 - Plaindrome
     */
    public static boolean palindrome(String str) {
        for (int i = 0, j = str.length() - 1; i < j; i++, j--) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    /*Boot camp
     */
    public static void bootCamp() {
        String s1 = "kumaran";
        String s2 = "kumara";
        String s3 = "ara";
        System.out.println(s1.compareTo(s2));
        System.out.println(s1.concat(s2));
        System.out.println(s1.contains(s3));
        System.out.println(s1.indexOf(s3));
        System.out.println(s2.endsWith(s3));
        System.out.println(s1.indexOf("a", 4));
        System.out.println(s1.lastIndexOf("a"));
        System.out.println(s1.replace('a', 'A'));
        System.out.println(s1.replace("a", "AAA"));
        System.out.println(s1.substring(4));
        System.out.println(s1.substring(2, 5));
        char[] c = s1.toCharArray();
        s1.charAt(4);

        System.out.println("**********");
        StringBuilder sb = new StringBuilder(s1);
        sb.append(true);
        System.out.println(sb);

    }

    /* 7.5 - Test palindromicity, ignore non alphabet characters.
    O(n), O(1)
     */
    public static boolean checkPalindrome(String str) {
        System.out.println(str);
        for (int i = 0, j = str.length() - 1; i < j; i++, j--) {
            while (!Character.isLetterOrDigit(str.charAt(i))) {
                i++;
            }
            while (!Character.isLetterOrDigit(str.charAt(j))) {
                j--;
            }
            if (i < j && Character.toLowerCase(str.charAt(i)) != Character.toLowerCase(str.charAt(j))) {
                return false;
            }
        }
        return true;
    }

    /* 7.6 - Reverse all words in a sentence.
     */
    public static void reverseSentence(String sentence) {
        System.out.println(sentence);
        char[] chars = sentence.toCharArray();
        reverseSentence(chars);
        System.out.println(new String(chars));
    }
    public static void reverseSentence(char[] sentence) {
        int i = 0, len = sentence.length - 1;
        reverse(sentence, 0, len);
        int wordBegin = 0, wordEnd = 0;
        while (i < len) {
            wordBegin = i;
            while (i < len && sentence[i++] != ' ') { }
            // We can even write a function to return the index of the next ' ' or end of sentence.
            wordEnd = i >= len ? i : i - 2;
            reverse(sentence, wordBegin, wordEnd);
        }
    }
    public static void reverse(char[] sentence, int s, int e) {
        while (s < e) {
            char temp = sentence[s];
            sentence[s] = sentence[e];
            sentence[e] = temp;
            s++;
            e--;
        }
    }

    /* 7.11 - Write a String Sinusoidally.
    O(n), O(n)
     */
    public static void printSine(String str) {
        StringBuilder fS = new StringBuilder();
        StringBuilder sS = new StringBuilder();
        StringBuilder lS = new StringBuilder();
        int skipF = 4;
        int skipS = 2;
        int skipL = 4;
        int strLen = str.length();
        int i = 0;
        for (int f = 1, s = 0, l = 3; i < strLen; i++) {
            if (f < strLen)
                fS.append(str.charAt(f));
            if (s < strLen)
                sS.append(str.charAt(s));
            if (l < strLen)
                lS.append(str.charAt(l));

            f += skipF;
            s += skipS;
            l += skipL;
        }

        System.out.println(fS);
        System.out.println(sS);
        System.out.println(lS);

        skipPrint(fS.toString(), 1, skipF-1);
        skipPrint(sS.toString(), 0, skipS-1);
        skipPrint(lS.toString(), 3, skipL-1);
    }

    public static void skipPrint(String str, int start, int skip) {
        System.out.println();
        for (int i = 0; i < start; i++) {
            System.out.print(' ');
        }
        for (int i = 0; i < str.length(); i++) {
            System.out.print(str.charAt(i));
            for (int j = 0; j < skip; j++) {
                System.out.print(' ');
            }
        }
    }

    public static void main(String[] args) {
        if (true) {
            return;
        }

        reverseSentence("This sentence is reversed");
        reverseSentence("This sentence i reversed");
        reverseSentence("I");

        System.out.println(checkPalindrome("kumar, is my name. eman ym si, ramuk."));
        System.out.println(checkPalindrome("A man, a plan, a canal, Panama."));
        System.out.println(checkPalindrome("Able was I, ere I saw Elba!"));
        bootCamp();
        System.out.print(palindrome("abcdefedcba"));
        printSine("kumaragurumuthurajofCoimbatore");
    }
}
