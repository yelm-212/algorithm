import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int cnt;
    static char[][] map;
    static boolean[][][][] visited;
    static int[] dx = {-1, 1, 0, 0} ;
    static int[] dy =  {0, 0, 1, -1} ;

    static Queue<Pair> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][N][M];

        q = new LinkedList<>();
        Point p1 = null, p2 = null;

        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp.charAt(j);
                if (map[i][j] == 'o') { // 각 동전의 위치 를 BFS 시작점 으로...
                    if (p1 == null) p1 = new Point(i, j);
                    else p2 = new Point(i, j);
                }
            }
        }

        q.offer(new Pair(p1, p2, 0));
        visited[p1.x][p1.y][p2.x][p2.y] = true;
        System.out.println(BFS());
    }

    private static int BFS() {

        while (!q.isEmpty()){
            Pair pair = q.poll(); // 두개씩 움직여야 함
            Point point1 = pair.p1;
            Point point2 = pair.p2;

            if (pair.cnt >= 10) break;

            for (int i = 0; i < 4; i++) {
                int nextX_1 = point1.x + dx[i];
                int nextY_1 = point1.y + dy[i];

                int nextX_2 = point2.x + dx[i];
                int nextY_2 = point2.y + dy[i];


                // 벽인지 확인 (이동 못하는지 확인)
                if (checkMove(nextX_1, nextY_1) && checkWall(nextX_1, nextY_1) ) {
                    nextX_1 = point1.x;
                    nextY_1 = point1.y;
                }
                if (checkMove(nextX_2, nextY_2) && checkWall(nextX_2, nextY_2) ) {
                    nextX_2 = point2.x;
                    nextY_2 = point2.y;
                }

                int tmp = 0; // 떨어지지 않는 동전의 수
                if (checkMove(nextX_1, nextY_1)){
                    tmp++;
                }
                if (checkMove(nextX_2, nextY_2)){
                    tmp++;
                }

                if (tmp == 1) return pair.cnt + 1;
                else if (tmp == 2 && !visited[nextX_1][nextY_1][nextX_2][nextY_2]) {
                    visited[nextX_1][nextY_1][nextX_2][nextY_2] = true;
                    q.offer(new Pair(new Point(nextX_1, nextY_1),
                            new Point(nextX_2, nextY_2),
                            pair.cnt + 1));
                }

            }
        }

        return -1;
    }

//    private static boolean check(int nextX_1, int nextY_1) {
//        return nextX_1 >= 0 && nextY_1 >= 0 && nextX_1 < N && nextY_1 < M
//                && checkWall(nextX_1, nextY_1);
//    }

    private static boolean checkMove(int nextX_1, int nextY_1) {
        return nextX_1 >= 0 && nextY_1 >= 0 && nextX_1 < N && nextY_1 < M;
    }

    private static boolean checkWall(int nextX_1, int nextY_1) {
        return map[nextX_1][nextY_1] == '#';
    }

    static class Point{
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Pair{
        Point p1;
        Point p2;
        int cnt;

        public Pair(Point p1, Point p2, int cnt) {
            this.p1 = p1;
            this.p2 = p2;
            this.cnt = cnt;
        }
    }
}
