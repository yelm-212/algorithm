import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static boolean[] visited; // 메모리 초과 -> 방문 여부를 확인해 BFS 횟수 줄이기
    static int[] check;
    static int result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[100001];
        check = new int[100001]; // 이전 위치를 저장하기 위한 배열

        BFS(0, 0);
        System.out.println(result);

        // 값 역추적
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int trace = K;
        while(trace != N){
            stack.push(trace);
            trace = check[trace];
        }
        stack.push(N);

        while (!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }

        System.out.println(sb);
    }

    private static void BFS(int i, int time) {
        Queue<Tmp> q = new LinkedList<>();
        q.offer(new Tmp(N, 0));
        visited[N] = true;

        while (!q.isEmpty()){
            Tmp present = q.poll();

            if (present.x < 0 || present.x > 100000) break; // 규모 안에서만 반복

            if (present.x == K){
                result = present.time; // 종료 조건
                break;
            }

            if(present.x - 1 >= 0 && present.x - 1 <= 100000 && !visited[present.x - 1]){
                q.offer(new Tmp(present.x - 1, present.time + 1)); // 걷기 1
                visited[present.x - 1] = true;
                check[present.x - 1] = present.x;
            }
            if(present.x + 1 >= 0 && present.x + 1 <= 100000 && !visited[present.x + 1]){
                q.offer(new Tmp(present.x + 1, present.time + 1)); // 걷기 2
                visited[present.x + 1] = true;
                check[present.x + 1] = present.x;
            }
            if(present.x * 2 >= 0 && present.x * 2 <= 100000 && !visited[present.x * 2]){
                q.offer(new Tmp(present.x * 2, present.time + 1)); // 순간 이동
                visited[present.x * 2] = true;
                check[present.x * 2] = present.x;
            }


        }

    }

    static class Tmp {
        int x,time;

        public Tmp(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }
}
