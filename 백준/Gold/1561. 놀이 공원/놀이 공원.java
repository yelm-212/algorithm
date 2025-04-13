import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 운행 시간
        int[] arr = new int[M];
        int max = Integer.MIN_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        if (N <= M) {
            bw.write(N + "\n");
            bw.flush();
            System.exit(0);
        }

        long res = binarySearch(arr, N, M, max);

        long prevT = res - 1;
        long prevCnt = M;
        long[] prevArr = new long[M];
        for (int i = 0; i < M; i++) {
            prevArr[i] = prevT / arr[i];
            prevCnt += prevArr[i]; // 경과 시간
        }

        long nowNCnt = N - prevCnt;
        long nowCnt = 0;
        long answer = N;

        for (int i = 0; i < M; i++) {
            if (res / arr[i] != prevArr[i]) {
                nowCnt++;
                if (nowCnt == nowNCnt) {
                    answer = i + 1;
                    break;
                }
            }
        }
        bw.write(answer + "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    private static long binarySearch(int[] a, long N, int M, int max) {
        // 마지막 사람이 타는 시간 기준으로 이분 탐색을 수행한다
        long left = 0, right = (N / M) * max;
        long res = 0;

        // N명 이상을 태울 수 있는 최소 시간을 구한다...
        while (left <= right) {
            long mid = (left + right) / 2;
            long cnt = M;

            for (int i = 0; i < M; i++) {
                cnt += mid / a[i];
            }

            if (N <= cnt){
                res = mid;
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }

        return res;
    }
}
