import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static int[][] DP;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();

        LCS2(str1, str2);
        getLCSString(str1, str1.length(), str2.length());

        System.out.println(sb);
    }

    private static void getLCSString(String str, int i, int j) {
        // 값 역추적
        Stack<Character> st = new Stack<>();
        while((i > 0) && (j > 0)) {

            if(i == 0 || j == 0) break;

            if(DP[i][j] == DP[i-1][j]) {
                i--;
            }else if(DP[i][j] == DP[i][j-1]) {
                j--;
            }else {
                st.push(str.charAt(i-1));
                i--;
                j--;
            }
        }

        while(!st.isEmpty()) {
            sb.append(st.pop());
        }
    }

    private static void LCS2(String str1, String str2) {
        int N1 = str1.length(), N2 = str2.length();

        DP = new int[N1 + 1][N2 + 1];
        int max =-1;

        // LCS 알고리즘 (DP)
        for(int i = 1; i <= N1; i++) {
            for(int j = 1; j <= N2; j++) {
                if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    DP[i][j] = DP[i-1][j-1] +1;
                }else {
                    DP[i][j] = Math.max(DP[i-1][j], DP[i][j-1]);
                }
            }
        }

        sb.append(DP[N1][N2] + "\n");
    }
}
