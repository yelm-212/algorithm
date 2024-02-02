import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int res = 0;

    static int[][] map;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        visit = new boolean[N+1];

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        for (int j = 0; j < M; j++){
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            map[x][y] = 1;
            map[y][x] = 1;
        }

        DFS(1);
        System.out.println(res);
    }

    private static void DFS(int idx){
        visit[idx] = true;

        for(int j = 1; j <= N; j++) {
            if(map[idx][j] == 1 && !visit[j]) {
                res++;
                DFS(j);
            }
        }

    }
}
