import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static char[][] map;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};
    private static int R, C, answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        
        // 모든 그래프 탐색하며 확인
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'X') continue;
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if (isBlocked(x, y)) {
                        cnt++;
                    }
                }
                
                if (cnt >= 3) {
                    bw.write("1\n");
                    bw.flush();
                    return;
                }
                
            }
        }
        
        bw.write("0\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean isBlocked(int x, int y) {
        return x < 0 || x >= R || y < 0 || y >= C || map[x][y] == 'X';
    }
}
