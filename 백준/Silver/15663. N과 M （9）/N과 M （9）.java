import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    static int N, M;
    static int[] foo;
    static int[] tmp;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        foo = new int[N];
        tmp = new int[M];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            foo[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(foo); // 정렬 먼저

        DFS(0);
        System.out.print(sb);
    }

    public static void DFS(int depth){
        if (depth == M){
            // 탐색 종료 조건 : 길이가 M
            printArray(tmp);
            return;
        }

        // 재귀 호출을 통한 DFS 구현
        int tmpref = 0;
        for(int i = 0; i < N; i++){
            if(visited[i]) continue;

            if(tmpref != foo[i]){ // 중복 수열은 1번만 출력
                visited[i] = true;
                tmp[depth] = foo[i];
                tmpref = foo[i];
                DFS( depth + 1);
                visited[i] = false;
            }

        }

    }

    public static void printArray(int[] array){

        for(int i = 0; i < array.length ; i++){
            sb.append(tmp[i] + " ");
        }
        sb.append('\n');
    }
}
