import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    // DP[i][j][k]
    // i : 현재 발판 인덱스
    // j : 왼발 현재 발판
    // k : 오른발 현재 발판
    static int[][][] DP;
    static ArrayList<Integer> list;	// 밟는 발판 순서 저장
    static int size;

    // 각 발판 이동할 때 드는 힘 저장 배열
    static int[][] width = {
            {1, 2, 2, 2, 2},
            {0, 1, 3, 4, 3},
            {0, 3, 1, 3, 4},
            {0, 4, 3, 1, 3},
            {0, 3, 4, 3, 1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        list = new ArrayList<>();

        while (true) {
            int n = Integer.parseInt(st.nextToken());
            if (n == 0)
                break;
            list.add(n);
        }
        size = list.size();
        DP = new int[size][5][5]; // 재귀를 이용한 탐색 진행
        System.out.println(search(0, 0, 0));	// 최소 힘 출력

    }

    private static int search(int idx, int l, int r) {
        if(idx == size)	// 모두 밟음
            return 0;

        if(DP[idx][l][r] != 0)	//이미 밟아본 발판일 경우
            return DP[idx][l][r];

        int nxt = list.get(idx);
        // search(idx+1, nxt, r) + width[l][nxt]) 왼발로 다음 발판 밟는 경우
        // search(idx+1, l, nxt) + width[r][nxt]) 오른발로 다음 발판 밟는 경우
        DP[idx][l][r] = Math.min(search(idx+1, nxt, r) + width[l][nxt],  search(idx+1, l, nxt) + width[r][nxt]);

        return DP[idx][l][r];
    }
}
