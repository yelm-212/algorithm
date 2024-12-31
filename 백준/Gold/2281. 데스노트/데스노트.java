import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[][] dp = new int[1001][1002];
    private static int[] names;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        names = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            names[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(check(1, names[0] + 1));

    }

    private static int check(int idx, int cnt) {
        if (idx == N) return 0;

        int ans = dp[idx][cnt];
        if (ans != -1) return ans;

        // 다음 줄에 작성하는 경우
        int left = M - cnt + 1; // 남은 칸 수
        ans = check(idx + 1, names[idx] + 1) + (left * left); // DP에 저장되는 값 계산

        // 현재 줄에 이어 작성하는 경우
        if (cnt + names[idx] <= M) {
            ans = Math.min(ans, check(idx + 1, cnt + names[idx] + 1));
        }
        dp[idx][cnt] = ans;
        return ans;
    }
}
