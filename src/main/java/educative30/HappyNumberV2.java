package educative30;

import java.util.HashMap;

/**
 * Write an algorithm to determine if a number n is a happy number.
 * We use the following process to check if a given number is a happy number:
 * Starting with the given number n, replace the number with the sum of the squares of its digits.
 * Repeat the process until:
 * The number equals 1, which will depict that the given number n is a happy number. The number enters a cycle,
 * which will depict that the given number n is not a happy number. Return TRUE if n is a happy number, and FALSE if not.
 *
 * T.C - O(log(n))
 * S.C - O(1)
 */
public class HappyNumberV2 {
    public static boolean isHappyNumber(int n) {
        int slow = n;
        int fast = n;
        do {
            slow = sumOfSquares(slow);
            fast = sumOfSquares(sumOfSquares(fast));
            if (fast == 1)
                return true;

        } while (slow != fast);

        return slow == 1;
    }

    private static int sumOfSquares(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(isHappyNumber(2147483647));
        //System.out.println(sumOfSquares(85));
    }

}
