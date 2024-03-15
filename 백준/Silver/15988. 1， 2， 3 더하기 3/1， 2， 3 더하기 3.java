import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int T, N;
    static long[] DP;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        DP = new long[1000001];
        DP[0] = 0;
        DP[1] = 1;
        DP[2] = 2;
        DP[3] = 4;

        for (int i = 4; i < 1000001; i++) {
            DP[i] = (DP[i - 1] + DP[i - 2] + DP[i - 3]) % 1000000009;
        }

        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            sb.append(DP[N] + "\n");
        }
        System.out.println(sb);
    }
}
