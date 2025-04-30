import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int x = scores[0][0];
        int y = scores[0][1];
        
        Arrays.sort(scores, (a, b) -> {
            if(a[0] == b[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });
        
        int max = scores[0][1];
        
        for(int i = 1; i < scores.length; i ++) {
            
            if(max > scores[i][1]) {
                if(scores[i][0] == x && scores[i][1] == y) return -1;
                
                scores[i][0] = -1;
                scores[i][1] = -1;
            } else {
                max = scores[i][1];
            }
        }
        
        Arrays.sort(scores, (a, b) -> (b[0] + b[1]) - (a[0] + a[1]));
        
        int answer = 1;
        
        for(int i = 0; i < scores.length; i ++) {
            if(scores[i][0] + scores[i][1] > x + y) answer ++;
            else break;
        }
        
        return answer;
    }
}