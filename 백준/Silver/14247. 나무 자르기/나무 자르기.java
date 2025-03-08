import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static long res;
    private static Tree[] trees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        trees = new Tree[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            trees[i] = new Tree();
            trees[i].H = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i].A = Integer.parseInt(st.nextToken());
        }

        // 자라는 길이 순서대로 정렬한다.
        Arrays.sort(trees);

        res = 0;
        // 하루마다[i] 잘라갈 수 있는 나무를 최대한 잘라간다.
        for (int i = 0; i < N; i++) {
            res += trees[i].A * i + trees[i].H;
        }

        System.out.println(res);
        br.close();
    }

    static class Tree implements Comparable<Tree> {
        int H;
        int A;

        @Override
        public int compareTo(Tree o) {
            return this.A - o.A;
        }
    }
}
