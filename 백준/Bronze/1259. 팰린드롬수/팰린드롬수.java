import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            boolean flag = true;
            StringTokenizer st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();
            if (tmp.equals("0")) break;
            N = tmp.length();

            for (int i = 0; i <= N / 2; i++) {
                if (tmp.charAt(i) != tmp.charAt(N - i - 1) ) flag = false;
            }

            System.out.println(flag ? "yes" : "no");
        }


    }
}
