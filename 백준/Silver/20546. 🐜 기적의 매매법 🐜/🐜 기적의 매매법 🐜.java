import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] price = new int[14];

    static int J, S;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Portfolio j = new Portfolio(N, 0);
        Portfolio s = new Portfolio(N, 0);

        int decreasingflag = 0, increasingflag = 0;
        for (int i = 0 ; i < 14; i++){
            price[i] = Integer.parseInt(st.nextToken());
            if (price[i] <= j.money){ // 오늘 주가가 현재 자산보다 작다면 매수
                j.stock +=  j.money / price[i]; // 살수 있는만큼 다살거임
                j.money -= j.stock * price[i]; // 남은 현금
            }
        }

        for (int i = 1 ; i < 14; i++){
            if (price[i - 1] < price[i]){ // 증가중
                increasingflag++;
                decreasingflag = 0;
            }else if (price[i - 1] > price[i]){
                increasingflag = 0;
                decreasingflag++;
            }

            if (decreasingflag >= 3 && (price[i] <= s.money)){ // 3일(이상) 연속 가격 하락
                // 살수 있는만큼 다삼
                s.stock += s.money / price[i];
                s.money -= s.stock * price[i];
            }
            if (increasingflag >= 3 && s.stock != 0){ // 3일(이상) 연속 가격 상승
                // 주식 전부 팜
                s.money += s.stock * price[i];
                s.stock = 0;
            }
        }

        System.out.print((j.sum() > s.sum()) ? "BNP" :
                ((j.sum() == s.sum()) ? "SAMESAME" : "TIMING"));

    }

    static class Portfolio{

        int money;
        int stock;
        public Portfolio(int money, int stock) {
            this.money = money;
            this.stock = stock;
        }

        public long sum(){
            return money + (stock * price[13]);
        }
    }
}
