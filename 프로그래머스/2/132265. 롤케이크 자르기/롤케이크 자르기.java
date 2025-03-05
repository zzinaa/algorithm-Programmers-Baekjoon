import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        Map<Integer, Integer> order = new HashMap<>();
        Map<Integer, Integer> younger = new HashMap<>();
        
        order.put(topping[0], 1);
        for(int i = 1; i < topping.length; i ++) {
            younger.put(topping[i], younger.getOrDefault(topping[i], 0) + 1);
        }
        
        if(order.size() == younger.size()) answer ++;
        
        for(int i = 1; i < topping.length - 1; i ++) {
            
            order.put(topping[i], order.getOrDefault(topping[i], 0) + 1);
            if(younger.get(topping[i]) == 1) {
                younger.remove(topping[i]);
            } else {
                younger.put(topping[i], younger.get(topping[i]) - 1);
            }
            
            if(order.size() == younger.size()) answer ++;
        }
        
        return answer;
    }
}