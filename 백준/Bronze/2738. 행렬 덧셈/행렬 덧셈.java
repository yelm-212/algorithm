import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] A, B, res;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N][M];
        B = new int[N][M];
        res = new int[N][M];

        for(int i = 0; i < N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < M ; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < M ; j++){
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for(int i = 0; i < N ; i++){
            for(int j = 0 ; j < M ; j++){
                res[i][j] = A[i][j] + B[i][j];
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }

    }
}