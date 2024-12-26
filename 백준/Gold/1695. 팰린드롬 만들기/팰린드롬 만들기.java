import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static long[][] DP = new long[5001][5001];
    private static int[] arr;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
               if (arr[N - i] == arr[j - 1]) {
                   DP[i][j] = DP[i - 1][j - 1] + 1;
               } else{
                   DP[i][j] = Math.max(DP[i][j - 1], DP[i - 1][j]);
               }
            }
        }

        System.out.println(N - DP[N][N]);

    }
}
