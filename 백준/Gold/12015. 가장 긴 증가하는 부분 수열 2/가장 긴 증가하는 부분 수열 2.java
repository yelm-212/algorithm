import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] res = new int[N];
        int len = 0;

        for (int i = 0; i < N; i++) {
            int pos = Arrays.binarySearch(res, 0, len, arr[i]);

            if (pos < 0) {
                pos = -(pos + 1);
            }

            res[pos] = arr[i];
            if (pos == len) {
                len++;
            }
        }

        System.out.println(len);
    }
}