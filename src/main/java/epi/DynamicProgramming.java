package epi;


import java.util.HashMap;

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

    public static void main(String[] args) {
        //17.3 - 2D array navigation
        testMatrixNavigation();
        if (true) {
            return;
        }
        testFB();
    }
}
