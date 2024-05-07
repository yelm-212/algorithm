import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static long s, t;
    static HashSet<Long> visited;
    static class Node{
        long num;
        String ops;

        public Node(long num, String ops) {
            this.num = num;
            this.ops = ops;
        }
    }

    static long MAX = 1000000000L;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        s = Long.parseLong(st.nextToken());
        t = Long.parseLong(st.nextToken());

        if (s == t){
            System.out.println(0);
            return;
        }

        // 방문 여부를 배열로 저장하지 않고, HashSet에 저장한다. (메모리 절약)
        visited = new HashSet<>();

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(s, ""));
        visited.add(s);

        while (!q.isEmpty()){
            Node tmp = q.poll();

            if (tmp.num == t){
                System.out.println(tmp.ops);
                return;
            }

            if (check(tmp.num * tmp.num)){
                q.add(new Node(tmp.num * tmp.num, tmp.ops+ "*") );
                visited.add(tmp.num * tmp.num);
            }
            if (check(tmp.num + tmp.num)){
                q.add(new Node(tmp.num + tmp.num, tmp.ops+ "+") );
                visited.add(tmp.num + tmp.num);
            }
            if (check(tmp.num - tmp.num)){
                q.add(new Node(tmp.num - tmp.num, tmp.ops+ "-") );
                visited.add(tmp.num - tmp.num);
            }
            if (tmp.num != 0 && check(tmp.num / tmp.num)){
                q.add(new Node(tmp.num / tmp.num, tmp.ops+ "/") );
                visited.add(tmp.num / tmp.num);
            }
        }

        System.out.println(-1);
    }

    private static boolean check(long val) {
        return 0 <= val &&
                val <= MAX &&
                !visited.contains(val);
    }

}
