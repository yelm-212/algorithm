import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int INF = 1000 * 1000; // 최대 비용 값
    static int n; // 각 칠하는 비용에 대한 정보
    static int arr[][];
    // [i번째 집][0 -> R, 1 -> G, 2 -> B]
    // i번째까지 칠하고 열의 색으로 칠했을 때의 최솟값을 저장하고 있다.
    static int dp[][];
    static int answer = INF;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1][3];
        dp = new int[n + 1][3];

        // 입력 값 저장
        for(int i = 1 ; i <= n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < 3; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // k = 0 -> RED, 1 -> GREEN, 2 -> BLUE  첫 집을 칠하는 경우
        for(int k = 0; k < 3; k++) {
            // RED, GREEN, BLUE로 칠하는 경우 각 색을 제외한 나머지는 INF로 초기화
            for(int i = 0 ; i < 3; i++) {
                if(i == k) dp[1][i] = arr[1][i];
                else dp[1][i] = INF;
            }

            // 0 -> RED, 1 -> GREEN, 2 -> BLUE로 칠했을 때의 최소값
            for (int i = 2; i <= n; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + arr[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + arr[i][2];
            }

            // 정답인 최솟값을 구하는 부분
            for(int i = 0 ; i < 3; i++)
                if(i != k) answer = Math.min(answer, dp[n][i]);
        }


        bw.write(answer + "\n");

        bw.close();
        br.close();
    }
}
