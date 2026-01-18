package epi;


import epi.util.MatrixXYOffset;

import java.util.*;
import java.util.Arrays;

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

    /** 17.1 - Number of score combinations
     * a(i,j) = a(i-1,j) + a(i,j-k), where k is the different denominations. k is for the current denomination.
     * T.C = O(s.n), s - size of denominations
     * S.C = O(s.n)
     */
    public static void testNumberOfScoreCombinations() {
        List<Integer> d = List.of(2, 3, 7);
        int sum = 12;
        int ways = numberOfScoreCombinations(sum, d);
        System.out.println(ways);
    }
    public static int numberOfScoreCombinations(int sum, List<Integer> denominations) {
        int[][] dp = new int[denominations.size()][sum+1];

        for (int i = 0; i < denominations.size(); i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= sum; j++) {
                int withoutCurrent = (i - 1 >= 0) ? dp[i - 1][j] : 0;
                int withCurrent = (j - denominations.get(i) >= 0) ? dp[i][j - denominations.get(i)] : 0;
                dp[i][j] = withoutCurrent + withCurrent;
            }
        }

        return dp[denominations.size() - 1][sum];
    }

    /**
     * 17.2 - Levenshtein distance.
     * Count of minimum number of inserts, deletes and replacements to make strings same.
     */
    public static void testLevenshteinDistance() {
        String s1 = "kitten"; //"Saturdays";
        String s2 = "sitting"; //"Sundays";
        levenshteinDistance(s1, s2);
    }

    /** A is word1 and B is word2, We are converting
     * A to B.
     */
    public static int levenshteinDistance(String A, String B) {
        int[][] dp = new int[A.length() + 1][B.length() + 1];
        for (int i = 0; i < A.length() + 1; i++) {
            Arrays.fill(dp[i], 0);
        }
        // Base case for row 0 - Convert empty String to w2 -> Insert all chars of w2
        for (int j = 0; j < B.length() + 1; j++) {
            dp[0][j] = j;
        }
        // Base case for column 0 - Convert w1 to empty string -> Delete all chars
        for (int i = 0; i < A.length() + 1; i++) {
            dp[i][0] = i;
        }
        //Decision case - Look at the notebook for EPI 17.2
        for (int i = 1; i < A.length() + 1; i++) {
            for (int j = 1; j < B.length() + 1; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + Math.min(
                                        Math.min(dp[i-1][j], dp[i][j-1]),
                                        dp[i-1][j-1]);
                }
            }
        }

        return dp[A.length()][B.length()];
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

    /**
     * 17.10 - Climb n stairs with 1 - k hops.
     * For n=4 and k=3 the values are
     * 1234
     * 124
     * 134
     * 14
     * 234
     * 24
     * 34
     * TC = O(n.k), S.C = O(n)
     */
    public static void testClimbNStairs() {
        int n = 5;
        int k = 3;
        dp = new int[n+1];
        Arrays.fill(dp, 0);
        int steps1 = climbNStairsKMax(n, k);
        Arrays.fill(dp, 0);
        int steps2 = climbNStairsKMaxFromBook(n, k);
        Arrays.fill(dp, 0);
        int steps3 = climbNStairsKMaxBottomUp(n, k);
        System.out.println("Steps - " + steps1 + ", " + steps2 + ", " + steps3);

    }
    public static int climbNStairsKMax(int n, int k) {
        if (n <= 1) {
            dp[n] = 1;
            return dp[n];
        }
        int steps = 0;
        if (dp[n] == 0) {
            for (int i = 1 ; i <= Math.min(n, k); i++) {
                dp[n] += climbNStairsKMax(n - i, k);
            }
        }
        steps = dp[n];
        return steps;
    }
    public static int[] dp;

    public static int climbNStairsKMaxFromBook(int n, int k) {
        if (n <= 1) {
            return 1;
        }
        if (dp[n] == 0) {
            for (int i = 1 ; i <= k && n - i >= 0; i++) {
                dp[n] += climbNStairsKMax(n - i, k);
            }
        }
        return dp[n];
    }

    public static int climbNStairsKMaxBottomUp(int n, int k) {
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int jump = 1 ; jump <= k; jump++) {
                if (i - jump >= 0) {
                    dp[i] += dp[i - jump];
                }
            }
        }
        return dp[n];
    }

    /** 17.8 - Minimum weight triangle.
     * T.C - O(n^2) because there are 1 + 2 + 3 + ... + n elements. Uniform work done for each element.
     * S.C - O(n) even though new memory is not allocated.
     */

    public static void testTriangleMinimumWeight() {
        Random r = new Random();
        triangle = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                row.add(r.nextInt(20) + 1);
            }
            triangle.add(row);
        }
        printTriangle();
        triangleMiniumWeight();
        printTriangle();
    }
    public static void triangleMiniumWeight() {
        int rowIdx = triangle.size() - 2;
        for (int i = rowIdx; i >= 0; i--) {
            ArrayList<Integer> row = triangle.get(i);
            for (int j = 0; j < row.size(); j++) {
                ArrayList<Integer> nextRow = triangle.get(i + 1);
                int weight = row.get(j) + Math.min(nextRow.get(j), nextRow.get(j+1));
                row.set(j, weight);
            }
        }
    }
    public static ArrayList<ArrayList<Integer>> triangle;
    public static void printTriangle() {
        for (int i = 0; i < triangle.size();i++) {
            ArrayList<Integer> row = triangle.get(i);
            for (int j = 0; j < row.size(); j++) {
                System.out.print(row.get(j) + ", ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        // 17.2 - Levenshtein distance.
        testLevenshteinDistance();

        if (true) {
            return;
        }

        //17.1 - Number of score combinations
        testNumberOfScoreCombinations();

        //17.3 - 2D array navigation
        testMatrixNavigation();

        //17.4 - nChooseK
        testNChooseK();

        //17.5 - testSearch2DArray
        testSearch2DArray();

        //17.8 - Minimum weight triangle.
        testTriangleMinimumWeight();

        //17.10
        testClimbNStairs();

        testFB();
    }
}
