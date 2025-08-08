import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int n;
    static long[] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        memo = new long[n+1];
        for(int i = 0; i < n + 1; i ++) memo[i] = -1;

        System.out.print(fibo(n));
    }

    public static long fibo(int n){
        if(n <= 3) return 1;
        if (memo[n] != -1) return memo[n];
        return memo[n] = fibo(n - 1) + fibo(n - 3);
    }
}
