import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    private static int N, A, B, answer;
    private static int[] tile_A;
    private static int[] tile_B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        tile_A = new int[A];
        tile_B = new int[B];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < A; i++) {
            tile_A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < B; i++) {
            tile_B[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬 수행
        Arrays.sort(tile_A);
        Arrays.sort(tile_B);


        // 값이 홀수인 경우, A 타일을 우선적으로 사용 후 남은 타일을 배치한다.
        int a_i = A - 1, b_i = B - 1;
        if (N % 2 != 0) {
            answer += tile_A[a_i--];
            N--;
        }

        while (N != 0) {
            int tmpA = 0, tmpB = 0;

            // 배치 가능 여부를 확인 후,
            // A 타일의 value와 B 타일의 value를 계산한다.
            if (a_i >= 1){
                tmpA = tile_A[a_i] + tile_A[a_i - 1];
            }
            if (b_i >= 0){
                tmpB = tile_B[b_i];
            }

            if (tmpA >= tmpB){
                answer += tmpA;
                a_i -= 2;
            }else {
                answer += tmpB;
                b_i--;
            }

            N -= 2;
        }

        System.out.println(answer);

    }
}
