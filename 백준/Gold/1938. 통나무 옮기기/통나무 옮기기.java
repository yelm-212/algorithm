import java.io.*;
import java.util.*;

public class Main {
    private static int N, cnt;
    private static char[][] map;
    private static boolean[][][] visited;

    static class Tree {
        int[] a;
        int[] b;
        int[] c;
        int count;
        boolean isVertical;

        public Tree(int[] a, int[] b, int[] c) {
            this.a = a;
            this.b = b;
            this.c = c;
            isVertical = isVertical();
            this.count = 0;
        }

        public Tree(int[] a, int[] b, int[] c, int count) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.count = count;
            this.isVertical = isVertical();
        }

        // 세로 방향 판정
        private boolean isVertical() {
            return (this.a[1] == this.b[1]) && (this.b[1] == this.c[1]) && (this.a[1] == this.c[1]);
        }

        @Override
        public boolean equals(Object obj) {
            return Arrays.equals(this.a, ((Tree) obj).a)
                    && Arrays.equals(this.b, ((Tree) obj).b)
                    && Arrays.equals(this.c, ((Tree) obj).c);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[2][N][N];

        ArrayList<int[]> start = new ArrayList<>();
        ArrayList<int[]> end = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'B') {
                    start.add(new int[]{i, j});
                }
                if (map[i][j] == 'E') {
                    end.add(new int[]{i, j});
                }
            }
        }

        Tree starttree = new Tree(start.get(0), start.get(1), start.get(2));
        Tree endtree = new Tree(end.get(0), end.get(1), end.get(2));

        int res = BFS(starttree, endtree);

        System.out.println(res == -1 ? 0 : res);
    }

    private static int BFS(Tree starttree, Tree endtree) {
        if (starttree.equals(endtree)) {
            return 0;
        }
        Queue<Tree> queue = new LinkedList<>();
        queue.add(starttree);
        // 중앙 지점 기준 방문 여부 표시
        visited[starttree.isVertical ? 1 : 0][starttree.b[1]][starttree.b[0]] = true;
        while (!queue.isEmpty()) {
            Tree cur = queue.poll();

            // 목표 도달 확인
            if (isGoal(cur, endtree)) {
                return cur.count;
            }

            char[] commands = {'U', 'D', 'L', 'R', 'T'};
            for (char command : commands) {
                // 다음 상태 계산
                Tree nextState = executeMove(cur, command);

                if (nextState != null) {
                    // 방문 여부 확인 (visited 배열 사용)
                    int direction = nextState.isVertical ? 1 : 0;
                    if (!visited[direction][nextState.b[1]][nextState.b[0]]) {
                        // 방문 처리 및 큐에 추가
                        visited[direction][nextState.b[1]][nextState.b[0]] = true;
                        queue.add(nextState);
                    }
                }
            }
        }

        return -1;
    }

    private static boolean isGoal(Tree nextState, Tree endtree) {
        return nextState.equals(endtree);
    }

    private static boolean checkMap(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N && map[y][x] != '1';
    }

    private static boolean checkPoint(int[] p){
        return checkMap(p[1], p[0]);
    }

    private static boolean checkPoints(int[] a, int[] b, int[] c){
        return checkPoint(a) && checkPoint(b) && checkPoint(c);
    }

    private static boolean checkRotate(int[] center) {
        int cx = center[1], cy = center[0];
        for (int i = cx - 1; i <= cx + 1; i++) {
            for (int j = cy - 1; j <= cy + 1; j++) {
                if (!checkMap(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static Tree executeMove(Tree currentTree, char command) {
        // 이동/회전 후의 좌표 계산
        int[] nextA = Arrays.copyOf(currentTree.a, currentTree.a.length);
        int[] nextB = Arrays.copyOf(currentTree.b, currentTree.b.length);
        int[] nextC = Arrays.copyOf(currentTree.c, currentTree.c.length);

        switch (command) {
            case 'U': // 행 감소
                nextA[1]--; nextB[1]--; nextC[1]--;
                break;
            case 'D': // 행 증가
                nextA[1]++; nextB[1]++; nextC[1]++;
                break;
            case 'L': // 열 감소
                nextA[0]--; nextB[0]--; nextC[0]--;
                break;
            case 'R': // 열 증가
                nextA[0]++; nextB[0]++; nextC[0]++;
                break;
            case 'T': // 회전 로직 (기존 로직 유지)
                // 회전 가능 여부 먼저 확인 (현재 중심점 기준)
                if (!checkRotate(currentTree.b)) return null;

                if (currentTree.isVertical) { // 세로 -> 가로
                    nextA = new int[] { currentTree.b[0], currentTree.b[1] - 1 };
                    nextC = new int[] { currentTree.b[0], currentTree.b[1] + 1 };
                } else { // 가로 -> 세로
                    nextA = new int[] { currentTree.b[0] - 1, currentTree.b[1] };
                    nextC = new int[] { currentTree.b[0] + 1, currentTree.b[1] };
                }
                break;
        }

        // 최종 이동/회전 후 위치 유효성 검사 (경계 & 장애물)
        if (!checkPoints(nextA, nextB, nextC)) {
            return null;
        }

        // 새로운 Tree 객체 생성 및 반환
        return new Tree(nextA, nextB, nextC, currentTree.count + 1);
    }
}