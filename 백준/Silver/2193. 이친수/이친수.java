import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long[] DP;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        DP = new long[N+1];

        for (int i = 0; i <= N; i++){
            if(i <= 1) DP[i] = i;
            else{
                DP[i] = DP[i - 1] + DP[i - 2];
            }
        }

        System.out.print(DP[N]);
    }
}
