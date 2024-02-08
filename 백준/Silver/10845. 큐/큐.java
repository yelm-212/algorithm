import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Deque<Integer> q = new ArrayDeque<>();
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();
            if(tmp.equals("pop")){
                Integer tmpint = q.poll();
                System.out.println(tmpint == null ? "-1" : tmpint);
            }else if(tmp.equals("size")){
                System.out.println(q.size());
            }else if(tmp.equals("empty")){
                System.out.println(q.isEmpty() ? "1" : "0");
            }else if(tmp.equals("front")){
                Integer tmpint = q.peekFirst();
                System.out.println(tmpint == null ? "-1" : tmpint);
            }else if(tmp.equals("back")){
                Integer tmpint = q.peekLast();
                System.out.println(tmpint == null ? "-1" : tmpint);
            }else if(tmp.startsWith("push")){
                tmp = st.nextToken();
                q.offer(Integer.valueOf(tmp));
            }

        }

    }
}
