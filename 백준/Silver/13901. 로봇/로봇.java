import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int R, C, K, dirIdx, r, c;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dir = new int[4];
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        visited = new boolean[R][C];

        // 장애물
        for (int i = 0; i < K ; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = -1;
        }

        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken());
        int sc = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4 ; i++) {
            dir[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        BFS(sr, sc);
        bw.write(r + " " + c + "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    private static void BFS(int sr, int sc) {
        Queue<int[]> q = new LinkedList<>();
        visited[sr][sc] = true;
        q.add(new int[]{sr, sc});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int d = dir[(dirIdx + i) % 4];
                int x = cur[0] + dx[d];
                int y = cur[1] + dy[d];

                if ( 0 > x || 0 > y || x >= R || y >= C || visited[x][y] || map[x][y] == -1) continue;

                visited[x][y] = true;
                q.add(new int[]{x, y});
                dirIdx = (dirIdx + i) % 4;
                r = x;
                c = y;
                break;
            }
        }
    }
}
