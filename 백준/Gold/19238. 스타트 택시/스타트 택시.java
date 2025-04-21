import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int fuel;
    private static int[][] map;
    private static int taxiRow, taxiCol;
    private static Passenger[] passengers;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};
    
    private static class Passenger {
        private int id;
        private int startRow, startCol;
        private int endRow, endCol;
        private boolean completed;
        
        public Passenger(int id, int startRow, int startCol, int endRow, int endCol) {
            this.id = id;
            this.startRow = startRow;
            this.startCol = startCol;
            this.endRow = endRow;
            this.endCol = endCol;
            this.completed = false;
        }
    }
    
    private static class Point implements Comparable<Point> {
        int row, col, dist;
        
        public Point(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
        
        @Override
        public int compareTo(Point o) {
            if (this.dist != o.dist) {
                return this.dist - o.dist;
            }
            if (this.row != o.row) {
                return this.row - o.row;
            }
            return this.col - o.col;
        }
    }

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
        
        passengers = new Passenger[M+1];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int startRow = Integer.parseInt(st.nextToken());
            int startCol = Integer.parseInt(st.nextToken());
            int endRow = Integer.parseInt(st.nextToken());
            int endCol = Integer.parseInt(st.nextToken());
            passengers[i] = new Passenger(i, startRow, startCol, endRow, endCol);
        }
        
        int completedCount = 0;
        while (completedCount < M) {
            // 가장 가까운 승객 찾기
            int[] result = findNearestPassenger();
            int nearestIndex = result[0];
            int distance = result[1];
            
            if (nearestIndex == -1 || distance > fuel) {
                System.out.println(-1);
                return;
            }
            
            // 연료 소비
            fuel -= distance;
            
            // 승객을 태운 후 목적지로 이동
            Passenger p = passengers[nearestIndex];
            int distToDest = getDistance(p.startRow, p.startCol, p.endRow, p.endCol);
            
            if (distToDest == -1 || distToDest > fuel) {
                System.out.println(-1);
                return;
            }
            
            // 연료 소비 및 충전
            fuel -= distToDest;
            fuel += (distToDest * 2);
            
            // 택시 위치 업데이트
            taxiRow = p.endRow;
            taxiCol = p.endCol;
            
            // 승객 완료 처리
            p.completed = true;
            completedCount++;
        }
        
        System.out.println(fuel);
    }
    
    // 가장 가까운 승객 찾기 - BFS 한 번으로 모든 승객까지의 거리 계산
    private static int[] findNearestPassenger() {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N+1][N+1];
        PriorityQueue<Point> candidates = new PriorityQueue<>();
        int[][] passengerMap = new int[N+1][N+1]; // 승객 인덱스 저장
        
        // 승객 위치 맵 생성
        for (int i = 1; i <= M; i++) {
            Passenger p = passengers[i];
            if (!p.completed) {
                passengerMap[p.startRow][p.startCol] = i;
            }
        }
        
        // 현재 택시 위치에 승객이 있는지 확인
        if (passengerMap[taxiRow][taxiCol] > 0 && !passengers[passengerMap[taxiRow][taxiCol]].completed) {
            taxiRow = passengers[passengerMap[taxiRow][taxiCol]].startRow;
            taxiCol = passengers[passengerMap[taxiRow][taxiCol]].startCol;
            return new int[]{passengerMap[taxiRow][taxiCol], 0};
        }
        
        queue.offer(new Point(taxiRow, taxiCol, 0));
        visited[taxiRow][taxiCol] = true;
        
        while (!queue.isEmpty()) {
            Point curr = queue.poll();
            
            // 현재 위치에 승객이 있는지 확인
            if (passengerMap[curr.row][curr.col] > 0 && !passengers[passengerMap[curr.row][curr.col]].completed) {
                candidates.offer(curr);
            }
            
            // 최단 거리의 승객을 모두 찾았다면 더 이상 탐색하지 않음
            if (!candidates.isEmpty() && candidates.peek().dist < curr.dist) {
                break;
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = curr.row + dr[i];
                int nc = curr.col + dc[i];
                
                if (nr < 1 || nr > N || nc < 1 || nc > N) continue;
                if (map[nr][nc] == 1 || visited[nr][nc]) continue;
                
                visited[nr][nc] = true;
                queue.offer(new Point(nr, nc, curr.dist + 1));
            }
        }
        
        if (candidates.isEmpty()) {
            return new int[]{-1, -1}; // 승객을 찾을 수 없음
        }
        
        Point nearest = candidates.poll();
        int nearestIndex = passengerMap[nearest.row][nearest.col];
        
        // 택시 위치 업데이트
        taxiRow = passengers[nearestIndex].startRow;
        taxiCol = passengers[nearestIndex].startCol;
        
        return new int[]{nearestIndex, nearest.dist};
    }
    
    // 목적지까지 거리 계산
    private static int getDistance(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == endRow && startCol == endCol) return 0;
        
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N+1][N+1];
        
        queue.offer(new Point(startRow, startCol, 0));
        visited[startRow][startCol] = true;
        
        while (!queue.isEmpty()) {
            Point curr = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                int nr = curr.row + dr[i];
                int nc = curr.col + dc[i];
                
                if (nr < 1 || nr > N || nc < 1 || nc > N) continue;
                if (map[nr][nc] == 1 || visited[nr][nc]) continue;
                
                if (nr == endRow && nc == endCol) {
                    return curr.dist + 1;
                }
                
                visited[nr][nc] = true;
                queue.offer(new Point(nr, nc, curr.dist + 1));
            }
        }
        
        return -1; // 도달할 수 없는 경우
    }
}