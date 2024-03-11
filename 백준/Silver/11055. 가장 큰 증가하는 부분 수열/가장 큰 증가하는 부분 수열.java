import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int[] DP;
    static int[] arr;
    static int N;
    static Stack<Integer> s = new Stack<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        DP = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            DP[i] = arr[i]; // DP 배열을 배열 값으로 초기화함
        }

        // LIS 알고리즘 - O(n^2)

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]){
                    DP[i] = Math.max(DP[i], DP[j] + arr[i]);
                }
            }
        }

        Arrays.sort(DP);

        System.out.println(DP[N - 1]);
    }
}
