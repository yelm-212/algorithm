import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] list; // 연결 리스트
    static boolean[] visited; // 1D 배열로 해도 ㄱㅊ
    static int res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) {
            list[i] = new ArrayList<Integer>();
        }

        visited = new boolean[N + 1];
        while (M-- != 0){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list[x].add(y);
            list[y].add(x);
        }

        // DFS

        for (int i = 0; i < N; i++) {
            visited[i] = true;
            DFS(i, 0);
            visited[i] = false;

            if(res == 1) break;
        }

        System.out.println(res);
    }

    private static void DFS(int idx, int count){
        if (count == 4){ // 4명 이상 연결됨
            res = 1;
            return;
        }

        for (int i : list[idx]) {
            if (!visited[i]) {
                visited[i] = true;
                DFS(i, count + 1);
                visited[i] = false;
            }
        }

    }
}
