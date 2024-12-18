import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, L, R, X, cnt;
    private static int[] A;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        cnt = 0;
        A = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬 먼저
        Arrays.sort(A);

        // 조합 갯수 구하기

        for (int i = 2; i <= N; i++) {
            combination(0, i);
        }

        System.out.println(cnt);
    }

    private static void combination(int start, int select) {
        if (select == 0){
            // 2개 이상 다 골랏음
            int sum = 0;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < N; i++) {
                if (visited[i]){
                    sum += A[i];
                    min = Math.min(min, A[i]);
                    max = Math.max(max, A[i]);
                }
            }

            // 조건에 맞는지 확인
            if (sum >= L && sum <= R && max - min >= X) cnt++;

            return;
        }

        for (int i = start; i < N; i++) {
            if(!visited[i]){
                visited[i] = true;
                combination(i + 1, select - 1);
                visited[i] = false;
            }
        }
    }

}
