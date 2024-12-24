import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static int N; // 수식의 길이
    private static int[] numbers; // 숫자 배열
    private static char[] ops; // 연산자 배열
    private static int max = Integer.MIN_VALUE; // 최댓값 저장

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 수식 길이
        String str = br.readLine(); // 수식 문자열

        numbers = new int[(N / 2) + 1]; // 숫자 배열
        ops = new char[N / 2]; // 연산자 배열

        int numIdx = 0, opIdx = 0;
        for (int i = 0; i < N; i++) {
            char ch = str.charAt(i);
            if (i % 2 == 0) { // 숫자
                numbers[numIdx++] = ch - '0';
            } else { // 연산자
                ops[opIdx++] = ch;
            }
        }

        // 재귀 호출 시작
        find(0, numbers[0]);

        System.out.println(max);
    }

    // 재귀 탐색
    private static void find(int idx, int total) {
        // 연산 종료 조건
        if (idx == ops.length) {
            max = Math.max(max, total);
            return;
        }

        // 현재 연산자와 숫자를 계산
        int cal1 = calc(total, numbers[idx + 1], ops[idx]);
        find(idx + 1, cal1); // 다음 연산자로 진행

        // 괄호를 사용하는 경우
        if (idx + 1 < ops.length) {
            int cal2 = calc(numbers[idx + 1], numbers[idx + 2], ops[idx + 1]); // 괄호 내부 계산
            int cal3 = calc(total, cal2, ops[idx]); // 괄호 결과와 현재 값 계산
            find(idx + 2, cal3); // 두 연산자 건너뛰고 진행
        }
    }

    // 연산 수행
    private static int calc(int a, int b, char op) {
        if (op == '+') return a + b;
        if (op == '-') return a - b;
        return a * b; // 곱셈
    }
}
