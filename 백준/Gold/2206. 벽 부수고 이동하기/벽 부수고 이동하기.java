import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static boolean[][] visitedCrash;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static class Point{
        int x, y, dist, wall; 

        public Point(int x, int y, int dist, int wall) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.wall = wall; // 한개는 부숴도 됨
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        visitedCrash = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp.charAt(j) - '0';
            }
        }

        System.out.println(BFS());
    }

    private static int BFS() {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0, 1, 1));
        visited[0][0] = true;

        while (!q.isEmpty()){
            Point polledPoint = q.poll();

            // 종료 조건
            if ((polledPoint.x == N - 1) && (polledPoint.y == M - 1)){
                list.add(polledPoint.dist);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int tmpX = polledPoint.x + dx[i];
                int tmpY = polledPoint.y + dy[i];

                // map 범위 벗어나거나면 다른 방향 검사
                if (tmpX < 0 || tmpY < 0 || tmpX >= N || tmpY >= M) continue;

                // 벽인지 확인
                boolean isWall = (map[tmpX][tmpY] == 1);

                // 벽 위치이고 이미 다른 벽을 부숨
                if (isWall && polledPoint.wall == 0) continue;

                if (polledPoint.wall == 0){
                    if (visitedCrash[tmpX][tmpY]) continue;
                    visitedCrash[tmpX][tmpY] = true;
                }else{
                    if (visited[tmpX][tmpY]) continue;
                    visited[tmpX][tmpY] = true;
                }

                q.add(new Point(tmpX, tmpY, polledPoint.dist + 1,
                        isWall ? polledPoint.wall - 1 : polledPoint.wall));
            }

        }

        Collections.sort(list);
        return list.size() == 0 ? -1 : list.get(0);
    }
}
