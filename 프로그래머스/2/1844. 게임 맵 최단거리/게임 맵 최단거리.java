import java.util.*;

class Solution {
    // 최단 경로여서 BFS 사용
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        int[][] visited = new int[maps.length][maps[0].length];

        bfs(maps, visited);
        answer = visited[maps.length - 1][maps[0].length - 1];

        if (answer == 0) { 
            answer = -1;
        }
        
        return answer;
    }
    
    private void bfs(int[][] maps, int[][] visited) {

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0}); // Queue에 시작 정점 추가
        visited[0][0] = 1;

        while (!q.isEmpty()) { // 더 나아갈 정점이 없을 때까지 반복

            int[] current = q.poll(); // 정점 하나 꺼내기
            int X = current[0];
            int Y = current[1];

            for (int i = 0; i < 4; i++) {

                int nX = X + dx[i];
                int nY = Y + dy[i];

                // 좌표가 maps에서 벗어날 경우 다음 반복으로 넘어간다
                if (nX < 0 || nX > maps.length - 1 || nY < 0 || nY > maps[0].length - 1) {
                    continue;
                }

                // 아직 방문하지 않았고, 벽이 아닐 경우
                if (visited[nX][nY] == 0 && maps[nX][nY] == 1) {
                    visited[nX][nY] = visited[X][Y] + 1;
                    q.add(new int[]{nX, nY});
                }
            }
        }
    }
}