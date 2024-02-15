import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int T, M, N, K;

    static int[] dx = new int[]{0, 0, -1, 1};
    static int[] dy = new int[]{-1, 1, 0, 0};

    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for(int i = 0; i < T ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            visited = new boolean[N][M];

            int res = 0;

            for(int j = 0; j < N ; j++){
                for(int k = 0; k < M ; k++){
                    map[j][k] = 0;
                }
            }

            for(int j = 0; j < K ; j++){
                st = new StringTokenizer(br.readLine(), " ");
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                map[Y][X] = 1;
            }

            for(int j = 0; j < N ; j++){
                for(int k = 0; k < M ; k++){
                    if (map[j][k] == 1 && !visited[j][k]){
                        BFS(j, k);
                        res++;
                    }
                }
            }

            System.out.println(res);
        }

    }

    private static void BFS(int y, int x) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(y, x));


        while (!q.isEmpty()){
            Point p = q.poll();
            visited[y][x] = true;

            for (int i = 0 ; i < 4 ; i++){
                int tmpx = p.x + dx[i];
                int tmpy = p.y + dy[i];

                // map 조건에 유의하기 
                // 방문 X, 범위 안이면
                if(tmpx >= 0 && tmpy >= 0 && tmpx < M && tmpy < N &&
                        !visited[tmpy][tmpx] && map[tmpy][tmpx] == 1){
                    q.add(new Point(tmpy, tmpx));
                    visited[tmpy][tmpx] = true;
                }
            }
        }
    }

    static class Point{
        int y;

        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
