import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long[][] DP;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        DP = new long[N - 1][21];
        DP[0][arr[0]] = 1; // 첫번째 원소 가능한 경우에 1 할당

        for (int i = 1; i < N - 1; i++) {
            for (int j = 0; j <= 20; j++) {
                if (DP[i - 1][j] > 0){ // 이전 루프에 1이상 20이하인 값이 존재하는 경우
                    int plus = j + arr[i]; // 이전 인덱스 값 + 현 위치 숫자값
                    if(plus >= 0 && plus <= 20) DP[i][plus] += DP[i - 1][j]; // 가능한 경우의 수 갱신

                    int minus = j - arr[i]; // 이전 인덱스 값 - 현 위치 숫자값
                    if(minus >= 0 && minus <= 20) DP[i][minus] += DP[i - 1][j]; // 가능한 경우의 수 갱신
                }
            }
        }

        System.out.println(DP[N - 2][arr[N - 1]]);
    }
}
