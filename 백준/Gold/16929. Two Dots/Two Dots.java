import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] board;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int firstX, firstY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        board = new char[N][M];
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                visited = new boolean[N][M];
                firstX = i;
                firstY = j;
                if(dfs(i, j, 1)) {
                    System.out.println("Yes");
                    return;
                }
            }
        }

        System.out.println("No");
    }

    public static boolean dfs(int x, int y, int count) {
        visited[x][y] = true;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && ny >= 0 && nx < N && ny < M
                    && board[x][y] == board[nx][ny]) {
                if(visited[nx][ny] == false) {
                    visited[nx][ny] = true;
                    if(dfs(nx, ny, count + 1)) return true;
                } else {
                    // 종료 조건 : 4 이상 이동했고, 처음 위치와 이동한 위치가 같음 (사이클 형성)
                    if(count >= 4 && firstX == nx && firstY == ny) return true;
                }
            }
        }
        return false;
    }

}
