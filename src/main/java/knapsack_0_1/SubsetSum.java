package knapsack_0_1;

import java.util.Arrays;

public class SubsetSum {

    static int callCount = 0;

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 4, 2, 1};
        int sum = 6;
        int n = arr.length;
        boolean result = isSubsetSumPresent_recursive(n, arr, sum);
        System.out.println("callCount " + callCount + " " + result);

        Boolean[][] t = new Boolean[n + 1][sum + 1];
        callCount = 0;
        final boolean subsetSumPresent_memoized = isSubsetSumPresent_memoized(n, arr, sum, t);
        System.out.println("callCount " + callCount + " subsetSumPresent_memoized " + subsetSumPresent_memoized);

        final boolean subsetSumPresent_tabulation = isSubsetSumPresent_tabulation(n, arr, sum);
        System.out.println("subsetSumPresent_tabulation " + subsetSumPresent_tabulation);

    }


    /**
     * Recursive approach
     *
     * @param n
     * @param arr
     * @param sum
     * @return
     */
    private static boolean isSubsetSumPresent_recursive(int n, int[] arr, int sum) {

        //sum is 0 means
        // 1. either we have achieved the required the sum
        // 2. required sum is 0
        if (sum == 0) {
            return true;
        }
        // n is 0 means that there is no element in array
        else if (n == 0) {
            return false;
        }

        callCount++;

        //include and cannot include
        if (arr[n - 1] <= sum) {

            return isSubsetSumPresent_recursive(n - 1, arr, sum - arr[n - 1]) || isSubsetSumPresent_recursive(n - 1, arr, sum);

        }
        //cannot include
        else {
            return isSubsetSumPresent_recursive(n - 1, arr, sum);
        }
    }


    /**
     * Memoized approach
     *
     * @param n
     * @param arr
     * @param sum
     * @param t
     * @return
     */
    private static boolean isSubsetSumPresent_memoized(int n, int[] arr, int sum, Boolean[][] t) {
        //sum is 0 means
        // 1. either we have achieved the required the sum
        // 2. required sum is 0
        if (sum == 0) {
            return true;
        }
        // n is 0 means that there is no element in array
        else if (n == 0) {
            return false;
        }

        if (t[n][sum] != null) {
            return t[n][sum];
        }

        callCount++;

        //include and cannot include
        if (arr[n - 1] <= sum) {
            return t[n][sum] = isSubsetSumPresent_memoized(n - 1, arr, sum - arr[n - 1], t) || isSubsetSumPresent_memoized(n - 1, arr, sum, t);
        }
        //cannot include
        else {
            return t[n][sum] = isSubsetSumPresent_memoized(n - 1, arr, sum, t);
        }
    }

    /**
     * Bottom up / Tabulation approach
     *
     * @param n
     * @param arr
     * @param sum
     * @return
     */
    private static boolean isSubsetSumPresent_tabulation(int n, int[] arr, int sum) {
        //System.out.println("n " + n + " sum " + sum);
        boolean[][] dp = new boolean[n + 1][sum + 1];

        for (int row = 0; row <= n; row++) {
            dp[row][0] = true;
        }

        for (int col = 0; col <= sum; col++) {
            dp[0][col] = false;
        }

        for (int row = 1; row < n + 1; row++) {
            for (int currentSum = 1; currentSum < sum + 1; currentSum++) {
                int val = arr[row - 1];
                if (val > currentSum) {
                    dp[row][currentSum] = dp[row - 1][currentSum];
                } else {
                    dp[row][currentSum] = dp[row - 1][currentSum] || dp[row - 1][currentSum - val];
                }
            }

            // optimization - if we found an answer then we can return true, no need to go further
            if (dp[row][sum]) {
                return true;
            }
        }

        return dp[n][sum];
    }


}
