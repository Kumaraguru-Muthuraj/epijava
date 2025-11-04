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

    /**
     * 12.3 - Find the miniumum value Index in a cyclic sorted array.
     * T.C - O(log(n)), S.C - O(1)
     */
    public static void testMinValueIdx() {
        int[] array = {17, 20, 25, 28, 5, 7, 9, 10, 15}; //Arrays.getArray(7);
        Arrays.print(array);

        System.out.println("\n" + minValueIdx(array));
    }
    public static int minValueIdx(int[] array) {
        int lo = 0;
        int hi = array.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (array[mid] > array[hi]) {
                lo = mid + 1;
            } else if (array[mid] < array[hi]) {
                hi = mid;
            }
        }
        return lo;
    }
    /** 12.4 - Compute integer sqrt
     * T.C - O(log(k))
     */
    public static void testSqrt() {
        System.out.println(getSqrt(10));
        System.out.println(getSqrt(50));
        System.out.println(getSqrt(100));
        System.out.println(getSqrt(120));
        System.out.println(getSqrt(150));
    }
    public static int getSqrt(int k) {
        int lo = 0; int hi = k;
        int mid = 0;
        while (lo <= hi) {
            mid = lo + (hi - lo)/2;
            long sqr = mid * mid;
            if (sqr <= k) {
                lo = mid + 1;
            } else if (sqr > k) {
                hi = mid - 1;
            }
        }
        return lo - 1;
    }

    /** 12.6 - Search in a sorted 2D array.
     * T.C -
     */
    public static void testSearch2DArray() {
        int[][] sM = Arrays.generateSortedMatrix();
        Arrays.printMatrix(sM);
        System.out.println(search(sM, 11));
    }
    public static boolean search(int[][] sM, int k) {
        int rowCount = sM.length;
        int colCount = sM[0].length;
        int row = 0;
        int col = colCount - 1;
        while (row < rowCount && col >= 0) {
            if (sM[row][col] < k) {
                ++row;
            } else if (sM[row][col] > k) {
                --col;
            } else if (sM[row][col] == k) {
                return true;
            }
        }

        return false;
    }

    /** 12.7 - Min and Max simultaneously.
     * T.C - O(n)
     */
    public static void testMinMax() {
        ArrayList<Integer> array = Arrays.getArrayList(11);
        ArrayList<Integer> minMax = minMax(array);
        System.out.println("Min - " + minMax.get(0) + " Max - " + minMax.get(1));
    }
    public static ArrayList<Integer> minMax(ArrayList<Integer> array) {
        Arrays.print(array);
        int minSoFar = Math.min(array.get(0), array.get(1));
        int maxSoFar = Math.max(array.get(0), array.get(1));
        ArrayList<Integer> minMaxSoFar = new ArrayList<>();
        minMaxSoFar.add(minSoFar);
        minMaxSoFar.add(maxSoFar);

        for (int i = 2; i < array.size() - 1; i += 2) {
            int cMin = Integer.MAX_VALUE;
            int cMax = Integer.MIN_VALUE;
            if (array.get(i+1) != null) {
                if (array.get(i) <= array.get(i+1)){
                    cMin = array.get(i);
                    cMax = array.get(i + 1);
                } else {
                    cMax = array.get(i);
                    cMin = array.get(i + 1);
                }
                minMaxSoFar.set(0, Math.min(minMaxSoFar.get(0), cMin));
                minMaxSoFar.set(1, Math.max(minMaxSoFar.get(1), cMax));
            } else {
                if (minMaxSoFar.get(0) <= array.get(i)) {
                    if (minMaxSoFar.get(1) < array.get(i)) {
                        minMaxSoFar.set(1, array.get(i));
                    }
                } else {
                    minMaxSoFar.set(1, array.get(i));
                }
            }
        }

        return minMaxSoFar;
    }

    public static void main(String[] args) {
        //12.6 - Search in a sorted 2D array.
        testSearch2DArray();

        if (true)
            return;

        //12.1 - First k in sorted array.
        testFirstK();
        //12.2 - k at Index k
        testKatIndxK();
        //12.3 - Find the miniumum value Index in a cyclic sorted array.
        testMinValueIdx();
        //12.4 - sqrt
        testSqrt();
        //12.7 - Min and Max simultaneously.
        testMinMax();

    }
}
