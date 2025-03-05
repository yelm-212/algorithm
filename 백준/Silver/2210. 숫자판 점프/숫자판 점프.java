import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    private static int[][] map = new int[5][5];
    private static int N = 5;
    private static HashSet<String> ans = new HashSet<>(); // 정답을 저장한다.
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 전체 map에 대해 DFS 수행
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dfs(i, j, String.valueOf(map[i][j]));
            }
        }

        System.out.println(ans.size());
    }

    private static void dfs(int i, int j, String str) {
        // 종료 조건
        if (str.length() == 6) {
            ans.add(str);
            return;
        }

        // 4개의 방향에 대해 검사
        for (int k = 0; k < 4; k++) {
            int n = i + dx[k];
            int m = j + dy[k];

            // 이동이 불가능하면 건너뛴다.
            if (checkMap(n, m)) continue;

            // 재귀 호출 DFS
            dfs(n, m, str + map[n][m]);
        }

    }

    private static boolean checkMap(int n, int m) {
        return n < 0 || n >= N || m < 0 || m >= N;
    }
}
