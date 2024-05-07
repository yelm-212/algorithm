import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int[][] DP;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();

        DP = new int[str1.length() + 1][str2.length() + 1];
        int ans = 0;

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                // 공통 부분 문자열인 경우
                if(str1.charAt(i - 1) == str2.charAt(j - 1)){
                    DP[i][j] = DP[i - 1][j - 1] + 1;
                    // 최대 길이 판정
                    ans = Math.max(ans, DP[i][j]);
                }
            }
        }

        System.out.println(ans);
    }
}
