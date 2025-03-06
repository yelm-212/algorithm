import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    /** 정답 값 */
    private static String min, max;
    /** 부등호 저장 배열 */
    private static char[] arr;
    /** 0 ~9에 해당하는 각 숫자 방문 여부 저장 배열 */
    private static final boolean[] visited = new boolean[10];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new char[N];

        st = new StringTokenizer(br.readLine());
        // 부등호 입력받기
        for (int i = 0; i < N; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        
        DFS(0, new StringBuilder());

        System.out.println(max +"\n" + min);
    }

    /**
     *
     * @param idx DFS 깊이
     * @param s 생성 문자열
     */
    private static void DFS(int idx, StringBuilder s) {
        // 종료 조건: idx가 N+1이면 완성된 숫자 생성
        if (idx == N + 1) {
            // 첫 번째 순회로 완성된 숫자가 최소값, 마지막이 최대값
            if (min == null) {
                min = s.toString();
            } else {
                max = s.toString();
            }
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (visited[i]) continue;
            // 첫 단계일 경우는 무조건 진행, 아닐 경우 마지막 추가 숫자와 비교
            if (idx == 0 || possible(arr[idx - 1], s.charAt(s.length() - 1), (char)(i + '0'))) {
                visited[i] = true;
                s.append(i);
                DFS(idx + 1, s);
                // 재귀 호출 후 마지막에 추가한 문자 제거
                s.deleteCharAt(s.length() - 1);
                visited[i] = false;
            }
        }
    }

    /**
    * @return 부등호 값에 따라 가능한지 여부를 반환
    * */
    private static boolean possible(char c, char cur, char next) {
        if (c == '<'){
            return cur < next;
        }
        if (c == '>'){
            return cur > next;
        }

        return true;
    }
}
