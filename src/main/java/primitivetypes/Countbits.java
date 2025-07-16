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


    public static void main(String[] args) {
        System.out.println(count1sV1(4090));
        if (true)
            return;
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
