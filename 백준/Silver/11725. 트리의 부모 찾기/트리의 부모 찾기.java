import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static List<List<Integer>> nodes = new ArrayList<>();
    private static int[] parent;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            nodes.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            nodes.get(a).add(b);
            nodes.get(b).add(a);
        }

        // 1번 노드를 루트 노드(시작 노드)로 해 탐색한다.
        DFS(1);

        for (int i = 2; i < parent.length; i++) {
            System.out.println(parent[i]);
        }

    }

    private static void DFS(int cur) {
        visited[cur] = true;

        // 현재 노드와 연결된 노드들 방문하게 한다.
        for (int i : nodes.get(cur)) {
            if (!visited[i]) {
                DFS(i);
                // 방문되는 노드의 부모 노드는 현재 노드이다.
                parent[i] = cur;
            }
        }
    }
}
