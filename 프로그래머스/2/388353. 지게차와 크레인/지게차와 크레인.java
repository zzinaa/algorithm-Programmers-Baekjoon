import java.util.*;
import java.awt.*;

class Solution {
    
    char[][] arr;
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int n, m;
    
    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();
        arr = new char[n + 2][m + 2];
        
        for(int i = 0; i < n + 2; i ++) {
            for(int j = 0; j < m + 2; j ++) {
                if(i == 0 || j == 0 || i == n + 1 || j == m + 1) arr[i][j] = '0';
            }
        }
        
        for(int i = 1; i < n + 1; i ++) {
            for(int j = 1; j < m + 1; j ++) {
                arr[i][j] = storage[i - 1].charAt(j - 1);
            }
        }
        
        for(int k = 0; k < requests.length; k ++) {
            ArrayList<Point> list = new ArrayList<>();
            
            String rqst = requests[k];
            
            if(rqst.length() == 1) { // 지게차 요청      
                for(int i = 1; i < n + 1; i ++) {
                    for(int j = 1; j < m + 1; j ++) {
                        if(arr[i][j] == rqst.charAt(0)) { // 알파벳 확인
                            
                            for(int l = 0; l < 4; l ++) { // 상하좌우 확인
                                int nx = i + dx[l];
                                int ny = j + dy[l];
                                if(arr[nx][ny] == '0') { // 외부랑 닿아있으면 리스트에 추가
                                    list.add(new Point(i, j));
                                    break;
                                }
                            }
                        }
                    }
                }
                
                for(Point p : list) { // 리스트에 넣은 것에 대해 외부 처리
                    arr[p.x][p.y] = '0';
                    outSide(p.x, p.y);
                } 
                
            } else { // 크레인 요청
                for(int i = 1; i < n + 1; i ++) {
                    for(int j = 1; j < m + 1; j ++) {
                        if(arr[i][j] == rqst.charAt(0)) {
                            arr[i][j] = '1'; // 지게차는 1로 표시
                            outSide(i, j); // 외부 바로 처리
                        }
                    }
                }
                
            }
            
            // System.out.println(result);
        }
        
        int result = 0;
        
        for(int i = 1; i < n + 1; i ++) {
            for(int j = 1; j < m + 1; j ++)
            if(arr[i][j] != '0' && arr[i][j] != '1') result ++;
        } 
        
        
        return result;
    }
    
    private void outSide(int x, int y) {
        boolean out = false;
        for(int i = 0; i < 4; i ++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(arr[nx][ny] == '0') {
                arr[x][y] = '0';
                out = true;
                break;
            }
        }
        
        if(out) {
            for(int i = 0; i < 4; i ++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
            
                if(arr[nx][ny] == '1') {
                    arr[nx][ny] = '0';
                    outSide(nx, ny);
                }
            }
        }
    }
}