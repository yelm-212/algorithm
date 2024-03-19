import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static long[][] DP;
    static int mod = 9901;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        DP = new long[N + 1][3];

        DP[1][0] = DP[1][1] = DP[1][2] = 1;

        for (int i = 2; i <= N; i++) {
            DP[i][0] = (DP[i - 1][1] + DP[i - 1][2]) % mod ; // 왼쪽에만 -> 이전 칸 비어있거나 오른쪽
            DP[i][1] = (DP[i - 1][0] + DP[i - 1][2]) % mod; // 오른쪽에만 -> 이전 칸 비어있거나 왼쪽
            DP[i][2] = (DP[i - 1][0] + DP[i - 1][1] + DP[i - 1][2]) % mod; // 비어있음 -> 모두 가능
        }

        System.out.println( (DP[N][0] + DP[N][1] + DP[N][2]) % mod);
    }
}
