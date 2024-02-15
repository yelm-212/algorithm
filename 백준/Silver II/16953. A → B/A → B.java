import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static long A, B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        BFS(A, B);
    }

    private static void BFS(long a, long b) {
        Queue<Temp> q = new LinkedList();
        q.add(new Temp(a, 1));

        while (!q.isEmpty()){
            Temp temp = q.poll();
            if(temp.a == b){
                System.out.print(temp.cnt);
                return;
            }

            if(temp.a * 2 <= b){
                q.add(new Temp(temp.a * 2, temp.cnt + 1));
            }

            if(temp.a * 10 + 1 <= b){
                q.add(new Temp(temp.a * 10 + 1, temp.cnt + 1));
            }

        }
        System.out.print("-1");
    }



    static class Temp{
        public Temp(long a, long cnt) {
            this.a = a;
            this.cnt = cnt;
        }

        long a, cnt;
    }
}
