class Solution {
    private static int[] board;
    private static int answer;
    public int solution(int n) {
        board = new int[n];
        answer = 0;
        DFS(0, n);
        return answer;
    }
    
    private void DFS(int depth, int n){
        // 재귀 종료 조건
        if (depth == n){
            answer++;
            return;
        }
        
        for(int i = 0 ; i < n ; i++){
            // 해당 깊이 i 열에 퀸 들어갈 수 있는지 확인
            board[depth] = i;
            if(valid(depth)){
                DFS(depth+1, n);
            }
        }  
    }
    
    private boolean valid(int i){
         for(int j = 0 ; j < i ; j++){
             if(board[i] == board[j]) 
                 return false;
             if(Math.abs(i - j) == Math.abs(board[i] - board[j])) // 같은 대각선상에 위치
                 return false;
         }
        return true;
    }
}