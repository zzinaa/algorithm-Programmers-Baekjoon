import java.util.*;

class Solution {
    
    ArrayList<ArrayList<Integer>> graph;
    boolean[] visited;
    int answer = 0;
    
    public int solution(int n, int[][] edge) {
        
        graph = new ArrayList<>();
        visited = new boolean[n + 1];
        
        for(int i = 0; i <= n; i ++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < edge.length; i ++) {
            graph.get(edge[i][0]).add(edge[i][1]);
            graph.get(edge[i][1]).add(edge[i][0]);
        }
        
        BFS(1);
        
        return answer;
    }
    
    private void BFS(int start) {
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{start, 0});
        visited[start] = true;
        
        int max = 0;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            
            if(max == cur[1]) answer ++;
            else if(max < cur[1]) {
                max = cur[1];
                answer = 1;
            }
            
            for(int i = 0; i < graph.get(cur[0]).size(); i ++) {
                int next = graph.get(cur[0]).get(i);
                if(visited[next]) continue;
                q.offer(new int[]{next, cur[1] + 1});
                visited[next] = true;
            }
        }
    }
}