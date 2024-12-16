import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][K + 1];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        int result = bfs();
        System.out.println(result);
    }

    static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, K, 1}); // {x, y, 남은 벽 부수기 횟수, 이동 거리}
        visited[0][0][K] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int x = cur[0];
            int y = cur[1];
            int wallsLeft = cur[2];
            int distance = cur[3];

            // 목표 지점 도달
            if (x == N - 1 && y == M - 1) {
                return distance;
            }

            // 상하좌우 이동
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위 체크
                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    // 벽이 아니고 방문하지 않았을 경우
                    if (map[nx][ny] == 0 && !visited[nx][ny][wallsLeft]) {
                        visited[nx][ny][wallsLeft] = true;
                        q.add(new int[]{nx, ny, wallsLeft, distance + 1});
                    }

                    // 벽이고, 벽을 부술 수 있는 경우
                    if (map[nx][ny] == 1 && wallsLeft > 0 && !visited[nx][ny][wallsLeft - 1]) {
                        visited[nx][ny][wallsLeft - 1] = true;
                        q.add(new int[]{nx, ny, wallsLeft - 1, distance + 1});
                    }
                }
            }
        }

        // 도달할 수 없는 경우
        return -1;
    }
}
