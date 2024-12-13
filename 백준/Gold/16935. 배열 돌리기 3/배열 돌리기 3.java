import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, R;
    static int[][] A;
    static int[][] tmpmap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        A = new int[N][M];

        // Initialize Array
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            int calc = Integer.parseInt(st.nextToken());

            switch (calc){
                case 1:
                    first();
                    break;
                case 2:
                    second();
                    break;
                case 3:
                    third();
                    break;
                case 4:
                    fourth();
                    break;
                case 5:
                    fifth();
                    break;
                case 6:
                    sixth();
                    break;
            }
        }

        printArray();

    }


    private static void first() {
        tmpmap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmpmap[N - i - 1][j] = A[i][j];
            }
        }

        A = tmpmap;
    }

    private static void second() {
        tmpmap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmpmap[i][M - j - 1] = A[i][j];
            }
        }

        A = tmpmap;
    }
    private static void third() {
        tmpmap = new int[M][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmpmap[j][N - i - 1] = A[i][j];
            }
        }

        swapNM();

        A = tmpmap;
    }


    private static void fourth() {
        tmpmap = new int[M][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmpmap[M - j - 1][i] = A[i][j];
            }
        }

        swapNM();

        A = tmpmap;
    }
    private static void swapNM() {
        // swap N, M
        int tmp = N;
        N = M;
        M = tmp;
    }

    private static void fifth() {
        tmpmap = new int[N][M];

        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < M / 2; j++) {
                tmpmap[i][j + M / 2] = A[i][j];
            }
        }

        for (int i = 0; i < N / 2; i++) {
            for (int j = M / 2; j < M; j++) {
                tmpmap[N / 2 + i][j] = A[i][j];
            }
        }

        for (int i = N / 2; i < N; i++) {
            for (int j = 0; j < M / 2; j++) {
                tmpmap[i - N / 2][j] = A[i][j];
            }
        }

        for (int i = N / 2; i < N; i++) {
            for (int j = M / 2; j < M; j++) {
                tmpmap[i][j - M / 2] = A[i][j];
            }
        }

        A = tmpmap;
    }
    private static void sixth() {
        tmpmap = new int[N][M];

        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < M / 2; j++) {
                tmpmap[i + N / 2][j] = A[i][j];
            }
        }

        for (int i = N / 2; i < N; i++) {
            for (int j = 0; j < M / 2; j++) {
                tmpmap[i][j + M / 2] = A[i][j];
            }
        }

        for (int i = N / 2; i < N; i++) {
            for (int j = M / 2; j < M; j++) {
                tmpmap[i - N / 2][j] = A[i][j];
            }
        }

        for (int i = 0; i < N / 2; i++) {
            for (int j = M / 2; j < M; j++) {
                tmpmap[i][j - M / 2] = A[i][j];
            }
        }

        A = tmpmap;
    }


    private static void printArray(){
        for(int i = 0; i < N ; i++){
            for (int j = 0; j < M; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }
}
