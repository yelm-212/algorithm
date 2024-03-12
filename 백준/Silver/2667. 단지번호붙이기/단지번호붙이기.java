import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    static ArrayList<Integer> list = new ArrayList<>();
    static int cnt;
    static int[][] map;
    static int N;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(tmp.charAt(j)));
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // ** 1일때만 DFS 혹은 BFS 수행 **
                if (map[i][j] == 1){
                    DFS(i, j);
                    list.add(cnt);
                    cnt = 0; // 리스트에 한 단지 값 추가되면 초기화
                }
            }
        }

        Collections.sort(list); // 오름차순 정렬임에 주의
        System.out.println(list.size());

        StringBuilder sb = new StringBuilder();
        for (Integer i: list) {
            sb.append(i + "\n");
        }
        System.out.println(sb);
    }

    private static void DFS(int x, int y) {
        // early return
        if (x < 0 || x >= N || y < 0 || y >= N) return;

        if (map[x][y] == 1){
            cnt++;
            map[x][y] = 0; // 한번 지나온 자리 못세게 함

            for (int i = 0; i < 4; i++) {
                int moveX = x + dx[i];
                int moveY = y + dy[i];
                DFS(moveX, moveY);
            }
        }
    }
}