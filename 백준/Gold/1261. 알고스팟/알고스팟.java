import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = Integer.MAX_VALUE / 2;

    static int N, M;
    static int[][] map;
    static int[][] ans;
    static boolean[][] visited; // 방문 여부를 확인해 BFS 횟수 줄이기
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[M + 1][N + 1];
        map = new int[M + 1][N + 1];
        ans = new int[M + 1][N + 1];


        for (int i = 1; i <= M; i++) {
            String tmp = br.readLine();
            for (int j = 1; j <= N; j++) {
                map[i][j] = tmp.charAt(j - 1) - '0';
            }
        }

        for (int[] arr : ans) {
            Arrays.fill(arr, INF);
        }

        BFS();
        System.out.println(ans[M][N]);

    }

    private static void BFS() {
        // 다익스트라 알고리즘

        PriorityQueue<Tmp> q = new PriorityQueue<>();
        q.offer(new Tmp(1, 1, 0));
        visited[1][1] = true;
        ans[1][1]= 0;

        while (!q.isEmpty()){
            Tmp present = q.poll();
            visited[present.x][present.y] = true;

            for (int i = 0; i < 4; i++) {
                int tmpX = present.x + dx[i];
                int tmpY = present.y + dy[i];

                if (tmpX >= 1 && tmpY >= 1 && tmpX <= M && tmpY <= N && !visited[tmpX][tmpY]){
                    int cnt = present.cnt;
                    if (map[tmpX][tmpY] == 1) cnt++;

                    if (ans[tmpX][tmpY] > cnt) {
                        ans[tmpX][tmpY] = cnt;
                        q.add(new Tmp(tmpX, tmpY, cnt));
                    }
                }
            }


        }

    }

    static class Tmp implements Comparable<Tmp>{
        int x, y, cnt;

        public Tmp(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Tmp o) {
            return this.cnt - o.cnt;
        }
    }
}
