package primitivetypes;

import java.util.*;

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

    public static void print(List<Integer> l, int k) {
        System.out.println();
        for (int i = 0; i < k; i++) {
            System.out.print(l.get(i) + ", ");
        }
    }

    public static void print(List<Integer> l) {
        System.out.println();
        for (int i : l) {
            System.out.print(i + ", ");
        }
    }
    public static void printReverse(List<Integer> l) {
        System.out.println();
        for (int h = l.size() - 1; h >= 0; h--) {
            System.out.print(l.get(h) + ", ");
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

    /* 6.3 - Multiply 2 arbitrary precision integers.
    O(n1L * n2L), O(m+n)
     */
    public static ArrayList<Integer> multiply(ArrayList<Integer> n1, ArrayList<Integer> n2) {
        print(n1);
        print(n2);
        int n1L = n1.size();
        int n2L = n2.size();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < (n1L + n2L); i++) {
            result.add(0); //Use this for storing carry-forward and result.
        }

        for (int j = n2L - 1, resI = 0; j >= 0; j--, resI++) {
            int resultIdx = (n1L + n2L - 1) - resI;
            int multiplier = n2.get(j);
            for (int i = n1L - 1; i >= 0; i--) {
                int prod = result.get(resultIdx) + n1.get(i) * multiplier;
                result.set(resultIdx, prod % 10); //Result
                result.set(resultIdx - 1, result.get(resultIdx - 1) + prod / 10); //Carry forward
                resultIdx--;
            }
        }

        print(result);
        return result;
    }

    /* 6.5 - Delete duplicates in a sorted array.
     */
    public static int deleteDuplicates() {
        ArrayList<Integer> al = getArrayList(20);
        Collections.sort(al);
        print(al);
        int wIdx = 1, rIdx = 1;
        for (; rIdx < al.size(); rIdx++) {
            if (!al.get(rIdx).equals(al.get(wIdx - 1))) {
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

    /* 6.6 - Given an array of stock prices, you are allowed to buy and sell a stock once.
    What is the max profit.
    O(n), O(1)
     */
    public static int maxProfit(int n) {
        ArrayList<Integer> al = getArrayList(10);
        print(al);
        int maxProfit = 0;
        int minSoFar = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            minSoFar = Math.min(minSoFar, al.get(i));
            maxProfit = Math.max(maxProfit, (al.get(i) - minSoFar));
        }
        System.out.println("Profit - " + maxProfit);
        return maxProfit;
    }

    /* 6.10 - Compute the next permutation.
    Lexicographic ordered.
    645720 -> 645|720 --> 647|520 --> 647|025 --> 647025
    12354 -> 12453 -> 12435
    12345 -> 12354
    Depending on the data, if it is 198765 its O(n-1) traversal + Reversing -> O(n/2).
    Since traversing is predominant, its O(n).
    S.C -> O(1)
     */
    public static void nextPermutation(List<Integer> ints) {
        //print(ints);

        //Find the longest increasing sequence
        int i = ints.size() - 1;
        for (; i > 0 && ints.get(i-1) > ints.get(i); i--) {
        }
        if (i - 1 == 0)
            return;
        --i;

        /*There must be some element just bigger than ints[i]
         */
        int j = ints.size() - 1;
        for (; j > i && ints.get(i) > ints.get(j); j--) {
        }
        Collections.swap(ints, i, j);
        for (int s = i+1, e = ints.size() - 1; s < e; s++, e--) {
            Collections.swap(ints, s, e);
        }
        print(ints);
    }

    /* 6.11 - Sample k items from offline data of size n
    O(k), O(1).
    If k > population.size / 2, then change k to pop.size - k, and sample is from k to pop.size.
     */
    public static List<Integer> sampleKItems(List<Integer> population, int k) {
        //print(population);
        Random r = new Random();
        for (int i = 0; i < k; i++) {
            int idx = r.nextInt(population.size() - i) + i;
            Collections.swap(population, i, idx);
        }
        //print(population, k);
        return population;
    }

    /* 6.12 - Sample k items from online data where at least n items are streamed.
    O(stream.len), O(k)
     */
    public static List<Integer> sampleKItems2(Iterator<Integer> stream, int k) {
        List<Integer> sample = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            sample.add(stream.next());
        }
        int soFar = k;
        Random r = new Random();
        while (stream.hasNext()) {
            Integer e = stream.next();
            int idx = r.nextInt(soFar++);
            if (idx < k) {
                sample.set(idx, e);
            }
        }
        print(sample);
        return sample;
    }

    /* 6.13 - Generate a random permutation.
    * */
    public static List<Integer> randomPermutation(int n) {
        List<Integer> perm = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            perm.add(i);
        }
        sampleKItems(perm, n);
        return perm;
    }

    /* 6.14 - Consider the population of numbers 0,1,2,...,n. Given only n and k.
    Generate a random subset of k numbers from this.
    Looks like 6.11, but the set is not given, only n and k are given.
    Soln - For 6.11, the population was given so we used it to store the result, but
    here, only the number is given. Storing all n (being high) is a waste of space if
    k << n. Use a hash table to store the result.
     */
    public static List<Integer> randomSubset(int n, int k) {
        HashMap<Integer, Integer> h = new LinkedHashMap<>();
        Random r = new Random();
        int idx = 0;
        for (int i = 0; i < k; i++) {
            idx = r.nextInt(n) + i;

            Integer iVal = h.get(i);
            Integer idxVal = h.get(idx);

            if (iVal == null && idxVal == null) {
                h.put(i, idx);
                h.put(idx, i);
            } else if (iVal == null) {
                h.put(i, idxVal);
                h.put(idx, i);
            } else if (idxVal == null) {
                h.put(i,idx);
                h.put(idx, iVal);
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(h.get(i));
        }
        return res;
    }

    /* 6.17 - Spiral printing of a 2D matrix clockwise.
    O(elements), O(1)
     */
    public static int iMin = 0, iMax = 0, jMin = 0, jMax = 0;
    public static void spiralPrint(int[][] matrix) {
        iMin = 0; iMax = matrix.length;
        jMin = 0; jMax = matrix.length;
        while (iMin <= iMax) {
            printLR(matrix);
            printTB(matrix);
            printRL(matrix);
            printBT(matrix);
        }
    }
    public static void printLR(int[][] matrix) {
        for (int j = jMin; j < jMax; j++) {
            System.out.print(matrix[iMin][j] + ", ");
        }
        iMin++;
    }
    public static void printRL(int[][] matrix) {
        for (int j = jMax - 1; j >= jMin ; j--) {
            System.out.print(matrix[iMax - 1][j] + ", ");
        }
        iMax--;
    }
    public static void printTB(int[][] matrix) {
        for (int i = iMin; i < iMax; i++) {
            System.out.print(matrix[i][jMax - 1] + ", ");
        }
        jMax--;
    }
    public static void printBT(int[][] matrix) {
        for (int i = iMax - 1; i >= iMin; i--) {
            System.out.print(matrix[i][jMin] + ", ");
        }
        jMin++;
    }

    /* 6.18 - Rotate a 2D matrix clockwise.
    We have to just get that a[i,j] = a[n-1-j,i]
    O(elements), O(1)
     */
    public static void rotateMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length/2; i++) {
            for (int j = i; j < matrix.length - 1 - i; j++) {
                int temp = matrix[i][j];
                IndexAndValue res1 = getIndexAndValue(i, j, matrix);
                matrix[i][j] = matrix[res1.i][res1.j];

                IndexAndValue res2 = getIndexAndValue(res1.i, res1.j, matrix);
                matrix[res1.i][res1.j] = matrix[res2.i][res2.j];

                IndexAndValue res3 = getIndexAndValue(res2.i, res2.j, matrix);
                matrix[res2.i][res2.j] = matrix[res3.i][res3.j];

                matrix[res3.i][res3.j] = temp;

                //printMatrix(matrix);
            }
        }
    }

    public static IndexAndValue getIndexAndValue(int i, int j, int[][] matrix) {
        return new IndexAndValue(matrix.length - 1 - j, i);
    }

    static class IndexAndValue {
        public IndexAndValue(int i, int j) {
            this.i = i;
            this.j = j;
        }
        public int i, j;
    }
    // Function to generate a random n x n matrix with numbers from 0 to 100
    public static int[][] generateRandomMatrix(int n) {
        int[][] matrix = new int[n][n];
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rand.nextInt(101); // 0 to 100 inclusive
            }
        }
        return matrix;
    }

    // Utility function to print the matrix
    public static void printMatrix(int[][] matrix) {
        System.out.println();
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
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
        int[][] matrix1 = generateRandomMatrix(5);
        printMatrix(matrix1);
        spiralPrint(matrix1);
        if (true) {
            return;
        }
        int[][] matrix = generateRandomMatrix(7);
        printMatrix(matrix);
        rotateMatrix(matrix);
        printMatrix(matrix);
        print(randomSubset(20, 10));
        print(randomPermutation(4));
        List<Integer> lst = getArrayList(15);
        sampleKItems2(lst.iterator(), 5);
        sampleKItems(getArrayList(10), 5);
        nextPermutation(new ArrayList<>(List.of(5,4,7,6,3,1)));
        ArrayList<Integer> l = new ArrayList<>(List.of(1,2,3,4,5));
        for (int i = 0; i < 10; i++) {
            nextPermutation(l);
        }
        maxProfit(10);
        multiply(getNumberAsList(4), getNumberAsList(2));
        pascal(5);
        deleteDuplicates();
        ArrayList<Integer> num = getNumberAsList(4);
        getNext(num);
        dutchFlag();
        quickSort();
        orderEvenOdd();
    }
}
