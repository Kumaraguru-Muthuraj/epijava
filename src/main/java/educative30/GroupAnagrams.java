package educative30;

import java.util.*;

public class GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs){
        HashMap<String, List<String>> groups = new LinkedHashMap<>();
        for (String str : strs) {
            char[] strAsChars = str.toCharArray();
            Arrays.sort(strAsChars);
            String sortedStr = new String(strAsChars);
            List<String> group = groups.get(sortedStr);
            if (group == null || group.isEmpty()) {
                group = new LinkedList<>();
                groups.put(sortedStr, group);
            }
            group.add(str);
        }
        List<List<String>> res = new ArrayList<>(groups.values());
        return res;
    }
    public static void main(String[] args) {
        String[] strs = {"duel", "dule", "speed", "spede", "deul", "cars"};
        List<List<String>> res = groupAnagrams(strs);
        for (List<String> group : res) {
            System.out.println();
            for (String str : group) {
                System.out.print(str + ", ");
            }
        }
    }

}
