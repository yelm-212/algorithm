import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] A;
    static int[] DP;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        DP = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            A[i] = Integer.parseInt(st.nextToken());
            DP[i] = 1;
        }

        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < i ; j++){
                if (A[i] > A[j]){
                    if (DP[i] < DP[j] + 1){
                        DP[i] = DP[j] + 1;
                    }
                }
            }
        }

        Arrays.sort(DP);
        System.out.println(DP[N - 1]);
    }
}
