import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, answer;
    private static int[][] dices;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 값 입력 및 초기화
        N = Integer.parseInt(st.nextToken());
        answer = 0;
        dices = new int[N][6];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dices[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 바닥 면으로 가능한 값 1 ~ 6까지 전부 순회해서 가능한 최댓값 찾음
        for (int i = 1; i <= 6; i++){
            answer = Math.max(answer, calcMax(i));
        }

        System.out.println(answer);
    }

    private static int calcMax(int floor) {
        // 밑면의 값에 따라 최대가 되는 값 계산
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 6; j++) {
                if (dices[i][j] == floor) {
                    int ceil = dices[i][findOppsiteSide(j)];

                    if (floor == 6 || ceil == 6) {
                        if (floor == 5 || ceil == 5) {
                            // 밑면, 윗면 둘다 6 또는 5
                            res += 4;
                        } else {
                            // 밑면, 윗면이 6이고 5 미포함
                            res += 5;
                        }

                    } else {
                        // 밑면 윗면 둘다 6 아님 (옆면으로 6 OK)
                        res += 6;
                    }

                    // 현재 밑면을 윗면으로 설정
                    floor = ceil;
                    break;
                }
            }
        }
        return res;
    }

    private static int findOppsiteSide(int i){
        switch(i){
            case 0:
                return 5;
            case 1:
                return 3;
            case 2:
                return 4;
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 0;
            default:
                return -1;
        }
    }
}
