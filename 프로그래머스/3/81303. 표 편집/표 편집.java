import java.util.*;

class Solution {
    
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
    
    public String solution(int n, int k, String[] cmd) {
        
        Stack<Node> stack = new Stack<>();
        Node cur = new Node(0);
        Node head = null;
        if(k == 0) head = cur;
        
        for(int i = 1; i < n; i ++) {
            Node node = new Node(i);
            cur.next = node;
            node.prev = cur;
            cur = node;
            if(i == k) head = cur;
        }
        
        for(String c : cmd) {
            char cc = c.charAt(0);
            if(cc == 'U') {
                int cn = Integer.parseInt(c.split(" ")[1]);
                for(int i = 0; i < cn; i ++) {
                    head = head.prev;
                }
            } else if(cc == 'D') {
                int cn = Integer.parseInt(c.split(" ")[1]);
                for(int i = 0; i < cn; i ++) {
                    head = head.next;
                }
            } else if(cc == 'C') {
                stack.push(head);
                if(head.prev != null) head.prev.next = head.next;
                if(head.next != null) {
                    head.next.prev = head.prev;
                    head = head.next;
                } else head = head.prev;
            } else if(cc == 'Z') {
                Node restore = stack.pop();
                if(restore.prev != null) restore.prev.next = restore;
                if(restore.next != null) restore.next.prev = restore;
            }
        }
        
        char[] arr = new char[n];
        Arrays.fill(arr, 'X');
        
        while(head.prev != null) {
            head = head.prev;
        }
        
        while(head != null) {
            arr[head.num] = 'O';
            head = head.next;
        }
        
        return new String(arr);
     }
}