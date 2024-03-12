import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static ArrayList<Cow> cows = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        // 초기화
        for (int i = 0; i < 10; i++) {
            cows.add(new Cow());
        }

        // 1이상 10이하 -> 실제 인덱스 값에 맞추려면 -1
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1;
            int position = Integer.parseInt(st.nextToken());

            Cow cow = cows.get(num);
            if (cow.position == -1){
                cow.position = position;
            }else if (cow.position != position){
                cow.position = position;
                cow.cnt++;
            }
        }

        int total = 0;
        for (Cow cow: cows) {
            total += cow.cnt;
        }
        System.out.println(total);
    }

    private static class Cow {
        int position;
        int cnt;

        public Cow() {
            this.position = -1;
            cnt = 0;
        }
    }
}