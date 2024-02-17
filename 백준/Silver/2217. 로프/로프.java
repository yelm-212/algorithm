import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int arr[] = new int[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬
        Arrays.sort(arr);

        long max = 0;
        // 최대값부터 순회하면서 (내림차순) 최대 중량 값을 계산하기
        for(int i = N - 1; i >= 0; i--) {
            arr[i] *= (N - i);
            max = Math.max(max, arr[i]);
        }

        System.out.println(max);
    }
}
