import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        HashSet<String> set = new HashSet<>(Arrays.asList(gems));
        
        int kind = set.size();
        int length = Integer.MAX_VALUE;
        int start = 0;
        
        // 보석 - 보석 개수
        Map<String, Integer> map = new HashMap<>();
        
        for(int end = 0; end < gems.length; end ++) {
            map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);
            
            while(map.get(gems[start]) > 1) { // start 의 보석이 중복이라면
                map.put(gems[start], map.get(gems[start]) - 1); // 한개 삭제
                start ++; // 구간 1 증가
            }
            
            // 모든 보석 탐색 완료 + 최단구간일 경우
            if(map.size() == kind && length > (end - start)) {
                length = end - start;
                answer[0] = start + 1;
                answer[1] = end + 1;
            }
        }
        
        return answer;
    }
}