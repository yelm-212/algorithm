class Solution {
    public String solution(String my_string, String overwrite_string, int s) {
        
        int tmp = s + overwrite_string.length();
        return my_string.substring(0, s)
            + overwrite_string 
            + my_string.substring(tmp, my_string.length());
    }
}