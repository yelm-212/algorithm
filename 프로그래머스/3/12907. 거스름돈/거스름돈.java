class Solution {
    public int solution(int n, int[] money) {
        int size = money.length;
        long [] DP = new long[n+1];
        for(int i = 0; i < size; i++){
            // money 배열 돌면서 확인
            int cur = money[i];
            
            if(cur > n) continue;
            DP[cur]++;
            for(int j = 1; j <= n; j++){
                if(j - cur >= 0){
                    DP[j] += DP[j-cur];
                    DP[j] %=  1000000007;
                }
            }
        }
        return (int) DP[n];
    }
}