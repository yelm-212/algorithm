import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, res;
    static char[][] map;

    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Point startPoint;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N ; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp.charAt(j);
                if (map[i][j] == 'I'){
                    startPoint = new Point(i, j);
                    visited[i][j] = true; // 시작점
                }
            }
        }

        BFS();

        System.out.println( res == 0 ? "TT" : res);
    }

    private static void BFS() {
        Queue<Point> q = new LinkedList<>();
        q.add(startPoint);
        res = 0;

        while (!q.isEmpty()){
            Point polledPoint = q.poll();

            if (map[polledPoint.x][polledPoint.y] == 'P') {
                res++;
                visited[polledPoint.x][polledPoint.y] = true;
            }

            for (int i = 0; i < 4; i++) {
                int tmpx = polledPoint.x + dx[i];
                int tmpy = polledPoint.y + dy[i];


                if (checkValidXY(tmpx, tmpy) && !visited[tmpx][tmpy]){
                    visited[tmpx][tmpy] = true;

                    if (map[tmpx][tmpy] == 'X') continue;

                    q.add(new Point(tmpx, tmpy));
                }
            }
        }
    }

    private static boolean checkValidXY(int tmpx, int tmpy) {
        return tmpx >= 0 && tmpx < N && tmpy >= 0 && tmpy < M;
    }

    static class Point{
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}