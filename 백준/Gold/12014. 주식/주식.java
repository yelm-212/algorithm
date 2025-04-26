import java.io.*;
import java.util.*;

public class Main {
    private static int N, K, ans = 0;
    private static int[] arr;
    private static int[] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            arr = new int[N];
            DP = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
                DP[j] = 1;
            }

            LIS();

            // 가장 긴 증가하는 부분 수열의 길이가 K 이상이면 가능하고, 그 이하면 불가능이다.
            ans = DP[N - 1] >= K ? 1 : 0;

            bw.write("Case #" + (i + 1) + "\n" + ans + "\n");
        }

        bw.flush();
        bw.close();
        br.close();

    }

    // 가장 긴 증가하는 부분 수열의 길이를 구한다.
    private static void LIS() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    if (DP[i] < DP[j] + 1) {
                        DP[i] = DP[j] + 1;
                    }
                }
            }
        }

        Arrays.sort(DP);
    }
}
