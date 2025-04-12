import java.io.*;
import java.util.*;

public class Main {
    private static int ml = 0, mm = 0, mr = 0; // 결과 값의 인덱스

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        // 탐색을 위해 정렬
        Arrays.sort(arr);

        solution(n, arr);

        System.out.println(arr[mm] + " " +arr[ml] + " " + arr[mr]);
    }

    private static void solution(int n, long[] arr) {
        // 이진 탐색
        long min = Long.MAX_VALUE;
        // 투 포인터
        for(int i = 0; i< n -2; i++) {
            int left = i+1, right = n-1;

            while (left < right) {
                long sum = arr[left] + arr[right] + arr[i];
                if(min > Math.abs(sum)) {
                    // 합의 절댓값이 현재 min값보다 작은 경우 갱신
                    min = Math.abs(sum);
                    mm = i;
                    ml = left;
                    mr = right;
                }
                if(sum == 0) {
                    // 합이 0이면 더 탐색할 필요 없음 종료
                    mm = i;
                    ml = left;
                    mr = right;
                    return;
                }
                if(sum > 0) { // 합이 양수일경우 우측 포인터 값 down
                    right--;
                }else { // 합이 음수일경우 좌측 포인터 값 up
                    left++;
                }
            }
        }
    }
}
