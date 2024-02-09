import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int[] stop = new int[]{0, 0, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            arr = new int[3];

            for (int i = 0; i < 3; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);

            if (Arrays.equals(arr, stop)) break;
            System.out.println( Math.pow(arr[2], 2) == Math.pow(arr[0], 2) + Math.pow(arr[1], 2) ? "right" : "wrong");
        }


    }
}
