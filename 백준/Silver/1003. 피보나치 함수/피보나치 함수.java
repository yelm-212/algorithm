import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[][] DP;
    static int T, N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        DP = new long[41][2];
        DP();

        for (int i = 0; i < T ; i++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            printNth(N);
        }

        System.out.print(sb);
    }

    private static void printNth(int N){
        sb.append(DP[N][0] + " " + DP[N][1]+"\n");
    }

    private static void DP() {
        // init
        DP[0][0] = DP[1][1] = 1;
        DP[0][1] = DP[1][0] = 0;

        for(int i = 2; i <= 40 ; i++){
            DP[i][0] = DP[i-1][0] + DP[i-2][0];
            DP[i][1] = DP[i-1][1] + DP[i-2][1];
        }
    }
}
