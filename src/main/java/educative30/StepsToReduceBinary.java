package educative30;

/*
Problem: Number of Steps to Reduce a Binary Number to One
Statement
You are given a string, str, as a binary representation of an integer. Your task is to return the number of steps needed to reduce it to
1 by following these rules:
If the number is even, divide it by 2.
If the number is odd, add 1 to it.
You can always reach 1 for all provided test cases.
 */
public class StepsToReduceBinary {
    public static int numSteps (String str) {
        int steps = 0;
        int carry = 0;
        for (int i = str.length() - 1; i > 0 ; i--) {
            int bit = str.charAt(i) - '0';
            int sum = bit + carry;
            if (sum == 1) {
                steps += 2;
                carry = 1;
            } else {
                steps += 1;
            }
        }
        return steps + carry;
    }

    public static void main(String[] args) {
        System.out.println(numSteps("1101"));
        System.out.println(numSteps("10"));
        System.out.println(numSteps("1"));
        System.out.println(numSteps("101010101010"));
        System.out.println(numSteps("1000"));
    }
}
