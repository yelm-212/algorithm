import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, T;
    static long[][] DP;

    public static void main(String[] args) throws Exception{
        // 1 <= N <= 1000000 이므로 완전 탐색시 시간 초과가 발생할 것이다.
        // DP를 사용하자.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        DP = new long[100001][4];

        DP[1][1] = DP[2][2] = DP[3][1] = DP[3][2] = DP[3][3] = 1;

        for(int i = 4 ; i <= 100000 ; i++){
            // DP[N][i] 는 마지막에 오는 수가 i일때 1,2,3 합으로 정수 N을 만드는 경우의 수
            // 연속으로 같은 수 사용이 불가하므로...

            if (i >= 1) {
                DP[i][1] = (DP[i - 1][2] + DP[i - 1][3]) % 1000000009 ;
            }
            if (i >= 2) {
                DP[i][2] = (DP[i - 2][1] + DP[i - 2][3]) % 1000000009 ;
            }
            if (i >= 3) {
                DP[i][3] = (DP[i - 3][1] + DP[i - 3][2]) % 1000000009 ;
            }

        }

        for(int i = 0 ; i < T ; i++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            System.out.println((DP[N][1] + DP[N][2] + DP[N][3]) % 1000000009);
        }
    }
}
