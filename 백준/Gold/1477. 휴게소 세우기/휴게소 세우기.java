import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, L;
    private static ArrayList<Integer> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        list.add(0); // 시작점
        list.add(L); // 끝점

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(list);

        // binary search
        int left = 1, right = L;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (available(mid)) left = mid + 1;
            else right = mid - 1;
        }

        System.out.println(left);
    }

    private static boolean available(int mid) {
        int cnt = 0;
        for (int i = 1; i < list.size(); i++) {
            int diff = list.get(i) - list.get(i - 1) - 1; // 휴게소 간 거리
            cnt += diff / mid; // 간격을 휴게소 없는 구간 최댓값(후보)으로  나누면 들어갈 수 있는 휴게소 수
        }
        return cnt > M; // 지을 수 있는 휴게소 수가 지으려는 휴게소 갯수보다 많으면 참
    }
}
