import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        int[][] book = new int[book_time.length][2];
        
        for(int i = 0; i < book_time.length; i ++) {
            int start = Integer.parseInt(book_time[i][0].replace(":", ""));
            int end = Integer.parseInt(book_time[i][1].replace(":", ""));
            
            end += 10;
            
            if(end % 100 >= 60) {
                end += 40;
            }
            
            book[i][0] = start;
            book[i][1] = end;
        }
        
        Arrays.sort(book, (a, b) -> {
            return a[0] - b[0];
        });
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        
        for(int[] b : book) {
            if(pq.isEmpty()) {
                pq.add(b);
            } else {
                int[] tmp = pq.peek();
                int s = tmp[0];
                int e = tmp[1];
                
                if(b[0] >= e) {
                    pq.poll();
                }
                pq.add(b);
            }
        }
        
        answer = pq.size();
        
        return answer;
    }
}