import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static char[][] map;
    static boolean[][] visited;
    static int N;
    static int answer1, answer2;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String st = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = st.charAt(j);
            }
        }

        answer1 = 0;
        for(int x = 0; x < N; x++) {
            for(int y = 0; y < N; y++) {
                if(!visited[x][y]) {
                    bfs(x, y, map[x][y]);
                    answer1++;
                }
                // 적록색맹인 경우 -> G 또는 R을 한가지 색으로 취급
                if(map[x][y] == 'G') {
                    map[x][y] = 'R';
                }
            }
        }

        visited = new boolean[N][N]; // 방문 배열 초기화
        answer2 = 0;
        for(int x = 0; x < N; x++) {
            for(int y = 0; y < N; y++) {
                if(!visited[x][y]) {
                    bfs(x, y, map[x][y]);
                    answer2++;
                }
            }
        }
        System.out.print(answer1 + " " + answer2);
    }

    public static void bfs(int x, int y, char target) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { x, y });
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] data = queue.poll();
            int curx = data[0], cury = data[1];

            for (int i = 0; i < 4; i++) {
                int nextx = curx + dx[i], nexty = cury + dy[i];

                if (nextx >= 0 && nexty >= 0 && nextx < N && nexty < N) {
                    // 색이 같고 미방문 노드
                    if(map[nextx][nexty] == target && !visited[nextx][nexty]) {
                        visited[nextx][nexty] = true;
                        queue.offer(new int[] {nextx, nexty});
                    }
                }
            }
        }
    }
}


