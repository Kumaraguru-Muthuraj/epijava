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
        bootCamp();
        if (true) {
            return;
        }
        System.out.print(palindrome("abcdefedcba"));
        printSine("kumaragurumuthurajofCoimbatore");
    }
}
