import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, K, cnt;
    private static char[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new char[N];

        // 배열 입력받기
        arr = br.readLine().toCharArray();

        solve();

        System.out.println(cnt);

    }

    private static void solve() {
        for (int i = 0; i < N; i++) {
            // 사람 위치가 아닌 경우 early return
            if (arr[i] != 'P') {
                continue;
            }

            // 사람 기준으로 제일 왼쪽 K번째 ~ 제일 오른쪽 K번째 (가능한 위치)
            for (int j = i - K; j <= i + K; j++) {
                // 현재 위치에서 햄버거를 먹을 수 있는 가장 좌측 위치를 찾고, 
                // 먹은 햄버거를 처리한다.
                if (isEdible(j)){
                    arr[j] = ' ';
                    cnt++;
                    break;
                }
            }
        }
    }

    private static boolean isEdible(int j) {
        return j >= 0 && j < N && arr[j] == 'H';
    }
}
