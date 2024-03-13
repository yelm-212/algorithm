import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, min, res;
    private static ArrayList<Integer>[] nodes;

    private static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        res = -1;
        nodes = new ArrayList[N + 1];
        min = Integer.MAX_VALUE;

        for (int i = 0; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) ;
            int y = Integer.parseInt(st.nextToken());

            // 양방향임에 주의
            nodes[x].add(y);
            nodes[y].add(x);
        }

        // 모든 정점에 대해 BFS 탐색
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            BFS(i);
        }

        // 결과 출력
        System.out.println(res);
    }

    private static void BFS(int start) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start, 0));
        visited[start] = true;
        int cnt = 0;

        while (!q.isEmpty()){
            Node node = q.poll();

            // 인접한 node 리스트를 탐색
            for (int nearNode : nodes[node.u]) {
                if(!visited[nearNode]){ // 미방문 노드일 경우
                    cnt += node.cnt + 1;
                    visited[nearNode] = true;
                    q.add(new Node(nearNode, node.cnt + 1)); // 새로운 노드를 더한다.
                }
            }
        }

        if(cnt < min){
            min = cnt;
            res = start;
        }

    }

    static class Node{

        public Node(int u, int cnt) {
            this.u = u;
            this.cnt = cnt;
        }

        int u;
        int cnt;
    }
}
