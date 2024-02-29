import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] foo;

    static int[] tmp;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        foo = new int[N];
        tmp = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            foo[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(foo);

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
        for(int i = 0; i < N; i++){

            tmp[depth] = foo[i];
            DFS( depth + 1);

        }

    }

    public static void printArray(int[] array){

        for(int i = 0; i < array.length ; i++){
            sb.append(tmp[i] + " ");
        }
        sb.append('\n');
    }
}
