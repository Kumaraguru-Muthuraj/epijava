package epi;

import java.util.Random;

public class Countbits {
    public static short count1sV0(int x) {
        short count = 0;
        while (x != 0) {
            System.out.println(Integer.toBinaryString(x));
            count += (x & 1);
            x >>>= 1;
        }
        return count;
    }

    public static short count1sV1(int x) {
        short count = 0;
        while (x != 0) {
            System.out.println(Integer.toBinaryString(x));
            x &= (x - 1);
            count++;
        }
        return count;
    }

    //5.1 Parity of bits in a word - O(log(word size)),O(1)
    public static short parityV0(long x) {
        x ^= x >>> 32;
        x ^= x >>> 16;
        x ^= x >>> 8;
        x ^= x >>> 4;
        x ^= x >>> 2;
        x ^= x >>> 1;

        //return (short) (x & 0x1);
        return (short) (x & 1);
    }

    /* 5.2 Swap bits (view numbers as an array starting with index 0 - LSB, 31-MSB)
    j - MSB, i - LSB - O(1), O(1)
    */
    public static long swapBits(int x, int j, int i) throws Exception {
        if (i > j || i < 0 || j > 31) {
            throw new Exception("Invalid index");
        }
        print(x);
        print((x >>> i & 1));
        print((x >>> j & 1));
        if (j > i && ((x >>> i & 1) != (x >>> j & 1))) {
            int mask = (1 << i) | (1 << j);
            x ^= mask;
        }
        print(x);
        return x;
    }

    /*5.4 - Closest number with the same binary weight
    87 - 0101 0111 --> Ans: 91 - 0101 1011
    O(1) algorithm. O(1) Space. Check notebook.
     */
    public static long nWithSameWeight(long x) throws Exception {
        print(x);
        //Validations here - All 0s and 1s cannot be processed.
        if (x == 0 || ~x == 0 || x < 0) {
            throw new Exception();
        }

        long lBitsClr = x ^ (x >>> 1);
        long onlyLast1 = lBitsClr & -lBitsClr;

        long mask = onlyLast1 | (onlyLast1 << 1);
        long res = x ^ mask;
        print(res);
        return res;
    }

    // 5.4 - Closest number with the same binary weight - O(n) algorithm.
    public static long nWithSameWeightV0(long x) throws Exception {
        print(x);
        int numBits = 62; // Leave the sign bit.
        for (int i = 0; i < numBits; i++) { //Deal with only 61 bits and 62nd is for the next bit check. mask line.
            if (((x >>> i) & 1L) != ((x >>> i+1) & 1L)) {
                long mask = (1L << i) | ((1L << i+1));
                x ^= mask;
                print(x);
                return x;
            }
        }
        throw new Exception("All 0s or 1s");
    }

    //5.5 - Compute X x Y without arithmetic operations.
    /* Use bitwise operations and invent your add method.
     O(n^2), O(1).
     1 addition takes n operations, where n is the width of the operand.
     There are n additions, hence n^2
     */
    public static long add(long a, long b) {
        print("a", a); print("b", b);
        long sum = 0, carryIn = 0, carryOut = 0, k = 1;
        long tempa = a, tempb = b;
        while (tempa != 0 || tempb != 0) {
            System.out.println("-");
            long ak = a & k, bk = b & k; //The bits to be added, followed by 0s
            print("ak", ak);
            print("bk", bk);
            carryOut = (ak & bk) | (ak & carryIn) | (bk & carryIn);
            print("cO", carryOut);
            sum |= (ak ^ bk ^ carryIn);
            print("sum", sum);
            carryIn = carryOut << 1;
            print("cI", carryIn);
            k <<= 1;
            print("k", k);
            tempa >>>= 1;
            tempb >>>= 1;
        }

        return sum | carryIn;
    }

    //My own logic
    public static long addV1(long a, long b) {
        long tempa = a, tempb = b;
        long sum = 0;
        long k = 1, ak = 0, bk = 0, carryIn = 0, carryOut = 0;

        while (tempa != 0 || tempb != 0) {
            ak = a & k;
            bk = b & k;

            carryOut = (ak & bk) | carryIn;
            sum |= ak ^ bk ^ carryIn;

            carryIn = carryOut << 1;
            k <<= 1;

            tempa >>>= 1;
            tempb >>>= 1;
        }
        return sum | carryIn;
    }

