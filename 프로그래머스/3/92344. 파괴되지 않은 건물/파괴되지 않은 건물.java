class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int n = board.length;
        int m = board[0].length;
        
        int[][] tmp = new int[n + 1][m + 1];
        
        for(int[] s : skill) {
            int value = s[0] == 1 ? -1 * s[5] : 1 * s[5];
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            
            tmp[r1][c1] += value;
            tmp[r2 + 1][c2 + 1] += value;
            tmp[r1][c2 + 1] -= value;
            tmp[r2 + 1][c1] -= value;
         }
        
        // 오 -> 왼 누적합
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                tmp[i][j + 1] += tmp[i][j];
            }
        }
        
        // 위 -> 아래 누적합
        for(int j = 0; j < m; j ++) {
            for(int i = 0; i < n; i ++) {
                tmp[i + 1][j] += tmp[i][j];
            }
        }
        
        // 기존 borad 랑 계산
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                int sum = tmp[i][j] + board[i][j];
                if(sum > 0) answer ++;
            }
        }
        
        return answer;
    }
}