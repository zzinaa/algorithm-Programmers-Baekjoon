import java.util.*;

class Solution {
    
    class Robot {
        int x;
        int y;
        
        public Robot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int[][] visited;
    int n, m;
    
    public int solution(String[] board) {
        
        n = board.length;
        m = board[0].length();
        
        visited = new int[n][m];
        
        int ans = 0;
            
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                if(board[i].charAt(j) == 'R') {
                    ans = BFS(board, i, j);
                }
            }
        }
        
        return ans;
    }
    
    private int BFS(String[] board, int a, int b) {
        
        Queue<Robot> q = new LinkedList<>();
        q.offer(new Robot(a, b));
        visited[a][b] = 1;
        
        while(!q.isEmpty()) {
            
            Robot cur = q.poll();
            
            for(int i = 0; i < 4; i ++) {
                int t = 0;
                while(true) {
                    int nx = cur.x + (t + 1) * dx[i];
                    int x = cur.x + t * dx[i];
                    int ny = cur.y + (t + 1) * dy[i];
                    int y = cur.y + t * dy[i];
                    
                    if(nx < 0 || ny < 0 || nx >= n || ny >= m || board[nx].charAt(ny) == 'D') {
                        if(visited[x][y] == 0) {
                            visited[x][y] = visited[cur.x][cur.y] + 1;
                            q.offer(new Robot(x, y));
                        }
                        if(board[x].charAt(y) == 'G') {
                            return visited[x][y] - 1;
                        }
                        break;
                    }
                    t ++;
                }
            }
        }
        return -1;
    }
}