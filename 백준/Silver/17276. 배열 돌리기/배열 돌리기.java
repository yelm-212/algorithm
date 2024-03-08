import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T, n, d;
    static int[][] tmp;
    static int[][] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());


        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            d = d >= 0 ? d : d + 360; // 음수인 경우 처리
            d /= 45; // 회전 횟수로 변환

            arr = new int[n][n];
            tmp = new int[n][n]; // 임시 배열 선언
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < n; k++) {
                    arr[j][k] = Integer.parseInt(st.nextToken());
                    tmp[j][k] = arr[j][k];
                }
            }

            rotateArr();

            printArr(arr); // 배열 출력
        }

        System.out.println(sb);
    }

    private static void rotateArr() { // 시계방향 회전
        while (d-- > 0){
            for (int i = 0; i < n; i++) {
                tmp[i][n / 2] = arr[i][i];
                tmp[i][i] = arr[n / 2][i];
                tmp[n / 2][i] = arr[n - i - 1][i];
                tmp[n - i - 1][i] = arr[n - i - 1][n / 2];
            }

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    arr[j][k] = tmp[j][k];
                }
            }
        }
    }

    private static void printArr(int[][] arr) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                sb.append(arr[j][k] + " ");
            }
            sb.append("\n");
        }
    }
}
