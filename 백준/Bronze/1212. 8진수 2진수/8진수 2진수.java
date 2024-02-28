import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Long N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String st = br.readLine();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < st.length(); i++){
            int target = st.charAt(i) - '0'; // integer 값으로 변환
            String s = Integer.toBinaryString(target); // binary로 변환

            // 변환된 s 길이에 따라 처리
            if(s.length() == 3){
                sb.append(s);
            }
            else if(s.length() == 2 && i!=0){
                sb.append("0" + s);
            }
            else if(s.length()==1 && i!=0){
                sb.append("00" + s);
            }
            else
                sb.append(s);
        }

        System.out.println(sb);
    }
}
