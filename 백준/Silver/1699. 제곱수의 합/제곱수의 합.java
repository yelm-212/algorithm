import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] DP;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        DP = new int[N + 1];
        visited = new boolean[N + 1];

        for(int i = 1; i <= N; i++){
            DP[i] = i;
            for(int j = 1; j * j <= i; j++){
                DP[i] = Math.min(DP[i], DP[i - j * j] + 1);
                // 1, 4, 9, ... 와 같이 정수의 제곱이 되는 수는 제곱수가 1개이다.
                // 이 값에 따라 최소값으로 갱신한다.
            }
        }

        System.out.print(DP[N]);
    }
}
