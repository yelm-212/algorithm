import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, K, answer;
    private static int[] arr;
    private static int[] subtract;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 차이 배열 구하기
        subtract = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            subtract[i] = arr[i + 1] - arr[i];
        }

        // 정렬
        Arrays.sort(subtract);

        answer = 0;

        // iteration 횟수 = (N - 1) - (K - 1)
        for (int i = 0; i < N - K; i++) {
            answer += subtract[i];
        }

        System.out.println(answer);
    }
}
