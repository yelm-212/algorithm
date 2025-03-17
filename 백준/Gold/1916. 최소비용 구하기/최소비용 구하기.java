import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, start, end;
    private static int[] distance;
    private static ArrayList<Node>[] nodes;
    private final static int INF = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        nodes = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for(int i = 0; i < M ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            nodes[s].add(new Node(e, m));
        }

        // 다익스트라 - 프림 알고리즘 초기 거리 값 INF로 설정
        distance = new int[N + 1];
        Arrays.fill(distance, INF);

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        Prim(start);

        System.out.println(distance[end]);    }

    private static void Prim(int start) {
        Queue<Node> q = new PriorityQueue<>(); // 우선순위 큐
        q.add(new Node(start, 0));
        distance[start] = 0;

        while (!q.isEmpty()){
            Node node = q.poll();
            int cur = node.cur;
            int curCost = node.cost;

            // 현재 정점까지 오는 비용이 다른 루트에서 방문하는 비용보다 비싸면 넘어감
            // 최적화
            if (curCost  > distance[cur]) continue;

            for (Node next : nodes[cur]) {
                // 다음 정점 최소 비용 > 현재 정점에서 다음 정점으로 가는 비용
                // 이면 다음 정점 최소 비용 업데이트, 큐에 추가
                if(distance[next.cur] > next.cost + curCost){
                    distance[next.cur] = next.cost + curCost;
                    q.add(new Node(next.cur, next.cost + curCost));
                }
            }
        }

    }
    private static class Node implements Comparable<Node> {
        int cur, cost;

        public Node(int cur, int cost){
            this.cur = cur;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
}
