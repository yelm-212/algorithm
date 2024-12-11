import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static char[][] map;
    static int[][] sumBl, sumWh; // 합 배열
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp.charAt(j);
            }
        }

        sumWh = makeSum('W'); // 누적합을 확인하기 위한 배열 (하양으로 시작)
        sumBl = makeSum('B'); // 누적합을 확인하기 위한 배열 (검정으로 시작)

        System.out.println(Math.min(makeBoard(sumWh), makeBoard(sumBl)));
    }

    private static int[][] makeSum(char c) {
        /*
        * 체스판의 누적합을 구하는 배열을 반환한다.
        * */
        int[][] tmp = new int[N + 1][M + 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int cur = 0;

                if((i + j) % 2 == 0){ // 같은 대각선상 위치
                    cur = map[i][j] == c ? 0 : 1;
                }else{
                    cur = map[i][j] != c ? 0 : 1;
                }

                tmp[i+ 1][j + 1] = tmp[i + 1][j] + tmp[i][j + 1] - tmp[i][j] + cur;
            }
        }

        return tmp;
    }


    private static int makeBoard(int[][] sumBoard) {
        /*
        체스판의 누적합 배열을 통해 K * K 배열로 잘라낸다.
        * */
        int tmp = Integer.MAX_VALUE;

        for (int i = 1; i <= N - K + 1; i++) {
            for (int j = 1; j <= M - K + 1; j++) {
                int sum = sumBoard[i + K - 1][j + K - 1]
                        - sumBoard[i - 1][j + K - 1]
                        - sumBoard[i + K - 1][j - 1]
                        + sumBoard[i - 1][j - 1];

                tmp = Math.min(tmp, sum);
            }
        }

        return tmp;
    }
}
