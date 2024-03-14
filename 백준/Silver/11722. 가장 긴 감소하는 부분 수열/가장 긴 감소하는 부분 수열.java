import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // 가장 긴 감소하는 부분 수열
    static int[] arr;
    static int[] DP;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        DP = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(DP, 1);
        int res = 1;

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]){ // 감소하는 경우
                    DP[i] = Math.max(DP[i], DP[j] + 1);
                    res = Math.max(DP[i], res);
                }
            }
        }

        System.out.println(res);
    }
}