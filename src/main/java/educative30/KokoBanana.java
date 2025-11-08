package educative30;

import java.util.Arrays;

public class KokoBanana {
    public static int minEatingSpeed(int[] piles, int h) {
        int max = Integer.MIN_VALUE;
        for (int pile : piles) {
            if (max < pile) {
                max = pile;
            }
        }

        int lo = 1;
        int hi = max;
        int res = 0;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            boolean can = canEatLessThanH(piles, mid, h);
            if (can) {
                res = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return res;
    }
    public static boolean canEatLessThanH(int[] piles, int speed, int hours) {
        int curHrs = 0;
        for (int pile : piles) {
            int q = pile / speed;
            if (pile % speed > 0) {
                q++;
            }
            curHrs += q;
            if (curHrs > hours) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int[] arr = {9, 1, 15, 7};
        int h = 5;
        System.out.println(minEatingSpeed(arr, h));
    }
}
