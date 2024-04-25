import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int T;
    static int[][] DP = new int[10001][4];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        DP[1][1] = 1;
        DP[2][1] = 1; // 1로 끝나는 경우의 수
        DP[2][2] = 1; // 2로 끝나는 경우의 수
        DP[3][1] = 1; // 1로 끝나는 경우의 수
        DP[3][2] = 1; // 2로 끝나는 경우의 수
        DP[3][3] = 1; // 3으로 끝나는 경우의 수

        for (int i = 4; i < 10001; i++) {
            DP[i][1] = DP[i - 1][1];
            DP[i][2] = DP[i - 2][1] + DP[i - 2][2];
            DP[i][3] = DP[i - 3][1] + DP[i - 3][2] + DP[i - 3][3];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(DP[n][1] + DP[n][2] + DP[n][3] +"\n");
        }

        System.out.println(sb);
    }
}
