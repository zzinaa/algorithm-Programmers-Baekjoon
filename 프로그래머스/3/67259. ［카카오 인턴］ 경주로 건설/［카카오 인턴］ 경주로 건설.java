import java.util.*;

class Solution {
    
    int[][][] cost;
    int N;
    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};
    
    public int solution(int[][] board) {
        
        N = board.length;
        cost = new int[N][N][4];
        
        for (int[][] layer : cost)
            for (int[] row : layer)
                Arrays.fill(row, Integer.MAX_VALUE);
        
        bfs(board);
        
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < 4; i ++) {
            min = Math.min(min, cost[N-1][N-1][i]);
        }
        return min;
    }
    
    private void bfs(int[][] board) {
        Queue<int[]> q = new LinkedList<>();
        
        for(int i = 0; i < 4; i ++) {
            cost[0][0][i] = 0;
            q.offer(new int[]{0, 0, i, 0}); // y, x, dir, cost
        }
        
        while(!q.isEmpty()) {
            
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];
            int dir = cur[2];
            int c = cur[3];
            
            for(int i = 0; i < 4; i ++) {
                
                int ny = y + dy[i];
                int nx = x + dx[i];
                
                if(ny < 0 || nx < 0 || ny >= N || nx >= N || board[ny][nx] == 1) continue;
                
                int nc = c + ((dir == i) ? 100 : 600);
                
                if(cost[ny][nx][i] > nc) {
                    cost[ny][nx][i] = nc;
                    q.offer(new int[]{ny, nx, i, nc});
                }
            }
        }
        
    }
}