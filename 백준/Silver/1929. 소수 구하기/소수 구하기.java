import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        int M = Integer.parseInt(arr[0]);
        int N = Integer.parseInt(arr[1]);

//        for(int i = N; i <= M; i ++) {
//            boolean flag = true;
//            for(int j = 2; j < i; j ++) {
//                if(i % j == 0) { // 나눠 떨어지면 소수가 아님
//                    flag = false;
//                    break;
//                }
//            }
//            if(flag) {
//                System.out.println(i);
//            }
//        }

        // 에라토스테네스의 체
        boolean[] prime = new boolean[N+1];

        prime[0] = true;
        prime[1] = true;

        for(int i = 2; i <= Math.sqrt(N + 1); i++){
            if(prime[i]) continue;
            for(int j = i * i; j <= N; j += i){
                prime[j] = true;
            }
        }

        for(int i = M; i <= N; i++){
            if(!prime[i]) System.out.println(i);
        }
    }
}
