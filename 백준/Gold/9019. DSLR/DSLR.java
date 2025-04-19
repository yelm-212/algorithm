import java.io.*;
import java.util.*;

public class Main {

    static char[] command = {'D', 'S', 'L', 'R'};
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            visited = new boolean[10000];
            bw.write(bfs(start, target) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static String bfs(int start, int target) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(start, ""));
        visited[start] = true;

        while(!q.isEmpty()) {
            Node current = q.poll();
            if(current.register == target) return current.result;

            // 전체 가능한 명령어 셋을 수행하면서 큐에 넣는다.
            for(int i = 0; i < 4; i++) {
                int change = changeRegister(current.register, command[i]);
                if(!visited[change]) {
                    visited[change] = true;
                    q.offer(new Node(change, current.result + command[i]));
                }
            }
        }
        return "";
    }

    public static int changeRegister(int register, char command) {

        switch (command) {
            case 'D':
                register *= 2;
                if(register > 9999) register %= 10000;
                break;
            case 'S':
                register -= 1;
                if(register == -1) register = 9999;
                break;
            case 'L':
                int temp1 = (register % 1000) * 10;
                int temp2 = register / 1000;
                register = temp1 + temp2;
                break;
            case 'R':
                int tmp1 = register / 10;
                int tmp2 = (register % 10) * 1000;
                register = tmp1 + tmp2;
                break;
            default:
                break;
        }

        return register;
    }

    // 현재 값의 레지스터와 결과 명령어 배열 값을 저장한다.
    public static class Node {
        int register;
        String result;

        public Node(int register, String result) {
            this.register = register;
            this.result = result;
        }
    }
}
