import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long N, R;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken()); // 젓가락의 종류 (충분히 많이 있다)
        R = Long.parseLong(st.nextToken()); // 맞추어야 하는 짝의 개수
        
        System.out.println(N + 1 + 2 *(R - 1));
    }
}
