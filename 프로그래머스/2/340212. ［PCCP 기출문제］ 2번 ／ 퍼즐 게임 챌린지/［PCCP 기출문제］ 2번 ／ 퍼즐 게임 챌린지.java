class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        
        return bs(diffs, times, limit);
    }
    
    private int bs(int[] diffs, int[] times, long limit) {
        int max = 1; int min = 100000; // max : level 1로 문제 푼 시간, min : level 100000으로 문제 푼 시간
        
        while(max <= min) {
            int level = (min + max) / 2; // level을 가운데 값으로 설정
            long mid = cal(level, diffs, times); // 해당 level 일 때 총 걸리는 시간 (=mid)
            
            if(limit < mid) max = level + 1; // limit 보다 mid가 더 걸린다면 level 상향
            else min = level - 1; // limit 보다 mid가 더 적게 걸린다면 level 하향
            // 반복하며 max값이 min값이 넘어가면 level 을 알 수 있음
        }
        
        return max;
    }
    
    private long cal(int level, int[] diffs, int[] times) {
        
        long total = times[0];
        for(int i = 1; i < diffs.length; i ++) {
            if(level >= diffs[i]) total += times[i];
            else total += ((diffs[i] - level) * (times[i - 1] + times[i]) + times[i]);
        }
        return total;
    }
}