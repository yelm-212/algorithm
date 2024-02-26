import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, T;
    static long[] list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 전부 순회시 시간 복잡도 O(N ** 2)
        // -> 에라토스테네스의 체로 구현 O(NlogN)

        T = Integer.parseInt(st.nextToken());

        list = new long[1000001];

        for (int k = 1 ; k <= 1000; k++){ // 1000000 * 0.5

            for (int i = 1 ; i * k <= 1000000 ; i++){
                // 중복 방지
                if(k <= i) list[i * k - 1] += k;
                if(k < i) list[i * k - 1] += i;
            }
        }

        // 누적합
        for (int i = 1 ; i <= 1000000 ; i++){
            list[i] += list[i-1];
        }

        for (int i = 0 ; i < T; i++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            sb.append(list[N-1] + "\n");
        }

        System.out.println(sb);
    }
}
