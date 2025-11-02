package epi;

import java.util.ArrayList;
import java.util.Collections;

public class Searching {
    /**
     * 12.1 - Search a sorted array for the first occurrence of k.
     * T.C - Book says log(n).
     * Consider the elements are k,k,...k. We will get k's index on first iteration. But for each k before we will do binary search in
     * disguise, which is a linear search. This means worst case dependent on the data is O(n/2) -> O(n).
     * S.C - O(1)
     */
    public static void testFirstK() {
        int[] array = Arrays.getArray(15);
        array[3] = 20;
        array[4] = 20;
        array[5] = 20;
        java.util.Arrays.sort(array);
        Arrays.print(array);
        System.out.println(firstK(array, 20, 0, array.length - 1));

    }
    public static int firstK(int[] array, int k, int lo, int hi) {
        int result = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (array[mid] == k) {
                result = mid;
                hi = mid - 1; // Just reduce the high by 1.
            } else if (array[mid] < k) {
                lo = mid + 1;
            } else if (k < array[mid]) {
                hi = mid - 1;
            }
        }
        return result;
    }

    /**
     * 12.2 - Find k whose index is k.
     * This algorithm doesn't work for index 0 with 0 value.
     * T.C - O(log(n)). S.C - O(1)
     */
    public static void testKatIndxK() {
        int[] array = {-2, -1, 0, 1, 3, 4, 6};//Arrays.getArray(7);
        java.util.Arrays.sort(array);

        Arrays.print(array);
        System.out.println(kAtIndxK(array,0, array.length - 1));
    }

    public static int kAtIndxK(int[] array, int lo, int hi) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int diff = array[mid] - mid;
            if (diff == 0) {
                return mid;
            } else if (diff > 0) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        testKatIndxK();
        if (true)
            return;

        //12.1 - First k in sorted array.
        testFirstK();

    }
}
