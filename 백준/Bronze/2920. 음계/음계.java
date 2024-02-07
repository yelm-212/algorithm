import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int[] asc = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
    static int[] desc = new int[]{8, 7, 6, 5, 4, 3, 2, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        arr = new int[8];

        for(int i = 0; i < 8; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (Arrays.equals(arr, asc)){
            System.out.print("ascending");
        }else if (Arrays.equals(arr, desc)){
            System.out.print("descending");
        }else {
            System.out.print("mixed");
        }


    }
}
