import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, R;
    static int[][] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M ; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 회전
        for (int i = 0; i < R ; i++){

            // N과 M 중 작은 값 기준으로 1/2 값이 돌아가는 사각형의 개수
            for (int j = 0 ; j < Math.min(N, M) / 2 ; j++){
                // 맨 마지막 값 할당하기 위한 임시값
                int tmp = arr[j][j];

                // L
                for(int k = j ; k < M - j - 1; k++){
                    arr[j][k] = arr[j][k + 1];
                }
                // U
                for(int k = j ; k < N - j - 1; k++){
                    arr[k][M - j - 1] = arr[k + 1][M - j - 1];
                }
                // R
                for(int k = M - j - 1 ; k > j; k--){
                    arr[N - j - 1][k] = arr[N - j - 1][k - 1];
                }
                // D
                for(int k = N - j - 1 ; k > j; k--){
                    arr[k][j] = arr[k - 1][j];
                }

                arr[j+1][j] = tmp;
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N ; i++){
            for (int j = 0; j < M ; j++){
                sb.append(arr[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}