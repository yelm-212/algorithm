import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int result = 0;
    static boolean[] visited;
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1];

        for(int i = 0; i < N ; i++)
            for(int j = 0; j < N ; j++)
                map[i][j] = 0;

        for(int i = 0; i < M ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = map[y][x] = 1; // 무방향 그래프
        }

        for(int i = 1; i <= N ; i++){
            if(!visited[i]){
                DFS(i);
                result++;
            }
        }

        System.out.print(result);
    }

    private static void DFS(int i) {
        visited[i] = true;

        for(int j = 1; j <= N ; j++){
            if(!visited[j] && map[i][j] == 1){
                DFS(j);
            }
        }
    }

}
