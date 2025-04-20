import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, cnt = 0;
    private static boolean[][] visited;
    private static int[][] map;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            // 외부 공기 영역 표시
            boolean[][] visited = new boolean[N][M];
            Queue<int[]> airQueue = new LinkedList<>();
            airQueue.add(new int[]{0, 0});
            visited[0][0] = true;

            while (!airQueue.isEmpty()) {
                int[] current = airQueue.poll();
                int x = current[0], y = current[1];

                for (int k = 0; k < 4; k++) {
                    int nx = x + dx[k], ny = y + dy[k];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                    if (map[nx][ny] == 0 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        airQueue.add(new int[]{nx, ny});
                    }
                }
            }

            // 녹을 치즈 찾기
            Queue<int[]> meltQueue = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1) {
                        int airCount = 0;
                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k], ny = j + dy[k];
                            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                            if (map[nx][ny] == 0 && visited[nx][ny]) {
                                airCount++;
                            }
                        }
                        if (airCount >= 2) {
                            meltQueue.add(new int[]{i, j});
                        }
                    }
                }
            }

            // 녹을 치즈가 없으면 종료
            if (meltQueue.isEmpty()) break;

            // 치즈 녹이기
            while (!meltQueue.isEmpty()) {
                int[] melt = meltQueue.poll();
                map[melt[0]][melt[1]] = 0;
            }

            cnt++;
        }

        System.out.println(cnt);
    }
}
