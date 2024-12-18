import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, x, y, K;
    private static int[][] map;
    private static int[] dice;
    private static StringBuilder sb = new StringBuilder();

    // 방향 순서 : 동, 서, 남, 북
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1,-1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dice = new int[]{0, 0, 0, 0, 0, 0}; // 처음에 모든 면이 0

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int dir = Integer.parseInt(st.nextToken());
            move(dir - 1);
        }

        System.out.println(sb);
    }

    private static void move(int dir) {
        // 이동 방향에 따른 이동 후 위치
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        // 바깥으로 이동하려 하는 경우
        if (nx < 0 || nx >= N || ny < 0 || ny >= M) return;

        int tmp = dice[5];

        if (dir == 0){
            // 동
            dice[5] = dice[2];
            dice[2] = dice[0];
            dice[0] = dice[3];
            dice[3] = tmp;
        } else if (dir == 1) {
            // 서
            dice[5] = dice[3];
            dice[3] = dice[0];
            dice[0] = dice[2];
            dice[2] = tmp;
        } else if (dir == 2) {
            // 남
            dice[5] = dice[4];
            dice[4] = dice[0];
            dice[0] = dice[1];
            dice[1] = tmp;
        } else if (dir == 3) {
            // 북
            dice[5] = dice[1];
            dice[1] = dice[0];
            dice[0] = dice[4];
            dice[4] = tmp;
        }

        // 윗면
        sb.append(dice[0]).append("\n");
        x = nx;
        y = ny;

        if (map[x][y] == 0){
            // 주사위 바닥면 -> 지도
            map[x][y] = dice[5];
        }else {
            // 주사위 바닥면 <- 지도
            dice[5] = map[x][y];
            // 지도 값은 0
            map[x][y] = 0;
        }
    }
}
