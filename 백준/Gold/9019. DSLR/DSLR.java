import java.util.*;

public class Main {

    static char[] command = {'D', 'S', 'L', 'R'};
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for(int i = 0; i < t; i++) {
            int start = scan.nextInt();
            int target = scan.nextInt();
            visited = new boolean[10000];
            System.out.println(bfs(start, target));
        }
    }

    public static String bfs(int start, int target) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(start, ""));
        visited[start] = true;

        while(!q.isEmpty()) {
            Node current = q.poll();
            if(current.register == target) return current.result;

            for(int i = 0; i < 4; i++) {
                int change = changeRegister(current.register, command[i]);
                if(visited[change] == false) {
                    visited[change] = true;
                    q.offer(new Node(change, current.result + command[i]));
                }
            }
        }
        return "";
    }

    public static int changeRegister(int register, char command) {
        if(command == 'D') {
            register *= 2;
            if(register > 9999) register %= 10000;
        } else if(command == 'S') {
            register -= 1;
            if(register == -1) register = 9999;
        } else if(command == 'L') {
            int temp1 = (register % 1000) * 10;
            int temp2 = register / 1000;
            register = temp1 + temp2;
        } else {
            int temp1 = register / 10;
            int temp2 = (register % 10) * 1000;
            register = temp1 + temp2;
        }
        return register;
    }

    public static class Node {
        int register;
        String result;

        public Node(int register, String result) {
            this.register = register;
            this.result = result;
        }
    }
}
