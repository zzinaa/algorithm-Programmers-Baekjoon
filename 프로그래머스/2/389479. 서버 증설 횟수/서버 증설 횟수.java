import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0; // 총 증설 횟수
        int cur = 0; // 현재 증설 개수
        
        int[][] time = new int[players.length][2]; // 현재 증설된 서버의 지속 시간 * 개수
        
        for(int i = 0; i < players.length; i ++) {
                        
            for(int j = 0; j < i; j ++) {
                if(time[j][0] <= 0) continue;
                time[j][0] --;
                if(time[j][0] == 0) { // 시간 다 끝났으면 증설 개수 감소
                    cur -= time[j][1];
                }
            }
            
            int need = (players[i] / m) - cur; // 필요한 증설 개수
            
            if(need > 0) { // 현재 있는 것 제외 하고 더 필요하면
                time[i][0] += k;
                time[i][1] += need; // 시간 측정, 개수 추가
                cur += need; // 증설 개수 증가
                answer += need; // 총 증설 횟수 증가
            }

        }
        
        return answer;
    }
}