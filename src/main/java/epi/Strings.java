package epi;

public class Strings {
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
        printSine("kumaragurumuthurajofCoimbatore");
    }
}
