import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, l;
    static int[] start = {0, 0};
    static int[] dest = {0, 0};

    // 나이트가 움직일 수 있는 방향
    static int[] dx = {1, 1, 2, 2,
                        -1, -1, -2, -2};
    static int[] dy = {-2, 2, -1, 1,
                        -2, 2, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            l = Integer.parseInt(br.readLine());

            start = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(string -> Integer.parseInt(string)).toArray();
            dest = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(string -> Integer.parseInt(string)).toArray();

            BFS();
        }
    }

    private static void BFS() {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[l][l];

        q.add(new Point(start[0], start[1], 0));
        visited[start[0]][start[1]] = true;

        while (!q.isEmpty()){
            Point polledPoint = q.poll();

            // 종료 조건
            if (polledPoint.x == dest[0] && polledPoint.y == dest[1]){
                System.out.println(polledPoint.cnt);
                break;
            }

            for (int i = 0; i < 8; i++) {
                int tmpX = polledPoint.x + dx[i],
                        tmpY = polledPoint.y + dy[i];
                if (checkValidXY(tmpX, tmpY) && !visited[tmpX][tmpY]){
                    visited[tmpX][tmpY] = true;
                    q.add(new Point(tmpX, tmpY, polledPoint.cnt + 1));
                }
            }
        }

        return;
    }

    private static boolean checkValidXY(int tmpX, int tmpY) {
        return tmpX >= 0 && tmpY >= 0 && tmpX < l && tmpY < l;
    }

    private static class Point{
        int x, y;
        int cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
