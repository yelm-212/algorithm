import java.util.*;

class Solution {
    public long solution(int a, int b) {
        if (a == b) return a;
        
        long max = Math.max(a, b);
        long min = Math.min(a, b);
        
        long answer = (max + min) * (max - min + 1) / 2;
        
        return answer;
    }
}