import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    private static int N, A, B, answer;
    private static Integer[] tile_A;
    private static Integer[] tile_B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        tile_A = new Integer[A];
        tile_B = new Integer[B];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < A; i++) {
            tile_A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < B; i++) {
            tile_B[i] = Integer.parseInt(st.nextToken());
        }

        // 내림차순 정렬 수행
        Arrays.sort(tile_A, Collections.reverseOrder());
        Arrays.sort(tile_B, Collections.reverseOrder());


        // 값이 홀수인 경우, A 타일을 우선적으로 사용 후 남은 타일을 배치한다.
        int a_i = 0, b_i = 0;
        if (N % 2 != 0) {
            answer += tile_A[a_i++];
            N--;
        }

        while (N != 0) {
            int tmpA = 0, tmpB = 0;

            // 배치 가능 여부를 확인 후,
            // A 타일의 value와 B 타일의 value를 계산한다.
            if (a_i + 1 < A){
                tmpA = tile_A[a_i] + tile_A[a_i+1];
            }
            if (b_i < B ){
                tmpB = tile_B[b_i];
            }

            if (tmpA >= tmpB){
                answer += tmpA;
                a_i += 2;
            }else {
                answer += tmpB;
                b_i++;
            }

            N -= 2;
        }

        System.out.println(answer);

    }
}
