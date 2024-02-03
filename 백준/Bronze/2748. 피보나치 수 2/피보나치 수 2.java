import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] DP;
    static int N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        DP = new long[91];
        DP();

        System.out.print(DP[N]);
    }

    private static void DP() {
        DP[0] = 0;
        DP[1] = DP[2] = 1;

        for(int i = 3; i <= 90 ; i++){
            DP[i] = DP[i - 1] + DP[i - 2];
        }
    }
}
