import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int fuel;
    static int[][] map;
    static int taxiRow, taxiCol;
    static int[][] passengers; // [승객번호][0: 출발행, 1: 출발열, 2: 도착행, 3: 도착열]
    static boolean[] completed;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        
        map = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        st = new StringTokenizer(br.readLine());
        taxiRow = Integer.parseInt(st.nextToken());
        taxiCol = Integer.parseInt(st.nextToken());
        
        passengers = new int[M+1][4];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            passengers[i][0] = Integer.parseInt(st.nextToken()); // 출발 행
            passengers[i][1] = Integer.parseInt(st.nextToken()); // 출발 열
            passengers[i][2] = Integer.parseInt(st.nextToken()); // 도착 행
            passengers[i][3] = Integer.parseInt(st.nextToken()); // 도착 열
        }
        
        completed = new boolean[M+1];
        
        // 모든 승객을 태워 목적지로 이동시키기
        for (int i = 0; i < M; i++) {
            int passengerIndex = findNearestPassenger();
            if (passengerIndex == -1 || fuel <= 0) {
                System.out.println(-1);
                return;
            }
            
            // 승객을 태운 후 목적지로 이동
            int[] passenger = passengers[passengerIndex];
            int distance = bfs(taxiRow, taxiCol, passenger[2], passenger[3]);
            if (distance == -1 || fuel < distance) {
                System.out.println(-1);
                return;
            }
            
            // 연료 소비 및 충전
            fuel -= distance;
            fuel += (distance * 2);
            
            // 택시 위치 업데이트
            taxiRow = passenger[2];
            taxiCol = passenger[3];
            
            // 승객 완료 처리
            completed[passengerIndex] = true;
        }
        
        System.out.println(fuel);
    }
    
    // 가장 가까운 승객 찾기
    static int findNearestPassenger() {
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;
        int minRow = Integer.MAX_VALUE;
        int minCol = Integer.MAX_VALUE;
        
        // 아직 태우지 않은 승객들에 대해 거리 계산
        for (int i = 1; i <= M; i++) {
            if (completed[i]) continue;
            
            int distance = bfs(taxiRow, taxiCol, passengers[i][0], passengers[i][1]);
            if (distance == -1) continue; // 갈 수 없는 경우
            
            if (distance > fuel) continue; // 연료 부족
            
            if (distance < minDistance || 
                (distance == minDistance && (passengers[i][0] < minRow || 
                (passengers[i][0] == minRow && passengers[i][1] < minCol)))) {
                minDistance = distance;
                minIndex = i;
                minRow = passengers[i][0];
                minCol = passengers[i][1];
            }
        }
        
        if (minIndex != -1) {
            // 선택된 승객에게 가는데 연료 소비
            fuel -= minDistance;
            // 택시 위치 업데이트
            taxiRow = passengers[minIndex][0];
            taxiCol = passengers[minIndex][1];
        }
        
        return minIndex;
    }
    
    // BFS로 최단 거리 계산
    static int bfs(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow && startCol == endCol) return 0;
        
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N+1][N+1];
        
        queue.offer(new int[]{startRow, startCol, 0});
        visited[startRow][startCol] = true;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int dist = curr[2];
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if (nr < 1 || nr > N || nc < 1 || nc > N) continue;
                if (map[nr][nc] == 1 || visited[nr][nc]) continue;
                
                if (nr == endRow && nc == endCol) {
                    return dist + 1;
                }
                
                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc, dist + 1});
            }
        }
        
        return -1; // 도달할 수 없는 경우
    }
}