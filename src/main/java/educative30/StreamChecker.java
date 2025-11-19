package educative30;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

class StreamChecker {
    public HashSet<String> words = new HashSet<>();
    public List<String> list = new LinkedList<>();
    public List<String> tempList = new LinkedList<>();
    public StreamChecker(String[] ws) {
        words.addAll(Arrays.asList(ws));
    }
    public boolean query(char letter) {
        boolean found = false;
        for (int i = list.size() - 1; i >= 0 ; i--) {
            String str = list.remove(i) + letter;
            if (words.contains(str)) {
                found = true;
            }
            tempList.add(str);
        }

        String letterS = String.valueOf(letter);
        tempList.add(letterS);
        if (words.contains(letterS)) {
            found = true;
        }
        list = tempList;
        tempList = new LinkedList<>();

        return found;
    }
    public static void main(String[] args) {
        String[] ws = {"go", "hi"};
        StreamChecker sc = new StreamChecker(ws);
        System.out.println(sc.query('h'));
        System.out.println(sc.query('i'));
        System.out.println(sc.query('g'));
        System.out.println(sc.query('o'));
        System.out.println(sc.query('x'));
        System.out.println(sc.query('y'));
    }
}
