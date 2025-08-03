package primitivetypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Arrays {
    /*6.0 - Order even followed by odd numbers.
    We might think that its O(log(n)), but its O(n) because if all numbers are even or odd,
    we traverse once completely.
    O(1), stable, in-place
     */
    public static void orderEvenOdd() {
        int[] array = getArray(10);
        print(array);
        for (int i = 0, k = array.length - 1; i < k;) {
            if (array[i] % 2 == 0) {
                i++;
            }
            if (array[k] % 2 == 1) {
                k--;
            }
            if (array[i] % 2 == 1 && array[k] % 2 == 0) {
                swap(array, i, k);
            }
        }
        print(array);
    }

    /* 6.0 - Quick Sort.
     */
    public static void quickSortHelper(ArrayList<Integer> array, int s, int e) {
        if (s >= e) {
            return;
        }
        int pI = new Random().nextInt(e - s + 1) + s;
        int p = array.get(pI);
        Collections.swap(array, s, pI);

        int L = s + 1;
        int R = e;

        while (L <= R) {
            if (array.get(L) < p) {
                L++;
            } else if (p < array.get(R)) {
                R--;
            } else {
                Collections.swap(array, L++, R--);
            }
        }
        Collections.swap(array, s, R);
        quickSortHelper(array, s, R - 1);
        quickSortHelper(array, R + 1, e);
    }
    public static void quickSort() {
        ArrayList<Integer> al = getArrayList(10);
        print(al);
        quickSortHelper(al, 0, al.size() - 1);
        print(al);
    }

    public static void print(int[] array) {
        System.out.println();
        for (int i : array) {
            System.out.print(i + ", ");
        }
    }
    public static void print(List<Integer> l) {
        System.out.println();
        for (int i : l) {
            System.out.print(i + ", ");
        }
    }

    public static void swap(int[] array, int i, int k) {
        int temp = array[i];
        array[i] = array[k];
        array[k] = temp;
    }

    public static int[] getArray(int size) {
        int[] array = new int[size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = r.nextInt(50);
        }
        return array;
    }
    public static ArrayList<Integer> getArrayList(int size) {
        ArrayList<Integer> al = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            al.add(r.nextInt(50));
        }
        return al;
    }
    public static ArrayList<Integer> getArrayList3Nums(int size) {
        ArrayList<Integer> al = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            al.add(r.nextInt(3));
        }
        return al;
    }

    /* 6.1 - Dutch National Flag problem.
     * 3 color codes, just segregate them.
     * Note that this algorithm works only if the pivot is the middle element.
     * O(n), O(1)
     */
    enum Color {RED, YELLOW, BLUE}
    public static void partitionHelper(ArrayList<Integer> l, int pivot) {
        //Set the first element as pivot
        int temp = l.get(0);
        l.set(0, pivot);

        System.out.println("pivot - " + pivot + " - temp - " + temp);

        int loIdx = -1;
        int hiIdx = l.size();
        int cIdx = 0;

        while (cIdx < hiIdx) {
            if (l.get(cIdx) == pivot) {
                cIdx++;
            } else if (l.get(cIdx) < pivot) {
                Collections.swap(l, cIdx++, ++loIdx);
            } else if (pivot < l.get(cIdx)) {
                Collections.swap(l, cIdx, --hiIdx);
            }
            print(l);
        }

        //Set temp to the right place.
        if (temp < pivot) {
            l.set(loIdx + 1, temp);
        } else if (pivot < temp) {
            l.set(hiIdx - 1, temp);
        }
        print(l);
    }
    public static void dutchFlag() {
        ArrayList<Integer> l = getArrayList3Nums(20);
        print(l);
        partitionHelper(l, 1);
    }

    /* 6.2 - Get the next number, where the input is a list of numbers from 0 to 9
    * as a list. O(n), O(1)
     */
    public static void getNext(List<Integer> num) {
        int n = num.size() - 1;
        num.set(n, num.get(n) + 1);
        for (int i = n; i > 0 && num.get(i) == 10; i--) {
            num.set(i, 0);
            num.set(i - 1, num.get(i - 1) + 1);
        }
        if (num.get(0) == 10) {
            num.set(0, 0);
            num.add(0, 1); // Insert the MSD
        }
    }
    public static ArrayList<Integer> getNumberAsList(int size) {
        ArrayList<Integer> al = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            al.add(r.nextInt(10));
        }
        return al;
    }

    /* 6.5 - Delete duplicates in a sorted array.
     */
    public static int deleteDuplicates() {
        ArrayList<Integer> al = getArrayList(20);
        Collections.sort(al);
        print(al);
        int wIdx = 1, rIdx = 1;
        for (; rIdx < al.size(); rIdx++) {
            if (al.get(rIdx) != al.get(wIdx - 1)) {
                al.set(wIdx++, al.get(rIdx));
            }
        }
        //Optional - clear the entries from wIdx;
        for (int i = wIdx; i < al.size(); i++) {
            al.set(i, 0);
        }
        print(al);
        System.out.println("Length - " + wIdx);
        return wIdx; //Length of the new array
    }

    /* 6.19 - Compute Pascal's triangle
    O(n^2) = number of elements in the triangle - 1 + 2 + 3 ... n
    O(n)
     */
    //How will an arbitrary iteration look like?
    public static void pascal(int n) {
        if (n < 1)
            return;
        List<List<Integer>> pascalT = new ArrayList<>(n);
        List<Integer> cR = new ArrayList<>();
        cR.add(1);
        pascalT.add(cR);

        for (int r = 1; r < n; r++) {
            List<Integer> pR = pascalT.get(r-1);
            cR = new ArrayList<>();
            for (int i = 0; i <= r; i++) {
                if (i == 0 || i == r) {
                    cR.add(i, 1);
                } else {
                    cR.add(i, pR.get(i) + pR.get(i - 1));
                }
            }
            pascalT.add(cR);
        }
        System.out.println("Pascal...");
        for (int r = 0; r < n; r++) {
            print(pascalT.get(r));
        }
    }

    public static void main(String[] args) {
        pascal(5);
        if (true) {
            return;
        }
        deleteDuplicates();
        ArrayList<Integer> num = getNumberAsList(4);
        getNext(num);
        dutchFlag();
        quickSort();
        orderEvenOdd();
    }
}
