import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // BFS 비용 최소화 -> 분리 집합

    private static int N, M;
    private static int[][] map;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static int[][] group;
    private static HashMap<Integer, Integer> hm = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        group = new int[N][M];

        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp.charAt(j) - '0';
            }
        }


        int idx = 1; // 그룹번호
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && group[i][j] == 0){
                    hm.put(idx, BFS(i, j, idx));
                    idx++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(count(i, j));
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static int count(int x, int y) {
        int sum = 1;
        HashSet<Integer> set = new HashSet<>();

        if(map[x][y] == 0) return 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || N <= nx || M <= ny ) continue;

            if (group[nx][ny] == 0) continue;

            if (map[nx][ny] == 0) set.add(group[nx][ny]);
        }

        for (int idx : set) {
            sum += hm.get(idx);
        }

        return sum % 10 ;
    }

    private static Integer BFS(int start, int end, int groupNo) {
        int cnt = 1;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start, end});
        group[start][end] = groupNo;

        while (!q.isEmpty()){
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || ny < 0 || N <= nx || M <= ny ) continue;

                // 이동 가능한 칸인 경우 큐에 추가
                if (map[nx][ny] == 0 && group[nx][ny] == 0){
                    q.add(new int[]{nx, ny});
                    group[nx][ny] = groupNo;
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
