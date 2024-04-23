import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        DP = new int[K + 1];
        DP[0] = 1; // 첫번째 경우의 수는 1가지

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            for (int j = arr[i]; j <= K; j++) {
                DP[j] += DP[j - arr[i]];
            }
        }

        System.out.println(DP[K]);
    }
}
