import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static String[] foo;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < N; i++){
            foo = br.readLine().split(" ");
            for (int j = 0; j < foo.length; j++){
                StringBuffer sb = new StringBuffer(foo[j]);
                foo[j] = sb.reverse().toString();
            }
            printArray(foo);
        }
    }

    public static void printArray(String[] array){

        for(int i = 0; i < array.length ; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}

