import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        
        int size = plans.length;
        
        List<String> answer = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        int[][] np = new int[size][3];
        
        for(int i = 0; i < size; i++) {
            String[] tmp = plans[i][1].split(":");
            int time = Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]);
            np[i][0] = i; // 기존 인덱스
            np[i][1] = time; // start 분 변환
            np[i][2] = Integer.parseInt(plans[i][2]); // 과제 playtime
        }
        
        Arrays.sort(np, (a, b) -> Integer.compare(a[1], b[1])); // 새로운 np 배열에 대하여 시작시간 순으로 정렬
        
        for(int i = 0; i < size - 1; i ++) {
            
            int diff = np[i + 1][1] - np[i][1]; // 앞 뒤 시작시간의 차
            int pt = np[i][2]; // 현재 playtime
            
            if(diff < pt) { // 한번에 다 끝낼 수 없으면 스택 삽입, diff 만큼만 감소
                stack.push(i);
                np[i][2] -= diff;
            } else { // 현재 playtime 보다 diff 가 더 크거나 같으면
                answer.add(plans[np[i][0]][0]); // 정답에 추가
                diff -= pt;
                while(diff > 0) { // diff 가 남았다면
                    if(stack.isEmpty()) break;
                    int idx = stack.pop();
                    if(diff >= np[idx][2]) { // 스택에 남아있는 과제 완료
                        answer.add(plans[np[idx][0]][0]);
                        diff -= np[idx][2];
                    } else {
                        stack.push(idx);
                        np[idx][2] -= diff; // 가능한 만큼만 완료
                        diff = 0;
                    }
                }
            }
        }
        
        answer.add(plans[np[size - 1][0]][0]);
        while(!stack.isEmpty()) {
            int idx = stack.pop();
            answer.add(plans[np[idx][0]][0]);
        }
        
        return answer.toArray(new String[size]);
    }
}