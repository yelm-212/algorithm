import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int H, W, N; // 모눈종이 크기, 스티커 수
    private static ArrayList<Sticker> stickers = new ArrayList<>(); // 스티커
    private static int ans = 0; // 정답 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        // Sticker 배열 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if ((h > H && h > W) || (w > W && w > H)) {
                //  붙일 수 없는 경우는 저장하지 않음
                continue;
            }
            stickers.add(new Sticker(h, w)) ;
        }

        checkFirst();

        System.out.println(ans);
    }

    private static void checkFirst() {
        // 첫번째 스티커를 확인한다.

        for (int i = 0; i < stickers.size(); i++) {
            Sticker sticker = stickers.get(i);
            int h = sticker.H, w = sticker.W;

            // 그대로 붙이기
            if (h <= H && w <= W) {
                checkSecond(H - h, W - w, i + 1, sticker.size);
            }

            // 90도 회전
            if (w <= H && h <= W) {
                checkSecond(H - w, W - h, i + 1, sticker.size);
            }
        }
    }

    private static void checkSecond(int newH, int newW, int idx, int size) {
        // 두번째 스티커부터 확인
        for (int i = idx; i < stickers.size(); i++) {
            Sticker sticker = stickers.get(i);
            int h = sticker.H, w = sticker.W;

            // 그대로 붙이기
            if ((h <= newH && w <= W) || (h <= H && w <= newW)) {
                ans = Math.max(ans, size + sticker.size);
            }

            // 90도 회전
            if ((w <= newH && h <= W) || (w <= H && h <= newW)) {
                ans = Math.max(ans, size + sticker.size);
            }
        }
    }

    private static class Sticker {
        int H, W, size;

        public Sticker(int H, int W) {
            this.H = H;
            this.W = W;
            size = H * W; // 넓이 저장.
        }
    }

}
