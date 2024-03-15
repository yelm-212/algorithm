import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static int[][] DP;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        DP = new int[N + 1][2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        DP[1][0] = DP[1][1] = arr[1];

        for (int i = 2; i <= N; i++) {
            DP[i][0] = Math.max(DP[i - 1][0] + arr[i] , arr[i]);
            DP[i][1] = Math.max(DP[i - 2][0] + arr[i] , DP[i - 1][1] + arr[i]);
        }

        int res = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= 1; j++) {
                res = Math.max(DP[i][j], res);
            }
        }

        System.out.println(res);
    }
}
