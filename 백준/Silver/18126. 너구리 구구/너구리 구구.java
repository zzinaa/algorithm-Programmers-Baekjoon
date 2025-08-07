import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static boolean[] visited;
    static List<long[]>[] lists;
    static long max = 0;

    public static void main(String[] args) throws IOException {
        // N개 노드 제공
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // List<int[]>[] N개의 인접 리스트 생성
        lists = new ArrayList[N + 1]; // 1번 방부터 시작하므로 N + 1
        for(int i = 0; i < N + 1; i++){
            lists[i] = new ArrayList<>();
        }

        visited = new boolean[N + 1];

        // N-1개 길 제공
        for(int i = 0; i < N - 1; i ++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            long len = Integer.parseInt(st.nextToken());

            lists[A].add(new long[]{B, len});
            lists[B].add(new long[]{A, len});
        }

        // dfs 로 1번부터 갈 수 있는 모든 경로 계산 -> 각 길이 더하여 최대 길이 출력
        dfs(1, 0);

        System.out.println(max);
    }

    public static void dfs(int idx, long sum) {

        visited[idx] = true;
        max = Math.max(max, sum);

        for(int i = 0; i < lists[idx].size(); i ++){
            long[] next = lists[idx].get(i);
            if(visited[(int) next[0]]) continue;
            dfs((int) next[0], sum + next[1]); // 인접리스트 탐색
        }
    }
}
