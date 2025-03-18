import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, vCnt, eCnt, caseCnt = 0;
    private static ArrayList<Integer>[] graph;
    private static boolean[] visited;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    public static void main(String[] args) throws IOException {
        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) break; // 종료 조건
            graph = new ArrayList[N + 1];
            visited = new boolean[N + 1];

            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                graph[b].add(a);
            }

            solve();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void solve() throws IOException {
        caseCnt++;
        int treeCnt = 0;

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;

            vCnt = 0;
            eCnt = 0;
            DFS(i);

            // tree 판정 기준
            // e == v - 1 인데 양방향이니까 * 2
            if ( eCnt == 2 * (vCnt - 1)) treeCnt++;
        }

        bw.write("Case " + caseCnt + ": ");

        switch (treeCnt){
            case 0:
                bw.write("No trees.");
                break;
            case 1:
                bw.write("There is one tree.");
                break;
            default:
                bw.write("A forest of "+ treeCnt + " trees.");
        }
        bw.newLine();
    }

    private static void DFS(int cur) {
        vCnt++; // vertex(node)  개수 count
        eCnt += graph[cur].size(); // 해당 노드에 연결된 edge 수
        visited[cur] = true;

        for (int next : graph[cur]) {
            if (!visited[next]) {
                DFS(next);
            }
        }
    }
}
