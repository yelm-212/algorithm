import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, X, res;
    static int[] list;

    public static void main(String[] args) throws Exception{
        // 1 <= N <= 1000000 이므로 완전 탐색시 시간 초과가 발생할 것이다.
        // 투포인터를 사용하자.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        list = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < N ; i++){
            list[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());

        Arrays.sort(list); // 투포인터를 사용하려면 배열이 정렬되어 있어야 한다.
        res = 0;
        int left = 0;
        int right = N - 1;

        while (left != right){ // 두 포인터가 같은 값을 가리킬 경우 종료
            if (list[left] + list[right] == X){ // 합이 X와 일치하는 경우
                left++;
                res++;
            }else if (list[left] + list[right] > X){ // 합이 X보다 큰 경우
                right--;
            }else{ // 합이 X보다 작은 경우
                left++;
            }

        }

        System.out.println(res);
    }
}
