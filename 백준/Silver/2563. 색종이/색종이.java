import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

import java.util.StringTokenizer;

public class Main {
    static int N;

    static boolean[][] board = new boolean[100][100];

    static int size = 10;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        init();

        for (int i = 0; i < N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            makeboard(x, y);
        }

        long count = countTrueValues(board);

        System.out.print(count);
    }

    private static void makeboard(int x, int y) {
        for(int j = x; j < x + size ; j++){
            for(int k = y; k < y + size ; k++){
                board[j][k] = true;
            }
        }
    }

    private static void init() {
        for(int j = 0; j < 100 ; j++){
            for(int k = 0; k < 100 ; k++){
                board[j][k] = false;
            }
        }
    }

    public static long countTrueValues(boolean[][] array) {
        long count = 0;
        for (boolean[] row : array) {
            for (boolean value : row) {
                if (value) {
                    count++;
                }
            }
        }
        return count;
    }

}
