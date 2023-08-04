package knapsack_0_1;

import java.util.Arrays;

public class MinSubsetSumDiff_WithPositiveNumbers {

    private static boolean[] minSubsetSum_bottomUp_helper(int[] arr, int n, int sum) {
        boolean[][] dp = new boolean[n + 1][sum + 1];

        dp[0][0] = true;

        for (int row = 1; row < n + 1; row++) {
            for (int col = 0; col < sum + 1; col++) {
                int val = arr[row - 1];
                if (val > col) {
                    dp[row][col] = dp[row - 1][col];
                } else {
                    dp[row][col] = dp[row - 1][col] || dp[row - 1][col - val];
                }
            }
        }

        return dp[n];
    }

    private static int minSubsetSum_bottomUp(int[] arr, int n, int sum) {
        final boolean[] subSets = minSubsetSum_bottomUp_helper(arr, n, sum);

        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i <= sum / 2; i++) {
            if (subSets[i]) {
                //System.out.println(i);
                int val = Math.abs(sum - 2 * i);
                minDiff = Math.min(minDiff, val);
            }
        }

        return minDiff;
    }

    private static int minSubsetSum_recursive(int[] arr, int sum) {

        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i <= sum / 2; i++) {
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
        int[] arr = new int[]{1, 2, 7};
        int sum = Arrays.stream(arr).sum();

        int minSum = minSubsetSum_recursive(arr, sum);
        System.out.println(minSum);


        minSum = minSubsetSum_bottomUp(arr, arr.length, sum);
        System.out.println(minSum);

    }
}
