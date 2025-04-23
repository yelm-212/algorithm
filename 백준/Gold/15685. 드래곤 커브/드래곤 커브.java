import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int N, cnt;
    private static int[][] map = new int[101][101];
    private static ArrayList<Curve> curves = new ArrayList<>();
    private static int[] dx = {1, 0, -1, 0}; // 인덱스에 따른 방향값 : → ↑ ← ↓
    private static int[] dy = {0, -1, 0, 1}; // 인덱스에 따른 방향값 : → ↑ ← ↓
    private static boolean[][] visited = new boolean[101][101]; // 정사각형 판정에 쓸거임

    static class Curve{
        int x, y, d, g;
        Curve(int x, int y, int d, int g){
            this.x = x;
            this.y = y;
            this.d = d;
            this.g = g;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            curves.add(new Curve(x, y, d, g));
        }

        drawDragonCurve();
        count1x1square();
        System.out.println(cnt);
    }

    private static void drawDragonCurve() {
        for (Curve c : curves) {
            ArrayList<Integer> directions = new ArrayList<>();
            int currentX = c.x;
            int currentY = c.y;

            // 일단 시작점은 방문 처리
            visited[currentY][currentX] = true;

            // 0세대 처리: 시작 방향으로 한 칸 이동 및 방문 처리
            int endX = currentX + dx[c.d];
            int endY = currentY + dy[c.d];

            // 0세대 끝점 방문 처리 및 방향 추가
            if (!checkMap(endX, endY)) {
                visited[endY][endX] = true;
                directions.add(c.d);
                // 현재 끝점 업데이트
                currentX = endX;
                currentY = endY;
            } else {
                // 격자 밖으로 나가면
                continue; // 다음 커브로
            }


            // 1세대부터 g세대까지 생성
            for (int i = 1; i <= c.g; i++) {
                int size = directions.size(); // 현재 세대까지의 방향 개수
                // 이전 세대 방향들을 역순으로 참조
                for (int j = size - 1; j >= 0; j--) {
                    int dir = directions.get(j);
                    // 시계방향 90도 회전
                    int newDir = (dir + 1) % 4;

                    // 현재 끝점에서 newDir 방향으로 이동
                    int nx = currentX + dx[newDir];
                    int ny = currentY + dy[newDir];

                    if (!checkMap(nx, ny)) {
                        visited[ny][nx] = true;
                        // 새 방향을 리스트 끝에 추가
                        directions.add(newDir);
                        // 끝점 업데이트
                        currentX = nx;
                        currentY = ny;
                    } else {
                        // 문제 조건 상 격자 밖으로 나가지 않아야 함
                        break; // 해당 세대 처리 중단
                    }
                }
            }
        }
    }

    private static boolean checkMap(int nx, int ny) {
        return nx < 0 || ny < 0 || nx > 100 || ny > 100;
    }

    private static void count1x1square() {
        cnt = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (visited[i][j] && visited[i + 1][j] && visited[i][j + 1] && visited[i + 1][j + 1]) cnt++;
            }
        }
    }
}
