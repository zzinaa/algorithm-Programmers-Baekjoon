import java.util.*;

class Solution {
    
    List<Integer>[] graph;
    int[] cost;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        
        graph = new List[n + 1];
        for(int i = 0; i <= n; i ++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int[] road : roads) {
            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
        }
        
        cost = new int[n + 1];
        Arrays.fill(cost, -1);
        bfs(destination);
        
        int[] answer = new int[sources.length];
        
        for(int i = 0; i < sources.length; i ++) {
            answer[i] = cost[sources[i]];
        }
        
        return answer;
    }
    
    private void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        cost[start] = 0;
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            int len = graph[cur].size();
            
            for(int i = 0; i < len; i ++) {
                int next = graph[cur].get(i);
                if(cost[next] == -1) {
                    cost[next] = cost[cur] + 1;
                    q.add(next);
                }
            }
        }
    }
}