class Solution { 
    int[] sale = {10, 20, 30, 40};
    int[] answer;
    public int[] solution(int[][] users, int[] emoticons) {
        answer = new int[2];
        int[] rates = new int[emoticons.length];
        
        back(users, emoticons, rates, 0);
        
        return answer;
    }
    
    // 모든 할인율 조합
    private void back(int[][] users, int[] emoticons, int[] rates, int depth) {
        if(depth == rates.length) {
            check(users, emoticons, rates);
            return;
        }
        
        for(int i = 0; i < sale.length; i ++) {
            rates[depth] = sale[i];
            back(users, emoticons, rates, depth + 1);
        }
    }
    
    private void check(int[][] users, int[] emoticons, int[] rates) {
        int[] result = new int[2];
        
        for(int i = 0; i < users.length; i ++) {
            int total = 0;
            
            for(int j = 0; j < emoticons.length; j ++) {
                if(users[i][0] <= rates[j]) { // 유저 한 명에 대해 각 이모티콘들이 할인율이 더 높으면
                    total += emoticons[j] * (100 - rates[j]) / 100; // 토탈에 더함
                }
            }
            
            if(total >= users[i][1]) result[0] ++; // 전체 더한 것이 유저 기준 가격을 넘어서면 임티플 가입
            else result[1] += total; // 아니라면 개별 구매
        }
        
        if(result[0] > answer[0]) { // 해당 리컬시브의 결과가 더 좋으면 교체
            answer[0] = result[0]; 
            answer[1] = result[1];
        }
        
        if(result[0] == answer[0] && result[1] > answer[1]) { // 임티플 가입자 수는 같으나 총 금액 결과가 더 좋을 때 교체
            answer[0] = result[0];
            answer[1] = result[1];
        }
    }
}