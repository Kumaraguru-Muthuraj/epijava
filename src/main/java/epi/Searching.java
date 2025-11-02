package epi;

import java.util.ArrayList;
import java.util.Collections;

public class Searching {
    /**
     * 12.1 - Search a sorted array for the first occurrence of k.
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

    public static void main(String[] args) {
        //12.1 - First k in sorted array.
        testFirstK();

    }
}
