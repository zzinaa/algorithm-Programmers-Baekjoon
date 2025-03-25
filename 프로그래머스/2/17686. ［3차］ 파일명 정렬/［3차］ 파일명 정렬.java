import java.util.*;

class File {
    
    String head;
    int number;
    String tail;
    String origin;
    
    public File(String origin) {
        this.origin = origin;
        
        int idx = 0;
        while((int) origin.charAt(idx) < 48 || (int) origin.charAt(idx) > 57) {
            idx ++;
        }
        int tmp = idx;
        head = origin.substring(0, tmp).toLowerCase();
        while((int) origin.charAt(idx) >= 48 && (int) origin.charAt(idx) <= 57) {
            idx ++;
            if(idx == origin.length()) break;
        }
        if(tmp + 5 < idx) idx = tmp + 5;
        number = Integer.parseInt(origin.substring(tmp, idx));
    }
    
    public String getHead() {
        return head;
    }
    
    public int getNumber() {
        return number;
    }
    
    public String getOrigin() {
        return origin;
    }
}

class Solution {
    public String[] solution(String[] files) {
        
        List<File> list = new ArrayList<>();
        for(String f : files) {
            list.add(new File(f));
        }
        
        list.sort(Comparator.comparing(File::getHead)
                 .thenComparingInt(File::getNumber));
        
        return list.stream().map(File::getOrigin).toArray(String[]::new);
    }
}