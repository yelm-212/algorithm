import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // 인접 행렬로 폴이하면 메모리 초과 (메모리 제한에 주의) -> 인접 리스트로 구현해야 함
    private static ArrayList<Node> nodes;
    private static int K, V, E;
    private static boolean flag;

    static final int RED = 1 , BLUE = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        while (K-- != 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            nodes = new ArrayList<>();

            for (int i = 1; i <= V; i++) {
                nodes.add(new Node(i));
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());

                // 두 방향을 모두 고려해야함에 주의하라.
                nodes.get(X - 1).addVertex(Y);
                nodes.get(Y - 1).addVertex(X);
            }

            flag = true;
            // 모든 정점에 대해 BFS 탐색
            for (Node node: nodes) {
                if (node.color == 0){
                    if(!BFS(node)){
                        flag = false;
                        break; // 순회 마치고 종료
                    }
                }
            }

            System.out.println(flag ? "YES" : "NO");
        }
    }

    private static boolean BFS(Node node) {
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        node.color = RED;

        while (!q.isEmpty()){
            Node n = q.poll();

            for (Integer i : n.v){
                Node nearNode = nodes.get(i - 1);
                if (n.color == nearNode.color){
                    return false;
                }

                if (nearNode.color == 0) {
                    nearNode.color = n.color * -1;
                    q.add(nearNode);
                }
            }
        }

        return true;
    }

    static class Node{
        int u;
        ArrayList<Integer> v;

        int color;

        public Node(int u) {
            this.u = u;
            this.v = new ArrayList<>();
            this.color = 0; // 처음엔 색칠 안되어있음
        }
        public void addVertex(int vertex){
            v.add(vertex);
        }

    }
}