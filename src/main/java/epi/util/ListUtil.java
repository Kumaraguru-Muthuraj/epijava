package epi.util;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ListUtil {
    public static List<Integer> getList(int size) {
        List<Integer> l = new LinkedList<>();
        Random r = new Random();
        while (size-- > 0) {
            l.add(r.nextInt(100));
        }
        return l;
    }
    public static List<Integer> getSortedList(int size) {
        List<Integer> l = getList(size);
        Collections.sort(l);
        return l;
    }
    public static void print(List<Integer> l) {
        System.out.print("\nPrinting list - ");
        for (Integer i : l) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }
    public static List<Integer> getKSortedList() {
        List<Integer> l = new LinkedList<>();
        l.add(40);l.add(41);l.add(42);l.add(43);l.add(45);
        l.add(39);l.add(38);l.add(37);
        l.add(50);l.add(52);l.add(55);
        l.add(49);l.add(48);l.add(47);
        return l;
    }


    public static void main(String[] args) {
        print(getList(10));
        print(getSortedList(10));
    }
}
