import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, S, answer;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        answer = 0;

        // 배열 입력받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        backtracking(0, 0);

        // S가 0인 경우 공집합은 제외한다.
        System.out.println(S == 0 ? answer - 1 : answer );
    }

    private static void backtracking(int idx, int sum) {
        if (idx == N) {
            if (sum == S) {
                answer++;
            }
            return;
        }

        backtracking(idx + 1, sum + arr[idx]); // 선택하는 경우
        backtracking(idx + 1, sum);                 // 선택하지 않는 경우
    }
}
