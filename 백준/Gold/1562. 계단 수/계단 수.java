import java.io.*;

public class Main {
    private static final int MOD = 1_000_000_000;
    private static long[][][] dp;
    private static int N, sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        dp = new long[N + 1][10][1 << 10];

        // 첫번째 자리에 i가 들어갈 수 잇는 경우
        for (int i = 1; i <= 9; i++) {
            dp[1][i][1 << i] = 1;
        }

        for (int i = 2; i <= N; i++) { // i 번째 자리수
            for (int j = 0; j <= 9; j++){ // 올 수 있는 숫자 순회
                for (int v = 0 ; v < (1<<10); v++){ // 방문 여부

                    int nextV = v | 1 << j;

                    switch (j){
                        case 0:
                            // 0일때는 선행하는 자리수에서 + 1 되는 경우만 존재한다.
                            dp[i][j][nextV] += dp[i - 1][j + 1][v] % MOD;
                            break;
                        case 9:
                            // 0일때는 선행하는 자리수에서 - 1 되는 경우만 존재한다.
                            dp[i][j][nextV] += dp[i - 1][j - 1][v] % MOD;
                            break;
                        default:
                            dp[i][j][nextV] += dp[i - 1][j + 1][v] % MOD + dp[i - 1][j - 1][v] % MOD;
                    }

                    dp[i][j][nextV] %= MOD;
                }
            }
        }

        sum = 0;
        for (int i = 0; i <= 9; i++) {
            sum += dp[N][i][(1 << 10) - 1] % MOD;
            sum %= MOD;
        }
        bw.write(sum + "\n");
        bw.flush();
        br.close();
        bw.close();
    }
}
