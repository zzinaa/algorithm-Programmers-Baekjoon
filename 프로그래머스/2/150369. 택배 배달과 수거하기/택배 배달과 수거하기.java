class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        for(int i = n - 1; i >= 0;) { // 가장 먼 곳 부터
            if(deliveries[i] == 0 && pickups[i] == 0) {
                i --; // 배달할 것도, 픽업할 것도 없으면 앞으로 한칸 온다
                continue;
            }
            
            // 배달 or 픽업 남아 있으면 해당 인덱스부터 모두 계산
            cal(cap, deliveries, i);
            cal(cap, pickups, i);
            
            answer += (i + 1) * 2; // 인덱스 + 1 (편도) * 2 (왕복)
            // 둘 중 하나라도 남아있는 경우 왕복으로 왔다갔다 해야하므로 계산
        }
        
        return answer;
    }
    
    private void cal(int cap, int[] arr, int idx) { // 최대한 많이 담아서 해결(왕복 줄이기 위함)
        
        while(idx >= 0) { // idx 뒤에서 앞으로, 한 칸이라도 덜 가야 하므로
            if(cap >= arr[idx]) { // 남은 빈자리가 현재 인덱스에서 배달 or 수거할 것보다 크거나 같다면
                cap -= arr[idx]; // 필요한 만큼 빈자리 제거(채움)
                arr[idx --] = 0; // idx 앞으로 오면서 현재 자리 0 처리
            } else { // 남은 빈자리가 지금 필요한 크기보다 작다면
                arr[idx] -= cap; // 할 수 있는 만큼만 제거
                break; // 멈춤
            }
        }
    }
}