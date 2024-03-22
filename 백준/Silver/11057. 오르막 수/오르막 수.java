import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] DP;
    static int mod = 10007;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        DP = new int[N + 1][10];

        // 초기값 설정
        for (int i = 0; i < 10; i++) {
            DP[0][i] = 1;
        }

        for (int i = 1; i <= N; i++) {

            for(int j = 0; j < 10; j++) {
                // 점화식 : DP[N][M] = DP[N - 1][] 배열의 M까지의 합
                for(int k = j; k < 10; k++) {
                    DP[i][j] += DP[i-1][k];
                    DP[i][j] %= mod;
                }
            }
        }

        System.out.println(DP[N][0] % mod);
    }
}
