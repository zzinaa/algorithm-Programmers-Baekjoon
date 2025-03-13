import java.util.*;
import java.awt.Point;

class Solution {
    
    int n, m;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    boolean[][] visited;
    
    public int[] solution(String[] maps) {
        List<Integer> list = new ArrayList<>();
        
        n = maps.length;
        m = maps[0].length();
        visited = new boolean[n][m];
        
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                if(!visited[i][j] && maps[i].charAt(j) != 'X') {
                    int result = bfs(maps, i, j);
                    list.add(result);
                }
            }
        }
        
        if(list.size() == 0) return new int[]{-1};
        
        Collections.sort(list);
        
        return list.stream().mapToInt(i -> i).toArray();
    }
    
    private int bfs(String[] maps, int x, int y) {
        
        int sum = 0;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        sum += maps[x].charAt(y) - '0';
        visited[x][y] = true;
        
        while(!q.isEmpty()) {
            Point cur = q.poll();
            for(int i = 0; i < 4; i ++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                                
                if(nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny]) continue;
                if(maps[nx].charAt(ny) == 'X') continue;
                
                q.add(new Point(nx, ny));
                sum += maps[nx].charAt(ny) - '0';
                visited[nx][ny] = true;                
            }
        }
        
        return sum;
    }
}