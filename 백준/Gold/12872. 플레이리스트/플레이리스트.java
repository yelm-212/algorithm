import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, P;
    private static long[][] DP = new long[101][101];
    private static final int MOD = 1000000007;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        // DP[i][j] = i개 음악을 플레이리스트에 넣고 j개 음악 사용한 경우의 수
        DP[0][0] = 1;

        for (int i = 1; i <= P; i++) {
            for (int j = 0; j <= N; j++) {
                if ( j > 0 ) {
                    // 아직 넣지 않은 음악
                    DP[i][j] += DP[i-1][j-1] * (N - j + 1);
                    DP[i][j] %= MOD;
                }
                if ( j > M ) {
                    // 이미 플레이리스트에 넣은 음악
                    DP[i][j] += DP[i-1][j] * (j - M);
                    DP[i][j] %= MOD;
                }
            }
        }
        
        System.out.println(DP[P][N]);
    }

}
