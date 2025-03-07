class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        
        // 지시대로 번호 쌓고, 해당 번호의 열번호 뽑기 (0 개수만큼 마이너스)
        
        int r = n % w == 0 ? n / w : n / w + 1;
        int[][] arr = new int[r][w];
        int box = 1;
        int x = 0; int y = 0;
        
        for(int i = 0; i < r; i ++) {
            
            if(i % 2 == 0) {
                for(int j = 0; j < w; j ++) {
                    if(box == num) {
                        x = i;
                        y = j;
                    }
                    arr[i][j] = box;
                    box += 1;
                    if(n < box) break;
                }
            } else {
                for(int j = w - 1; j >= 0; j --) {
                    if(box == num) {
                        x = i;
                        y = j;
                    }
                    arr[i][j] = box;
                    box += 1;
                    if(n < box) break;
                }
            }
        }
        
        // for(int i = 0; i < r; i ++) {
        //     for(int j = 0; j < w; j ++) {
        //         System.out.print(arr[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        
        for(int i = r - 1; i >= x; i --) {
            if(arr[i][y] == 0) continue;
            answer ++;
        }
        
        return answer;
    }
}