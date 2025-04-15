import java.io.*;
import java.util.*;

public class Main {
    private static int[][] map;
    private static int N, L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int res = 0;
        // 가로 방향
        for (int[] arr : map) {
            if (checkPath(arr)) res++;
        }

        int[][] transposed = transpose(map);

        // 세로 방향
        for (int[] arr : transposed) {
            if (checkPath(arr)) res++;
        }

        bw.write(res + "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    private static int[][] transpose(int[][] arr) {
        int[][] res = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                res[j][i] = arr[i][j];
            }
        }
        return res;
    }

    private static boolean checkPath(int[] line) {
        boolean[] used = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            int diff = line[i + 1] - line[i];

            if (diff == 0) continue;
            else if (diff == 1) {
                // 뒤가 1 더 높으면 i에서부터  왼쪽으로 L칸 확인
                for (int j = i; j > i - L; j--) {
                    if (j < 0 || line[j] != line[i] || used[j]) return false;
                    used[j] = true;
                }
            } else if (diff == -1) {
                // 앞이 1 더 높으면 i+1부터 오른쪽으로 L칸 확인
                for (int j = i + 1; j <= i + L; j++) {
                    if (j >= N || line[j] != line[i + 1] || used[j]) return false;
                    used[j] = true;
                }
            } else // 단차가 2 이상이면 경사로 배치 불가능
                return false;
        }
        return true;
    }
    
}
