class Solution {
    public int[] solution(int n, int m) {
        int[] answer = new int[2];
        
        answer[0] = gcd(n, m);
        answer[1] = n * m / answer[0];
        return answer;
    }
    
    private int gcd(int n, int m){
        
        // 유클리드 호제법
        while(m != 0){
            int tmp = m;
            m = n % m;
            n = tmp;
        }
        return n;
    }
    
}