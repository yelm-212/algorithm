import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        // 이진 탐색
        int left = 0, right = n-1;
        int ml = 0, mr = 0; // 결과 값의 인덱스
        long min = Long.MAX_VALUE;
        while(left < right) {
            long sum = arr[left] + arr[right];
            if(min > Math.abs(sum)) {
                // 합의 절댓값이 현재 min값보다 작은 경우 갱신
                min = Math.abs(sum);
                ml = left; mr = right;
            }

            if(sum >= 0) { // 합이 0이나 양수일경우 우측 포인터 값 down
                right--;
            }else { // 합이 음수일경우 좌측 포인터 값 up
                left++;
            }
        }
        System.out.println(arr[ml] + " " + arr[mr]);
    }

}
