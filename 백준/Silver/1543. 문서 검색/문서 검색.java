import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static String doc, word;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), "\n");
        doc = st.nextToken();
        st = new StringTokenizer(br.readLine(), "\n");
        word = st.nextToken();

        int res = 0;
        while ( true ){
            if (!doc.contains(word)) break; // 종료 조건

            doc = doc.replaceFirst(word, "1");
            res++;
        }

        System.out.println(res);
    }
}
