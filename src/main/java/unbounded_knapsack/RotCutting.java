package unbounded_knapsack;

public class RotCutting {
  public static void main(String[] args) {
    int[] length = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
    int[] price = new int[] {1, 5, 8, 9, 10, 17, 17, 20};
    int requiredLength = 8;

    int maxProfit = maxProfit_recursive(length, price, requiredLength, length.length);
    System.out.println("maxProfit " + maxProfit);

    maxProfit = maxProfit_bottomUp(length, price, requiredLength, length.length);
    System.out.println("maxProfit " + maxProfit);
  }

  private static int maxProfit_bottomUp(
      int[] length, int[] price, int requiredLength, int sizeOfInput) {

    // 2d dp - as in recursive approach two variables are changing
    int[][] dp = new int[sizeOfInput + 1][requiredLength + 1];

    for (int row = 1; row < sizeOfInput + 1; row++) {
      for (int col = 1; col < requiredLength + 1; col++) {
        int currentLength = length[row - 1];
        int currentPrice = price[row - 1];

        if (currentLength > col) {
          dp[row][col] = dp[row - 1][col];
        } else {
          dp[row][col] = Math.max(currentPrice + dp[row][col - currentLength], dp[row - 1][col]);
        }
      }
    }

    return dp[sizeOfInput][requiredLength];
  }

  private static int maxProfit_recursive(
      int[] length, int[] price, int requiredLength, int sizeOfInput) {

    if (sizeOfInput == 0 || requiredLength == 0) {
      return 0;
    }

    if (length[sizeOfInput - 1] > requiredLength) {
      return maxProfit_recursive(length, price, requiredLength, sizeOfInput - 1);
    } else {
      return Math.max(
          price[sizeOfInput - 1]
              + maxProfit_recursive(
                  length, price, requiredLength - length[sizeOfInput - 1], sizeOfInput),
          +maxProfit_recursive(length, price, requiredLength, sizeOfInput - 1));
    }
  }
}
