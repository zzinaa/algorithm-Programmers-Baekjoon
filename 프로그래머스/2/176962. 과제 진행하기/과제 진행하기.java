import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        List<String> list = new ArrayList<>();
        Stack<String[]> stack = new Stack<>();
        
        for(String[] p : plans) {
            String[] time = p[1].split(":");
            int minutes = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
            p[1] = Integer.toString(minutes);            
        }
        
        Arrays.sort(plans, (a, b) -> {
            return Integer.parseInt(a[1]) - Integer.parseInt(b[1]);
        });
        
        for(int i = 1; i < plans.length; i ++) {
            String[] cur = plans[i - 1];
            String[] next = plans[i];
            
            int curTotal = Integer.parseInt(cur[1]) + Integer.parseInt(cur[2]);
            int nextStart = Integer.parseInt(next[1]);
            
            if(curTotal <= nextStart) { // 현재 과제를 한번에 다 했을 때 다음 것 시간에 아직 못미친다면 (한번에 완료)
                list.add(cur[0]);
                if(nextStart - curTotal > 0) { // 다 하고도 시간이 남으면
                    int remain = nextStart - curTotal;
                    while(remain > 0 && !stack.isEmpty()) { // stack에 들어가있는 과제 하기
                        String[] work = stack.pop();
                        int time = Integer.parseInt(work[2]);
                        if(remain - time >= 0) {
                            list.add(work[0]);
                            remain -= time;
                        }
                        else {
                            work[2] = Integer.toString(time - remain);
                            stack.push(work);
                            remain = 0;
                        }
                    }
                }
            } else { // 중간에 다음 과제로 넘어가야 할 때
                int cap = nextStart - Integer.parseInt(cur[1]);
                cur[2] = Integer.toString(Integer.parseInt(cur[2]) - cap);
                stack.push(cur);
            }
        }
        
        // 마지막 거 List에 넣고, stack 순서대로 list
        list.add(plans[plans.length - 1][0]);
        while(!stack.isEmpty()) {
            list.add(stack.pop()[0]);
        }
        
        return list.toArray(new String[list.size()]);
    }
}