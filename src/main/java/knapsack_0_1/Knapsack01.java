package knapsack_0_1;

import java.util.Arrays;

/**
 * Return max profit
 */
public class Knapsack01 {
    static int callCount = 0;

    // 1 - recursive
    public static int knapsack01_recursive(int[] wt, int[] val, int W, int n) {
        if (n == 0 || W == 0) {
            return 0;
        }

        callCount++;
        if (wt[n - 1] <= W) {
            int valIncluding = val[n - 1] + knapsack01_recursive(wt, val, W - wt[n - 1], n - 1);
            int valExcluding = knapsack01_recursive(wt, val, W, n - 1);

            return Math.max(valExcluding, valIncluding);
        } else {
            return knapsack01_recursive(wt, val, W, n - 1);
        }

    }

    // 2. Memoization
    public static int knapsack01_memoization(int[] wt, int[] val, int W, int n, int[][] t) {
        if (n == 0 || W == 0) {
            return 0;
        }

        if (t[n][W] != -1) {
            return t[n][W];
        }

        callCount++;
        if (wt[n - 1] <= W) {
            int valIncluding = val[n - 1] + knapsack01_recursive(wt, val, W - wt[n - 1], n - 1);
            int valExcluding = knapsack01_recursive(wt, val, W, n - 1);

            final int max = Math.max(valExcluding, valIncluding);
            t[n][W] = max;
            return max;
        } else {
            final int maxVal = knapsack01_recursive(wt, val, W, n - 1);
            t[n][W] = maxVal;
            return maxVal;
        }
    }

    // 3. Bottom up | Tabulation
    public static int knapsack01_tabulation(int[] wt, int[] val, int W, int n) {
        int[][] dp = new int[n + 1][W + 1];

        for (int row = 0; row <= n; row++) {
            for (int col = 0; col <= W; col++) {
                if (row == 0 || col == 0) {
                    dp[row][col] = 0;
                }
            }
        }

        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= W; col++) {
                if (wt[row - 1] <= col) {
                    dp[row][col] = Math.max(val[row - 1] + dp[row - 1][col - wt[row - 1]], dp[row - 1][col]);
                } else {
                    dp[row][col] = dp[row - 1][col];
                }
            }
        }

        return dp[n][W];
    }

    public static void main(String[] args) {

        int[] wt = new int[]{1, 3, 4, 5};
        int[] val = new int[]{1, 4, 5, 7};
        int W = 7;
        int n = wt.length;
        callCount = 0;
        int maxValue = knapsack01_recursive(wt, val, 7, n);
        System.out.println("callCount " + callCount + " " + maxValue);


        int[][] t = new int[n + 1][W + 1];

        for (int[] x : t) {
            Arrays.fill(x, -1);
        }

        callCount = 0;
        maxValue = knapsack01_memoization(wt, val, W, n, t);
        System.out.println("callCount " + callCount + " " + maxValue);

        callCount = 0;
        maxValue = knapsack01_tabulation(wt, val, W, n);
        System.out.println(maxValue);


    }
}
