import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, B;
    static int[][] nlist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        nlist = new int[N][M];
        int minHeight = 256; // 최소 높이

        // 땅의 높이를 입력받고, 최소 높이를 찾음
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                nlist[i][j] = Integer.parseInt(st.nextToken());
                minHeight = Math.min(minHeight, nlist[i][j]);
            }
        }

        int minTime = Integer.MAX_VALUE; // 최소 시간
        int maxHeight = 0; // 최대 높이

        // 높이를 최소높이부터 최대높이까지 탐색
        for (int h = minHeight; h <= 256; h++) {
            int time = 0; // 현재 높이로 만드는 데 걸리는 시간
            int blocks = B; // 현재 높이로 만들기 위해 사용 가능한 블록 수

            // 모든 좌표를 탐색하여 블록 추가/제거 작업 수행
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int diff = nlist[i][j] - h; // 현재 높이와의 차이

                    // 현재 높이보다 블록이 많으면 블록 제거
                    if (diff > 0) {
                        time += diff * 2; // 블록 제거에 소요되는 시간 추가
                        blocks += diff; // 제거한 블록을 인벤토리에 추가
                    }
                    // 현재 높이보다 블록이 부족하면 블록 추가
                    else {
                        time -= diff; // 블록 추가에 소요되는 시간 추가
                        blocks += diff; // 추가한 블록을 인벤토리에서 제거
                    }
                }
            }

            // 인벤토리에 있는 블록 수가 음수가 되면 더 이상 높이를 조정할 수 없음
            if (blocks < 0) break;

            // 현재 시간이 최소 시간보다 작으면 갱신
            if (time <= minTime) {
                minTime = time;
                maxHeight = h;
            }
        }

        System.out.println(minTime + " " + maxHeight);
    }

}
