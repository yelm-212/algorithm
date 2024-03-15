import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T, N;
    static String[] peoples;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            peoples = new String[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            if ( N > 32) {
                sb.append(0 + "\n");
                continue;
            }

            for (int j = 0; j < N; j++) {
                peoples[j] = st.nextToken();
            }

            int min = Integer.MAX_VALUE; // 세 사람의 최솟값

            // 3명을 고르는 경우의 수에 대해 모두 검사 : Brute Force
            for (int j = 0; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    for (int l = k + 1; l < N; l++) {
                        int tmp = 0;

                        for (int m = 0; m < 4; m++) {
                            tmp += peoples[j].charAt(m) == peoples[k].charAt(m) ? 0 : 1;
                            tmp += peoples[j].charAt(m) == peoples[l].charAt(m) ? 0 : 1;
                            tmp += peoples[l].charAt(m) == peoples[k].charAt(m) ? 0 : 1;
                        }

                        min = Math.min(min, tmp);
                        if (min == 0) break;
                    }
                }
            }

            sb.append(min + "\n");
        }

        System.out.println(sb);

    }
}
