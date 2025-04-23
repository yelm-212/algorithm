import java.util.*;
import java.io.*;

public class Main {
    private static int N, S;
    private static ArrayList<Painting> paintings = new ArrayList<>();
    private static long[] dp;
    // 높이 순으로 정렬된 그림들 중, i+1개 (인덱스 0부터 i까지)만을 고려했을 때 얻을 수 있는 최대 가격의 합

    static class Painting implements Comparable<Painting> {
        int H, C;
        public Painting(int H, int C) {
            this.H = H;
            this.C = C;
        }

        @Override
        public int compareTo(Painting o) {
            return this.H - o.H;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        dp = new long[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            paintings.add(new Painting(H, C));
        }

        Collections.sort(paintings);

        // base case 처리
        dp[0] = paintings.get(0).C;

        // DP 점화식
        for (int i = 1; i < N; i++) {
            int j = binarySearch(i);

            long val = (j == -1) ? 0 : dp[j];

            dp[i] = Math.max(dp[i - 1], val + paintings.get(i).C);
        }
        System.out.println(dp[N - 1]);
    }

    // paintings[i].H - S 이하의 높이를 가진 그림 중 가장 큰 인덱스를 반환하고
    // 없으면 -1 반환
    private static int binarySearch(int i) {
        int start = 0, end = i - 1;
        int target = paintings.get(i).H - S;
        int res = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int h = paintings.get(mid).H;
            if (h <= target) {
                res = mid; // 가능한 후보 값 발견
                start = mid + 1; // 더 높은 인덱스 가능한지 확인
            }else {
                end = mid - 1;
            }
        }

        return res;
    }
}