    //Multiply y, x times. Simulate binary multiplication.
    public static long multiply(long x, long y) {
        long sum = 0;
        while (x != 0) {
            if ((x & 1) != 0) {
                sum = addV1(sum, y);
            }
            x >>>= 1;
            y <<= 1;
        }
        return sum;
    }

    //5.6 - Compute x/y using addition, subtraction and bit-wise operations
    /* Check the page 53 for detailed explanation on how this is a simple algorithm.
    For x =  Long.MAX_VALUE - 1 and y =  Long.MAX_VALUE - 2, this algorithm will not work
    because y << 32 will overflow. Perhaps we need to go from power's value from 1 to higher value
    when the difference between x and y is small.
    O(n), O(1)
     */
    public static long divide(long x, long y) {
        long result = 0;
        int power = 32; //Why 32??
        long maxDiv = y << power;
        while (x > y) {
            while (maxDiv > x) {
                maxDiv >>= 1;
                power--;
            }
            x -= maxDiv;
            result += 1L << power;
        }

        return result;
    }

    /* 5.7 - Compute x^y
    * There is an iterative algorithm that is equivalent of this. Check in the book.
    * O(log(y)), O(log(y))
     */
    public static long power(long x, long y) {
        if (y == 0) {
            return 1;
        } else if ((y & 1) == 0) {
            long temp = power(x, y >> 1);
            return temp * temp;
        } else {
            return x * power(x, y-1);
        }
    }



    /* 5.8 - Reverse digits. 457 --> 754
    O(n), O(1)
     */
    public static int reverse(int x) {
        int absX = Math.abs(x);
        int result = 0;
        while (absX > 0) {
            result = result * 10 + absX % 10;
            absX /= 10;
        }
        return x < 0 ? -result : result;
    }

    /* 5.9 - Palindrome in digits.
    For a 4 digit number, we have 10 options in all the 4 places => 10 x 10 x 10 x 10 = 10 pow 4.
    Number of 4 digit nos - k = 10 pow 4.
    log k = 4.
    Number of digits = log (number).
    O(number of digits), O(1)
     */
    public static boolean checkPalindrome(int x) {
        if (x < 0)
            return false;
        int div = (int) Math.pow(10, (int) Math.floor(Math.log10(x)));
        //System.out.println(div);
        while (x > 0) {
            int msd = x / div;
            int lsd = x % 10;
            if (msd != lsd) {
                return false;
            }
            x %= div; // First remove msd.
            x /= 10; // Second remove lsd.
            div /= 100; //Since we lost 2 digits.
        }
        return true;
    }

    //5.10 - Generate a uniform random number with a boolean random function.
    public static int randomBoolean() {
        return new Random().nextBoolean() ? 1 : 0;
    }

    public static int nextRandom(int max) {
        int rand = 0;
        do {
            rand = 0;
            for (int i = 0; (1 << i) < max; i++) { // 1<<i just checks if we exceeded because the 1 in the MSB moves.
                rand = (rand << 1) | randomBoolean();
                //print("Rand", rand);
            }
        } while (rand > max);
        //print("Rand", rand);
        return rand;
    }

    public static void print(String s, long x) {
        System.out.println(s + " - " + Long.toBinaryString(x));
    }
    public static void print(long x) {
        System.out.println(Long.toBinaryString(x));
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 7; i++) {
            System.out.println(power(2, i));
        }
        if (true)
            return;
        for (int i = 0; i < 30; i++) {
            System.out.println(nextRandom(5));
        }
        //print("sum", add(7, 7));
        long sum = multiply(1,3);
        System.out.print(sum);
        print(" - addV0", sum);
        Long x = Long.MAX_VALUE - 1;
        Long y = Long.MAX_VALUE - 2;
        System.out.println(divide(x, y));
        System.out.println(x+ ", " + y);
        System.out.println(nWithSameWeight(8));
        System.out.println("--------------------");
        System.out.println(nWithSameWeightV0(8));
        System.out.println(checkPalindrome(234551432));
        System.out.println(reverse(-235));
        swapBits(23457, 30, 5);
        System.out.println(parityV0(4091));
        System.out.println(count1sV1(4090));
        System.out.println(-8);
        System.out.println(-8>>>1);
        System.out.println("Number of bits - " + count1sV0(9));
        System.out.println(1 << 3);
        System.out.println(Double.valueOf("1.23"));

        Character[] cs = new Character[] {'v', 'w'};

        System.out.println(Character.getNumericValue('9'));
        System.out.println( '8' - '0');
        for (int i = 0; i < 20;i++)
            System.out.println( "Random - " + new Random().nextInt(20));


    }
}
