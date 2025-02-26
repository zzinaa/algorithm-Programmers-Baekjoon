import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        
        int limit = queue1.length * 3 - 3;
        
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        long sumq1 = 0;
        long sumq2 = 0;
        
        for(int i = 0; i < queue1.length; i ++) {
            sumq1 += queue1[i];
            q1.offer(queue1[i]);
            
            sumq2 += queue2[i];
            q2.offer(queue2[i]);
        }
        
        while(answer <= limit) {
            if(sumq1 == sumq2) break;
            
            if(sumq1 < sumq2) {
                sumq2 -= q2.peek() != null ? q2.peek() : 0;
                sumq1 += q2.peek() != null ? q2.peek() : 0;
                q1.add(q2.poll());
            } else {
                sumq2 += q1.peek() != null ? q1.peek() : 0;
                sumq1 -= q1.peek() != null ? q1.peek() : 0;
                q2.add(q1.poll());
            }
            
            answer ++;
        }
        
        return answer > limit ? -1 : answer;
    }
}