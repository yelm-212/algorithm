import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int S;
    // clipboard, cnt 방문 여부 저장하는 배열
    static boolean[][] visited = new boolean[1001][1001];
    static class Emoji{
        int clipboard;
        int cnt;
        int time;
        public Emoji(int clipboard, int cnt, int time) {
            this.clipboard = clipboard;
            this.cnt = cnt;
            this.time = time;
        }

    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());

        Queue<Emoji> q = new LinkedList<>();
        q.add(new Emoji(0,1, 0));
        visited[0][1] = true;

        // 클립보드 저장 & 총개수 분리 (한번에 X)
        while (!q.isEmpty()){
            Emoji polled = q.poll();

            if (polled.cnt == S){
                System.out.println(polled.time);
                break;
            }

            // 클립보드에 저장
            q.add(new Emoji(polled.cnt, polled.cnt, polled.time + 1));

            // 붙여넣기 하는 경우
            if (polled.clipboard != 0 && polled.cnt + polled.clipboard <= S &&
                    !visited[polled.clipboard][polled.cnt + polled.clipboard]){
                q.add(new Emoji(polled.clipboard, polled.cnt + polled.clipboard , polled.time + 1));
                visited[polled.clipboard][polled.cnt + polled.clipboard] = true;
            }


            if (polled.cnt >= 1 && !visited[polled.clipboard][polled.cnt - 1] ){
                q.add(new Emoji(polled.clipboard, polled.cnt - 1 , polled.time + 1));
                visited[polled.clipboard][polled.cnt - 1] = true;
            }

        }
    }
}
