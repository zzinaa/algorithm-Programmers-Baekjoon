import java.util.*;
import java.awt.Point;

class Solution {
    
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int[][] visited;
    int n, m;
    
    public int solution(String[] maps) {
        
        int answer = -1;
        
        n = maps.length;
        m = maps[0].length();
        
        visited = new int[n][m];
        
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                if(maps[i].charAt(j) == 'S') {
                    answer = bfs(maps, i, j);
                }
            }
        }
        
        return answer;
    }
    
    private int bfs(String[] maps, int x, int y) {
        
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));
        boolean open = false;
        
        while(!q.isEmpty()) {
            
            Point cur = q.poll();
            if(maps[cur.x].charAt(cur.y) == 'L' && !open) {
                open = true;
                q = new LinkedList<>();
                int num = visited[cur.x][cur.y];
                visited = new int[n][m];
                visited[cur.x][cur.y] = num;
            } else if(maps[cur.x].charAt(cur.y) == 'E' && open) {
                return visited[cur.x][cur.y];
            }
            
            for(int i = 0; i < 4; i ++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= n || ny >= m || maps[nx].charAt(ny) == 'X') continue;
                if(visited[nx][ny] != 0) continue; 
                q.offer(new Point(nx, ny));
                visited[nx][ny] = visited[cur.x][cur.y] + 1;
            }
        }
        
        return -1;
    }
}