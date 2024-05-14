import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static long[] dp;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new long[n+1];

        for(int i = 1; i < n+1; i++) {
            dp[i] = dp[i-1]+1;

            // i가 6 이상인 경우 점화식 수행
            if(i > 6) {
                for(int j = 2; j < 5; j++) {
                    dp[i] = Math.max(dp[i], dp[i-(j+1)] * j);
                }
            }
        }
        System.out.println(dp[n]);
    }
}
