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
        price[0] = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++){
            DP[i] = price[i] = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= i; j++){
                DP[i] = Math.min(DP[i], DP[i - j] + price[j]);
            }
        }

        System.out.print(DP[N]);
    }
}
