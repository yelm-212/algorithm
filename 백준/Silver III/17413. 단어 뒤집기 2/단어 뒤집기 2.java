import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), "\n");

        char[] tmp = st.nextToken().toCharArray();

        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        boolean istag = false;

        for (char ch : tmp){
            if(ch == '<'){
                if (sb1.length() != 0) {
                    sb.append(sb1.reverse());
                    sb1 = new StringBuilder();
                }

                istag = true;
                sb.append(ch);
                continue;
            }else if(ch == '>'){
                istag = false;
                sb.append(ch);
                continue;
            }

            if (istag && ch != '<' && ch != '>') {
                sb.append(ch);
            }
            else if (ch != ' '){
                sb1.append(ch);
            }

            if (ch == ' '&& !istag){
                sb1 = sb1.reverse();
                sb.append(sb1);
                sb.append(ch);
                sb1 = new StringBuilder();
            }
        }

        if (sb1.length() != 0) sb.append(sb1.reverse());

        System.out.print(sb);
    }
}
