import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int N, cnt;
    static boolean[] prime;
    static ArrayList<Integer> primeNumbers = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        prime = new boolean[N+1];

        checkPrimeNumber();

        count();

        System.out.println(cnt);
    }

    private static void count() {
        int left = 0, right = 0, sum = 0;
        while (true){
            if ( sum >= N ) sum -= primeNumbers.get(left++);
            else if (right == primeNumbers.size())  break;
            else sum += primeNumbers.get(right++);

            if (N == sum) cnt++; // 연속합으로 만들어질 수 있는 경우
        }
    }

    // 소수를 판정하는 메서드
    private static void checkPrimeNumber() {
        prime[0] = prime[1] = true;

        for(int i = 2; i * i <= N; i++) {
            if(!prime[i])
                for(int j=i*i; j<=N; j+=i) prime[j]=true;
        }

        for(int i=1; i<=N;i++){
            if(!prime[i]) primeNumbers.add(i);
        }
    }
}
