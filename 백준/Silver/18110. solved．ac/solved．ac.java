import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nlist = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            nlist[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nlist);

        int tmp = (int) Math.round(N * 0.15);

        int sum = 0;
        int count = 0;
        for (int i = tmp; i < N - tmp; i++) { // Start from tmp to N - tmp
            sum += nlist[i];
            count++;
        }

        if (count != 0) {
            double res = (double) sum / count;
            System.out.print(Math.round(res));
        } else {
            System.out.print(0);
        }
    }
}
