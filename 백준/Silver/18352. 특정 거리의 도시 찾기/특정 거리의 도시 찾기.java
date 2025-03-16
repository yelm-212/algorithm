import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, K, X ;
    private static List<List<Integer>> nodes = new ArrayList<>();
    private static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        // 현재 노드의 간선 정보 초기화
        for (int i = 0; i <= N; i++) {
            nodes.add(new ArrayList<>());
        }

        // X를 출발 지점으로 한 거리 정보 배열
        distance = new int[N + 1];
        Arrays.fill(distance, -1);

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 단방향 노드
            nodes.get(a).add(b);
        }

        BFS();

        boolean flag = false;
        for (int i = 1; i <= N; i++) {
            if(distance[i] == K){
                bw.write(i + "\n");
                flag = true;
            }
        }

        if (!flag) {
            bw.write("-1\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void BFS() {
        Queue<Integer> queue = new LinkedList<>();
        // 출발 위치를 지정한다.
        queue.add(X);
        distance[X] = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            // 다음 노드 정보를 꺼내온다.
            for (int next : nodes.get(node)) {
                if (distance[next] == -1) {
                    // 거리 정보를 업데이트하고, 큐에 다음 노드를 추가한다.
                    distance[next] = distance[node] + 1;
                    queue.add(next);
                }
            }
        }
    }
}