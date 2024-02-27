import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,K;
    static long[][] DP;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        DP = new long[N + 1][K + 1];

        // 배열 초기화
        for (int i = 0 ; i <= N ; i++){
            for (int j = 0 ; j <= K ; j++){
                DP[i][j] = 1;
            }
        }

        for (int i = 1 ; i <= N ; i++){
            for (int j = 2 ; j <= K ; j++){
                DP[i][j] = (DP[i][j - 1] + DP[i - 1][j]) % 1000000000;
            }
        }

        System.out.print(DP[N][K] % 1000000000);
    }
}
