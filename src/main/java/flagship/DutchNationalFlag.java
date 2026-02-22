package flagship;

import epi.Arrays;

import java.util.Collections;

public class DutchNationalFlag {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void partitionHelper(int[] colors) {
        int ZERO = 0;
        int ONE = 1;
        int TWO = 2;

        int size = colors.length;
        int s1Zone = -1;
        int cIdx = 0;
        int rIdx = size;
        while (cIdx < rIdx) {
            if (colors[cIdx] == ONE) {
                cIdx++;
            } else if (colors[cIdx] == ZERO) {
                swap(colors, cIdx++, ++s1Zone);
            } else if (colors[cIdx] == TWO) {
                swap(colors, cIdx, --rIdx);
            }
        }
    }
    public static int[] sortColors (int[] colors) {
        partitionHelper(colors);
        return colors;
    }
    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 0, 2, 1, 2, 0, 1, 0, 1, 2, 2};
        //int[] nums = {-1};
        Arrays.print(nums);
        System.out.println();
        Arrays.print(sortColors(nums));
    }
}
