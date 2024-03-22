import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] DP = new int[31];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        // 홀수번째에는 못들어간다.
        DP[0] = 1;

        for (int i = 2; i <= N; i+=2) {
            DP[i] = DP[i - 2] * 3; // 바로 이전의 결과를 가장 끝에 두는 경우.
            for (int j = i - 4; j >= 0; j-=2 ) {
                DP[i] += DP[j] * 2; // 새로운 도형의 결과를 두는 경우
            }
        }

        System.out.println(DP[N]);
    }
}
