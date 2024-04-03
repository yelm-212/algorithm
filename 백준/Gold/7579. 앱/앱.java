import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, costSum, result;
    static int[] mem, cost;
    static int[][] DP;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        mem = new int[N];
        cost = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            mem[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            costSum += cost[i];
        }

        // 0-1 knapsack
        DP = new int[N][costSum + 1];
        result = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            int curmem = mem[i], curcost = cost[i];

            for (int j = 0; j <= costSum; j++) {
                if (i == 0){// 첫 번째는 비교 대상 없으므로 값만 할당해줌
                    if (curcost <= j) DP[i][j] = curmem;
                } else {
                    // 각 cost에 따라 얻을 수 있는 최대 메모리 바이트 수 구함
                    if (curcost <= j) {
                        DP[i][j] = Math.max(DP[i - 1][j - curcost] + curmem, DP[i - 1][j]);
                    } else {
                        DP[i][j] = DP[i - 1][j];
                    }

                }
                
                // 구한 메모리 값이 필요 메모리 값보다 큰 경우 값을 저장한다.
                if (DP[i][j] >= M) result = Math.min(result, j);
            }
        }

        System.out.println(result);
    }
}
