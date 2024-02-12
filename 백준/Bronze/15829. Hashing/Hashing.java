import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int L;
    static String str;
    static long sum;
    static long pow;
    static int M = 1234567891;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        str = st.nextToken();

        sum = 0;
        pow = 1;

        for(int i = 0; i < L; i++)
        {
            sum += (str.charAt(i) - 'a' + 1) * pow % M; 
            pow = pow * 31 % M; 
        }

        System.out.print(sum % M);
    }
}
