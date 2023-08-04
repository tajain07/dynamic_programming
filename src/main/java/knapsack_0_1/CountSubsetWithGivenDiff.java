package knapsack_0_1;

import java.util.Arrays;

/**
 * https://practice.geeksforgeeks.org/problems/partitions-with-given-difference/1
 */
public class CountSubsetWithGivenDiff {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 6, 2, 4};
        int n = arr.length;
        int d = 3;

        final int count = countPartitions(n, d, arr);
        System.out.println(count);
    }

    public static int countPartitions(int n, int d, int arr[]) {
        int sum = Arrays.stream(arr).sum();
        int sumPlusDiff = (sum + d);
        if (sumPlusDiff % 2 != 0) {
            return 0;
        }

        int target = sumPlusDiff / 2;

        int[][] dp = new int[n + 1][target + 1];

        dp[0][0] = 1;

        for (int row = 1; row < n + 1; row++) {
            for (int col = 0; col < target + 1; col++) {
                int val = arr[row - 1];

                if (val > col) {
                    dp[row][col] = dp[row - 1][col];
                } else {
                    dp[row][col] = dp[row - 1][col] + dp[row - 1][col - val] % 1000000007;
                }
            }
        }


        /*for(int[] x : dp){
            System.out.println(Arrays.toString(x));

        }*/
        return dp[n][target];
    }

}
