import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, H;
    static int[][][] map;
    static boolean[][][] visited;

    static int res = 0;

    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[N][M][H];
        visited = new boolean[N][M][H];

        Queue<Point> q = new LinkedList<>();

        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++){
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    if (map[i][j][k] == 1) {
                        // 익은 토마토를 시작점으로
                        visited[i][j][k]= true;
                        q.add(new Point(i, j, k));
                    }

                }
            }
        }

        // 종료 조건을 계산하지 않고, 전체 순회 후 결과를 계산할 것이다.
        while (!q.isEmpty()){
            Point tmp = q.poll();

            // 6개의 방향 검사
            for(int i = 0; i < 6 ; i++){
                int nextx = tmp.x + dx[i];
                int nexty = tmp.y + dy[i];
                int nextz = tmp.z + dz[i];

                if (nextx >= 0 && nexty >= 0 && nextz >= 0
                        && nexty < M && nextx < N && nextz < H){
                    if (!visited[nextx][nexty][nextz] && map[nextx][nexty][nextz] == 0){
                        map[nextx][nexty][nextz] = map[tmp.x][tmp.y][tmp.z] + 1;
                        q.add(new Point(tmp.x + dx[i], tmp.y + dy[i], tmp.z + dz[i]));
                    }
                }
            }

        }

        checkMap();
        if (res == -1) System.out.println(-1);
        else System.out.println(res - 1);
    }

    // map이 0(덜익은 토마토)를 포함하면 -1, -1과 1로만 구성된 경우 결과 계산
    private static void checkMap() {

        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++){
                for (int j = 0; j < M; j++){
                    if (map[i][j][k] == 0) {
                        res = -1;
                        return;
                    }
                    res = Math.max(res, map[i][j][k]);
                }
            }
        }
    }

    static class Point{
        int x, y, z;
        int cnt;

        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
