import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[] DP = new int[50005];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // DP
        for (int i = 1; i <= N; i++) {
            DP[i] = DP[i - 1] + 1; // 1 ~ N까지 1로만 구성
            for (int j = 1; j * j <= i; j++) { // j ^ 2 <= i 일때만 탐색
                // 최소 개수를 구함
                DP[i] = Math.min(DP[i], DP[i - j * j] + 1);
            }
        }

        System.out.println(DP[N]);

    }
}