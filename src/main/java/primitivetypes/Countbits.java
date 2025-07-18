package primitivetypes;

import java.util.Arrays;
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

    /* 5.8 - Reverse digits. 457 --> 754
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

    public static void print(long x) {
        System.out.println(Long.toBinaryString(x));
    }

    public static void main(String[] args) throws Exception {
        System.out.println(reverse(-235));
        if (true)
            return;
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
