import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, V;
    static int[][] arr;
    static boolean[] visit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        arr = new int[N+1][N+1];
        visit = new boolean[N+1];

        for(int j=0; j<N+1;j++){
            Arrays.fill(arr[j], 0);
        }
        Arrays.fill(visit, false);

        for(int i=0; i<M; i++){
            String edge = br.readLine();
            st = new StringTokenizer(edge," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a][b]=1;
            arr[b][a]=1;
        }

        DFS(V);
        System.out.println();
        Arrays.fill(visit, false);
        BFS(V);
    }

    public static void DFS(int i){
        visit[i] = true;
        System.out.print(i + " ");

        // 재귀 호출을 통한 DFS 구현.
        for(int j = 1; j < N + 1; j++){
            if(!visit[j] & arr[i][j] == 1){
                // 경로가 존재하고, 방문하지 않았다면 이동한다.
                DFS(j);
            }
        }

    }

    public static void BFS(int i){
        visit[i] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i);

        while(!queue.isEmpty()){
            int temp = queue.poll(); // 첫번째 방문한 위치 확인
            System.out.print(temp+" ");
            for(int k = 1; k <= N; k++){
                if(arr[temp][k]==1 && !visit[k]){
                    queue.offer(k);
                    visit[k] = true; // 방문한 위치로 변경
                    }
            }
        }

    }


}
