package educative30;

import java.util.HashMap;

/**
 * Write an algorithm to determine if a number n is a happy number.
 * We use the following process to check if a given number is a happy number:
 * Starting with the given number n, replace the number with the sum of the squares of its digits.
 * Repeat the process until:
 * The number equals 1, which will depict that the given number n is a happy number. The number enters a cycle,
 * which will depict that the given number n is not a happy number. Return TRUE if n is a happy number, and FALSE if not.
 */

/**
 * T.C - O((log n)²) - Because of reverse
 * S.C - O(1) Because convergence happens quickly and the hashmap contains ~20 elements anytime.
 *
 */
public class HappyNumber {
    public static HashMap<Long, Long> map = new HashMap<>();
    public static boolean isHappyNumber(int n) {
        long goldenN = n;
        long nCopy = n;
        if (nCopy == 1) {
            return true;
        }
        if (nCopy < 1) {
            return false;
        }

        long res = 0;
        while (true) {
            res = sumOfSquares(nCopy);
            if (map.containsKey(nCopy) && map.get(nCopy) != 1) {
                return false;
            } else {
                map.put(nCopy, res);
            }
            if (goldenN == res) {
                return false;
            } else if (res == 1) {
                return true;
            }
            nCopy = res;
        }
    }

    public static long sumOfSquares(long n) {
        if (map.get(n) != null) {
            return map.get(n);
        }
        long rever = reverse(n);
        if (map.get(rever) != null) {
            return map.get(rever);
        }
        int res = 0;
        while (n > 0) {
            rever = reverse(n);
            if (map.get(n) != null) {
                res += map.get(n);
            } else if (map.get(rever) != null) {
                res += map.get(rever);
            } else {
                long lsd = n % 10;
                res += lsd * lsd;
            }
            n /= 10;
        }
        return res;
    }

    public static long reverse(Long n) {
        long rev = 0;
        long lsd = 0;
        while (n > 0) {
            lsd = n % 10;
            rev = rev * 10 + lsd;
            n /= 10;
        }
        return rev;
    }


    public static void main(String[] args) {
        System.out.println(isHappyNumber(2147483647));
        //System.out.println(sumOfSquares(85));
    }

}
