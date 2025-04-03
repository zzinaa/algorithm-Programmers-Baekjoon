import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        
        Arrays.sort(routes, (a, b) -> {
            return a[1] - b[1];
        });
        
        List<Integer> cctv = new ArrayList<>();
        
        cctv.add(routes[0][1]);
        
        for(int i = 1; i < routes.length; i ++) {
            int last = cctv.get(cctv.size() - 1);
            if(last < routes[i][0]) cctv.add(routes[i][1]);
        }
        
        return cctv.size();
    }
}