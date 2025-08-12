import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int W, H;
    static int[][] map;
    static boolean[][] visited;
    static int[] di = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dj = {0, 0, -1, 1, -1, 1, -1, 1};
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken()); // 컬럼
            H = Integer.parseInt(st.nextToken()); // 로우
            if(W == 0 && H == 0) break;

            map = new int[H][W];
            visited = new boolean[H][W];
            result = 0;

            for(int i = 0; i < H; i ++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < W; j ++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0; i < H; i ++) {
                for(int j = 0; j < W; j ++) {
                    if(map[i][j] == 0 || visited[i][j]) continue;
                    dfs(i, j);
                    result ++;
                }
            }

            System.out.println(result);
        }
    }

    public static void dfs(int i, int j) {
        visited[i][j] = true;

        for(int k = 0; k < 8; k ++) {
            int ni = i + di[k];
            int nj = j + dj[k];
            if(ni >= H || nj >= W || ni < 0 || nj < 0 || visited[ni][nj]) continue;
            if(map[ni][nj] == 0) continue;
            dfs(ni, nj);
        }
    }
}
