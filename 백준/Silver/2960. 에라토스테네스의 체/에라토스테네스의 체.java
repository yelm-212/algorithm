import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        boolean[] primeNum = new boolean[N+1];

        for(int i = 2; i <= N; i++) {
            primeNum[i] = true;
        }

        for(int i = 2; i <= N; i++) {
            for(int j = i; j <= N; j += i) {
                if(!primeNum[j])
                    continue;
                primeNum[j] = false;
                K--;
                if(K == 0) {
                    System.out.println(j);
                    return;
                }
            }
        }
    }

}
