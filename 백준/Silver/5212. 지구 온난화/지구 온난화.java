import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int R, C;
    private static char[][] map = new char[12][12];
    private static char[][] newMap = new char[12][12];

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};

    private static int minX = 11, minY = 11;
    private static int maxX = 0, maxY = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 초기화
        for (int i = 0; i < 12; i++) {
            Arrays.fill(map[i], '.');
            Arrays.fill(newMap[i], '.');
        }

        // 지도 입력받기
        for (int i = 1; i <= R; i++) {
            String line = br.readLine();
            for (int j = 1; j <= C; j++) {
                map[i][j] = line.charAt(j - 1);
                newMap[i][j] = map[i][j];
            }
        }

        // 50년 후 지도 계산
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (map[i][j] == 'X') {
                    int cnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (map[nx][ny] != 'X') cnt++;

                    }

                    if (cnt >= 3) {
                        newMap[i][j] = '.';
                    }
                }
            }
        }

        // 최대~최소 범위 계산
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (newMap[i][j] == 'X') {
                    minX = Math.min(minX, i);
                    maxX = Math.max(maxX, i);
                    minY = Math.min(minY, j);
                    maxY = Math.max(maxY, j);
                }
            }
        }

        // 출력
        for(int i=minX; i<=maxX; i++){
            for(int j=minY; j<=maxY; j++){
                bw.write(newMap[i][j]);
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
