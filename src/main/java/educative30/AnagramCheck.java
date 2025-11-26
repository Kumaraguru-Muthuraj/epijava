package educative30;

import java.util.Arrays;

public class AnagramCheck {
    public static boolean isAnagram(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        }
        if (str1.length() != str2.length()) {
            return false;
        }
        char[] c1 = str1.toCharArray();
        char[] c2 = str2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        for (int i = 0; i < str1.length(); i++) {
            if (c1[i] != c2[i]) {
                return false;
            }
        }
        // Replace this placeholder return statement with your code
        return true;
    }
}
