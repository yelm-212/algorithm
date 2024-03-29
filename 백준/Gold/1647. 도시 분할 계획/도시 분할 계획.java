import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 프림 알고리즘 사용.
    static List<Node>[] nodes;
    static boolean[] visited;
    static int total = 0, cnt = 0, max_weight = 0;
    static int N, M;
    static private class Node implements Comparable<Node>{
        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }

        int to, w;

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodes = new List[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            nodes[i] = new ArrayList<>();
            // i번째 노드에 간선들을 담을 list 선언
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            nodes[A].add(new Node(B, C));
            nodes[B].add(new Node(A, C));
        }

        Prim();
        System.out.println(total - max_weight);
    }

    static void Prim(){
        Queue<Node> q = new PriorityQueue<>();

        q.add(new Node(1, 0));

        while(!q.isEmpty()){
            Node node = q.poll();
            if (visited[node.to]) continue; // 이미 방문했으면 스킵

            visited[node.to] = true;
            total += node.w;
            max_weight = Math.max(max_weight, node.w);
            cnt++;

            if (cnt == N) break; // 선택된 노드 수 확인

            for (Node nextnode : nodes[node.to]) {
                if (!visited[nextnode.to]) q.add(nextnode);
            }
        }
    }
}
