import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] result;
    static boolean[][] visited;
    static int start_i, start_j;

    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        result = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++)
                result[i][j] = -1;
        }

        for(int i = 0; i < N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    start_i = i;
                    start_j = j;
                    result[i][j] = 0;
                    visited[i][j] = true;
                }
                if(map[i][j] == 0) 
                    result[i][j] = 0;
            }
        }

        BFS(start_i, start_j);

        printResult();

        bw.close();
    }

    private static void printResult() throws IOException {
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++)
                bw.write(result[i][j]+" ");
            bw.write("\n");
        }
    }

    private static void BFS(int i, int j) {


        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(i, j));

        while(!queue.isEmpty()){
            Point temp = queue.poll(); // 첫번째 방문한 위치 확인

            for(int k = 0; k < 4; k++){
                int tmpx = temp.x + dx[k];
                int tmpy = temp.y + dy[k];
                // 이동하는 경우 점 위치

                if (tmpx <0 || tmpy <0 || tmpx >=N || tmpy >=M ) continue;
                // 위치 밖으로 나가는 경우 제외

                if(visited[tmpx][tmpy] || map[tmpx][tmpy] == 0) continue;
                // 방문했거나 이동 불가능한 경우 제외

                visited[tmpx][tmpy] = true;
                result[tmpx][tmpy] = result[temp.x][temp.y] + 1;
                queue.add(new Point(tmpx, tmpy));
            }
        }

    }

    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
}
