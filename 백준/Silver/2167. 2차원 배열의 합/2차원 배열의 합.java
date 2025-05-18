import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[][] arr;
    static int[][] prefix;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][M + 1];
        prefix = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(line[j - 1]);
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1] + arr[i][j];
            }
        }

        K = Integer.parseInt(br.readLine());

        for (int l = 0; l < K; l++) {
            String[] line = br.readLine().split(" ");
            int i = Integer.parseInt(line[0]);
            int j = Integer.parseInt(line[1]);
            int x = Integer.parseInt(line[2]);
            int y = Integer.parseInt(line[3]);

            int result = prefix[x][y] - prefix[i - 1][y] - prefix[x][j - 1] + prefix[i - 1][j - 1];

            System.out.println(result);
        }
    }
}
