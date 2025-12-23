package epi;

import java.util.ArrayList;
import java.util.List;

public class PowerSetOptimized {
    List<String> result = new ArrayList<>();
    StringBuilder path = new StringBuilder();
    public List<String> genPowerSet(String elems) {
        backtrack(elems, 0);
        return result;
    }

    private void backtrack(String elems, int idx) {
        if (idx == elems.length()) {
            if (path.length() > 0) { // exclude empty set
                result.add(path.toString());
            }
            return;
        }

        // Choice 1: include current character
        path.append(elems.charAt(idx));
        backtrack(elems, idx + 1);
        path.deleteCharAt(path.length() - 1); // undo

        // Choice 2: exclude current character
        backtrack(elems, idx + 1);
    }

    public static void main(String[] args) {
        List<String> l = new PowerSetOptimized().genPowerSet("234");
        for (String s : l) {
            System.out.println(s);
        }
    }

}
