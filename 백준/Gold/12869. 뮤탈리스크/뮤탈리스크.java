import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][][] DP = new int[61][61][61];
    static int[] scv = {0, 0, 0};
    static int min = Integer.MAX_VALUE;
    static int[][] deltas = {{-9,-3,-1},{-9,-1,-3},{-3,-9,-1},{-3,-1,-9},{-1,-9,-3},{-1,-3,-9}};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }

        solution(scv, 0);

        System.out.println(min);
    }

    private static void solution(int[] arr, int cnt) {
        int s1 = arr[0];
        int s2 = arr[1];
        int s3 = arr[2];

        // 공격 횟수 최솟값 보다 현재 공격 횟수가 같거나 클 경우 중단
        if(min <= cnt) return;

        // 이미 방문했는데 기존 공격 횟수가 더 작을 경우 중단
        if(DP[s1][s2][s3] != 0 && DP[s1][s2][s3] <= cnt) return;

        DP[s1][s2][s3] = cnt;

        // 모든 scv가 죽을 경우 최솟값 갱신 및 중단
        if(s1 == 0 && s2 == 0 && s3 == 0) {
            min = Math.min(min, cnt);
            return;
        }

        // 6가지 공격 패턴으로 현재 scv를 공격한 후 넘김
        for(int i=0;i<6;i++) {
            solution(new int[] {
                    Math.max(s1 + deltas[i][0], 0),
                    Math.max(s2 + deltas[i][1], 0),
                    Math.max(s3 + deltas[i][2], 0)}, cnt+1);
        }
    }
}
