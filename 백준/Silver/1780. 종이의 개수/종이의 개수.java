import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int ans[] = new int[]{0, 0, 0};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DnC(0, 0, N);

        for (int res: ans){
            System.out.println(res);
        }
    }

    private static void DnC(int i, int j, int size) {
        if (checkColor(i, j, size)){
            if (map[i][j] == -1) ans[0]++;
            if (map[i][j] == 0) ans[1]++;
            if (map[i][j] == 1) ans[2]++;
            return;
        }

        int newSize = size / 3;

        // 윗부분 체크
        DnC(i, j, newSize);
        DnC(i, j + newSize, newSize);
        DnC(i, j + 2 * newSize, newSize);

        // 중앙부분 체크
        DnC(i + newSize, j, newSize);
        DnC(i + newSize, j + newSize, newSize);
        DnC(i + newSize, j + 2 * newSize, newSize);

        // 아래 체크
        DnC(i + 2 * newSize, j, newSize);
        DnC(i + 2 * newSize, j + newSize, newSize);
        DnC(i + 2 * newSize, j + 2 * newSize, newSize);
    }

    private static boolean checkColor(int i, int j, int size) {
        int init = map[i][j];

        for (int k = i; k < i + size; k++) {
            for (int l = j; l < j + size; l++) {
                if (init != map[k][l]) return false;
            }
        }

        return true;
    }
}
