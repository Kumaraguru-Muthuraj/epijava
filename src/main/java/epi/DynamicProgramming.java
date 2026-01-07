package epi;


import epi.util.MatrixXYOffset;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DynamicProgramming {
    public static int fb(int x) {
        if (x <= 1) {
            return x;
        }
        if (!fbCache.containsKey(x)) {
            int fbX = fb(x - 1) + fb(x - 2);
            fbCache.put(x, fbX);
        }
        return fbCache.get(x);
    }
    public static HashMap<Integer, Integer> fbCache = new HashMap<>();

    public static int fbIterative(int n) {
        if (n <= 1) {
            return n;
        }
        int fMinus2 = 0;
        int fMinus1 = 1;
        int f = 0;
        for (int i = 2; i <= n; i++) {
            f = fMinus1 + fMinus2;
            fMinus2 = fMinus1;
            fMinus1 = f;
        }
        return f;
    }

    public static void testFB() {
        for (int i = 0; i < 10; i++) {
            System.out.print( i + "-" + fbIterative(i)+ ", ");
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.print( i + "-" + fb(i)+ ", ");
        }
    }

    /**
     * 17.3 - Number of ways to reach an element in a 2D array.
     */
    public static void testMatrixNavigation() {
        int[][] array = new int[5][5];
        printMatrix(array);
        navigateMatrix(array, array.length - 1, array[0].length - 1);
        printMatrix(array);
    }
    public static int navigateMatrix(int[][] array, int i, int j) {
        if (i == 0 || j == 0) {
            array[i][j] = 1;
        } else {
            if (array[i][j] == 0) {
                int way2AboveCell = navigateMatrix(array, i - 1, j);
                int way2LeftCell = navigateMatrix(array, i, j - 1);
                array[i][j] = way2AboveCell + way2LeftCell;
            }
        }
        return array[i][j];
    }

    public static void printMatrix(int[][] array) {
        for (int i = 0 ; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + ", ");
            }
            System.out.println();
        }
    }

    /**
     * 17.4 - Compute binomial coefficients.
     * n choose k.
     */
    public static void testNChooseK() {
        int n = 5;
        int k = 2;
        int[][] dp = new int[n+1][k+1];
        printMatrix(dp);
        System.out.println(nChooseK(n, k, dp));
        printMatrix(dp);
    }
    public static int nChooseK(int n, int k, int[][] dp) {
        if (n == 0) {
            dp[n][k] = 0;
        } else if (n == k || k == 0) {
            dp[n][k] = 1;
        } else {
            if (dp[n][k] == 0) {
                int withOutY = nChooseK(n-1, k, dp); //Without the element
                int withY = nChooseK(n-1, k-1, dp); //With the element
                dp[n][k] = withOutY + withY;
            }
        }
        return dp[n][k];
    }

    /**
     * 17.5 - Search for a sequence in a 2D array.
     */
    public static Set<MatrixXYOffset> dpCache = new HashSet<>();
    public static void testSearch2DArray() {
        int[][] matrix = { {1, 2, 3},
                            {3, 4, 5},
                            {5, 6, 7}};
        int[] pattern = {2, 3, 5, 7, 6};
        System.out.println(patternFound(matrix, pattern));

    }
    public static boolean patternFound(int[][] matrix, int[] pattern) {
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[0].length; y++) {
                if (patternFound(matrix, pattern, x, y, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean patternFound(int[][] matrix, int[] pattern, int x, int y, int offset) {
        if (offset == pattern.length) {
            return true;
        }
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length
            || dpCache.contains(new MatrixXYOffset(x, y, offset))) {
            return false;
        }
        if (matrix[x][y] == pattern[offset]) {
            return
                patternFound(matrix, pattern, x, y - 1, offset + 1) ||
                patternFound(matrix, pattern, x, y + 1, offset + 1) ||
                patternFound(matrix, pattern, x - 1, y, offset + 1) ||
                patternFound(matrix, pattern, x + 1, y, offset + 1);
        }
        dpCache.add(new MatrixXYOffset(x, y, offset));
        return false;
    }

    public static void main(String[] args) {
        //17.5 - testSearch2DArray
        testSearch2DArray();

        if (true) {
            return;
        }
        //17.3 - 2D array navigation
        testMatrixNavigation();

        //17.4 - nChooseK
        testNChooseK();

        testFB();
    }
}
