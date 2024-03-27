import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] DP;
    static int[] arr;
    static int N;
    public static void main(String[] args) throws Exception{
        // 전형적인 DP 문제 -> 연쇄 행렬 곱셈
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        DP = new int[N + 1][N + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int tmp1 = Integer.parseInt(st.nextToken());
            int tmp2 = Integer.parseInt(st.nextToken());
            arr[i] = tmp1;
            arr[i + 1] = tmp2;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N - i; j++) {
                int k = i + j + 1;
                DP[j][k] = Integer.MAX_VALUE;
                for (int l = j; l < k; l++) {
                    DP[j][k] = Math.min(DP[j][k],
                            DP[j][l] + DP[l + 1][k] + (arr[j - 1] * arr[l] * arr[k]));
                }
            }
        }

        System.out.println(DP[1][N]);
    }
}
