import java.util.*;

class Solution {
    
    int n = 2500;
    int[] grp;
    String[] values;
    
    public String[] solution(String[] commands) {
        grp = new int[n];
        values = new String[n];
        List<String> answer = new ArrayList<>();
        
        for(int i = 0; i < n; i ++) {
            grp[i] = i;
        }
        
        for(int i = 0; i < commands.length; i ++) {
            String[] tmp = commands[i].split(" ");
            String cmd = tmp[0];
            // int r = Integer.parseInt(tmp[1]);
            // int c = Integer.parseInt(tmp[2]);
            
            if(cmd.equals("UPDATE")) {
                // 초기화
                if(tmp.length == 4) {
                    int r = Integer.parseInt(tmp[1]) - 1;
                    int c = Integer.parseInt(tmp[2]) - 1;
                    values[find(r * 50 + c)] = tmp[3];
                } else { // 선택 초기화
                    String value1 = tmp[1];
                    String value2 = tmp[2];
                    for(int j = 0; j < n; j ++) {
                        if(values[find(j)] != null && values[find(j)].equals(value1)) {
                            values[find(j)] = value2;
                        }
                    }
                }
            } else if(cmd.equals("MERGE")) {
                int r1 = Integer.parseInt(tmp[1]) - 1;
                int c1 = Integer.parseInt(tmp[2]) - 1;
                int r2 = Integer.parseInt(tmp[3]) - 1;
                int c2 = Integer.parseInt(tmp[4]) - 1;
                
                int cell1 = r1 * 50 + c1;
                int cell2 = r2 * 50 + c2;
                
                // cell1 이 null + cell2가 null 이 아니면 -> 뒤의 셀로 병합
                if(values[find(cell1)] == null && values[find(cell2)] != null) {
                    int temp = cell1;
                    cell1 = cell2;
                    cell2 = temp;
                }
                
                union(cell1, cell2);
            } else if(cmd.equals("UNMERGE")) {
                int r = Integer.parseInt(tmp[1]) - 1;
                int c = Integer.parseInt(tmp[2]) - 1;
                
                int cell = find(r * 50 + c);
                String v = values[cell];
                
                for(int j = 0; j < n; j ++) {
                    find(j);
                }
                
                for(int j = 0; j < n; j ++) {
                    if(find(j) == cell) {
                        grp[j] = j;
                        
                        if(j == r * 50 + c) {
                            values[j] = v;
                        } else {
                            values[j] = null;
                        }
                    }
                }
                
            } else { // 출력
                int r = Integer.parseInt(tmp[1]) - 1;
                int c = Integer.parseInt(tmp[2]) - 1;
                String v = values[find(r * 50 + c)];
                answer.add(v == null ? "EMPTY" : v);
            }
        }
        
        String[] ans = new String[answer.size()];
        
        int i = 0;
        for(String a : answer) {
            ans[i ++] = a;
        }
        
        return ans;
    }
    
    private int find(int idx) {
        if(idx == grp[idx]) {
            return idx;
        }
        return grp[idx] = find(grp[idx]);
    }
    
    private void union(int g1, int g2) {
        g1 = find(g1);
        g2 = find(g2);
        
        if(g1 == g2) {
            return;
        }
        
        values[g2] = null;
        grp[g2] = g1;
    }
}