import java.io.*;  
  
public class Main {  
    public static void main(String[] args) throws IOException{  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));  
  
        int t = Integer.parseInt(br.readLine());  
        long[] dp = new long[5001];  
        dp[0] = dp[2] = 1;  
  
        // 점화식  
        for(int i = 2; i< 2501; i++) {  
            for(int j = 0; j<i; j++) {  
                dp[i*2] += (dp[j*2]*dp[(i-1-j)*2]);  
                dp[i*2] %= 1_000_000_007L;  
            }  
        }  
  
        for(int i=0; i < t; i++) {  
            int a = Integer.parseInt(br.readLine());  
            if (a % 2 != 0) { // 홀수인 경우
                bw.write("0\n");
            } else {
                bw.write(dp[a] + "\n");
            }
        }  
  
        bw.flush();  
        bw.close();  
        br.close();  
    }  
}