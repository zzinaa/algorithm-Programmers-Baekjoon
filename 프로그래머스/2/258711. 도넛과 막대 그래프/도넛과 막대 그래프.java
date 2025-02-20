import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];

        int max = 1;
        for(int i = 0; i < edges.length; i ++) max = Math.max(max, Math.max(edges[i][0], edges[i][1]));
        
        int[][] inout = new int[max + 1][2]; // 0은 들어오고 1은 나가고
        
        for(int i = 0; i < edges.length; i ++) {
            
            int a = edges[i][0];
            int b = edges[i][1];
            
            inout[a][1] += 1; // a 정점 나가는 간선 + 1
            inout[b][0] += 1; // b 정점 들어오는 간선 + 1
        }
        
        for(int i = 1; i < inout.length; i ++) {
            
            if(inout[i][0] == 0 && inout[i][1] >= 2) answer[0] = i; // 시작 정점
            else if(inout[i][1] == 0 && inout[i][0] >= 1) answer[2] += 1; // 막대 그래프 개수
            else if(inout[i][0] > 0 && inout[i][1] == 2) answer[3] += 1; // 8자 그래프 개수
        }
        
        answer[1] = (inout[answer[0]][1] - answer[2] - answer[3]); // 도넛그래프 개수
        
        return answer;
    }
}