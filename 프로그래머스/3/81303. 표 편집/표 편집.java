import java.util.*;

class Node {
    int num;
    Node prev;
    Node next;
    
    public Node(int num) {
        this.num = num;
        this.prev = null;
        this.next = null;
    }
}

class Solution {
    public String solution(int n, int k, String[] cmd) {
        
        Stack<Node> del = new Stack<>();
        Node head = null;
        Node cur = new Node(0);
        if(k == 0) head = cur;
        
        for(int i = 1; i < n; i ++) {
            Node node = new Node(i);
            cur.next = node;
            node.prev = cur;
            cur = node;
            if(k == i) head = cur;
        }
        
        for(int i = 0; i < cmd.length; i ++) {
            char cs = cmd[i].charAt(0);
            
            if(cs == 'U') {
                int cn = Integer.parseInt(cmd[i].split(" ")[1]);
                for(int j = 0; j < cn; j ++) {
                    head = head.prev;
                }
            } else if (cs == 'D') {
                int cn = Integer.parseInt(cmd[i].split(" ")[1]);
                for(int j = 0; j < cn; j ++) {
                    head = head.next;
                }
            } else if (cs == 'C') {
                del.push(head);
                if(head.prev != null) {
                    head.prev.next = head.next;
                }
                if(head.next != null) {
                    head.next.prev = head.prev;
                    head = head.next;
                } else {
                    head = head.prev;
                }
            } else if (cs == 'Z') {
                Node restore = del.pop();
                if(restore.prev != null)
                    restore.prev.next = restore;
                if(restore.next != null)
                    restore.next.prev = restore;
            }
        }
        
        while(head.prev != null) {
            head = head.prev;
        }
        
        char[] arr = new char[n];
        Arrays.fill(arr, 'X');
        
        while(head != null) {
            arr[head.num] = 'O';
            head = head.next;
        }
        
        return new String(arr);
    }
}