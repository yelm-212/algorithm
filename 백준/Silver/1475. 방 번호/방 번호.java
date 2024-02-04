import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static String N;
    static int[] arr = new int[10];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = st.nextToken();

        for(char c : N.toCharArray()){
            int num = Character.getNumericValue(c);
            if(num == 9) num = 6;
            arr[num]++;
        }

        arr[6] = arr[6] / 2 + arr[6] % 2;

        Arrays.sort(arr);
        System.out.print(arr[9]); // 최대값
    }

}
