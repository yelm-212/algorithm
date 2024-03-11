import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[][] map = new int[5][5]; // size 고정
    private static boolean[][] bingo = new boolean[5][5]; // bingo 여부 확인을 위함
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                bingo[i][j] = false;
            }
        }

        int idx = 1;
        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                findMap(tmp);
                if (check3Bingo()) {
                    break;
                }
                idx++;
            }
        }

        System.out.println(idx);
    }

    private static boolean check3Bingo() {
        int tmp = 0;
        for (int i = 0; i < 5; i++) {
            // 가로 방향 확인
            if (bingo[i][0] && bingo[i][1] && bingo[i][2]
                    && bingo[i][3] && bingo[i][4]) tmp++;

            // 세로 방향 확인
            if (bingo[0][i] && bingo[1][i] && bingo[2][i]
                    && bingo[3][i] && bingo[4][i]) tmp++;

        }

        // 대각선 방향 확인
        if (bingo[0][0] && bingo[1][1] && bingo[2][2]
                && bingo[3][3] && bingo[4][4]) tmp++;

        if (bingo[0][4] && bingo[1][3] && bingo[2][2]
                && bingo[3][1] && bingo[4][0]) tmp++;

        return tmp >= 3;
    }

    private static void findMap(int tmp) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == tmp){
                    bingo[i][j] = true;
                }
            }
        }
    }
}
