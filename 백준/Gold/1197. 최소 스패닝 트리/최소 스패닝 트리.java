import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 가중치가 음수일 수 있으므로 다익스트라 사용 불가
    // 프림 혹은 크루스칼 알고리즘 사용.
    static List<Node>[] nodes;
    static boolean[] visited;
    static int total;
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
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        nodes = new List[V + 1];
        visited = new boolean[V + 1];

        for (int i = 0; i <= V; i++) {
            nodes[i] = new ArrayList<>();
            // i번째 노드에 간선들을 담을 list 선언
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            nodes[A].add(new Node(B, C));
            nodes[B].add(new Node(A, C));
        }

        Prim(1);
        System.out.println(total);
    }

    static void Prim(int start){
        Queue<Node> q = new PriorityQueue<>();
        q.add(new Node(start, 0));

        while(!q.isEmpty()){
            Node node = q.poll();
            if (visited[node.to]) continue; // 이미 방문했으면 스킵

            visited[node.to] = true;
            total += node.w;

            for (Node nextnode : nodes[node.to]) {
                if (!visited[nextnode.to]) q.add(nextnode);
            }
        }
    }

}