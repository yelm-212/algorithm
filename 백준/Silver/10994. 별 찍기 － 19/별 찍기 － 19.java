import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        N = (4 * N) - 3;
        arr = new char[N][N];
        for(int i = 0 ; i < arr.length; i++) Arrays.fill(arr[i], ' ');

        DFS(0, N);
        printArray();
    }

    // N - 사각형의 길이, start - 시작 좌표
    private static void DFS(int start, int n) {
        if (start >= n) { // 범위 벗어나면 종료
            return;
        }

        for (int i = start; i < n; i++){
            arr[start][i] = '*'; // 상
            arr[n - 1][i] = '*'; // 하
            arr[i][start] = '*'; // 좌
            arr[i][n - 1] = '*'; // 우
        }

        DFS(start + 2, n - 2); // 시작 좌표는 2씩 더하고, 길이는 2씩 감소
    }

    private static void printArray() {
        for(int i = 0 ; i < arr.length; i++) {
            for(int j = 0; j < arr.length; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
