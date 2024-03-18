import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, start, end;
    static ArrayList<Bus>[] buses;
    static int[] dis;
    public static void main(String[] args) throws IOException {
        // 최소 스패닝 트리, 다익스트라 (풀이 참고함)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        buses = new ArrayList[N + 1];

        for (int i = 0; i < N + 1; i++) {
            buses[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int tmps = Integer.parseInt(st.nextToken());
            int tmpe = Integer.parseInt(st.nextToken());
            int tmpm = Integer.parseInt(st.nextToken());

            //  방향 존재함에 주의
            buses[tmps].add(new Bus(tmpe, tmpm));
        }
        dis = new int[N + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        Prim(start);

        System.out.println(dis[end]);

    }

    private static void Prim(int start) {
        Queue<Bus> q = new PriorityQueue<>(); // 우선순위 큐
        q.add(new Bus(start, 0));
        dis[start] = 0;

        while (!q.isEmpty()){
            Bus bus = q.poll();
            int cur = bus.cur;
            int curCost = bus.cost;

            // 현재 정점까지 오는 비용이 다른 루트에서 방문하는 비용보다 비싸면 넘어감
            // 최적화
            if (curCost  > dis[cur]) continue;

            for (Bus nextBus : buses[cur]) {
                // 다음 정점 최소 비용 > 현재 정점에서 다음 정점으로 가는 비용
                // 이면 다음 정점 최소 비용 업데이트, 큐에 추가
                if(dis[nextBus.cur] > nextBus.cost + curCost){
                    dis[nextBus.cur] = nextBus.cost + curCost;
                    q.add(new Bus(nextBus.cur, nextBus.cost + curCost));
                }
            }
        }

    }

    private static class Bus implements Comparable<Bus> {
        int cur;
        int cost;

        public Bus(int cur, int cost) {
            this.cur = cur;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bus o) {
            return this.cost - o.cost;
        }
    }
}