import java.util.*;

class Solution {
    
    int max = 1;
    ArrayList<ArrayList<Integer>> graph;
    boolean[][][] visited;
    int[] node;
    
    public int solution(int[] info, int[][] edges) {
        node = info;
        graph = new ArrayList<>();
        visited = new boolean[17][18][18]; // 현재 노드 상태에서 양, 늑대의 개수 (같은 노드를 같은 상태로 반복하지 않기 위해)
        for(int i = 0; i < 17; i ++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < edges.length; i ++) {
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }
                
        dfs(0, 0, 0);
        
        return max;
    }
    
    private void dfs(int cur, int s, int w) {
        
        if(visited[cur][s][w]) return;
        
        int bs = s, bw = w;
        int bnc = node[cur];
        
        if(node[cur] == 0) ++s;
        else if(node[cur] == 1) ++w;
        
        node[cur] = -1;
        visited[cur][s][w] = true;
        
        if(s > w) {
            max = Math.max(s, max);
            
            for(int next : graph.get(cur)) {
                dfs(next, s, w);
            }
        }
        
        node[cur] = bnc;
        visited[cur][bs][bw] = false;
    }
}