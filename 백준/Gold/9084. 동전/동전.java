import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] coins;
    static int T, N, M;
    static int[] DP;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            coins = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            M = Integer.parseInt(br.readLine());

            // DP / knapsack과 유사
            DP = new int[M + 1];

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    int idx = j - coins[i];
                    if (idx > 0){
                        DP[j] += DP[idx];
                    }else if (idx == 0){
                        DP[j]++;
                    }
                }
            }
            sb.append(DP[M] + "\n");
        }

        System.out.println(sb);
    }

}