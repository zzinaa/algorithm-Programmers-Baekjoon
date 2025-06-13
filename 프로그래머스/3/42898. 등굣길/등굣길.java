class Solution {
    public int solution(int m, int n, int[][] puddles) {        
        int[][] dp = new int[m + 1][n + 1];
        dp[1][1] = 1;
        
        for(int[] p: puddles) {
            dp[p[0]][p[1]] = -1;
        }
        
        for(int i = 1; i <= m; i ++) {
            for(int j = 1; j <= n; j ++) {
                if(dp[i][j] == -1) {
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] += (dp[i - 1][j] + dp[i][j - 1]) % 1000000007;
            }
        }
        
        return dp[m][n];
    }
}