import java.util.*;
import java.awt.Point;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        List<List<Point>> list = new ArrayList<>();
        int max = 0;
        
        for(int i = 0; i < routes.length; i ++) {  // 모든 로봇의 경로 계산
            
            List<Point> route = new ArrayList<>(); // 각 로봇 최단 경로 저장
            route.add(new Point(points[routes[i][0] - 1][0], points[routes[i][0] - 1][1]));
            
            for(int j = 0; j < routes[i].length - 1; j ++) { // 로봇 하나의 경로
                int[] start = points[routes[i][j] - 1];
                int[] dest = points[routes[i][j + 1] - 1];
                
                int curr = start[0];
                int curc = start[1];
                
                int r = dest[0] - curr;
                int c = dest[1] - curc;
                                                
                int nr = curr;
                int nc = curc;
                
                for(int k = 0; k < Math.abs(r); k ++) {
                    if(r < 0) nr --;
                    else nr ++;
                    route.add(new Point(nr, start[1]));
                }
                
                for(int k = 0; k < Math.abs(c); k ++) {
                    if(c < 0) nc --;
                    else nc ++;
                    route.add(new Point(nr, nc));
                }
            }
            
            list.add(route);
        }
        
        for(int i = 0; i < list.size(); i ++) {
            max = Math.max(max, list.get(i).size());
        }
        
        // for(List<Point> pl : list) {
        //     for(Point p : pl) System.out.print(p.x + "," + p.y + " ");
        //     System.out.println();
        // }
                
        // 각 루트끼리 비교하며 같은 시간대(index)에 같은 좌표가 있으면 충돌 횟수 +1
        for(int i = 0; i < max + 1; i ++) { // 가장 긴 경로 기준으로
            
            Set<Point> time = new HashSet<>(); // 해당 시간대 point 저장
            Set<Point> crash = new HashSet<>(); // 충돌난 Point 저장
            
            for(int j = 0; j < list.size(); j ++) { // 모든 route들 충돌하는지 검사
                if(list.get(j).size() <= i) continue; // 시간 넘어가는 경로는 검사 x
                
                Point cur = list.get(j).get(i);
                
                // 해당 시간대에 같은 좌표를 가진 게 있고, 이미 충돌로 표시된 게 아니라면 정답 + 1
                if(time.contains(cur) && !crash.contains(cur)) {
                    answer ++;
                    crash.add(cur);
                }
                time.add(cur);
            }
            
            // System.out.println("time " + time);
            // System.out.println("cruch " + crash);
        }
        
        return answer;
    }
}