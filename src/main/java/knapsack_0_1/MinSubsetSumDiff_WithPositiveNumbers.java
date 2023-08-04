package knapsack_0_1;

import java.util.Arrays;

public class MinSubsetSumDiff_WithPositiveNumbers {
    private static int minSubsetSum_recursive(int[] arr, int sum) {

        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i <= sum/2 ; i++) {
            boolean found = helper(arr, arr.length, i);
            if (found) {
                //System.out.println(i);
                int val = Math.abs(sum - 2 * i);
                minDiff = Math.min(minDiff, val);
            }
        }

        return minDiff;
    }

    private static boolean helper(int[] arr, int n, int sum) {
        if (sum == 0)
            return true;
        if (n == 0)
            return false;

        if (arr[n - 1] > sum) {
            return helper(arr, n - 1, sum);
        } else {
            return helper(arr, n - 1, sum) || helper(arr, n - 1, sum - arr[n - 1]);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,9,7,3};
        int sum = Arrays.stream(arr).sum();

        final int minSum = minSubsetSum_recursive(arr, sum);
        System.out.println(minSum);

    }
}
