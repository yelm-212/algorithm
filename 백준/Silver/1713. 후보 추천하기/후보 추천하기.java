import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

public class Main {
    private static int N, K;
    private static LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++){
            int tmp = Integer.parseInt(st.nextToken());

            if (map.containsKey(tmp)){
                map.put(tmp, map.get(tmp)+1);
            } else {
                if (map.size() == N){
                    map.entrySet().stream()
                            .reduce((e1, e2) -> e1.getValue() <= e2.getValue() ? e1 : e2)
                            .ifPresent(e -> map.remove(e.getKey()));
                }
                map.put(tmp, map.getOrDefault(tmp, 0) + 1);
            }
        }

        map.keySet().stream().sorted().forEach(i -> System.out.print(i + " "));
    }

}
