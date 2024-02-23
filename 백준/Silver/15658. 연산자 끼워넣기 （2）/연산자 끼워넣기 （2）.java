import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] list;
    static int[] operator;
    static int[] end = new int[]{0, 0, 0, 0};
    static int max, min;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        list = new int[N];
        operator = new int[4];
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N ; i++){
            list[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4 ; i++){
            operator[i] = Integer.parseInt(st.nextToken());
        }

        calc(1, list[0]);
        System.out.println(max);
        System.out.println(min);
    }

    private static void calc(int i, int result) {
        if (i == N){ // 재귀 호출의 종료 조건
            min = Math.min(min, result);
            max = Math.max(max, result);
            return;
        }

        if(operator[0] > 0){ // 덧셈 개수 남은 경우
            operator[0] -= 1;
            calc(i+1, result + list[i]);
            operator[0] += 1;
        }
        if(operator[1] > 0){ // 뺄셈 개수 남은 경우
            operator[1] -= 1;
            calc(i+1, result - list[i]);
            operator[1] += 1;
        }
        if(operator[2] > 0){ // 곱셈 개수 남은 경우
            operator[2] -= 1;
            calc(i+1, result * list[i]);
            operator[2] += 1;
        }
        if(operator[3] > 0){ // 나눗셈 개수 남은 경우
            operator[3] -= 1;
            calc(i+1, result / list[i]);
            operator[3] += 1;
        }

    }
}
