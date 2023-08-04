package knapsack_0_1;

import java.util.Arrays;

public class CountSubsetsWithGivenSum {

    private static int countSubsetsWithGivenSum_recursive(int[] arr, int n, int sum) {
        //smallest valid input
        if (sum == 0) {
            return 1;
        }
        if (n == 0) {
            return 0;
        }

        if (arr[n - 1] > sum) { //skip this value
            return countSubsetsWithGivenSum_recursive(arr, n - 1, sum);
        } else {
            return countSubsetsWithGivenSum_recursive(arr, n - 1, sum) + countSubsetsWithGivenSum_recursive(arr, n - 1, sum - arr[n - 1]);
        }

    }

    private static int countSubsetsWithGivenSum_memo(int[] arr, int n, int sum, int[][] t) {
        //smallest valid input
        if (sum == 0) {
            return 1;
        }
        if (n == 0) {
            return 0;
        }

        if (t[n][sum] != -1) {
            return t[n][sum];

        }
        if (arr[n - 1] > sum) { //skip this value
            return t[n][sum] = countSubsetsWithGivenSum_memo(arr, n - 1, sum, t);
        } else {
            return t[n][sum] = countSubsetsWithGivenSum_memo(arr, n - 1, sum, t) + countSubsetsWithGivenSum_memo(arr, n - 1, sum - arr[n - 1], t);
        }

    }

    private static int countSubsetsWithGivenSum_bottomUp(int[] arr, int n, int sum) {
        int[][] dp = new int[n + 1][sum + 1];

        for (int row = 0; row < n + 1; row++) {
            dp[row][0] = 1;
        }

        for (int row = 1; row < n + 1; row++) {
            for (int col = 1; col < sum + 1; col++) {
                int val = arr[row - 1];
                if (val > col) {
                    dp[row][col] = dp[row - 1][col];
                } else {
                    dp[row][col] = dp[row - 1][col] + dp[row - 1][col - val];
                }
            }
        }

        return dp[n][sum];
    }

    public static void main(String[] args) {

        int[] arr = new int[]{2, 3, 5, 6, 8, 10};
        int sum = 11;
        int n = arr.length;

        int count = countSubsetsWithGivenSum_recursive(arr, n, sum);
        System.out.println(count);

        int[][] t = new int[n + 1][sum + 1];
        for (int[] _idx : t) {
            Arrays.fill(_idx, -1);
        }
        count = countSubsetsWithGivenSum_memo(arr, n, sum, t);
        System.out.println(count);

        count = countSubsetsWithGivenSum_bottomUp(arr, n, sum);
        System.out.println(count);
    }
}
