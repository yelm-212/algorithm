import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int C, R, K;
    private static boolean[][] map = new boolean[1001][1001];
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        if ( K > C * R ) {
            System.out.println("0");
            return;
        }

        if ( K == 1 ) {
            System.out.println("1 1");
            return;
        }

        int x = 1, y = 1, dir = 0;
        map[x][y] = true;

        while (K != 1){
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx <= 0 || ny <= 0 || nx > C || ny > R || map[nx][ny]) {
                dir = (dir + 1) % 4;
                nx = x + dx[dir];
                ny = y + dy[dir];
            }

            map[nx][ny] = true;

            x = nx;
            y = ny;
            K--;
        }

        System.out.println((x) + " " + (y));
    }
}
