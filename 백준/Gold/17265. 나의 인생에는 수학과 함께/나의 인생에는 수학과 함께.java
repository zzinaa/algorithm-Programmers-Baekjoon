import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static char[][] board;

    static int[][] maxDp;
    static int[][] minDp;

    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N =  Integer.parseInt(br.readLine());
        board = new char[N][N];
        maxDp = new int[N][N];
        minDp = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] parts = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                board[i][j] = parts[j].charAt(0);
            }
        }

        for (int[] row : maxDp) Arrays.fill(row, Integer.MIN_VALUE);
        for (int[] row : minDp) Arrays.fill(row, Integer.MAX_VALUE);

        dfs(0, 0, board[0][0] - '0', ' ');

        System.out.println(max + " " + min);
    }

    private static void dfs(int r, int c, int cur, char op) {
        if(r == N - 1 && c == N - 1) { // 종착지 도착, min max 계산하여 종료
            max = Math.max(max, cur);
            min = Math.min(min, cur);
            return;
        }

        // 현재 위치의 max 값이 cur 보다 크거나 같음 + 현재 위치의 min 값이 cur 보다 작거나 같으면 종료
        // 이미 최댓값도, 최솟값도 아닌 방향이므로 더 갈 필요 없음
        if(maxDp[r][c] >= cur && minDp[r][c] <= cur) return;

        // 위 조건에 만족하지 않으면 현재 값과 maxDp, minDp 값 비교하여 갱신
        maxDp[r][c] = Math.max(maxDp[r][c], cur);
        minDp[r][c] = Math.min(minDp[r][c], cur);

        int[] dr = {1, 0};
        int[] dc = {0, 1};

        for(int i = 0; i < 2; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr >= N || nc >= N) continue;
            char next = board[nr][nc];
            if(Character.isDigit(next)) {
                int num = next - '0';
                int nextVal = calc(cur, op, num);
                dfs(nr, nc, nextVal, ' ');
            } else {
                dfs(nr, nc, cur, next);
            }
        }
    }

    static int calc(int a, char op, int b) {
        if(op == '+') return a + b;
        else if(op == '-') return a - b;
        else if(op == '*') return a * b;
        return a;
    }
}
