import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] nlist;
    static int[] ans;
    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        ans = new int[]{0, 0};
        nlist = new int[N][N];

        for (int i = 0; i < N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N ; j++){
                nlist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        calc(0, 0, N);
        print();

        System.out.print(sb);
    }

    private static void calc(int x, int y, int n) {
        int tmp = nlist[x][y];

        for (int i = x; i < x + n ; i++){
            for (int j = y; j < y + n ; j++){
                if(tmp != nlist[i][j]){
                    calc(x, y, n / 2);
                    calc(x, y+n/2, n/2);
                    calc(x+n/2, y, n/2);
                    calc(x+n/2, y+n/2, n/2);
                    return;
                }
            }
        }

        if (tmp == 0)
            ans[0] += 1;
        else
            ans[1]+= 1;
    }

    private static void print(){
        sb.append(ans[0] + "\n" + ans[1]);
    }
}
