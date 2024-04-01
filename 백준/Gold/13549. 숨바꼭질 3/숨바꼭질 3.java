import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static boolean[] visited; // 메모리 초과 -> 방문 여부를 확인해 BFS 횟수 줄이기
    static int result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[100001];

        Arrays.fill(visited, false);

        BFS(0, 0);
        System.out.println(result);

    }

    private static void BFS(int i, int time) {
        if (N == K) {
            result = 0;
            return;
        }

        PriorityQueue<Tmp> q = new PriorityQueue<>();
        q.offer(new Tmp(N, 0));
        visited[N] = true;

        while (!q.isEmpty()){
            Tmp present = q.poll();
            visited[present.x] = true;

            if (present.x == K){
                result = present.time; // 종료 조건
                break;
            }

            if(present.x - 1 >= 0 && present.x - 1 <= 100000 && !visited[present.x - 1]){
                q.offer(new Tmp(present.x - 1, present.time + 1)); // 걷기 1
            }
            if(present.x + 1 >= 0 && present.x + 1 <= 100000 && !visited[present.x + 1]){
                q.offer(new Tmp(present.x + 1, present.time + 1)); // 걷기 2
            }
            if(present.x * 2 >= 0 && present.x * 2 <= 100000 && !visited[present.x * 2]){
                q.offer(new Tmp(present.x * 2, present.time)); // 순간 이동
            }

        }

    }

    static class Tmp implements Comparable<Tmp>{
        int x,time;

        public Tmp(int x, int time) {
            this.x = x;
            this.time = time;
        }

        @Override
        public int compareTo(Tmp o) {
            return this.time - o.time;
        }
    }
}
