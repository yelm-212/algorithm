import java.io.*;
import java.util.*;

public class Main {
    private static int[][] map;
    private static int R, C, K, W, cnt = 0;
    // 방향: 0은 사용안함, 1:오른쪽, 2:왼쪽, 3:위쪽, 4:아래쪽
    private static int[] dr = {0, 0, 0, -1, 1};     // 온풍기 방향
    private static int[] dc = {0, 1, -1, 0, 0};     // 온풍기 방향

    // 각 방향별로 퍼지는 바람의 방향 (행,열)
    private static int[][] spreadR = {{0, 0, 0}, {-1, 0, 1}, {-1, 0, 1}, {-1, -1, -1}, {1, 1, 1}};
    private static int[][] spreadC = {{0, 0, 0}, {1, 1, 1}, {-1, -1, -1}, {-1, 0, 1}, {-1, 0, 1}};

    // 벽 정보 저장
    private static boolean[][][][] wall;

    // 조사할 칸과 온풍기 정보
    private static List<Point> checkPoints = new ArrayList<>();
    private static List<Heater> heaters = new ArrayList<>();

    private static final int MAX = 100;

    // 위치 정보
    static class Point {
        int r, c; // 행, 열

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    // 온풍기 정보
    static class Heater {
        int r, c, dir; // 행, 열, 방향

        public Heater(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    // 바람 퍼짐 정보
    static class Wind {
        int r, c, power;

        Wind(int r, int c, int power) {
            this.r = r;
            this.c = c;
            this.power = power;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        wall = new boolean[R][C][R][C];

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                int value = Integer.parseInt(st.nextToken());
                if (value == 5) {
                    checkPoints.add(new Point(r, c));
                } else if (value >= 1 && value <= 4) {
                    heaters.add(new Heater(r, c, value));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());

        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1; // 행
            int y = Integer.parseInt(st.nextToken()) - 1; // 열
            int t = Integer.parseInt(st.nextToken());

            if (t == 0) { // 위쪽 벽
                wall[x][y][x-1][y] = true;
                wall[x-1][y][x][y] = true;
            } else { // 오른쪽 벽
                wall[x][y][x][y+1] = true;
                wall[x][y+1][x][y] = true;
            }
        }

        while (cnt <= MAX && !isComfortable()) {
            // 1. 온풍기 바람 나옴
            blowWind();

            // 2. 온도 조절
            adjustTemperature();

            // 3. 가장 바깥쪽 칸 온도 1 감소
            decreaseEdgeTemperature();

            // 4. 초콜릿 먹기
            cnt++;

            // 5. 모든 칸의 온도가 K 이상인지 확인
            // isComfortable() 함수를 while 조건에서 체크
        }

        System.out.println(cnt > 100 ? 101 : cnt);
    }

    // 온풍기 바람 나오는 함수
    private static void blowWind() {
        for (Heater heater : heaters) {
            int r = heater.r;
            int c = heater.c;
            int dir = heater.dir;

            // 바람이 처음 나오는 칸
            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if (!inRange(nr, nc)) continue;

            boolean[][] visited = new boolean[R][C];
            Queue<Wind> q = new ArrayDeque<>();

            // 바람 시작점
            visited[nr][nc] = true;
            map[nr][nc] += 5; // 온도 5 상승
            q.offer(new Wind(nr, nc, 5));

            while (!q.isEmpty()) {
                Wind curr = q.poll();

                if (curr.power == 1) continue; // 파워 1이면 더 퍼지지 않음

                // 3방향으로 퍼짐
                for (int i = 0; i < 3; i++) {
                    int nnr = curr.r + spreadR[dir][i];
                    int nnc = curr.c + spreadC[dir][i];

                    if (!inRange(nnr, nnc) || visited[nnr][nnc]) continue;
                    if (!canSpread(curr.r, curr.c, nnr, nnc, dir, i)) continue;

                    visited[nnr][nnc] = true;
                    map[nnr][nnc] += (curr.power - 1); // 온도 증가
                    q.offer(new Wind(nnr, nnc, curr.power - 1));
                }
            }
        }
    }

    // 바람이 퍼질 수 있는지 벽 체크하는 함수
    private static boolean canSpread(int r1, int c1, int r2, int c2, int dir, int idx) {
        if (dir == 1) { // 오른쪽
            if (idx == 0) { // 위+오른쪽
                return !wall[r1][c1][r1-1][c1] && !wall[r1-1][c1][r1-1][c1+1];
            } else if (idx == 1) { // 직진
                return !wall[r1][c1][r1][c1+1];
            } else { // 아래+오른쪽
                return !wall[r1][c1][r1+1][c1] && !wall[r1+1][c1][r1+1][c1+1];
            }
        } else if (dir == 2) { // 왼쪽
            if (idx == 0) { // 위+왼쪽
                return !wall[r1][c1][r1-1][c1] && !wall[r1-1][c1][r1-1][c1-1];
            } else if (idx == 1) { // 직진
                return !wall[r1][c1][r1][c1-1];
            } else { // 아래+왼쪽
                return !wall[r1][c1][r1+1][c1] && !wall[r1+1][c1][r1+1][c1-1];
            }
        } else if (dir == 3) { // 위쪽
            if (idx == 0) { // 왼쪽+위
                return !wall[r1][c1][r1][c1-1] && !wall[r1][c1-1][r1-1][c1-1];
            } else if (idx == 1) { // 직진
                return !wall[r1][c1][r1-1][c1];
            } else { // 오른쪽+위
                return !wall[r1][c1][r1][c1+1] && !wall[r1][c1+1][r1-1][c1+1];
            }
        } else { // 아래쪽
            if (idx == 0) { // 왼쪽+아래
                return !wall[r1][c1][r1][c1-1] && !wall[r1][c1-1][r1+1][c1-1];
            } else if (idx == 1) { // 직진
                return !wall[r1][c1][r1+1][c1];
            } else { // 오른쪽+아래
                return !wall[r1][c1][r1][c1+1] && !wall[r1][c1+1][r1+1][c1+1];
            }
        }
    }

    // 온도 조절 함수
    private static void adjustTemperature() {
        int[][] tempDiff = new int[R][C]; // 온도 변화량 저장

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                for (int d = 1; d <= 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (!inRange(nr, nc) || wall[r][c][nr][nc]) continue;

                    int diff = (map[r][c] - map[nr][nc]) / 4;
                    if (diff > 0) {
                        tempDiff[r][c] -= diff;
                        tempDiff[nr][nc] += diff;
                    }
                }
            }
        }

        // 온도 변화 적용
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                map[r][c] += tempDiff[r][c];
            }
        }
    }

    // 가장 바깥쪽 칸 온도 감소 함수
    private static void decreaseEdgeTemperature() {
        // 위쪽과 아래쪽 행
        for (int c = 0; c < C; c++) {
            if (map[0][c] > 0) map[0][c]--;
            if (map[R-1][c] > 0) map[R-1][c]--;
        }

        // 왼쪽과 오른쪽 열 (가장자리 제외)
        for (int r = 1; r < R-1; r++) {
            if (map[r][0] > 0) map[r][0]--;
            if (map[r][C-1] > 0) map[r][C-1]--;
        }
    }

    // 모든 조사 칸의 온도가 K 이상인지 확인
    private static boolean isComfortable() {
        for (Point p : checkPoints) {
            if (map[p.r][p.c] < K) {
                return false;
            }
        }
        return true;
    }

    // 범위 체크
    private static boolean inRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
}
