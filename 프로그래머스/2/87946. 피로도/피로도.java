import java.util.*;

class Solution {
    int max = 0;
    int cur, len;
    public int solution(int k, int[][] dungeons) {
        cur = k;
        len = dungeons.length;
        boolean[] visited = new boolean[len];
        int[] arr = new int[len];
        dfs(0, visited, arr, dungeons);
        
        return max;
    }
    
    private void dfs(int dep, boolean[] visited, int[] arr, int[][] dungeons) {
        
        if(dep == len) {
            check(arr, dungeons);
            return;
        }
        
        for(int i = 0; i < len; i ++) {
            if(visited[i]) continue;
            arr[dep] = i;
            visited[i] = true;
            dfs(dep + 1, visited, arr, dungeons);
            visited[i] = false;
        }
    }
    
    private void check(int[] arr, int[][] dungeons) {
        int stress = cur;
        int cnt = 0;
        for(int i = 0; i < len; i ++){
            if(stress < dungeons[arr[i]][0]) {
                break;
            }
            
            stress -= dungeons[arr[i]][1];
            cnt ++;
        }
        
        max = Math.max(cnt, max);
    }
}