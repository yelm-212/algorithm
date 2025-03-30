import java.util.*;

class Solution {
    public int solution(int a, int b) {
        String tmp1 = Integer.toString(a);
        String tmp2 = Integer.toString(b);
        
        return Math.max(Integer.parseInt(tmp1 + tmp2), Integer.parseInt(tmp2 + tmp1));
    }
}