import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int[] DP;
    static int[] arr;
    static int N, res;
    static Stack<Integer> s = new Stack<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // LIS 알고리즘 - O(n^2)
        DP = new int[N];
        res = 1;

        Arrays.fill(DP, 1);

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]){
                    DP[i] = Math.max(DP[i], DP[j] + 1);
                    res = Math.max(res, DP[i]);
                }
            }
        }
        sb.append(res + "\n"); // LIS 길이

        // LIS가 되는 값 역추적
        for (int i = N - 1; i >= 0; i--) {
            if(DP[i] == res){ // DP 배열 값이 LIS를 구성하는 값인지 확인
                s.push(arr[i]); // stack에 배열의 값 추가
                res--;
            }
        }


        // 스택에서 값 꺼내서 출력해줌
        while (!s.isEmpty()){
            sb.append(s.pop() + " ");
        }

        System.out.println(sb);
    }
}
