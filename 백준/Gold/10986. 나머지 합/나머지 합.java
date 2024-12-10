import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] C = new int[M];
        
        st = new StringTokenizer(br.readLine());
        int prefixSum = 0; // 누적합
        long ans = 0; // 정답을 long으로 선언 (합이 큰 경우를 처리)
        
        // 초기값 처리: 나머지가 0인 경우를 포함
        C[0] = 1;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            prefixSum = (prefixSum + num) % M;

            // 음수 나머지 처리
            if (prefixSum < 0) prefixSum += M;

            // 나머지가 같은 구간의 개수를 더함
            ans += C[prefixSum];

            // 현재 나머지를 카운트
            C[prefixSum]++;
        }

        System.out.println(ans);
    }
}
