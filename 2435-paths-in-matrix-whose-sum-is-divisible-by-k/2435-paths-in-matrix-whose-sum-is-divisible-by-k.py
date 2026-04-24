class Solution:
    def numberOfPaths(self, grid, k):
        MOD = 10**9 + 7
        m, n = len(grid), len(grid[0])
        
        # dp[j][r] = number of ways to reach column j with remainder r
        dp = [[0] * k for _ in range(n)]
        
        dp[0][grid[0][0] % k] = 1
        
        # First row
        for j in range(1, n):
            for r in range(k):
                if dp[j-1][r]:
                    new_r = (r + grid[0][j]) % k
                    dp[j][new_r] = (dp[j][new_r] + dp[j-1][r]) % MOD
        
        # Remaining rows
        for i in range(1, m):
            new_dp = [[0] * k for _ in range(n)]
            
            # First column
            for r in range(k):
                if dp[0][r]:
                    new_r = (r + grid[i][0]) % k
                    new_dp[0][new_r] = (new_dp[0][new_r] + dp[0][r]) % MOD
            
            for j in range(1, n):
                for r in range(k):
                    # From top
                    if dp[j][r]:
                        new_r = (r + grid[i][j]) % k
                        new_dp[j][new_r] = (new_dp[j][new_r] + dp[j][r]) % MOD
                    
                    # From left
                    if new_dp[j-1][r]:
                        new_r = (r + grid[i][j]) % k
                        new_dp[j][new_r] = (new_dp[j][new_r] + new_dp[j-1][r]) % MOD
            
            dp = new_dp
        
        return dp[n-1][0]