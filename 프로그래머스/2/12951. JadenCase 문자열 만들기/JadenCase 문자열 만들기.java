class Solution {
    public String solution(String s) {
        
        StringBuilder sb = new StringBuilder();
        char[] arr = s.toCharArray();
        
        for(int i = 0; i < arr.length; i ++) {
            if(Character.isAlphabetic(arr[i])) { //알파벳일때
                if(i == 0) //맨 첫번째 글자일 경우
                    sb.append(Character.toUpperCase(arr[i])); 
                else if(arr[i - 1] == ' ') //맨 첫번째는 아니면서, 공백 다음 글자일 경우
                    sb.append(Character.toUpperCase(arr[i]));
                else sb.append(Character.toLowerCase(arr[i])); // 중간에 있는 글자일 경우
            } else { //알파벳이 아니라면
                sb.append(arr[i]);
            }
        }
        
        return sb.toString();
    }
}