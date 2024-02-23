import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String tmp;
        while ((tmp = br.readLine()) != null){
            N = Integer.parseInt(tmp);
            long val = 1;
            int cnt = 1;
            while (true){
                if(check(val, N)) break;
                val = (val * 10 + 1) % N; // 자리수 up, mod(나머지) 연산
                cnt++;
            }
            System.out.println(cnt);
        }

    }

    private static boolean check(long val, int n) {
        // val이 n으로 나뉘어 떨어지는지 검사
        return val % n == 0;
    }
}
