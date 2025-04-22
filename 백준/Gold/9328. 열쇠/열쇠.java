import java.io.*;
import java.util.*;

public class Main {
    private static int h, w;
    private static char[][] map;
    private static boolean[][] visited;
    private static boolean[] keys;
    private static List<int[]>[] doors;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new char[h][w];
            visited = new boolean[h][w];
            keys = new boolean[26]; // a-z
            doors = new ArrayList[26]; // A-Z

            for (int i = 0; i < 26; i++) {
                doors[i] = new ArrayList<>();
            }

            // 지도 입력
            for (int i = 0; i < h; i++) {
                String line = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = line.charAt(j);
                }
            }

            // 초기 열쇠 설정
            String initialKeys = br.readLine();
            if (!initialKeys.equals("0")) {
                for (char key : initialKeys.toCharArray()) {
                    keys[key - 'a'] = true;
                }
            }

            bw.write(bfs() + "\n");
            bw.flush();

        }

        bw.close();

    }

    private static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        int documents = 0;

        // 가장자리 위치를 시작점으로 확인
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if ((i == 0 || i == h - 1 || j == 0 || j == w - 1) && map[i][j] != '*') {
                    if (map[i][j] >= 'A' && map[i][j] <= 'Z') {
                        // 문인 경우 열쇠가 있는지 확인
                        int door = map[i][j] - 'A';
                        if (keys[door]) {
                            q.offer(new int[]{i, j});
                            visited[i][j] = true;
                        } else {
                            doors[door].add(new int[]{i, j});
                        }
                    } else {
                        // 문이 아닌 경우 바로 시작점으로 추가
                        if (map[i][j] == '$') {
                            documents++;
                            map[i][j] = '.';
                        } else if (map[i][j] >= 'a' && map[i][j] <= 'z') {
                            int key = map[i][j] - 'a';
                            keys[key] = true;

                            // 해당 열쇠로 열 수 있는 문들 큐에 추가
                            for (int[] door : doors[key]) {
                                q.offer(door);
                                visited[door[0]][door[1]] = true;
                            }
                            doors[key].clear();
                        }

                        q.offer(new int[]{i, j});
                        visited[i][j] = true;
                    }
                }
            }
        }

        // BFS 탐색
        while (!q.isEmpty()) {
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 0 && nx < h && ny >= 0 && ny < w && !visited[nx][ny] && map[nx][ny] != '*') {
                    if (map[nx][ny] >= 'A' && map[nx][ny] <= 'Z') {
                        // 문인 경우
                        int door = map[nx][ny] - 'A';
                        if (keys[door]) {
                            visited[nx][ny] = true;
                            q.offer(new int[]{nx, ny});
                        } else {
                            doors[door].add(new int[]{nx, ny});
                        }
                    } else {
                        visited[nx][ny] = true;

                        if (map[nx][ny] == '$') {
                            // 문서 발견
                            documents++;
                            map[nx][ny] = '.';
                        } else if (map[nx][ny] >= 'a' && map[nx][ny] <= 'z') {
                            // 열쇠 발견
                            int key = map[nx][ny] - 'a';
                            if (!keys[key]) {
                                keys[key] = true;

                                // 해당 열쇠로 열 수 있는 문들 큐에 추가
                                for (int[] door : doors[key]) {
                                    q.offer(door);
                                    visited[door[0]][door[1]] = true;
                                }
                                doors[key].clear();
                            }
                        }

                        q.offer(new int[]{nx, ny});
                    }
                }
            }
        }

        return documents;
    }
}