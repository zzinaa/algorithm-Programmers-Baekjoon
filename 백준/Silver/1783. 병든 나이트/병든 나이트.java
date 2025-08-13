import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        int ans = 0;

        if(N == 1) ans = 1;
        else if(N == 2) ans = Math.min(4, (M + 1) / 2);
        else if(M >= 7) ans = M - 2;
        else ans = Math.min(4, M);

        System.out.println(ans);
    }
}
