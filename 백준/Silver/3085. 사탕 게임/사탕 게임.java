import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int maxCandies;
    static char[][] charBoard;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        charBoard = new char[N][N];
        maxCandies = 0;

        for(int i = 0; i < N ; i++){
            String tmp = br.readLine();
            for(int j = 0; j < N ; j++){
                // Board 초기화  
                charBoard[i][j] = tmp.charAt(j);
            }
        }

        // 가로 방향 검사  
        for(int i = 0; i < N ; i++){
            for(int j = 0; j < N - 1 ; j++){
                swapAndCalc(i, j, i, j+1);
            }
        }

        // 세로 방향 검사  
        for(int i = 0; i < N - 1 ; i++){
            for(int j = 0; j < N ; j++){
                swapAndCalc(i, j, i+1, j);
            }
        }

        System.out.println(maxCandies);
    }

    private static void swapAndCalc(int i, int j, int i1, int i2) {
        // swap  i.j <-> i1.j1
        char tmp = charBoard[i][j];
        charBoard[i][j] = charBoard[i1][i2];
        charBoard[i1][i2] = tmp;

        int currentCandies = countMaxCandies();

        // 최댓값 갱신  
        if(maxCandies < currentCandies) {
            maxCandies = currentCandies;
        }

        // 원위치로  
        tmp = charBoard[i][j];
        charBoard[i][j] = charBoard[i1][i2];
        charBoard[i1][i2] = tmp;
    }

    // 현재 board에서 최대 사탕 개수 계산  
    private static int countMaxCandies() {
        int maxcnt = Integer.MIN_VALUE;

        // 가로 방향으로 연속된 사탕 개수 계산  
        for (int i = 0; i < charBoard.length; i++) {
            int count = 1;
            for (int j = 1; j < charBoard.length; j++) {
                // 연속되는 경우에 +1하고, 그렇지 않으면 초기화  
                if (charBoard[i][j] == charBoard[i][j - 1]) {
                    count++;
                } else {
                    count = 1;
                }

                // 최대 개수 갱신  
                if(maxcnt < count) maxcnt = count;

            }
        }

        // 세로 방향으로 연속된 사탕 개수 계산  
        for (int i = 0; i < charBoard.length; i++) {
            int count = 1;
            for (int j = 1; j < charBoard.length; j++) {
                // 연속되는 경우에 +1하고, 그렇지 않으면 초기화  
                if (charBoard[j][i] == charBoard[j - 1][i]) {
                    count++;
                } else {
                    count = 1;
                }

                // 최대 개수 갱신  
                if(maxcnt < count) maxcnt = count;
            }
        }

        return maxcnt;
    }

}