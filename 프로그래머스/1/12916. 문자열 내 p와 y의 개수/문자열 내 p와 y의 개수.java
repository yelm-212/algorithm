class Solution {
    boolean solution(String s) {
        int cnt1 = 0;
        int cnt2 = 0;
        for(int i = 0; i < s.length(); i++){
            if (s.charAt(i) == 'p' || s.charAt(i) == 'P') cnt1++;
            if (s.charAt(i) == 'Y' || s.charAt(i) == 'y') cnt2++;
        }

        return cnt1 == cnt2 ;
    }
}