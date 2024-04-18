import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static Node[] nodes;
    static boolean[] visited;

    public static class Node{
        int index; // Node의 index 값
        boolean isCycle;
        int dist; // cycle과의 거리
        List<Node> nextNodes = new ArrayList<>(); // 인접한 노드

        public Node(int index){
            this.index = index;
        }
    }
    public static void main(String[] args) throws IOException {
        // DFS 두번 쓰기
        // 사이클이라고 무조건 union & find 아님
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        nodes = new Node[n+1];
        visited = new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i); // 그래프 초기화
        }

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 양방향 그래프
            nodes[a].nextNodes.add(nodes[b]);
            nodes[b].nextNodes.add(nodes[a]);
        }

        // 사이클 찾기
        for(int i=1;i<n+1;i++){
            visited[i] = true;
            // 사이클 찾으면 끝냄
            if(findCycle(nodes[i],nodes[i],1)) break;
            visited[i] = false;
        }

        // 사이클 거리 계산
        for(int i = 1;i <= n;i++){
            // 싸이클 -> 거리 = 0
            if(nodes[i].isCycle) nodes[i].dist = 0;
            else{
                // 싸이클 아님 -> DFS로 거리 계산하기
                visited = new boolean[n+1];
                visited[i] = true;
                setDist(nodes[i],nodes[i],0);
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for(int i = 1;i <= n;i++){
            sb.append(nodes[i].dist).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // DFS로 사이클 여부 판단
    public static boolean findCycle(Node node, Node startNode, int depth){

        for (Node nextNode : node.nextNodes) {
            if(depth > 2 && nextNode == startNode) {
                // 시작 노드와 도착 노드가 같고
                // 탐색 깊이 2 이상이면 (동일 노드 왕복 제외)
                node.isCycle = true; // 현재 노드가 사이클을 이룸
                return true;
            }

            if(!visited[nextNode.index]) {
                visited[nextNode.index] = true;
                if (findCycle(nextNode, startNode, depth + 1)) {
                    node.isCycle = true; //싸이클에 포함 true
                    return true;
                }
                visited[nextNode.index] = false; // 방문 해제
            }
        }
        return false; // 싸이클을 찾지 못하면 false 반환
    }

    // 거리 계산 DFS
    public static void setDist(Node node, Node startNode, int depth){
        if(node.isCycle) { // 싸이클 만나면 종료
            startNode.dist = depth; // DFS 깊이로 싸이클과의 거리 판단
            return;
        }

        // 사이클인 노드 만날 때까지 DFS 탐색
        for( Node nextNode : node.nextNodes ) {
            if(!visited[nextNode.index]){
                visited[nextNode.index] = true; // 역행 방지
                setDist(nextNode,startNode,depth+1); // DFS 탐색
            }
        }
    }

}
