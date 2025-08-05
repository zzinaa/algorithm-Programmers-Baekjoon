import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int[][] arr;
    public static boolean[][] visited;
    public static int result = 0;

    public static int[] di = {-1, 1, 0, 0};
    public static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        int maxValue = 0;

        for(int i = 0; i < N; i ++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j ++) {
                int value = Integer.parseInt(st.nextToken());
                arr[i][j] = value;
                maxValue = Math.max(maxValue, value);
            }
        }

        for(int rain = 0; rain <= maxValue; rain ++) {
            visited = new boolean[N][N];
            int cnt = 0;
            for(int i = 0; i < N; i ++) {
                for(int j = 0; j < N; j ++) {
                    if(arr[i][j] > rain && !visited[i][j]) { // 해당 칸이 잠기지 않았고, 방문하지 않은 곳이라면 방문
                        dfs(i, j, rain); // 이어져 있는 모든 잠기지 않은 곳 확인
                        cnt ++; // 안전 영역 하나 추가
                    }
                }
            }
            result = Math.max(result, cnt); // rain 만큼 올 때
        }

        System.out.println(result);
    }

    private static void dfs(int i, int j, int rain) {
        visited[i][j] = true;

        for(int k = 0; k < 4; k ++) {
            int ni = i + di[k];
            int nj = j + dj[k];

            if(ni < 0 || nj < 0 || ni >= N || nj >= N || visited[ni][nj]) continue; // 범위를 벗어나거나 방문한 경우 지나감
            if(arr[ni][nj] <= rain) continue; // 물에 잠겼을 경우 지나감

            dfs(ni, nj, rain);
        }
    }
}
