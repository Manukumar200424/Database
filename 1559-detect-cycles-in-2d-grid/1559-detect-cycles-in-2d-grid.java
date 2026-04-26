class Solution {
    int m, n;
    boolean[][] visited;
    
    public boolean containsCycle(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    if (dfs(grid, i, j, -1, -1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] grid, int x, int y, int px, int py) {
        visited[x][y] = true;
        
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx < 0 || ny < 0 || nx >= m || ny >= n)
                continue;
            
            if (grid[nx][ny] != grid[x][y])
                continue;
            
            // If not visited → explore
            if (!visited[nx][ny]) {
                if (dfs(grid, nx, ny, x, y))
                    return true;
            }
            // If visited AND not parent → cycle detected
            else if (nx != px || ny != py) {
                return true;
            }
        }
        
        return false;
    }
}