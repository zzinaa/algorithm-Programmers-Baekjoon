import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        
        Map<Integer, Integer> map = new HashMap<>();
        for(int t : tangerine) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        Set<Integer> set = new HashSet<>();
        int total = 0;
        int i = 0;
        int result = 0;
        while(total < k) {
            total += list.get(i).getValue();
            result ++;
            i++;
        }
        
        return result;
    }
}