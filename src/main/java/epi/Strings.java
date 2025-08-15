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

    /* 7.1 - Convert String <---> Integer
     */
    // Don't go from back, go from front. This makes it simple.
    // O(n), O(1)
    public static int strToInteger(String str) {
        int res = 0;
        int i = 0;
        if (str.charAt(0) == '-') {
            i = 1;
        }
        for (; i < str.length(); i++) {
            int d = str.charAt(i) - '0';
            res = res * 10 + d;
        }
        return (str.charAt(0) == '-') ? -res : res;
    }

    // Inserting chars in the beginning of the String is expensive, we do append and reverse.
    //O(n), O(n)
    public static String intToString(int i) {
        int val = Math.abs(i);
        StringBuilder sb = new StringBuilder();
        int d = 0;
        char c;
        while (val > 0) {
            d = val % 10;
            c = (char) ('0' + d);
            sb.append(c); //Even sb.append(d) would work.
            val /= 10;
        }
        if (i < 0) {
            sb.append("-");
        }
        sb.reverse();
        return sb.toString();
    }

    /* 7.2 - Convert to different bases.
    * O(n + n log(fromB1)) <-- log(fromB1) is base toB2, where n is the number of digits.
    * O(n) - For the StringBuilder
    *
     */
    public static String convert(String num, int fromB1, int toB2) {
        int b10 = convertToBase10(num, fromB1);
        String str = convertBase10ToB(b10,toB2);
        return str;
    }

    // O(n)
    public static int convertToBase10(String num, int b) {
        System.out.println("Converting " + num + "to b10 from base" + b);
        int asB10 = 0;
        System.out.println(num);
        //Handle negative too
        boolean neg = num.charAt(0) == '-';
        int i = neg ? 1 : 0;
        for (; i < num.length(); i++) {
            int digit = Character.isDigit(num.charAt(i))
                            ? num.charAt(i) - '0'
                            : num.charAt(i) - 'A' + 10;
            asB10 *= b;
            asB10 += digit;
        }

        return asB10;
    }

    /*
    Consider converting a decimal to binary. How many divisions would you do?
    num = 2 ^ k, and k is the number of times => k = log(num) base 2.
    If you have to convert a decimal num to base b, we divide it k = log(num) base b times.
    ******* NOT FINISHED YET ******* Express num w.r.t b
     */
    public static String convertBase10ToB(int num, int b) {
        System.out.println("Converting "+ num + " from b10 to base" + b);
        StringBuilder sb = new StringBuilder();

        while(num > 0) {
            int digit = num % b;
            char c = digit >= 10 ? (char) (digit - 10 + 'A') : (char) (digit + '0');
            sb.append(c);
            num /= b;
        }
        sb.reverse();
        return sb.toString();
    }

    /* 7.3 - Spreadsheet column encoding.
    A, B, ...Z, AA, AB ... AZ, AAA, AAB ... BAA, BAB, BAC...
    O(n)
     */
    public static int convertToBase26(String columnCode) {
        int b10 = 0;
        for (int i = 0; i < columnCode.length(); i++) {
            b10 = b10 * 26 + columnCode.charAt(i) - 'A' + 1;
        }
        return b10;
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
    O(n) - Traverse once to reverse and again traverse word by word to reverse. 2.n
    O(1)
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

    /* 7.12 - Run Length Encoding.
    aaaabbcdddggg -> 4a2b1c3d3g - Encoding
    2r11t4g -> rrtttttttttttgggg - Decoding
     */


    /* 7.13 - Rolling hash / Rabin-Karp. Find the first substring.
    O(m+n) - m and n are lengths of the strings.
    O(1).
     */
    public static int getSubstringIdx(String text, String s) {
        System.out.println(text);
        System.out.println(s);

        int BASE = 26;
        long pToSubtract = (int) Math.pow(BASE, s.length() - 1);
        long sHash = hash(s, 0, s.length() - 1);
        long tHash = hash(text, 0, s.length() - 1);
        int tS = 0;
        while (tS < text.length() - s.length()) {
            if (sHash == tHash) {
                // Might have to check the strings if the hash is not spread well.
                return tS;
            }
            tHash = tHash - text.charAt(tS) * pToSubtract;
            tHash = tHash * BASE + text.charAt(tS + s.length());

            tS++;
        }
        if (sHash == tHash) {
            // Might have to check the strings if the hash is not spread well.
            return tS;
        }

        return -1;
    }

    public static long hash(String str, int s, int e) {
        int BASE = 26;
        long value = 0;
        for (int i = s; i <= e; i++) {
            value = value * BASE + str.charAt(i);
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.println(convertToBase26("A"));
        System.out.println(convertToBase26("Z"));
        System.out.println(convertToBase26("AA"));
        System.out.println(convertToBase26("AB"));
        System.out.println(convertToBase26("AZ"));
        System.out.println(convertToBase26("BAA"));
        if (true) {
            return;
        }

        System.out.println(getSubstringIdx("abcdefghijklmn", "bc"));

        System.out.println(convert("10111010110000010000", 2, 16));

        System.out.println(convertToBase10("53AB", 16));
        System.out.println(convertToBase10("10101100", 2));
        System.out.println(convertBase10ToB(532541, 16));
        System.out.println(convertBase10ToB(53, 8));
        System.out.println(convertBase10ToB(53, 2));

        System.out.println(strToInteger("-4521"));
        System.out.println(intToString(-2534));

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
