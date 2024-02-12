import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int L;
    static String str;
    static long res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        str = st.nextToken();

        for (int i = 0 ; i < str.length() ; i++ ){
            int a = str.charAt(i) - 96;
            res += a * Math.pow(31, i);
        }


        System.out.print(res % 1234567891);
    }
}
