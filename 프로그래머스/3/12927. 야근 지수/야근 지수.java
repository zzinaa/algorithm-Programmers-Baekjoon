import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        // pq 내림차순 정렬
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int w : works) {
            pq.offer(w);
        }
        
        while(n > 0) { // n 이 0이 될 때 까지
            int w = pq.poll();
            w = w > 0 ? w - 1 : w; // w 가 0보다 크면 -1
            pq.offer(w); // 다시 넣음
            n --;
        }
        
        for(int i = 0; i < works.length; i ++) {
            answer += Math.pow(pq.poll(), 2); // pq 에 있는 것 모두 꺼내서 야근지수 계산
        }
        
        return answer;
    }
}