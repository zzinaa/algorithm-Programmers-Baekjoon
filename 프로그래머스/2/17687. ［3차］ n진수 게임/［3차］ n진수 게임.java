class Solution {
    public String solution(int n, int t, int m, int p) {
        
        String num = "";
        int i = 0;
        
        while(num.length() <= m * t) {
            num += Integer.toString(i ++, n);
        }
                
        String answer = "";
        
        for(int j = p - 1; j < num.length(); j += m) {
            answer += Character.toString(num.charAt(j));
            if(answer.length() == t) break;
        }
        
        return answer.toUpperCase();
    }
}