import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] price;
    static int[] DP;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        price = new int[N+1];
        DP = new int[N+1];
        price[0] = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++){
            price[i] = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= i; j++){
                // i - j 개 카드를 구입하는 최대 가격 + j번째 카드의 가격
                DP[i] = Math.max(DP[i - j] + price[j], DP[i]);
            }
        }

        System.out.println(DP[N]);

    }
}
