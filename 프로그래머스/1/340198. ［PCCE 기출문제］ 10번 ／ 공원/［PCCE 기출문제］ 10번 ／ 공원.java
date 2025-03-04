import java.util.*;

class Solution {
    
    public int solution(int[] mats, String[][] park) {
        int answer = -1;
        
        Arrays.sort(mats);
        
        for(int i = 0; i < park.length; i ++) {
            for(int j = 0; j < park[0].length; j ++) {
                if(park[i][j].equals("-1")) {
                    answer = Math.max(getMaxSize(mats, park, i, j), answer);
                    if(answer == mats[mats.length - 1]) return answer;
                }
            }
        }
        
        return answer;
    }
    
    private int getMaxSize(int[] mats, String[][] park, int x, int y) {
        int max = -1;
        
        // 매트 큰 순 부터
        for(int i = mats.length - 1; i >= 0; i --) {
            int size = mats[i]; 
            
            if(x + size - 1 >= park.length || y + size - 1 >= park[0].length) continue;
            
            boolean flag = true;
            
            for(int j = x; j < x + size; j ++) {
                for(int k = y; k < y + size; k ++) {
                    if(!park[j][k].equals("-1")) {
                        flag = false;
                        break;
                    }
                }
            }
            
            if(flag) {
                max = size;
                break;
            }
        }
        
        return max;
    }
}