import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean[] arr = new boolean[31];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i <= 28; i++) {
            int tmp = Integer.parseInt(br.readLine());
            arr[tmp] = true;
        }

        for (int i = 1; i <= 30; i++) {
            if (!arr[i]) System.out.println(i);
        }
    }
}
