class PrintingLongestCommonSubsequence
{
    public List<String> all_longest_common_subsequences(String s, String t)
    {
        int l1 = s.length();
        int l2 = t.length();
        
        int[][] dp = new int[l1+1][l2+1];
        
        for(int row = 1; row < l1+1; row++){
            for(int col = 1; col < l2+1; col++){
                if(s.charAt(row-1) == t.charAt(col-1)){
                    dp[row][col] = dp[row-1][col-1]+1;
                }else{
                    dp[row][col] = Math.max(dp[row-1][col], dp[row][col-1]);
                }
            }
        }
        
        
        StringBuilder result = new StringBuilder();
        int row = l1;
        int col = l2;
        
        while(row >0 && col > 0){
            if(s.charAt(row-1) == t.charAt(col-1)){
                result.append(s.charAt(row-1));
                row--;
                col--;
            }else{
                if(dp[row-1][col] > dp[row][col-1]){
                    row--;
                }else{
                    col--;
                }
            }

        }
        
        System.out.println(result.reverse().toString()); // reverse
        return new ArrayList<>();
    }
}
