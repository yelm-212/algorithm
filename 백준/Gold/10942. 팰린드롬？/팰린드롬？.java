import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static int N, M;
    static int[] arr;
    static boolean[][] DP;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        DP = new boolean[N + 1][N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // DP
        for (int i = 1; i <= N; i++) {
            // 길이가 1이면 무조건 팰린드롬
            DP[i][i] = true;
        }

        for (int i = 1; i < N; i++) {
            // 길이가 2면 두 수 값이 같으면 팰린드롬
            if (arr[i] == arr[i + 1]) DP[i][i + 1] = true;
        }

        for (int i = 2; i < N; i++) {
            for (int j = 1; j <= N - i; j++) {
                // 시작점 + 1 ~ 끝 - 1 이 팰린드롬 수이고,
                // 시작값 = 끝값이면 팰린드롬 수
                if (arr[j] == arr[j + i] && DP[j + 1][j + i - 1])
                    DP[j][j + i] = true;
            }
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());
            bw.write(DP[S][E] ? "1\n" : "0\n");
        }

        bw.flush();
        br.close();
    }
}
