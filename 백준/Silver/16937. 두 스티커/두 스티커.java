import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int H, W;
    static int ans = 0;
    
    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        
        int N = Integer.parseInt(br.readLine().trim());
        Sticker[] stickers = new Sticker[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            stickers[i] = new Sticker(h, w);
        }
        
        // 모든 스티커 쌍에 대해 배치 가능 여부를 확인
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                tryPair(stickers[i], stickers[j]);
            }
        }
        
        System.out.println(ans);
    }
    
    /**
     * 두 스티커 s1, s2를 전달받아 s1을 먼저 붙인 후 남은 공간에 s2가 들어갈 수 있는지 확인합니다.
     * s1은 원래 상태와 90도 회전 상태 두 가지 경우를 각각 검사하며, 
     * s2는 canPlaceSticker() 헬퍼 메서드를 통해 두 가지 남은 공간(아래, 오른쪽) 모두 검사합니다.
     */
    private static void tryPair(Sticker s1, Sticker s2) {
        // s1을 원래 상태로 놓는 경우
        if (s1.H <= H && s1.W <= W) {  // s1이 모눈종이에 붙을 수 있으면
            // 남은 공간: 아래쪽 (H - s1.H, W) 또는 오른쪽 (H, W - s1.W)
            if (canPlaceSticker(s2, H - s1.H, W) || canPlaceSticker(s2, H, W - s1.W)) {
                ans = Math.max(ans, s1.size + s2.size);
            }
        }
        // s1을 90도 회전한 상태로 놓는 경우 (즉, s1의 치수가 뒤바뀜)
        if (s1.W <= H && s1.H <= W) {
            if (canPlaceSticker(s2, H - s1.W, W) || canPlaceSticker(s2, H, W - s1.H)) {
                ans = Math.max(ans, s1.size + s2.size);
            }
        }
    }
    
    /**
     * 주어진 보드(boardH x boardW)에 스티커 s를 원래 상태 또는 회전한 상태 둘 중 하나로 붙일 수 있는지 확인합니다.
     */
    private static boolean canPlaceSticker(Sticker s, int boardH, int boardW) {
        return (s.H <= boardH && s.W <= boardW) || (s.W <= boardH && s.H <= boardW);
    }
    
    static class Sticker {
        int H, W, size;
        Sticker(int H, int W) {
            this.H = H;
            this.W = W;
            this.size = H * W;
        }
    }
}
