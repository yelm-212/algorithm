import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long N, K;
    static long[] course;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Long.parseLong(st.nextToken().replace("\n", ""));
        K = Long.parseLong(st.nextToken().replace("\n", ""));

        st = new StringTokenizer(br.readLine(), " ");
        course = new long[(int) N];
        long sum = 0;
        long prev;
        boolean flag = true;
        for(long i = 0; i < N ; i++){
            long tmp = Long.parseLong(st.nextToken().replace("\n", ""));
            prev = sum;
            sum += tmp; // 7, 11, 13, 17, 22
            course[(int) i] = tmp; // 7, 4, 2, 4, 5

            if (prev <= K && K < sum){
                System.out.println(i + 1);
                flag = false;
                break;
            }
        }

        if (flag){
            for(long i = N - 1; i >= 0 ; i--){
                prev = sum;
                sum += course[(int) i];
                // 22, 27, 31, 33, 37, 44

                if (prev <= K && K < sum){
                    System.out.println(i + 1);
                    break;
                }
            }
        }

    }
}
