import java.util.*;

class Solution {
    
    String[] cate = {"diamond", "iron", "stone"};
    int[][] stress = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
    int min = 1250;
    int n, mLen;
    int[][] mine;
    boolean[] visited;
    
    public int solution(int[] picks, String[] minerals) {
        mLen = minerals.length;
        n = (int) Math.ceil((double) mLen / 5.0); // 광물 5개씩 묶음이 몇개인지
        mine = new int[n][5];
        
        int idx = 0;
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < 5; j ++) {
                if(idx == mLen) break;
                for(int k = 0; k < 3; k ++) {
                    if(minerals[idx].equals(cate[k]))
                        mine[i][j] = k;
                }
                idx ++;
            }
        }
        
        int pSum = Arrays.stream(picks).sum();
        if(pSum * 5 < mLen) n = pSum; // 곡괭이만큼밖에 못캠
        
        int[] pick = new int[pSum];
        int pIdx = 0;
        for(int i = 0; i < 3; i ++) {
            for(int j = 0; j < picks[i]; j ++) {
                pick[pIdx ++] = i;
            }
        }
        
        List<Integer> pickChoice = new ArrayList<>();
        visited = new boolean[pSum];
        
        // for(int i = 0; i < n; i ++) {
        //     for(int j = 0; j < 5; j ++) {
        //         System.out.print(mine[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        
        back(pick, pickChoice);
        
        return min;
    }
    
    // 
    private void back(int[] pick, List<Integer> pickChoice) {
        
        if(pickChoice.size() == n) { // n은 캘 수 있는 세트의 개수
            // System.out.println(Arrays.toString(pickChoice));
            min = Math.min(min, calculate(pickChoice, mine));
            return;
        }
        
        for(int i = 0; i < pick.length; i ++) {
            if(visited[i]) continue;
            if(i > 0 && pick[i] == pick[i - 1] && !visited[i - 1]) continue;
            
            visited[i] = true;
            pickChoice.add(pick[i]);
            back(pick, pickChoice);
            visited[i] = false;
            pickChoice.remove(pickChoice.size() - 1);
        }
    }
    
    private int calculate(List<Integer> pickList, int[][] mine) { // pickArr 에 사용하는 n 개의 곡괭이 idx 존재
        
        int sum = 0;
        int idx = 0;
        for(int i = 0; i < n; i ++) {
            int pickIdx = pickList.get(i);
            for(int j = 0; j < 5; j ++) {
                if(idx == mLen) break;
                sum += stress[pickIdx][mine[i][j]]; // 해당 곡괭이로 해당 광물 캤을 때의 피로도 총합
                idx ++;
            }
        }
        
        // System.out.println(sum);
        
        return sum;
    }
}