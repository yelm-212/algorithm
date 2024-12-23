import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 노드 개수가 최대 100~200개일때 플로이드 워셜을 사용할 수 있다.

    private static int N, M;
    private static int[][] dist;
    private static int INF = 10000000;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dist = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) dist[i][j] = 0;
                else dist[i][j] = INF;
            }
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            // 이동 시간에 대해 최소 입력값이 선택되도록 보장해야 한다.
            dist[a][b] = Math.min(dist[a][b], c);
        }

        floyd_warshall();

        printDist();
    }

    private static void printDist() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dist[i][j] == INF)
                    sb.append("0 ");
                else
                    sb.append(dist[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void floyd_warshall() {

        for (int k = 0; k < N; k++) { // 경유 노드
            for (int s = 0; s < N; s++) { // 출발 노드
                for (int e = 0; e < N; e++) { // 도착 노드
                    if (dist[s][k] != INF && dist[k][e] != INF) { // INF 오버플로 방지
                        dist[s][e] = Math.min(dist[s][e], dist[s][k] + dist[k][e]);
                    }
                }
            }
        }

    }
}
