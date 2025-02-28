import java.util.*;

class Solution {
    
    int[] sale = {10, 20, 30, 40};
    int[] answer;
    
    public int[] solution(int[][] users, int[] emoticons) {
        answer = new int[2];
        
        int[] cur = new int[emoticons.length];
        back(users, emoticons, cur, 0);
        
        return answer;
    }
    
    private void back(int[][] users, int[] emoticons, int[] cur, int depth) {
        
        if(depth == emoticons.length) {
            check(users, emoticons, cur);
            return;
        }
        
        for(int i = 0; i < 4; i ++) {
            cur[depth] = sale[i];
            back(users, emoticons, cur, depth + 1);
        }
    }
    
    private void check(int[][] users, int[] emoticons, int[] cur) {
        
        int[] result = new int[2];
        
        for(int i = 0; i < users.length; i ++) {
            int total = 0;
            for(int j = 0; j < emoticons.length; j ++) {
                if(cur[j] >= users[i][0]) { // 현재 할인율이 비율보다 크다면
                    int price = emoticons[j] * (100 - cur[j]) / 100; // 할인된 가격으로 구매
                    total += price;
                }
            }
                        
            if(total >= users[i][1]) result[0] ++; // 모든 이모티콘을 따져봤을 때 전체 구매 금액이 임티플 가격을 넘어가면 임티플 구매
            else result[1] += total;
        }
        
        if(result[0] > answer[0]) {
            answer[0] = result[0];
            answer[1] = result[1];
        } else if(result[0] == answer[0] && result[1] > answer[1]) {
            answer[1] = result[1];
        }
        
    } 
}