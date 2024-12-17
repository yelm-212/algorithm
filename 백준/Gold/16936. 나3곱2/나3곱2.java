import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, de;
    private static long[] B;
    private static long[] A;
    private static boolean[] visited;
    private static boolean flag = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = new long[N];
        A = new long[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Long.parseLong(st.nextToken());
        }

        // 정렬 이후 수행
        Arrays.sort(B);

        for (int i = 0; i < N; i++) {
            if (flag) break; // 순서가 완성된 경우 종료
            solve(0, i);
        }

        printArray();
    }

    private static void printArray() {
        for (int i = 0; i < N; i++) {
            System.out.print(A[i] + " ");
        }
    }

    // 모든 경우를 진행해 올바른 순서를 찾는다.
    private static void solve(int depth, int idx) {
        if (A[N - 1] != 0){
            flag = true;
            return;
        }

        if (!visited[idx]){
            visited[idx] = true;
            A[depth] = B[idx];

            // 나3
            if (B[idx] % 3 == 0){
                // 뒤에서부터 나누기 진행하면서 현재 값보다 작은 수만...
                for (int i = idx - 1; i >= 0; i--) {
                    if(B[idx] / 3 == B[i]){
                        solve(depth + 1, i);
                        break;
                    }
                }
            }
            // 곱2
            for (int i = idx + 1; i < N; i++) {
                // 앞에서부터 곱셈 진행해서 현재 값보다 큰 수만 탐색
                if (B[idx] * 2 == B[i]){
                    solve(depth + 1, i);
                    break;
                }
            }
        }

        visited[idx] = false;
    }
}
