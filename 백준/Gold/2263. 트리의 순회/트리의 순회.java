import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] in, post, pre;
    static int idx;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        in = new int[n];
        post = new int[n];
        pre = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) in[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) post[i] = Integer.parseInt(st.nextToken());

        getPreOrder(0, n - 1, 0, n - 1);

        for (int n : pre)
            bw.write(n + " ");

        bw.flush();
    }

    public static void getPreOrder(int is, int ie, int ps, int pe) {
        // is는 in 범위의 시작 위치, ie는 in 끝 위치
        // ps는 post 범위의 시작 위치, pe는 끝위치

        if (is <= ie && ps <= pe) {
            pre[idx++] = post[pe]; 
            // 포스트 오더의 가장 오른쪽은(post[pe]) 루트 노드이다.

            int pos = is;
            for (int i = is; i <= ie; i++) { // 인오더에서 루트 노드의 위치를 찾음
                if (in[i] == post[pe]) {
                    pos = i;
                    break;
                }
            }

            // 왼쪽 자식 트리 재귀호출
            getPreOrder(is, pos - 1, ps, ps + pos - is - 1);
            // 오른쪽 자식 트리 재귀호출
            getPreOrder(pos + 1, ie, ps + pos - is, pe - 1);
        }
    }
}
