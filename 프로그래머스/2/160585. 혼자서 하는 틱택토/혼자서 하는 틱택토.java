class Solution {
    public int solution(String[] board) {
        
        int osum = 0;
        int xsum = 0;
        
        for(int i = 0; i < 3; i ++) {
            for(int j = 0; j < 3; j ++) {
                if(board[i].charAt(j) == 'O') osum ++;
                else if(board[i].charAt(j) == 'X') xsum ++;
            }
        }
        
        if(xsum > osum) return 0;
        
        if(osum > xsum + 1) return 0;
        
        if(hasWin(board, 'O')) {
            if(osum == xsum) return 0;
        }
        
        if(hasWin(board, 'X')) {
            if(osum == xsum + 1) return 0;
        }
        
        return 1;
    }
    
    private boolean hasWin(String[] board, char ch) {
        
        for(int i = 0; i < 3; i ++) {
            if(board[i].charAt(0) == ch &&
              board[i].charAt(1) == ch &&
              board[i].charAt(2) == ch) {
                return true;
            } 
        }
        
        for(int i = 0; i < 3; i ++) {
            if(board[0].charAt(i) == ch &&
              board[1].charAt(i) == ch &&
              board[2].charAt(i) == ch) {
                return true;
            }
        }
        
        if(board[0].charAt(0) == ch &&
          board[1].charAt(1) == ch &&
          board[2].charAt(2) == ch) return true;
        
        if(board[0].charAt(2) == ch &&
          board[1].charAt(1) == ch &&
          board[2].charAt(0) == ch) return true;
        
        return false;
    }
}