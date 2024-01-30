import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, V;
    static int[][] arr;
    static boolean[][] visit;
    static int[] moveX = {0, 0, 1, -1};
    static int[] moveY = {1, -1, 0, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visit = new boolean[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();
            for(int j=0; j<M; j++){
                arr[i][j] = Character.getNumericValue(tmp.charAt(j));
            }
        }

        visit[0][0] = true; // 시작 위치
        BFS(0,0);
        System.out.println(arr[N-1][M-1]);
    }

    public static void BFS(int x, int y){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));

        while(!queue.isEmpty()){
            Point p = queue.poll(); // 큐에 들어있는 위치 확인함

            for(int i = 0; i < 4; i++){
                // 4가지 방향으로 이동하는 경우의 수를 확인
                int nextX = p.x + moveX[i];
                int nextY = p.y + moveY[i];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    // 방문이 불가능한 위치면 건너뜀
                    continue;
                }
                if (visit[nextX][nextY] || arr[nextX][nextY] == 0) {
                    // 이미 방문했거나 길이 없으면 건너뜀
                    continue;
                }
                queue.add(new Point(nextX, nextY));
                arr[nextX][nextY] = arr[p.x][p.y] + 1;
                visit[nextX][nextY] = true;
            }
        }

    }

    static class Point{
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}



