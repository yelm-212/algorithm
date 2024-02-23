import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[] dx = new int[]{0, 0, 1, -1};
    static int[] dy = new int[]{1, -1, 0, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        int res = 0;

        res = 2 * N * M; // 바닥면, 윗면 단면 넓이

        for (int i = 0; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N ; i++){
            for (int j = 0; j < M ; j++){
                for(int k = 0; k < 4 ; k++){ // 4방향 탐색
                    int nextX = i + dx[k];
                    int nextY = j + dy[k];

                    if(nextX < 0 || nextY < 0 || nextY > M - 1 || nextX > N - 1){ // 범위 밖인지 확인
                        res += map[i][j];
                        continue;
                    }
                    if(map[i][j] > map[nextX][nextY]){
                        res += map[i][j] - map[nextX][nextY]; // 값이 더 큰 경우에만 그 차이를 겉넓이에 더해줌
                    }
                }
            }
        }



        System.out.println(res);
    }
}
