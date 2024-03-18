import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, res;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        // 사이클 판정 -> union & find
        // Union & Find 문제 풀이 참고함
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        res = 0;

        parent = new int[N];

        for (int i = 0; i < N ; i++) {
            // 초기값 : 각 노드의 부모 노드 값은 자기 자신으로 초기화
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (find(x) != find(y)) { // 부모 노드가 같지 않다면
                union(x, y); // union 연산 수행
            }else{ // 같으면 Cycle 발생 -> 종료
                res = i + 1; // iteration 횟수
                break;
            }
        }

        System.out.println(res);

    }

    private static void union(int x, int y) {
        if (x < y) {
            parent[find(y)] = parent[find(x)];
        } else {
            parent[find(x)] = parent[find(y)];
        }
    }

    private static int find(int x) {
        if (parent[x] == x){ // 자기 자신인 경우
            return x;
        }else { // 재귀 호출을 통해 부모 노드 찾음
            return parent[x] = find(parent[x]);
        }
    }
}
