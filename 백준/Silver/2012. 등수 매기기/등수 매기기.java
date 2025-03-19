import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static long answer;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        answer = 0L;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        // 1 1 4 4 4

        for (int i = 0; i < N; i++) {
            answer += Math.abs(arr[i] - (i + 1));
        }

        bw.write(answer + "\n");
        bw.flush();
        br.close();
        bw.close();
    }
}
