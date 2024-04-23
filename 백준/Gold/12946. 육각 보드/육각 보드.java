import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, result = 0;
    static char[][] board;
    static int[] dx = {0, 1, -1, 1, -1, 0}; // 탐색을 위한 X 이동방향
    static int[] dy = {-1, -1, 0, 0, 1, 1}; // 탐색을 위한 Y 이동방향
    static int[][] tempBoard; // 색상 정보 저장 배열
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new char[N][N];
        tempBoard = new int[N][N];
        visited = new boolean[N][N];

        for(int i = 0;i < N;i++) {
            String str = br.readLine();
            for(int j = 0;j < N;j++) {
                board[i][j] = str.charAt(j);
            }
        }
        search(); // DFS

        System.out.println(result);
    }

    // 보드 'X'칸 DFS탐색
    // 색 종류 3개(색의 최대 개수)일 때 DFS 탐색 종료
    static void search() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(board[i][j] == 'X' && !visited[i][j])
                    // 'X'칸에 대해 DFS 탐색
                    dfs(j,i);

                if(result == 3)	// 색 종류 3개 되면 탐색 종료
                    return;
            }
        }
    }

    // DFS 탐색 색칠
    static void dfs(int x, int y) {
        if(result == 3)	// 색 종류가 3되면 종료
            return;

        boolean[] color = new boolean[4]; // 색 확인
        visited[y][x] = true;

        for(int i = 0;i < 6;i++) {
            int tempX = x + dx[i];
            int tempY = y + dy[i];
            // 근처 킨 색칠에 사용된 색상 확인
            if(tempX>=0 && tempY>=0 && tempX<N && tempY<N) {
                if(board[tempY][tempX] == 'X' && tempBoard[tempY][tempX]!=0)
                    color[tempBoard[tempY][tempX]] = true;
            }
        }

        // 주변 사용된 색 제외한 현재 칸 색칠하기
        for(int i = 1;i < color.length; i++) {
            if(!color[i]) {
                result = Math.max(result, i);
                tempBoard[y][x] = i;
                break;
            }
        }

        // 인접한 'X'칸으로 이동 (DFS)
        for(int i = 0;i < 6;i++) {
            int tempX = x + dx[i];
            int tempY = y + dy[i];
            if(tempX>=0 && tempY>=0 && tempX<N && tempY<N) {
                if(board[tempY][tempX] == 'X' && !visited[tempY][tempX])
                    dfs(tempX,tempY);
            }
        }
    }
}
