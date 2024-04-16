import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;
    static int N, idx;
    static int[] arr;
    static ArrayList<Integer>[] graph;

    // 단순 그래프 구현 -> 시간 초과. 부모 노드 값을 저장하는 set 변수 필요함
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        arr = new int[N];
        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int tmp1 = Integer.parseInt(st.nextToken());
            int tmp2 = Integer.parseInt(st.nextToken());

            // 양방향 그래프임에 주의
            graph[tmp1].add(tmp2);
            graph[tmp2].add(tmp1);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if(arr[0]==1) {
            visited[1] = true;
            idx = 1;
            DFS(-1, 1);
        }

        System.out.println(idx == N ? 1 : 0);
    }

    private static void DFS(int parent, int cur) {
        //set을 이용해 부모가 가진 자식의 노드 값을 저장함
        Set<Integer> s = new HashSet<>();

        for (int i = 0; i < graph[cur].size(); i++) {
            int nextNode = graph[cur].get(i);
//            if (!visited[nextNode] && arr[idx] == nextNode) {
//                idx++;
//                visited[nextNode] = true;
//                DFS(nextNode);
//            }
            if (nextNode != parent) s.add(nextNode);
        }

        int len = s.size();
        if(len==0) return;
        while(len>0){
            int val = arr[idx];
            // 자식 노드에 찾고자 하는 값이 있을 때만 DFS 수행
            if(!visited[val] && s.contains(val)){
                idx++;
                visited[val]=true;
                DFS(cur, val);
                len--;
            }
            else break;
        }

    }
}
