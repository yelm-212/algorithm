import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    private static int N, l, r;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(st.nextToken());

        long[][][] dp = new long[21][21][21];

        // 첫번째 값은 한개의 블록을 보는 경우의 수이다.
        dp[1][1][1] = 1;

        for (int i = 2; i <= 20; i++) {
            for (int j = 1; j <= 20; j++) {
                for (int k = 1; k <= 20; k++) {
                    dp[i][j][k] += dp[i - 1][j - 1][k]
                            + dp[i - 1][j][k - 1]
                            + (dp[i - 1][j][k] * (i - 2));
                }
            }
        }


        while (t-- != 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());

            bw.write(dp[N][l][r] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();

    }
}
