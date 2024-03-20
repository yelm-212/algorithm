import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] DP;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        DP = new int[N+1][N+1];

        for (int i = 1 ; i <= N ; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1 ; i <= N ; i++){
            for (int j = 1; j <= N; j++) {
                // DP 점화식 
                DP[i][j] = DP[i - 1][j] + DP[i][j - 1] - DP[i - 1][j - 1]
                        + map[i][j];
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int res = DP[x2][y2]
                    - DP[x2][y1 - 1] - DP[x1 - 1][y2]
                    + DP[x1 - 1][y1 - 1];

            sb.append(res + "\n");
        }

        System.out.println(sb);

    }
}
