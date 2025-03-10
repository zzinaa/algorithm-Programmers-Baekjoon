class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        for(int i = n - 1; i >= 0;) {
            
            if(deliveries[i] == 0 && pickups[i] == 0) {
                i --;
                continue;
            }
            
            cal(cap, deliveries, i);
            cal(cap, pickups, i);
            
            answer += (i + 1) * 2;
        }
        
        return answer;
    }
    
    private void cal(int cap, int[] arr, int idx) {
        
        while(idx >= 0) {
            if(cap >= arr[idx]) {
                cap -= arr[idx];
                arr[idx --] = 0;
            } else {
                arr[idx] -= cap;
                break;
            }
        }
    }
}