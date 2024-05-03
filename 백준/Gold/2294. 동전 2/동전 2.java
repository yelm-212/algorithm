import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] arr, DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        DP = new int[100001];

        Arrays.fill(DP, Integer.MAX_VALUE - 1);
        DP[0] = 0;

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            DP[arr[i]] = 1;
        }

        // 점화식 : DP[n] = Math.min(DP[n - 동전가치] + 1, DP[n]);
        for (int i = 1; i <= N; i++) {
            for (int j = arr[i]; j <= K; j++) {
                DP[j] = Math.min(DP[j - arr[i]] + 1, DP[j]);
            }
        }

        System.out.println(DP[K] == Integer.MAX_VALUE - 1 ? -1 : DP[K]);
    }
}
