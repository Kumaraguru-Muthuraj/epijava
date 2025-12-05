package educative30;

public class LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        StringBuilder lon = new StringBuilder();
        int shortLen = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            shortLen = Math.min(strs[i].length(), shortLen);
        }
        int idx = 0;
        boolean allMatch = true;
        while (idx < shortLen && allMatch) {
            char fChar0 = strs[0].charAt(idx);
            allMatch = true;
            for (int k = 1; k < strs.length; k++) {
                char fChar = strs[k].charAt(idx);
                if (fChar0 != fChar) {
                    allMatch = false;
                    break;
                }
            }
            if (allMatch) {
                lon.append(fChar0);
            }
            idx++;
        }
        return lon.toString();
    }

    public static void main(String[] args) {
        String[] strs = {"cir", "car"};
        //String[] strs = {"cat", "catalina", "catling", "cats", "a"};
        longestCommonPrefix(strs);
    }
}
