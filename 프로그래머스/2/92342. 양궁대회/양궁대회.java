import java.util.*;

class Solution {
    int[] ryan = new int[11];
    int[] apeach = new int[11];
    int maxPoint = 0;
    ArrayList<int[]> comb = new ArrayList<>();
    
    public int[] solution(int n, int[] info) {
        
        apeach = info;
        
        int ascore = 0;
        for(int i = 0; i < 11; i ++) {
            if(apeach[i] > 0) ascore += (10 - i);
        }
        
        maxPoint = -ascore;
        dfs(0, n, maxPoint);
        
        if(maxPoint <= 0) return new int[]{-1};
        
        for(int i = 0; i <= 10; i ++) {
            if(comb.size() == 1) break;
            
            int maxCount = 0;
            for(int[] ryan : comb) {
                maxCount = Math.max(ryan[10 - i], maxCount);
            }
            
            int c = 0;
            while(c < comb.size()) {
                if(comb.get(c)[10 - i] != maxCount) comb.remove(c);
                else c ++;
            }
        }
        
        return comb.get(0);
    }
    
    public void dfs(int index, int n, int nowPoint) {
        if(n == 0) {
            if(nowPoint > maxPoint) {
                maxPoint = nowPoint;
                comb = new ArrayList<>();
                comb.add(ryan.clone());
            } else if(nowPoint == maxPoint) {
                comb.add(ryan.clone());
            }
        } else if(n >= 0 && index < 11) {
            for(int i = 0; i <= apeach[index] + 1; i ++) {
                ryan[index] += i;
                
                int nextPoint = nowPoint;
                if(ryan[index] > apeach[index]) {
                    if(apeach[index] == 0) nextPoint += (10 - index);
                    else if(apeach[index] > 0) nextPoint += (10 - index) * 2;
                }
                
                dfs(index + 1, n - i, nextPoint);
                
                ryan[index] -= i;
            }
        }
    }
}