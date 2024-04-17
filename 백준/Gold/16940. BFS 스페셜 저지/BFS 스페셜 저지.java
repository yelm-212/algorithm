import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[] visited;
    static int N, idx;
    static int[] arr;
    static ArrayList<HashSet<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        arr = new int[N + 1];
        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new HashSet<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int tmp1 = Integer.parseInt(st.nextToken());
            int tmp2 = Integer.parseInt(st.nextToken());

            // 양방향 그래프임에 주의
            graph.get(tmp1).add(tmp2);
            graph.get(tmp2).add(tmp1);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if(arr[1] == 1) {
            BFS(1); // 1에서부터 시작
        }else{
            // 시작점이 1이 아니면 불가능
            System.out.println(0);
            return;
        }

    }

    private static void BFS(int i) {
        Queue<Integer> q = new LinkedList<>();
        q.add(i);
        visited[i] = true;
        idx = 2;

        while (!q.isEmpty()){
            int cur = q.poll();

            // 현재 노드의 자식들 방문 처리
            int cnt = 0;
            for (int next : graph.get(cur)) {
                if(!visited[next]){
                    visited[next] = true;
                    cnt++;
                }
            }

            for (int j = idx; j < idx + cnt; j++) {
                if (!visited[arr[j]]){
                    // 현재 노드의 자식이 아닌 경우
                    System.out.println(0);
                    return;
                }else {
                    // 현재 노드의 자식인 경우 큐에 넣음
                    q.add(arr[j]);
                }
            }
            idx += cnt;
        }

        System.out.println(1);
    }

}
