import java.io.*;
import java.util.*;

public class Main {
    private static int cnt = 0;
    private static int number;
    private static boolean[] visited = new boolean[10000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int w = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int z = Integer.parseInt(st.nextToken());

        number = calcNumber(w, x, y, z);

        for (int i = 1111; i <= number; i++) {
            String tmp = Integer.toString(i);
            int tmpNum = calcNumber(tmp.charAt(0) - '0', tmp.charAt(1) - '0', tmp.charAt(2) - '0', tmp.charAt(3) - '0');
            // 이미 앞에서 나온 시계수이거나 0을 포함하는 경우
            if (tmp.contains("0") || visited[tmpNum]) continue;

            visited[tmpNum] = true;  // 중복 확인 체크
            cnt++;                   // 새로운 시계수이므로 카운트 증가
            if (tmpNum == number) {  // 입력 시계수와 같으면 break
                break;
            }

        }
        bw.write(cnt + "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    // 시계수 계산 로직
    private static int calcNumber(int a, int b, int c, int d) {
        int res = Integer.MAX_VALUE;
        res = Math.min(res, a * 1000 + b * 100 + c * 10 + d);
        res = Math.min(res, b * 1000 + c * 100 + d * 10 + a);
        res = Math.min(res, c * 1000 + d * 100 + a * 10 + b);
        res = Math.min(res, d * 1000 + a * 100 + b * 10 + c);

        return res;
    }
}
