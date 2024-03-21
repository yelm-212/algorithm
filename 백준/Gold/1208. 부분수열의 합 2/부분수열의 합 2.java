import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static long[] arr;
    static List<Long> left = new ArrayList<>();
    static List<Long> right = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        // 풀이 참조함
        // 완전 탐색시 시간 초과가 발생할 것이다.
        // 투 포인터를 사용하여 풀자.
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N ; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        // 주어진 배열 전체 가지고 부분수열 합 -> 시간 초과
        // 배열 2개로 나눠서 투포인터
        getSubsequence(0, N / 2, 0, left);
        getSubsequence(N / 2, N, 0, right);

        Collections.sort(left);
        Collections.sort(right);

        long cnt = getCnt();

        // S값이 0인 경우 중복 제거
        if (S == 0)
            cnt--;

        System.out.println(cnt);
    }

    private static void getSubsequence(int idx, int end, long sum, List<Long> list) {
        // 모든 부분수열의 합을 구하는 메서드
        if (idx == end) {
            list.add(sum);
            return;
        }

        getSubsequence(idx + 1, end, sum + arr[idx], list);
        getSubsequence(idx + 1, end, sum, list);
    }

    private static long getCnt() {
        // 정렬된 두개의 리스트를 사용해 투 포인터 알고리즘을 진행하는 메서드
        int pl = 0;
        int pr = right.size() - 1;
        long cnt = 0;

        while (pl < left.size() && pr >= 0) {

            long sum = left.get(pl) + right.get(pr);

            if (sum == S) {
                long a = left.get(pl);
                long cnt1 = 0;
                while (pl < left.size() && left.get(pl) == a) {
                    pl++;
                    cnt1++;
                }

                long b = right.get(pr);
                long cnt2 = 0;
                while (pr >= 0 && right.get(pr) == b) {
                    pr--;
                    cnt2++;
                }

                cnt += cnt1 * cnt2;
            }

            else if (sum < S)
                pl++;
            else
                pr--;
        }

        return cnt;
    }
}
