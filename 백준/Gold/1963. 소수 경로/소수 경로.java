import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[] prime = new boolean[100001];
    static int N;
    static ArrayList<Integer> primeNumbers = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        checkPrimeNumber();

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int answer = Integer.parseInt(st.nextToken());

            sb.append(bfs(src,answer)+"\n");
        }
        System.out.println(sb);
    }

    static String bfs(int src, int answer) {
        Queue<Integer> q = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();

        map.put(src,0);
        q.add(src);

        while(!q.isEmpty()) {
            int pos = q.poll();
            int move = map.get(pos);

            if(pos == answer) {
                return move+"";
            }

            int[] pNum = {pos/1000, (pos/100)%10, (pos/10)%10, pos%10};
            for(int i = 0; i < 4; i++) {
                for(int j=0; j<10; j++) {
                    if(i==0 && j==0) continue; // 1000이상

                    int tmp = pNum[i];
                    pNum[i] = j;
                    int next = changePassword(pNum);
                    pNum[i] = tmp;

                    if(prime[next]) continue;

                    if(!map.containsKey(next)) {
                        q.add(next);
                        map.put(next, move+1);
                    }
                }
            }
        }

        return "Impossible";
    }

    static int changePassword(int[] pNum) {
        int num =0;
        for(int i=0; i<4; i++) {
            num += pNum[i]*(Math.pow(10, 3-i));
        }
        return num;

    }

    // 소수를 판정하는 메서드
    private static void checkPrimeNumber() {
        prime[0] = prime[1] = true;

        for(int i = 2; i * i <= 10000; i++) {
            if(!prime[i])
                for(int j= i*i; j <= 10000; j+=i) prime[j] = true;
        }

    }
}
