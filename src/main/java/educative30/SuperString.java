package educative30;

/** This solution is wrong. The definition of subsequence is
A string `s` is considered a **subsequence** of another string `t` if `s` can be obtained by deleting **zero or
more characters** from `t` **without changing the order** of the remaining characters.
*/

public class SuperString {
    public int getIndexSofar(String searchIn, String searchFor) {
        int retVal = -1;
        char lastChar = searchFor.charAt(searchFor.length() - 1);
        for (int i = 0; i < searchIn.length() - 1; i++) {
            if (lastChar == searchIn.charAt(i)) {
                int k = i; int p = searchFor.length() - 1;
                while (k >= 0 && p >= 0 &&
                        searchIn.charAt(k) == searchFor.charAt(p)) {
                    k--;
                    p--;
                }
                if (k == -1) {
                    retVal = i; //Math.max(retVal, k);
                }
            }
        }
        return retVal;
    }
    public String doSuperString(String str1, String str2) {
        int idx1 = getIndexSofar(str1, str2);
        int idx2 = getIndexSofar(str2, str1);
        if (idx1 == -1 && idx2 == -1) {
            return "";
        }
        if (idx1 >= idx2) {
            return str2 + str1.substring(idx1+1);
        } else {
            return str1 + str2.substring(idx2+1);
        }
    }
    public static void main(String[] args) {
        String str1 = "AGGTAB";
        String str2 = "GXTXAYB";
        SuperString st = new SuperString();
        System.out.println(st.doSuperString(str1, str2));
    }
}
