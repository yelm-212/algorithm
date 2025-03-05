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
                dfs(i, j, new StringBuilder().append(map[i][j]));
            }
        }

        System.out.println(ans.size());
    }

    private static void dfs(int i, int j, StringBuilder sb) {
        // 종료 조건: 길이가 6이면 set에 추가 후 종료
        if (sb.length() == 6) {
            ans.add(sb.toString());
            return;
        }

        // 4방향 탐색
        for (int k = 0; k < 4; k++) {
            int n = i + dx[k];
            int m = j + dy[k];

            if (checkMap(n, m)) continue;

            // 현재 숫자 추가
            sb.append(map[n][m]);
            dfs(n, m, sb);
            // 탐색 후 마지막 문자 제거
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private static boolean checkMap(int n, int m) {
        return n < 0 || n >= N || m < 0 || m >= N;
    }
}
