import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int A, B, C;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        if ( (A + B + C) % 3 != 0) {
            System.out.println(0);
            return;
        }

        visited = new boolean[1501][1501];
        Queue<Stones> q = new LinkedList<>();
        q.add(new Stones(A, B, C));
        visited[A][B] = true;

        while (!q.isEmpty()){
            // 종료조건
            Stones s = q.poll();

            if (s.a == s.b && s.b == s.c){
                System.out.println(1);
                return;
            }

            int a = s.a, b = s.b, c = s.c;

            if (a != b){
                int ta = swapA(a, b);
                int tb = swapB(a, b);
                if (!visited[ta][tb]) {
                    q.add(new Stones(ta, tb, c));
                    visited[ta][tb] = true;
                }
            }

            if (b != c){
                int tb = swapA(b, c);
                int tc = swapB(b, c);
                if (!visited[tb][tc]) {
                    q.add(new Stones(a, tb, tc));
                    visited[tb][tc] = true;
                }
            }

            if (a != c){
                int ta = swapA(a, c);
                int tc = swapB(a, c);
                if (!visited[ta][tc]) {
                    q.add(new Stones(ta, b, tc));
                    visited[ta][tc] = true;
                }
            }
        }

        // 큐 비엇는데 안되면 0
        System.out.println(0);
    }

    private static int swapA(int a, int b) {
        return a > b ? a - b : 2 * a;
    }

    private static int swapB(int a, int b) {
        return a > b ? 2 * b : b - a;
    }

    static class Stones {
        int a, b, c;

        public Stones(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

    }
}
