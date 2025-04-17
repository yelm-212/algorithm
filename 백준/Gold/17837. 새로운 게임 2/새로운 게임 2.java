import java.io.*;
import java.util.*;

public class Main {
    private static int N, K, cnt = 0;
    private static ArrayList<Integer>[][] board;
    private static Horse[] horses;
    private static int[] dx = {0,0,-1,1};
    private static int[] dy = {1,-1,0,0};
    private static final int WHITE = 0;
    private static final int RED = 1;
    private static final int BLUE = 2;
    private static final int MOD = 1_000;

    static class Horse {
        int x, y, dir;
        Horse(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new ArrayList[N][N];
        horses = new Horse[K + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = new ArrayList<>();
                board[i][j].add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 1; i <= K ; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) - 1;
            horses[i] = new Horse(x, y, dir);
            board[x][y].add(i);

        }

        solution();

        if (cnt >= MOD) {
            System.out.println(-1);
        } else {
            System.out.println(cnt);
        }
    }

    private static void solution() {
        while (cnt < MOD) {
            cnt++;
            for (int i = 1; i <= K; i++) {
                if (nextTurn(horses[i], i)) {
                    return;
                }
            }
        }
    }

    private static boolean nextTurn(Horse h, int n) {
        int cur_x = h.x;
        int cur_y = h.y;
        int level = 0;
        int size = board[cur_x][cur_y].size();

        for (int i = 1; i < size; i++) {
            if (board[cur_x][cur_y].get(i) == n) {
                level = i;
                break;
            }
        }

        int moved_x = cur_x + dx[h.dir];
        int moved_y = cur_y + dy[h.dir];

        int nextBlock = 2;

        if(checkValid(moved_x,moved_y)) nextBlock = board[moved_x][moved_y].get(0);

        if(nextBlock == 2)
        {
            switch (h.dir) {
                case 0:
                    h.dir = 1;
                    break;
                case 1:
                    h.dir = 0;
                    break;
                case 2:
                    h.dir = 3;
                    break;
                case 3:
                    h.dir = 2;
                    break;
            }

            moved_x = cur_x + dx[h.dir];
            moved_y = cur_y + dy[h.dir];

            if(!checkValid(moved_x,moved_y) || board[moved_x][moved_y].get(0) == 2) return false;
            else
            {
                return move(board[moved_x][moved_y].get(0),level,size,cur_x,cur_y,moved_x,moved_y);
            }
        }
        else
        {
            return move(nextBlock,level,size,cur_x,cur_y,moved_x,moved_y);
        }
    }

    private static boolean move(int color, int start, int end, int x, int y, int mx, int my) {
        List<Integer> moving = new ArrayList<>(board[x][y].subList(start, end));
        if (board[mx][my].size() - 1 + moving.size() >= 4) return true;
        
        if (color == RED) {
            Collections.reverse(moving);
        }
        
        for (int num : moving) {
            board[mx][my].add(num);
            horses[num].x = mx;
            horses[num].y = my;
        }
        
        while (board[x][y].size() > start) {
            board[x][y].remove(board[x][y].size() - 1);
        }
        
        return checkGameOver(mx, my);
    }

    private static boolean checkValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    private static boolean checkGameOver(int x, int y) {
        return board[x][y].size() - 1 >= 4;
    }
}