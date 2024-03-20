import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, S, res, sum;
    static int[] list;
    public static void main(String[] args) throws Exception{
        // 1 <= N <= 100000 이므로 완전 탐색시 시간 초과가 발생할 것이다.
        // 투 포인터를 사용하여 풀자.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        list = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N ; i++){
            list[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int sum = 0;
        res = Integer.MAX_VALUE;

        while (left <= right && right <= N){
            if (sum < S){ // 합이 S보다 작은 경우
                sum += list[right++];
            }else{ // 합이 S보다 작은 경우
                res = Math.min(res, right - left);
                sum -= list[left++];            }

        }

        System.out.println(res == Integer.MAX_VALUE ? 0 : res);
    }
}
