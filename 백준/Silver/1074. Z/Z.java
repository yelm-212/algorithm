import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, r, c, res;

    static int x, y, count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int res = calc((int) Math.pow(2, N), r, c);

        System.out.println(res);
    }

    private static int calc(int n, int r, int c) {
        n /= 2;

        //왼쪽 위
        if(r < x + n && c < y + n) {
            count += (n * n * 0);

            //오른쪽 위
        }else if(r < x + n && c >= y + n) {
            count += (n * n * 1);
            y += n;
            //왼쪽 아래
        }else if(c < y + n) {
            count += (n * n * 2);
            x += n;
            //오른쪽 아래
        }else {
            count += (n * n * 3);
            x += n;
            y += n;
        }

        // n 값이 1이 되면 결과값을 리턴한다.
        if(n == 1) return count;

        // 재귀호출
        return calc(n, r, c);
    }
}
